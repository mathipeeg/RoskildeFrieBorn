import Members.Child;
import Members.Parent;
import Members.ParentOptions;
import StaffMembers.AdminOptions;
import StaffMembers.Staff;
import StaffMembers.StaffOptions;

import java.util.Scanner;

public class Menu
{
    public static int id = -1;
    Scanner scanner = new Scanner(System.in);
    Scanner s = new Scanner(System.in);
    StaffOptions staffOptions = new StaffOptions();
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
                staffOptions.options(currentStaff);
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

}