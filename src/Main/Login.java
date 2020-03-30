package Main;

import Main.Models.Child;
import Main.Models.CurrentUser;
import Main.Models.Parent;
import Main.Tools.HelpingMethods;
import Main.Models.Staff;

import java.util.Scanner;

public class Login {


    HelpingMethods help = new HelpingMethods();
    Scanner intScan = new Scanner(System.in);
    Scanner stringScan = new Scanner(System.in);

    public CurrentUser parentLogin() {
        System.out.println("Venligst indtast dit barns ID. Indtast -1 for glemt ID.");
        int id = intScan.nextInt();
        if (id == -1){
            if (forgottenIDParents() == -1){
                System.out.println("Navn eksisterer ikke i systemet.");
            }
        } else {
            CurrentUser currentUser = getUserParent(id);
            if (currentUser != null) {
                return currentUser;
            }
            System.out.println("Beklager, dit ID eksisterer ikke.");
            return currentUser;
        }
        return null;
    }

    public CurrentUser logIn() {
        int id;
        System.out.println("Venligst indtast dit ID. Indtast -1 for glemt ID.");
        id = intScan.nextInt();
        if (id == -1){
            if (forgottenIDStaff() == -1){
                System.out.println("Navn eksisterer ikke i systemet.");
            }
        } else {
            CurrentUser currentUser = getUserStaff(id);
            if (currentUser != null) {
                System.out.println("Indtast password.");
                String pass = stringScan.nextLine();
                Staff staff = help.getStaff(id);
                if (staff.getPassword().equals(pass)) {
                    return currentUser;
                }
            }
            System.out.println("Username or pass was wrong, sorry, man.");
            return null;
        }
        return null;
    }

    public int forgottenIDStaff()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Indtast dit fulde navn");
        String name = s.nextLine();
        //Medlem indtaster deres navn, og vi tjekker databasen igennem for navnet
        //Hvis det ikke findes, sættes returneres -1
        for (Staff staff : Staff.staffArray) {
            if (name.equalsIgnoreCase(staff.getFirstname() + " " + staff.getLastname())){
                System.out.println("Dit ID er " + staff.getId());
                return staff.getId();
            }
        }
        return -1;
    }

    public int forgottenIDParents()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Indtast dit barns fulde navn");
        String name = s.nextLine();
        for (Child child : Child.childArray) {
            if (name.equalsIgnoreCase(child.getFirstname() + " " + child.getLastname())){
                System.out.println("Dit ID er " + child.getId());
                return child.getId();
            }
        }
        return -1;
    }

    public CurrentUser getUserStaff(int id) {
        CurrentUser currentUser = null;
        for (Staff staff : Staff.staffArray) {
            //Hvis id'et passer
            if (staff.getId() == id){
                currentUser = new CurrentUser(staff.getId(), staff.getFirstname(), staff.getLastname(), staff.getRole());
                return currentUser;
            }
        }
        return currentUser;
    }

    public CurrentUser getUserParent(int id){
        CurrentUser currentUser = null;
        for (Child child : Child.childArray) {
            if (child.getId() == id) {
                Parent parent = help.getParent(child.getParentId());
                currentUser = new CurrentUser(parent.getId(), parent.getFirstname(), parent.getLastname(), parent.getRole());
                return currentUser;
            }
        }
        return currentUser;
    }
}
