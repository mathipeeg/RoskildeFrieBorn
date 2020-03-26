package Main.Members;

import Main.Models.Child;
import Main.Models.Parent;
import Main.Tools.HelpingMethods;

import java.util.Scanner;

public class ParentOptions {

    HelpingMethods help = new HelpingMethods();
    Child child = new Child();
    Parent parent = new Parent();

    Scanner intScan = new Scanner(System.in);
    Scanner stringScan = new Scanner(System.in);

    public void editParent(int parentId) {
        int id;
        if (parentId == -1) {
            System.out.println("Lad os ændre en forældre! \nIndtast ID på forældre, der skal ændres");
            id = intScan.nextInt();
        } else{
            id = parentId;
        }
        Parent parent = help.getParent(id);
        System.out.println("Hvilken info skal ændres? \n1) Fornavn \n2) Efternavn \n3) Forældre ID \n3 Email \n4) Telefonnummer \5) Kontonummer \6) Adresse \7) Password");
        int choice = intScan.nextInt();
        switch (choice){
            case 1:
                parent.setFirstname(help.validateStuff("fornavn på forældre", "Hint: Store forbogstaver", help.nameRegex));
                System.out.println("Fornavn er ændret!");
                break;
            case 2:
                parent.setLastname(help.validateStuff("efternavn på forældre", "Hint: Store forbogstaver", help.nameRegex));
                System.out.println("Efternavn er ændret!");
                break;
            case 3:
                parent.setEmail(help.validateStuff("e-mail","Hint: eksempel@gmail.com", help.emailRegex));
                System.out.println("Email er ændret");
                break;
            case 4:
                parent.setPhone(Integer.parseInt(help.validateStuff("telefon", "Hint: 91827384", help.numberRegex)));
                System.out.println("Telefonnummer er ændret");
                break;
            case 5:
                parent.setAccount(help.validateStuff("konto nr.", "Hint: 12345678901234", help.accountRegex));
                System.out.println("Kontonummer er ændret");
                break;
            case 6:
                System.out.println("Venligst indtast adresse");
                parent.setAddress(stringScan.nextLine());
            default:
                System.out.println("Mærkeligt input mand");
                break;
        }
        parent.parentFileWriter(Parent.parentArray);
    }

    public void abortParent() {

        System.out.println("Indtast ID der skal slettes");
        int parentId = intScan.nextInt();
        int parenti = help.getIndexParent(parentId, Parent.parentArray);
        Child.childArray.removeIf(child -> child.getParentId() == parentId);
        Parent.parentArray.remove(parenti);

        child.childFileWriter(Child.childArray);
        parent.parentFileWriter(Parent.parentArray);
        System.out.println("YOU AND YOUR KID HAS BEEN ABO--- DELETED");
    }

    public void createParent() {
        System.out.println("Lad os oprette dig som forældre! Woooo");
        Parent newParent = new Parent();
        Child newChild = new Child();

        newParent.setId(Parent.parentArray.size());
        newParent.setFirstname(help.validateStuff("Dit fornavn", "Hint: Store forbogstaver", help.nameRegex));
        newParent.setLastname(help.validateStuff("Dit efternavn", "Hint: Store forbogstaver", help.nameRegex));
        newParent.setEmail(help.validateStuff("e-mail", "Hint: eksempel@gmail.com", help.emailRegex));
        newParent.setPhone(Integer.parseInt(help.validateStuff("telefon", "Hint: 91827384", help.numberRegex)));
        newParent.setAccount(help.validateStuff("konto nr.", "Hint: 12345678901234", help.accountRegex));
        System.out.println("Venligst indtast adresse");
        newParent.setAddress(stringScan.nextLine());
        newParent.setRole("Parent");
        Parent.parentArray.add(newParent);

        newChild.setId(Child.childArray.size());
        newChild.setBirthdate(help.validateStuff("fødselsdato","Hint: dd/MM-yyyy", help.birthdayRegex));
        newChild.setFirstname(help.validateStuff("fornavn på barn", "Hint: Store forbogstaver", help.nameRegex));
        newChild.setLastname(help.validateStuff("efternavn på barn", "Hint: Store forbogstaver", help.nameRegex));
        newChild.setParentId(newParent.getId());
        Child.childArray.add(newChild);

        parent.parentFileWriter(Parent.parentArray);
        child.childFileWriter(Child.childArray);
        System.out.println("Du er blevet oprettet! Tillykke!");
    }
}
