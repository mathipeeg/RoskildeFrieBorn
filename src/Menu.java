import Members.Child;
import Members.Parent;
import Members.ParentOptions;
import StaffMembers.AdminOptions;
import StaffMembers.Staff;
//import StaffMembers.StaffOptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Menu
{
    // TODO: 22-03-2020 I hver abort metode, så erstat id med getIndex
    public static int id = -1;
    Scanner scanner = new Scanner(System.in);
    Scanner s = new Scanner(System.in);
//    StaffOptions staffOptions = new StaffOptions();
    ParentOptions parentOptions = new ParentOptions();
    AdminOptions adminOptions = new AdminOptions();

    public void menu() {
        while (true) {
            System.out.println("Velkommen til Roskilde Frie Boernehave! Er du 1) medarbejder eller 2) Forældre?");
            int role = scanner.nextInt();
            if (role == 1){
                System.out.println("Venligst indtast dit ID.");
                id = scanner.nextInt();
                System.out.println("Indtast password.");
                String pass = s.nextLine();
                // TODO: Tjek om password og ID eksisterer
                Staff currentStaff = adminOptions.getStaff(id);
                options(currentStaff.getId());
                break;
            } else if (role == 2){
                System.out.println("Venligst indtast dit barns ID.");
                id = scanner.nextInt();
                // TODO: Tjek om id eksisterer
                Parent parent = adminOptions.getParent(adminOptions.getChild(id).getParentId());
                parentOptions.options(parent);
                break;
            } else{
                System.out.println("Mærkeligt input alligevel...");
            }
        }
    }

    public void options(int id) {
        while (true) {
            // TODO: 22-03-2020 Få StaffOptions.java til at virke
            System.out.println("Dine valgmuligheder: \n1) Nyheder \n2) Opret/Aendre boern " +
                    "\n3) Aendre foraeldreinfo \n4) Opret/Aendre medarbejdere \n5) Timeplan " +
                    "\n6) Venteliste \n7) Indskriv boern \n8) Afslut");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("News");
                    // TODO: 22-03-2020 Eventuelle news
                    //Malene og Mads
                    break;
                case 2:
                    childOptions();
                    break;
                case 3:
                    System.out.println("Parent options");
                    parentOptions();
                    //Malene
                    break;
                case 4:
                    adminStaffOptions();
                    //Mads
                    break;
                case 5:
                    System.out.println("Timeplan");
                    timetableOptions();
                    //Mathilde
                    break;
                case 6:
                    System.out.println("Venteliste");
                    waitlistOptions();
                    //Casper
                    break;
                case 7:
                    System.out.println("Indtjek/Udtjek barn");
                    checkedInOut();
                    //Casper
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

    public void parentOptions() {

        while (true){
            System.out.println("1) Ny forældre \n2) Aendre forældre \n3) Slet forældre");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    adminOptions.createParent();
                    break;
                case 2:
                    adminOptions.editParent(-1);
                    break;
                case 3:
                    adminOptions.abortParent();
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
                adminOptions.viewTimetable(currentMonthString);
            } else if (choice == 2) {
                String emptyMonth = adminOptions.getEmptyTimetable(currentMonthString);
                adminOptions.createShift(emptyMonth);
                adminOptions.viewTimetable(emptyMonth);
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
                    adminOptions.createChild();
                    break;
                case 2:
                    adminOptions.editChild();
                    break;
                case 3:
                    adminOptions.abortChild();
                    break;
                default:
                    System.out.println("Mærkeligt input alligevel lmao");
                    break;
            }
        }
    }

    public void adminStaffOptions() {
        while (true) {
            System.out.println("1) Opret medarbejder \n2) Aendre medarbejder info \n3) Slet medarbejder \n4) Afslut");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Opret medarbejder");
                    adminOptions.createStaff();
                    break;
                case 2:
                    System.out.println("Aendre medarbejderinfo");
                    adminOptions.editStaff();
                    break;
                case 3:
                    System.out.println("Slet meadarbejder");
                    adminOptions.deleteStaff();
                    break;
                case 4:
                    System.out.println("Afslut");
                    break;
                default:
                    System.out.println("Not gonna happen");
                    break;
            }
        }

    }
    public void waitlistOptions() {
        System.out.println("1) Se venteliste \n2) Opret barn på venteliste \n3) Slet barn fra venteliste \n4) Afslut");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Venteliste");
                adminOptions.getWaitlist();
                break;
            case 2:
                System.out.println("Opret barn");
                adminOptions.createChildWaitlist();
                break;
            case 3:
                System.out.println("Slet barn");
                adminOptions.abortChildFromWaitlist();
                break;
            case 4:
                System.out.println("Afslut");
                break;
            default:
                System.out.println("Not gonna happen");
                break;
        }
    }

    public void checkedInOut()
    {
        System.out.println("1)Indskriv barn \n2)Udtjek barn \n3)Afslut");

        int choice = scanner.nextInt();
        switch (choice)
        {
            case 1:
                System.out.println("Indskriv barn");
                adminOptions.checkChild();
                break;
            case 2:
                System.out.println("Udskriv barn");
                // TODO: 22-03-2020 Lav metode, der wiper filen når dagen er omme evt brug Date?
                adminOptions.checkOutChild();
                break;
            case 3:
                System.out.println("Afslut");
                break;
            default:
                System.out.println("Underligt input...");
                break;
        }
    }
}