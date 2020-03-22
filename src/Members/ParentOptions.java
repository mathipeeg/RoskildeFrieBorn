package Members;

import StaffMembers.Options;
import java.util.Scanner;

public class ParentOptions
{
    Scanner scanner = new Scanner(System.in);
    Options options = new Options();

    public void options(Parent parent)
    {
        // TODO: 22-03-2020 SMID IND I MENU
        System.out.println("Velkommen " + parent.getFirstname() + "!");
        while (true){
            System.out.println("Vil du \n1) Se nyheder \n2) Ændr din information \n3) Afslut");
            int choice = scanner.nextInt();
            if (choice == 1){
                System.out.println("NEWS");
                break;
            } else if (choice == 2){
                System.out.println("CHANGE YOUR INFO");
                options.editParent(parent.getId());
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
