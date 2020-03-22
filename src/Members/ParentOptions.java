package Members;

import StaffMembers.AdminOptions;
import java.util.Scanner;

public class ParentOptions
{
    Scanner scanner = new Scanner(System.in);
    AdminOptions adminOptions = new AdminOptions();

    public void options(Parent parent)
    {
        System.out.println("Velkommen " + parent.getFirstname() + "!");
        while (true){
            System.out.println("Vil du \n1) Se nyheder \n2) Ændr din information \n3) Afslut");
            int choice = scanner.nextInt();
            if (choice == 1){
                System.out.println("NEWS");
                break;
            } else if (choice == 2){
                System.out.println("CHANGE YOUR INFO");
                adminOptions.editParent(parent.getId());
                break;
            } else if (choice == 3){
                System.out.println("See ya :)");
                break;
            } else{
                System.out.println("Mærkeligt input alligevel...");
            }
        }
    }
}
