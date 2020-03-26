package Updates;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Updates
{
    public static ArrayList<Updates> updatesArray = new ArrayList<>();
    private int id;
    private String headLine;
    private String body;

    public Updates(int id, String headLine, String body)
    {
        this.id = id;
        this.headLine = headLine;
        this.body = body;
    }

    public Updates()
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

    public ArrayList<Updates> initialiseInfo() {
        Scanner input = null;
        try
        {
            input = new Scanner(new File("src/Updates/Updates"));
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
                Updates updates = new Updates(id, headLine, body);
                updatesArray.add(updates);

                id = -1;
                headLine = "";
                body = "";
            }
        }
        return updatesArray;
    }

    public void newsFileWriter(ArrayList<Updates> updatesArray){
        FileWriter fileWriter;
        try
        {
            fileWriter = new FileWriter("src/Updates/Updates");
            writeNewsInfo(fileWriter, updatesArray);
            fileWriter.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void writeNewsInfo(FileWriter fileWriter, ArrayList<Updates> updatesArray){
        for (int i = 0; i < updatesArray.size(); i++){
            try
            {
                fileWriter.write("ID: " + updatesArray.get(i).getId()+ "\n");
                fileWriter.write("Overskrift: " + updatesArray.get(i).getHeadLine() + "\n");
                fileWriter.write("Indhold: " + updatesArray.get(i).getBody() + "\n");

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
