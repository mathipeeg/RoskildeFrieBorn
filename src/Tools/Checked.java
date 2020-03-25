package Tools;

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
    private int childId;
    private String allHours;
    private String date;
    private String checkIn;
    private String checkOut;

    public Checked()
    {
    }

    public Checked(int id, int childId, String allHours, String date, String checkIn, String checkOut) {
        this.id = id;
        this.childId = childId;
        this.allHours = allHours;
        this.date = date;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAllHours() {
        return allHours;
    }

    public void setAllHours(String allHours) {
        this.allHours = allHours;
    }

    public String getCheckIn() {
        return checkIn;
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

    public ArrayList<Checked> initialiseInfo()
    {
        Scanner input = null;
        try
        {
            input = new Scanner(new File("src/Tools/CheckedKids"));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        int id = -1;
        int childId = -1;
        String allHours = "";
        String date = "";
        String checkIn = "";
        String checkOut = "";

        while (input.hasNextLine())
        {
            String[] lineArray = input.nextLine().split(": ");

            if (lineArray[0].equalsIgnoreCase("Checked ID")) id = Integer.parseInt(lineArray[1]);
            if (lineArray[0].equalsIgnoreCase("Barn ID")) childId = Integer.parseInt(lineArray[1]);
            if (lineArray[0].equalsIgnoreCase("Indtjek")) checkIn = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("Udtjek")) checkOut = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("Dato")) date = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("Samlet tid")) allHours = lineArray[1];


            if (id != -1 && !date.equalsIgnoreCase("") && childId != -1 && !allHours.equalsIgnoreCase("")
                    && !checkIn.equalsIgnoreCase("")
                    && !checkOut.equalsIgnoreCase("")){

                Checked checked = new Checked(id, childId, allHours, date, checkIn, checkOut);
                checkedKidsArray.add(checked);
                id = -1;
                childId = -1;
                date = "";
                allHours = "";
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
            fileWriter = new FileWriter("src/Tools/CheckedKids");
            writeCheckedInfo(fileWriter, checkedKidsArray);
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
                fileWriter.write("Checked ID: " + checkedKidsArray.get(i).getId() + "\n");
                fileWriter.write("Barn ID: " + checkedKidsArray.get(i).getId() + "\n");
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
