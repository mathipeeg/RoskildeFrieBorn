package Organising;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class News
{
    public static ArrayList<News> newsArray = new ArrayList<>();
    private int id;
    private String headLine;
    private String body;

    public News(int id, String headLine, String body)
    {
        this.id = id;
        this.headLine = headLine;
        this.body = body;
    }

    public News()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getHeadLine()
    {
        return headLine;
    }

    public void setHeadLine(String headLine)
    {
        this.headLine = headLine;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public ArrayList<News> initialiseInfo() {
        Scanner input = null;
        try
        {
            input = new Scanner(new File("src/Organising/News"));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        int id = -1;
        String headLine = "";
        String body = "";

        while (input.hasNextLine()) {
            String[] lineArray = input.nextLine().split(": ");

            if (lineArray[0].equalsIgnoreCase("ID")) id = Integer.parseInt(lineArray[1]);
            if (lineArray[0].equalsIgnoreCase("Overskrift")) headLine = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("Indhold")) body = lineArray[1];

            if (id != -1 && !headLine.equalsIgnoreCase("") && !body.equalsIgnoreCase(""))
            {
                News news = new News(id, headLine, body);
                newsArray.add(news);

                id = -1;
                headLine = "";
                body = "";
            }
        }
        return newsArray;
    }

    public void newsFileWriter(ArrayList<News> newsArray){
        FileWriter fileWriter;
        try
        {
            fileWriter = new FileWriter("src/Organising/News");
            writeNewsInfo(fileWriter, newsArray);
            fileWriter.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void writeNewsInfo(FileWriter fileWriter, ArrayList<News> newsArray){
        for (int i = 0; i < newsArray.size(); i++){
            try
            {
                fileWriter.write("ID: " + newsArray.get(i).getId()+ "\n");
                fileWriter.write("Overskrift: " + newsArray.get(i).getHeadLine() + "\n");
                fileWriter.write("Indhold: " + newsArray.get(i).getBody() + "\n");

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
