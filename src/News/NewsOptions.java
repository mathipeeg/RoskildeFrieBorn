package News;

import Tools.HelpingMethods;

import java.util.Scanner;

public class NewsOptions {

    News news = new News();
    
    HelpingMethods help = new HelpingMethods();
    Scanner intScan = new Scanner(System.in);
    Scanner stringScan = new Scanner(System.in);

    public void seeNews()
    {
        for (int i = 0; i < News.newsArray.size(); i++)
        {
            System.out.println("Du har valgt at se nyheder");
            System.out.println("Virker det ?" + News.newsArray.get(i).getHeadLine());
            System.out.println(News.newsArray.get(i).getBody());
        }
    }

    public void createNews()
    {
        System.out.println("Du har valgt at oprette nye nyheder");
        News newNews = new News();

        newNews.setId(News.newsArray.size());
        System.out.println("Venlig indtast en overskrift");
        newNews.setHeadLine(stringScan.nextLine());
        System.out.println("Skriv noget indhold");
        newNews.setBody(stringScan.nextLine());


        News.newsArray.add(newNews);
        news.newsFileWriter(News.newsArray);
        System.out.println("Dine nyheder er lavet, TILLYKKE!");
    }

    public void editNews()
    {
        System.out.println("Du har valgt at ændre nogle nyheder \nIndtast ID på nyheden, der skal ændres");
        int id = intScan.nextInt();
        News news = help.getNews(id);
        System.out.println("Hvilken info skal ændres? \n1) Overskrift \n2) Indhold \n3) Afslut");
        int choice = intScan.nextInt();
        switch (choice)
        {
            case 1:
                System.out.println("Ændre overskift");
                news.setHeadLine(stringScan.nextLine());
                break;
            case 2:
                System.out.println("Ændre indhold");
                news.setBody(stringScan.nextLine());
                break;
            case 3:
                System.out.println("Afslut");
                break;
        }
        news.newsFileWriter(News.newsArray);
    }

    public void deleteNews()
    {
        System.out.println("Indtast ID på nyhed der skal slettes");
        int id = intScan.nextInt();
        int newsIndex = help.getIndexNews(id, News.newsArray);
        News.newsArray.remove(newsIndex);
        news.newsFileWriter(News.newsArray);
        System.out.println("Nyhederne er nu slettet");
    }
}
