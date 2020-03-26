import Members.ChildOptions;
import Members.Parent;
import Members.ParentOptions;
import Updates.UpdatesOptions;
import Tools.HelpingMethods;
import Tools.ScheduleOptions;
import Tools.WaitlistOptions;
import StaffMembers.StaffOptions;
import StaffMembers.Staff;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Menu
{
    // TODO: 22-03-2020 I hver abort metode, så erstat id med getIndex
    ParentOptions parentOptions = new ParentOptions();
    ChildOptions childOptions = new ChildOptions();
    ScheduleOptions scheduleOptions = new ScheduleOptions();
    WaitlistOptions waitlistOptions = new WaitlistOptions();
    UpdatesOptions updatesOptions = new UpdatesOptions();
    HelpingMethods help = new HelpingMethods();

    Scanner scanner = new Scanner(System.in);
    StaffOptions staffOptions = new StaffOptions();
    Login login = new Login();


    public void menu() throws ParseException {
        while (true) {
            System.out.println("Velkommen til Roskilde Frie Boernehave! Er du 1) medarbejder eller 2) Forældre? \n3) Glemt ID?");
            int choice = scanner.nextInt();
            if (choice == 1){
                    int id = login.logIn();
                    if (id != -1){
                        Staff staff = help.getStaff(id);
                        if (staff.getRole().equalsIgnoreCase("admin")) {
                            adminOptions();
                        } else {
                            staffOptions(staff);
                        }
                    }
                break;
            } else if (choice == 2){
                if (login.parentLogin() != null){
                    options(login.parentLogin());
                }
                break;
            } else if (choice == 3) {
                int id = login.forgottenIDStaff();
                if (id != -1) {
                    System.out.println("Dit ID er: " + id);
                } else {
                    System.out.println("Kunne ikke finde dit navn i systemet, prøv igen.");
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
                    newsOptions();
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

    public void options(Parent parent)
    {
        System.out.println("Velkommen " + parent.getFirstname() + "!");
        System.out.println("Vil du \n1) Se opdateringer \n2)Opret opdateringer \n3)Ændr opdateringer \n4) Ændr din information \n5) Afslut");
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
                parentOptions.editParent(parent.getId());
                break;
            case 5:
                System.out.println("---------------------");
                break;
            default:
                break;
        }
    }

    public void newsOptions() {
        System.out.println("Vil du \n1) Se opdateringer \n2) Opret opdateringer \n3) Ændre opdateringer \n4) " +
                "Slet opdateringer \n5) Afslut");
        int choice = scanner.nextInt();
        switch (choice) {
            // TODO: Husk ID godkender, parents skal kun kunne se se nyheder
            // TODO: Dato, publisher
            // TODO: stilling check
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
                newsOptions();
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
                System.out.println("Exit");
                break;
            default:
                System.out.println("lmao");
                break;
        }
    }

    public void parentOptions() {

        while (true){
            System.out.println("1) Ny forældre \n2) Aendre forældre \n3) Slet forældre");
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
                default:
                    System.out.println("Mærkeligt input alligevel lmao");
                    break;
            }
        }
    }

    public void timetableOptions() {
        Date date = new Date();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        String currentMonthString = monthFormat.format(date);

        while (true) {
            System.out.println("\nVil du \n1) Se timeplan for denne måned \n2) Opret vagter for næste tomme måned \n3) Afslut");
            int choice = scanner.nextInt();
            if (choice == 1) {
                scheduleOptions.viewTimetable(currentMonthString);
            } else if (choice == 2) {
                String emptyMonth = scheduleOptions.getEmptyTimetable(currentMonthString);
                scheduleOptions.createShift(emptyMonth);
                scheduleOptions.viewTimetable(emptyMonth);
            } else if (choice == 3) {
                break;
            } else{
                System.out.println("Forstod ikke lige dit input...");
            }
        }
    }

    public void childOptions()
    {
        while (true)
        {
            System.out.println("1) Nyt barn \n2) Aendre barn \n3) Slet barn");
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
                default:
                    System.out.println("Mærkeligt input alligevel lmao");
                    break;
            }
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
                System.out.println("----------------------");
                break;
            default:
                System.out.println("Not gonna happen");
                break;
        }

    }
    public void waitlistOptions() {
        while (true) {
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
                    System.out.println("--------------------");
                    break;
                default:
                    System.out.println("Not gonna happen");
                    break;
            }
        }
    }

    public void checkedInOut() throws ParseException {
        System.out.println("1)Indskriv barn \n2)Udtjek barn \n3)Afslut");

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
                System.out.println("---------------------");
                break;
            default:
                System.out.println("Underligt input...");
                break;
        }
    }
}