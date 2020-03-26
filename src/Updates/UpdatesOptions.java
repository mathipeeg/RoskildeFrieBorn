package Updates;

import Tools.HelpingMethods;

import java.util.Scanner;

public class UpdatesOptions {

    Updates updates = new Updates();
    
    HelpingMethods help = new HelpingMethods();
    Scanner intScan = new Scanner(System.in);
    Scanner stringScan = new Scanner(System.in);

    public void seeUpdates()
    {
        for (int i = 0; i < Updates.updatesArray.size(); i++)
        {
            // TODO: 25-03-2020 Noget andet herinde?
            System.out.println("Du har valgt at se opdateringer");
            System.out.println("Virker det ?" + Updates.updatesArray.get(i).getHeadLine());
            System.out.println(Updates.updatesArray.get(i).getBody());
        }
    }

    public void createUpdates()
    {
        System.out.println("Du har valgt at oprette nye opdateringer");
        Updates newUpdate = new Updates();

        newUpdate.setId(Updates.updatesArray.size());
        System.out.println("Venlig indtast en overskrift");
        newUpdate.setHeadLine(stringScan.nextLine());
        System.out.println("Skriv noget indhold");
        newUpdate.setBody(stringScan.nextLine());

        Updates.updatesArray.add(newUpdate);
        updates.newsFileWriter(Updates.updatesArray);
        System.out.println("Din opdatering er lavet, TILLYKKE!");
    }

    public void editUpdate()
    {
        System.out.println("Du har valgt at ændre nogle nyheder \nIndtast ID på nyheden, der skal ændres");
        int id = intScan.nextInt();
        if (help.checkId(id, "news")){
            Updates updates = help.getNews(id);
            System.out.println("Hvilken info skal ændres? \n1) Overskrift \n2) Indhold \n3) Afslut");
            int choice = intScan.nextInt();
            switch (choice)
            {
                case 1:
                    System.out.println("Ændre overskift");
                    updates.setHeadLine(stringScan.nextLine());
                    break;
                case 2:
                    System.out.println("Ændre indhold");
                    updates.setBody(stringScan.nextLine());
                    break;
                case 3:
                    System.out.println("Afslut");
                    break;
            }
            updates.newsFileWriter(Updates.updatesArray);
        } else{
            System.out.println("ID eksisterer ikke. Prøv med et andet.");
        }
    }

    public void deleteUpdate()
    {
        System.out.println("Indtast ID på opdatering der skal slettes");
        // TODO: 26-03-2020 Titel i stedet for måske?
        int id = intScan.nextInt();
        int newsIndex = help.getIndexNews(id, Updates.updatesArray);
        Updates.updatesArray.remove(newsIndex);
        updates.newsFileWriter(Updates.updatesArray);
        System.out.println("Opdatering er nu slettet");
    }
}
