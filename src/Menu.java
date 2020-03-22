import Members.Child;
import Members.Parent;
import Members.ParentOptions;
import Organising.GetMethods;
import StaffMembers.Options;
import StaffMembers.Staff;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Menu
{
    // TODO: 22-03-2020 I hver abort metode, så erstat id med getIndex
    public static int id = -1;
    Scanner scanner = new Scanner(System.in);
    Scanner s = new Scanner(System.in);
    ParentOptions parentOptions = new ParentOptions();
    Options options = new Options();
    GetMethods get = new GetMethods();

    public void menu() {
        while (true) {
            System.out.println("Velkommen til Roskilde Frie Boernehave! Er du 1) medarbejder eller 2) Forældre? \n3) Glemt ID?");
            int choice = scanner.nextInt();
            if (choice == 1){
                boolean loggedIn = logIn();
                if (loggedIn){
                    Staff staff = get.getStaff(id);
                    if (staff.getRole().equalsIgnoreCase("admin")) {adminOptions();}
                    else{staffOptions(staff);}
                }
                break;
            } else if (choice == 2){
                System.out.println("Venligst indtast dit barns ID.");
                id = scanner.nextInt();
                int checked = checkIdChild(id);
                if (checked == 1){
                    Parent parent = get.getParent(get.getChild(id).getParentId());
                    parentOptions.options(parent);
                } else{
                    System.out.println("Beklager, dit ID eksisterer ikke.");
                }
                break;
            } else if (choice == 3) {
                int id = forgottenIDStaff();
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

    private boolean logIn() {
        System.out.println("Venligst indtast dit ID.");
        id = scanner.nextInt();
        int checkId = checkIdStaff(id);
        if (checkId == 1){
            System.out.println("Indtast password.");
            String pass = s.nextLine();
            Staff staff = get.getStaff(id);
            if (staff.getPassword().equals(pass)) {
                return true;
            }
        }
        System.out.println("Username or pass was wrong, sorry, man.");
        return false;
    }

    public int forgottenIDStaff()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Indtast dit fornavn");
        String firstname = s.nextLine();
        System.out.println("Indtast dit efternavn");
        String lastname = s.nextLine();
        //Medlem indtaster deres navn, og vi tjekker databasen igennem for navnet
        //Hvis det ikke findes, sættes returneres -1
        for (Staff staff : Staff.staffArray) {
            if (staff.getFirstname().equalsIgnoreCase(firstname) && staff.getLastname().equalsIgnoreCase(lastname)){
                return staff.getId();
            }
        }
        return -1;
    }

    private int checkIdStaff(int id) {
        for (Staff staff : Staff.staffArray) {
            //Hvis id'et passer
            if (staff.getId() == id){
                return 1;
            }
        }
        return 0;
    }

    private int checkIdChild(int id){
        for (Child child : Child.childArray) {
            if (child.getId() == id) {
                return 1;
            }
        }
        return 0;
    }

    public void adminOptions() {
        while (true) {
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

    public void staffOptions(Staff currentStaff)
    {
        System.out.println("VELKOMMEN " + currentStaff.getFirstname() + " " + currentStaff.getLastname());
        System.out.println("Vil du \n1) Opret/se nyheder \n2) Se timeplan \n3) Ændr dine informationer " +
                "\n4) Se barns info \n5) Indskriv barn \n6) Afslut");
        int choice = scanner.nextInt();
        switch (choice)
        {
            case 1:
                System.out.println("nyheder");
                break;
            case 2:
                System.out.println("Timeplan");
                timetableOptions();
                break;
            case 3:
                System.out.println("Ændr info");
                adminStaffOptions();
                break;
            case 4:
                System.out.println("Se barns info");
                childOptions();
                break;
            case 5:
                System.out.println("indskriv barn");
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
                    options.createParent();
                    break;
                case 2:
                    options.editParent(-1);
                    break;
                case 3:
                    options.abortParent();
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
                options.viewTimetable(currentMonthString);
            } else if (choice == 2) {
                String emptyMonth = options.getEmptyTimetable(currentMonthString);
                options.createShift(emptyMonth);
                options.viewTimetable(emptyMonth);
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
                    options.createChild();
                    break;
                case 2:
                    options.editChild();
                    break;
                case 3:
                    options.abortChild();
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
                    options.createStaff();
                    break;
                case 2:
                    System.out.println("Aendre medarbejderinfo");
                    options.editStaff();
                    break;
                case 3:
                    System.out.println("Slet meadarbejder");
                    options.deleteStaff();
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
                options.getWaitlist();
                break;
            case 2:
                System.out.println("Opret barn");
                options.createChildWaitlist();
                break;
            case 3:
                System.out.println("Slet barn");
                options.abortChildFromWaitlist();
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
                options.checkChild();
                break;
            case 2:
                System.out.println("Udskriv barn");
                // TODO: 22-03-2020 Lav metode, der wiper filen når dagen er omme evt brug Date?
                options.checkOutChild();
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