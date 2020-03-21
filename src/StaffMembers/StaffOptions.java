package StaffMembers;

import java.util.Scanner;

public class StaffOptions
{
    Scanner scanner = new Scanner(System.in);
    AdminOptions adminOptions = new AdminOptions();
    public void options(Staff currentStaff)
    {
        System.out.println("VELKOMMEN " + currentStaff.getFirstname() + " " + currentStaff.getLastname());
        if (currentStaff.getRole().equalsIgnoreCase("Admin")){
            adminOptions.options();
        } else
        {
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
                    break;
                case 3:
                    System.out.println("Ændr info");
                    break;
                case 4:
                    System.out.println("Se barns info");
                    break;
                case 5:
                    System.out.println("indskriv barn");
                    // TODO: 21-03-2020 CASPERS :D
                    break;
                case 6:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("lmao");
                    break;
            }
        }
    }
}
