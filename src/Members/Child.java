package Members;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Child
{
    public static ArrayList<Child> childArray = new ArrayList<>();
    private int id;
    private String birthdate;
    private String firstname;
    private String lastname;
    private int parentId;

    public Child(int id, String birthdate, String firstname, String lastname, int parentId)
    {
        this.id = id;
        this.birthdate = birthdate;
        this.firstname = firstname;
        this.lastname = lastname;
        this.parentId = parentId;
    }

    public Child()
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

    public String getBirthdate()
    {
        return birthdate;
    }

    public void setBirthdate(String birthdate)
    {
        this.birthdate = birthdate;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public int getParentId()
    {
        return parentId;
    }

    public void setParentId(int parentId)
    {
        this.parentId = parentId;
    }

    public ArrayList<Child> initialiseInfo()
    {
        Scanner input = null;
        try
        {
            input = new Scanner(new File("src/Members/Children"));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        int id = -1;
        String birthdate = "";
        String firstname = "";
        String lastname = "";
        int parentId = -1;

        while (input.hasNextLine())
        {
            String[] lineArray = input.nextLine().split(": ");

            if (lineArray[0].equalsIgnoreCase("ID")) id = Integer.parseInt(lineArray[1]);
            if (lineArray[0].equalsIgnoreCase("Fødselsdato")) birthdate = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("Fornavn")) firstname = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("Efternavn")) lastname = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("Forældre ID")) parentId = Integer.parseInt(lineArray[1]);

            if (id != -1 && !birthdate.equalsIgnoreCase("") && !firstname.equalsIgnoreCase("")
                    && !lastname.equalsIgnoreCase("") && parentId != -1)
            {
                Child child = new Child(id, birthdate, firstname, lastname, parentId);
                childArray.add(child);

                id = -1;
                birthdate = "";
                firstname = "";
                lastname = "";
                parentId = -1;
            }
        }
        return childArray;
    }

    public void childFileWriter(ArrayList<Child> childArray)
    {
        FileWriter fileWriter;
        try
        {
            fileWriter = new FileWriter("src/Members/Children");
            writeChildInfo(fileWriter, childArray);
            fileWriter.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void writeChildInfo(FileWriter fileWriter, ArrayList<Child> childArray)
    {
        for (int i = 0; i < childArray.size(); i++)
        {
            try
            {
                fileWriter.write("ID: " + childArray.get(i).getId() + "\n");
                fileWriter.write("Fødselsdato: " + childArray.get(i).getBirthdate() + "\n");
                fileWriter.write("Fornavn: " + childArray.get(i).getFirstname() + "\n");
                fileWriter.write("Efternavn: " + childArray.get(i).getLastname() + "\n");
                fileWriter.write("Forældre ID: " + childArray.get(i).getParentId() + "\n");
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
