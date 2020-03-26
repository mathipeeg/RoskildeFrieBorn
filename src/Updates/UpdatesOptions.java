package Updates;

import Members.Parent;
import StaffMembers.Staff;
import Tools.HelpingMethods;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UpdatesOptions {

    Updates updates = new Updates();
    
    HelpingMethods help = new HelpingMethods();
    Scanner intScan = new Scanner(System.in);
    Scanner stringScan = new Scanner(System.in);

    public void seeUpdates()
    {
        System.out.println("Du har valgt at se opdateringer \n");
        for (int i = 0; i < Updates.updatesArray.size(); i++)
        {
            System.out.println(Updates.updatesArray.get(i).getHeadLine());
            System.out.println(Updates.updatesArray.get(i).getDate());
            System.out.println("Af: " + Updates.updatesArray.get(i).getAuthor());
            System.out.println(Updates.updatesArray.get(i).getBody() + "\n");
            System.out.println("------------------------------------------------- \n");
        }
    }

    public void createUpdates()
    {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM-yyyy");
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
        String currentDate = dateFormat.format(date) + ", " + hourFormat.format(date);

        System.out.println("Du har valgt at oprette nye opdateringer");
        Updates newUpdate = new Updates();
        String name = getName();
        newUpdate.setId(Updates.updatesArray.size());
        System.out.println("Venlig indtast en overskrift");
        newUpdate.setHeadLine(stringScan.nextLine());
        System.out.println("Skriv noget indhold");
        newUpdate.setBody(stringScan.nextLine());
        newUpdate.setAuthor(name);
        newUpdate.setDate(currentDate);

        Updates.updatesArray.add(newUpdate);
        updates.newsFileWriter(Updates.updatesArray);
        System.out.println("Din opdatering er lavet, TILLYKKE!");
    }

    public String getName() {
        // TODO: 26-03-2020 få id til at være det static og find rolle
        System.out.println("Indtast dit (eller dit barns )ID");
        int id = intScan.nextInt();
        System.out.println("er du 1) Forældre 2) Medarbejder?");
        if (intScan.nextInt() == 1){
            Parent parent = help.getParent(help.getChild(id).getParentId());
            return parent.getFirstname() + " " + parent.getLastname();
        } else {
            Staff staff = help.getStaff(id);
            return staff.getFirstname() + " " + staff.getLastname();
        }
    }

    public void editUpdate()
    {
        int choice = 0;
        while(choice != 3) {
            System.out.println("Du har valgt at ændre nogle opdateringer \nIndtast titel på opdateringen, der skal ændres \nSkriv 'afslut' for at afslutte");
            String title = stringScan.nextLine();
            if (title.equalsIgnoreCase("afslut")){
                break;
            }
            if (help.getNews(title) != null) {
                int id = help.getNews(title).getId();
                if (help.checkId(id, "news")) {
                    Updates updates = help.getNews(id);
                    System.out.println("Hvilken info skal ændres? \n1) Overskrift \n2) Indhold \n3) Afslut");
                    choice = intScan.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("Ændre overskift");
                            updates.setHeadLine(stringScan.nextLine());
                            break;
                        case 2:
                            System.out.println("Ændre indhold");
                            updates.setBody(stringScan.nextLine());
                            break;
                        case 3:
                            // TODO: 26-03-2020 Find ud af hvorfor den afslutter helt ???
                            System.out.println("Afslut");
                            break;
                    }
                    updates.newsFileWriter(Updates.updatesArray);
                } else {
                    System.out.println("ID eksisterer ikke. Prøv med et andet.");
                }
            } else {
                System.out.println("Titel eksisterer ikke.");
            }
        }
    }

    public void deleteUpdate()
    {
        System.out.println("Indtast titel på opdatering der skal slettes");
        String title = stringScan.nextLine();
        int id = help.getNews(title).getId();
        int newsIndex = help.getIndexNews(id, Updates.updatesArray);
        Updates.updatesArray.remove(newsIndex);
        updates.newsFileWriter(Updates.updatesArray);
        System.out.println("Opdatering er nu slettet");
    }
}
