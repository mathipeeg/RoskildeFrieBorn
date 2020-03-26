import Members.Child;
import Members.Parent;
import Tools.HelpingMethods;
import StaffMembers.Staff;

import java.text.ParseException;
import java.util.Scanner;

public class Login {


    HelpingMethods get = new HelpingMethods();
    Scanner scanner = new Scanner(System.in);
    Scanner s = new Scanner(System.in);

    public Parent parentLogin() {
        System.out.println("Venligst indtast dit barns ID.");
        int id = scanner.nextInt();
        int checked = checkIdChild(id);
        if (checked == 1) {
            Parent parent = get.getParent(get.getChild(id).getParentId());
            return parent;
        } else{
            System.out.println("Beklager, dit ID eksisterer ikke.");
            return null;
        }
    }

    public int logIn() {
        System.out.println("Venligst indtast dit ID.");
        int id = scanner.nextInt();
        int checkId = checkIdStaff(id);
        if (checkId == 1){
            System.out.println("Indtast password.");
            String pass = s.nextLine();
            Staff staff = get.getStaff(id);
            if (staff.getPassword().equals(pass)) {
                return id;
            }
        }
        System.out.println("Username or pass was wrong, sorry, man.");
        return -1;
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

    public int checkIdStaff(int id) {
        for (Staff staff : Staff.staffArray) {
            //Hvis id'et passer
            if (staff.getId() == id){
                return 1;
            }
        }
        return 0;
    }

    public int checkIdChild(int id){
        for (Child child : Child.childArray) {
            if (child.getId() == id) {
                return 1;
            }
        }
        return 0;
    }
}
