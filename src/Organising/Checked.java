package Organising;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Checked
{
    public static ArrayList<Checked> checkedKidsArray = new ArrayList<>();
    private int id;
    private int allHours;
    private String date;
    private String checkIn;
    private String checkOut;
    private String name;



    public Checked()
    {
    }

    public Checked(int id, String date, int allHours, String checkIn, String checkOut, String name)
    {
        this.id = id;
        this.date = date;
        this.allHours = allHours;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.name = name;

    }

    public static ArrayList<Checked> getCheckedKidsArray() {
        return getCheckedKidsArray();
    }

    public static void setCheckedKidsArray(ArrayList<Checked> checkedKidsArray) {
        Checked.checkedKidsArray = checkedKidsArray;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public int getAllHours() {
        return allHours;
    }

    public void setAllHours(int allHours) {
        this.allHours = allHours;
    }

    public String getCheckIn() {
        return getCheckIn();
    }
    public void setCheckIn(String checkIn){
        this.checkIn = checkIn;
    }
    public String getCheckOut(){
        return checkOut;
    }
    public void setCheckOut (String checkOut){
        this.checkOut = checkOut;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return getName();
    }


    public ArrayList<Checked> initialiseInfo()
    {
        Scanner input = null;
        try
        {
            input = new Scanner(new File("src/Organising/CheckedKids"));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        int id = -1;
        String date = "";
        int allHours = -1;
        String name = "";
        String checkIn = "";
        String checkOut = "";



        while (input.hasNextLine())
        {
            String[] lineArray = input.nextLine().split(": ");

            if (lineArray[0].equalsIgnoreCase("Barn ID")) id = Integer.parseInt(lineArray[1]);
            if (lineArray[0].equalsIgnoreCase("Navn")) name = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("Indtjek")) checkIn = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("Udtjek")) checkOut = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("Dato")) date = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("Samlet tid")) allHours = Integer.parseInt(lineArray[1]);


            if (id != -1 && !date.equalsIgnoreCase("") && allHours != -1
                    && !checkIn.equalsIgnoreCase("")
                    && !checkOut.equalsIgnoreCase("")
                    && !name.equalsIgnoreCase("")){
                Checked checked = new Checked(id, date, allHours, name, checkIn, checkOut);
                checkedKidsArray.add(checked);
                id = -1;
                date = "";
                allHours = -1;
                name = "";
                checkIn = "";
                checkOut = "";

            }
        }
        return checkedKidsArray;
    }

    public void checkedFileWriter(ArrayList<Checked> checkedKidsArray){
        FileWriter fileWriter;
        try
        {
            fileWriter = new FileWriter("src/Organising/CheckedKids");
            writeCheckedInfo(fileWriter, Checked.checkedKidsArray);
            fileWriter.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void writeCheckedInfo(FileWriter fileWriter, ArrayList<Checked> checkedKidsArray){
        for (int i = 0; i < checkedKidsArray.size(); i++){
            try
            {
                fileWriter.write("Barn ID: " + checkedKidsArray.get(i).getId() + "\n");
                fileWriter.write("Navn: " + checkedKidsArray.get(i).getName() + "\n");
                fileWriter.write("Indtjek: " + checkedKidsArray.get(i).getCheckIn() + "\n");
                fileWriter.write("Udtjek: " + checkedKidsArray.get(i).getCheckOut() + "\n");
                fileWriter.write("Dato: " + checkedKidsArray.get(i).getDate() + "\n");
                fileWriter.write("Samlet tid: " + checkedKidsArray.get(i).getAllHours() + "\n");
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
