package Main.Tools;

import Main.Models.Child;
import Main.Models.Waitlist;

import java.util.Scanner;

public class WaitlistOptions {

    HelpingMethods help = new HelpingMethods();
    Waitlist waitlist = new Waitlist();

    Scanner intScan = new Scanner(System.in);
    Scanner stringScan = new Scanner(System.in);

    public void abortChildFromWaitlist()
    {
        System.out.println("Indtast ID på barn der skal slettes");
        int id = intScan.nextInt();
        int childId = -1;
        if (help.checkId(id, "waitlist")){
            childId = id;
        } else {
            System.out.println("ID eksisterer ikke i database");
        }
        if (childId != -1) {
            int childIndex = help.getIndexChild(childId, Child.childArray);
            Waitlist.waitlistArray.remove(childIndex);
            waitlist.waitlistFileWriter(Waitlist.waitlistArray);
            System.out.println("THE KID HAS BEEN ABO--- DELETED");
        }
    }

    public void createChildWaitlist()
    {
        System.out.println("Lad os oprette et barn på ventelisten");
        Waitlist newWaitlist = new Waitlist();

        //Add parent
        newWaitlist.setParentFirstname(help.validateStuff("fornavn på forældre", "Hint: Store forbogstaver", help.nameRegex));
        newWaitlist.setParentLastname(help.validateStuff("efternavn på forældre", "Hint: Store forbogstaver", help.nameRegex));
        newWaitlist.setEmail(help.validateStuff("e-mail", "Hint: eksempel@gmail.com", help.emailRegex));
        newWaitlist.setPhone(Integer.parseInt(help.validateStuff("telefon", "Hint: 91827384", help.numberRegex)));
        System.out.println("Venligst indtast adresse");
        newWaitlist.setAddress(stringScan.nextLine());
        //Add child
        newWaitlist.setId(Waitlist.waitlistArray.size());
        newWaitlist.setBirthdate(help.validateStuff("fødselsdato på barn", "Hint: dd/MM-yyyy", help.birthdayRegex));
        newWaitlist.setChildFirstname((help.validateStuff("fornavn på barn", "Hint: Store forbogstaver", help.nameRegex)));
        newWaitlist.setChildLastname(help.validateStuff("efternavn på barn", "Hint: Store forbogstaver", help.nameRegex));
        Waitlist.waitlistArray.add(newWaitlist);

        waitlist.waitlistFileWriter(Waitlist.waitlistArray);

        System.out.println("Barn er blevet oprettet på venteliste!");
    }

    public void getWaitlist()
    {
        for (Waitlist waitlist : Waitlist.waitlistArray) {
            System.out.println("Barnets ID: " + waitlist.getId());
            System.out.println("Barnets navn: " + waitlist.getChildFirstname() +
                    " " + waitlist.getChildLastname());
            System.out.println("Foedsels dato (DD/MM-YYYY): " + waitlist.getBirthdate());
            System.out.println("Foraeldre navn: " + waitlist.getParentFirstname() +
                    " " + waitlist.getParentLastname());
            System.out.println(" Tlf.: " + waitlist.getPhone() +
                    " Adresse: " + waitlist.getAddress());
            System.out.println("E-mail: " + waitlist.getEmail());
            System.out.println("\n-------------------------------------------------\n");
        }
    }
}
