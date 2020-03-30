package Main.Members;

import Main.Models.Child;
import Main.Models.Parent;
import Main.Tools.*;

import java.util.Scanner;

public class ChildOptions {

    HelpingMethods help = new HelpingMethods();
    Child child = new Child();
    Parent parent = new Parent();

    Scanner intScan = new Scanner(System.in);
    Scanner stringScan = new Scanner(System.in);

    public void editChild()
    {
        System.out.println("Lad os ændre et barn! \nIndtast ID på barn, der skal ændres");
        int id = intScan.nextInt();
        if (help.checkId(id, "child")) {
            Child child = help.getChild(id);
            System.out.println("Hvilken info skal ændres? \n1) Fornavn \n2) Efternavn \n3) Forældre ID");
            int choice = intScan.nextInt();
            switch (choice) {
                case 1:
                    child.setFirstname(help.validateStuff("fornavn på barn", "Hint: Store forbogstaver", help.nameRegex));
                    System.out.println("Fornavn er ændret!");
                    break;
                case 2:
                    child.setLastname(help.validateStuff("efternavn på barn", "Hint: Store forbogstaver", help.nameRegex));
                    System.out.println("Efternavn er ændret!");
                    break;
                case 3:
                    child.setParentId(Integer.parseInt(help.validateStuff("forældre ID", "Hint: Kun tal", help.idRegex)));
                    System.out.println("Forældre ID er rettet!");
                    break;
                default:
                    System.out.println("Mærkeligt input mand");
                    break;
            }
            child.childFileWriter(Child.childArray);
        } else{
            System.out.println("ID eksisterer ikke. Prøv igen med et andet ID.");
        }
    }

    public void abortChild()
    {
        System.out.println("Indtast ID på barn der skal slettes");
        int id = intScan.nextInt();
        if (help.checkId(id, "child")) {
            int parentId = -1;
            for (int i = 0; i < Child.childArray.size(); i++) {
                if (Child.childArray.get(i).getId() == id) {
                    parentId = Child.childArray.get(i).getParentId();
                }
            }
            int iChild = help.getIndexChild(id, Child.childArray);
            int iParent = help.getIndexParent(parentId, Parent.parentArray);
            Child.childArray.remove(iChild);
            Parent.parentArray.remove(iParent);
            child.childFileWriter(Child.childArray);
            parent.parentFileWriter(Parent.parentArray);
            System.out.println("YOUR KID HAS BEEN ABO--- DELETED");
        } else {
            System.out.println("ID eksisterer ikke. Prøv igen med et andet ID.");
        }
    }

    public void createChild()
    {
        System.out.println("Lad os oprette et barn! Woooo");
        //Nye objekter
        Child newChild = new Child();
        Parent newParent = new Parent();
        //New parents
        newParent.setId(Parent.parentArray.size());
        newParent.setFirstname(help.validateStuff("fornavn på forældre", "Hint: Store forbogstaver", help.nameRegex));
        newParent.setLastname(help.validateStuff("efternavn på forældre", "Hint: Store forbogstaver", help.nameRegex));
        newParent.setEmail(help.validateStuff("e-mail", "Hint: eksempel@gmail.com", help.emailRegex));
        newParent.setPhone(Integer.parseInt(help.validateStuff("telefon", "Hint: 91827384", help.numberRegex)));
        newParent.setAccount(help.validateStuff("konto nr.", "Hint: 12345678901234", help.accountRegex));
        System.out.println("Venligst indtast adresse");
        newParent.setAddress(stringScan.nextLine());
        newParent.setRole("Parent");
        Parent.parentArray.add(newParent);
        //New child
        newChild.setId(Child.childArray.size());
        newChild.setBirthdate(help.validateStuff("fødselsdato", "Hint: dd/MM-yyyy", help.birthdayRegex));
        newChild.setFirstname(help.validateStuff("fornavn på barn", "Hint: Store forbogstaver", help.nameRegex));
        newChild.setLastname(help.validateStuff("efternavn på barn", "Hint: Store forbogstaver", help.nameRegex));
        newChild.setParentId(newParent.getId());
        Child.childArray.add(newChild);

        parent.parentFileWriter(Parent.parentArray);
        child.childFileWriter(Child.childArray);
        System.out.println("Barn er blevet oprettet! Tillykke!");
    }
}
