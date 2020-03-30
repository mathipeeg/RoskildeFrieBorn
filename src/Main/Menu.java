package Main;

import Main.Members.*;
import Main.Models.*;
import Main.Tools.*;
import Main.Updates.UpdatesOptions;
import Main.StaffMembers.*;

import java.text.*;
import java.util.*;

public class Menu
{
    ParentOptions parentOptions = new ParentOptions();
    ChildOptions childOptions = new ChildOptions();
    ScheduleOptions scheduleOptions = new ScheduleOptions();
    WaitlistOptions waitlistOptions = new WaitlistOptions();
    UpdatesOptions updatesOptions = new UpdatesOptions();
    HelpingMethods help = new HelpingMethods();

    Scanner scanner = new Scanner(System.in);
    StaffOptions staffOptions = new StaffOptions();
    Login login = new Login();
    public static CurrentUser CURRENTUSER = null;

    public void menu() throws ParseException {
        System.out.println("Velkommen til Roskilde Frie Boernehave! Er du 1) medarbejder eller 2) Forældre?");
        int choice = scanner.nextInt();
        while (CURRENTUSER == null) {
            if (choice == 1){
                CURRENTUSER = login.logIn();
                if (CURRENTUSER != null){
                        Staff staff = help.getStaff(CURRENTUSER.id);
                        if (staff.getRole().equalsIgnoreCase("admin")) {
                            adminOptions();
                        } else {
                            staffOptions(staff);
                        }
                    }
            } else if (choice == 2){
                CURRENTUSER = login.parentLogin();
                if (CURRENTUSER != null){
                    options();
                }
            }else {
                System.out.println("Mærkeligt input alligevel...");
            }
        }
    }


    public void adminOptions() throws ParseException {
        while (true) {
            System.out.println("Dine valgmuligheder: \n1) Nyheder \n2) Opret/Aendre boern " +
                    "\n3) Aendre foraeldreinfo \n4) Opret/Aendre medarbejdere \n5) Timeplan " +
                    "\n6) Venteliste \n7) Indskriv boern \n8) Afslut");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    updatesOptions();
                    //Malene og Mads
                    break;
                case 2:
                    childOptions();
                    break;
                case 3:
                    parentOptions();
                    //Malene
                    break;
                case 4:
                    adminStaffOptions();
                    //Mads
                    break;
                case 5:
                    timetableOptions();
                    //Mathilde
                    break;
                case 6:
                    waitlistOptions();
                    break;
                case 7:
                    checkedInOut();
                    break;
                case 8:
                    System.out.println("Logger ud...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Mærkeligt input alligevel...");
                    break;
            }
        }
    }

    public void options()
    {
        System.out.println("Velkommen " + CURRENTUSER.firstname + "!");
        System.out.println("Vil du \n1) Se opdateringer \n2) Opret opdateringer \n3) Ændr opdateringer \n4) Ændr din information \n5) Afslut");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                updatesOptions.seeUpdates();;
                break;
            case 2:
                updatesOptions.createUpdates();
                break;
            case 3:
                updatesOptions.editUpdate();
                break;
            case 4:
                parentOptions.editParent(CURRENTUSER.id);
                break;
            case 5:
                System.out.println("----------------------");
                break;
            default:
                break;
        }
    }

    public void updatesOptions() {
        System.out.println("Vil du \n1) Se opdateringer \n2) Opret opdateringer \n3) Ændre opdateringer \n4) " +
                "Slet opdateringer \n5) Afslut");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                updatesOptions.seeUpdates();
                break;
            case 2:
                updatesOptions.createUpdates();
                break;
            case 3:
                updatesOptions.editUpdate();
                break;
            case 4:
                updatesOptions.deleteUpdate();
                break;
            case 5:
                System.out.println("----------------------");
                break;
            default:
                System.out.println("Not gonna happen");
                break;
        }

    }

    public void staffOptions(Staff currentStaff) throws ParseException {
        System.out.println("VELKOMMEN " + currentStaff.getFirstname() + " " + currentStaff.getLastname());
        System.out.println("Vil du \n1) Opret/se nyheder \n2) Se timeplan \n3) Ændr dine informationer " +
                "\n4) Se barns info \n5) Indskriv barn \n6) Afslut");
        int choice = scanner.nextInt();
        switch (choice)
        {
            case 1:
                updatesOptions();
                break;
            case 2:
                timetableOptions();
                break;
            case 3:
                adminStaffOptions();
                break;
            case 4:
                childOptions();
                break;
            case 5:
                checkedInOut();
                break;
            case 6:
                System.out.println("----------------------");
                break;
            default:
                System.out.println("lmao");
                break;
        }
    }

    public void parentOptions() {

        System.out.println("1) Ny forældre \n2) Aendre forældre \n3) Slet forældre \n4) Afslut");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                parentOptions.createParent();
                break;
            case 2:
                parentOptions.editParent(-1);
                break;
            case 3:
                parentOptions.abortParent();
                break;
            case 4:
                System.out.println("----------------------");
                break;
            default:
                System.out.println("Mærkeligt input alligevel lmao");
                break;
        }
    }

    public void timetableOptions() {
        Date date = new Date();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        String currentMonthString = monthFormat.format(date);

        while (true) {
            System.out.println("Vil du \n1) Se timeplan for denne måned \n2) Opret vagter for næste tomme måned \n3) Afslut");
            int choice = scanner.nextInt();
            if (choice == 1) {
                scheduleOptions.viewTimetable(currentMonthString);
            } else if (choice == 2) {
                String emptyMonth = scheduleOptions.getEmptyTimetable(currentMonthString);
                scheduleOptions.createShift(emptyMonth);
                scheduleOptions.viewTimetable(emptyMonth);
            } else if (choice == 3) {
                System.out.println("----------------------");
                break;
            } else{
                System.out.println("Forstod ikke lige dit input...");
            }
        }
    }

    public void childOptions()
    {
        System.out.println("1) Nyt barn \n2) Aendre barn \n3) Slet barn \n4) Afslut");
        int choice = scanner.nextInt();
        switch (choice)
        {
            case 1:
                childOptions.createChild();
                break;
            case 2:
                childOptions.editChild();
                break;
            case 3:
                childOptions.abortChild();
                break;
            case 4:
                System.out.println("----------------------");
                break;
            default:
                System.out.println("Mærkeligt input alligevel lmao");
                break;
        }
    }

    public void adminStaffOptions() {

        System.out.println("1) Opret medarbejder \n2) Aendre medarbejder info \n3) Slet medarbejder \n4) Afslut");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                staffOptions.createStaff();
                break;
            case 2:
                staffOptions.editStaff();
                break;
            case 3:
                staffOptions.deleteStaff();
                break;
            case 4:
                System.out.println("-----------------------");
                break;
            default:
                System.out.println("Not gonna happen");
                break;
        }

    }
    public void waitlistOptions() {
        System.out.println("1) Se venteliste \n2) Opret barn på venteliste \n3) Slet barn fra venteliste \n4) Afslut");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                waitlistOptions.getWaitlist();
                break;
            case 2:
                waitlistOptions.createChildWaitlist();
                break;
            case 3:
                waitlistOptions.abortChildFromWaitlist();
                break;
            case 4:
                System.out.println("---------------------");
                break;
            default:
                System.out.println("Not gonna happen");
                break;
        }
    }

    public void checkedInOut() throws ParseException {

        for (Checked checked : Checked.checkedKidsArray) {
            if (checked.getCheckOut().equalsIgnoreCase("0")) {
                Child child = help.getChild(checked.getChildId());
                System.out.println(child.getFirstname() + " " + child.getLastname() + " ✓");
            }
        }
        System.out.println("\n1) Indskriv barn \n2) Udtjek barn \n3) Afslut");

        int choice = scanner.nextInt();
        switch (choice)
        {
            case 1:
                staffOptions.checkChild();
                break;
            case 2:
                staffOptions.checkOutChild();
                break;
            case 3:
                System.out.println("----------------------");
                break;
            default:
                System.out.println("Underligt input...");
                break;
        }
    }
}