package Organising;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Waitlist
{
    public static ArrayList<Waitlist> waitlistArray = new ArrayList<>();
    private int id;
    private String childFirstname;
    private String childLastname;
    private String parentFirstname;
    private String parentLastname;
    private String birthdate;
    private int phone;
    private String address;
    private String email;

    public Waitlist(int id, String childFirstname, String childLastname, String parentFirstname, String parentLastname, String birthdate, int phone, String address, String email)
    {
        this.id = id;
        this.childFirstname = childFirstname;
        this.childLastname = childLastname;
        this.parentFirstname = parentFirstname;
        this.parentLastname = parentLastname;
        this.birthdate = birthdate;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public Waitlist()
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

    public String getChildFirstname()
    {
        return childFirstname;
    }

    public void setChildFirstname(String childFirstname)
    {
        this.childFirstname = childFirstname;
    }

    public String getChildLastname()
    {
        return childLastname;
    }

    public void setChildLastname(String childLastname)
    {
        this.childLastname = childLastname;
    }

    public String getParentFirstname()
    {
        return parentFirstname;
    }

    public void setParentFirstname(String parentFirstname)
    {
        this.parentFirstname = parentFirstname;
    }

    public String getParentLastname()
    {
        return parentLastname;
    }

    public void setParentLastname(String parentLastname)
    {
        this.parentLastname = parentLastname;
    }

    public String getBirthdate()
    {
        return birthdate;
    }

    public void setBirthdate(String birthdate)
    {
        this.birthdate = birthdate;
    }

    public int getPhone()
    {
        return phone;
    }

    public void setPhone(int phone)
    {
        this.phone = phone;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public ArrayList<Waitlist> initialiseInfo()
    {
        Scanner input = null;
        try
        {
            input = new Scanner(new File("src/Organising/Waitlist"));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        int id = -1;
        String childFirstname = "";
        String childLastname = "";
        String parentFirstname = "";
        String parentLastname = "";
        String birthdate = "";
        int phone = -1;
        String address = "";
        String email = "";

        while (input.hasNextLine())
        {
            String[] lineArray = input.nextLine().split(": ");

            if (lineArray[0].equalsIgnoreCase("ID")) id = Integer.parseInt(lineArray[1]);
            if (lineArray[0].equalsIgnoreCase("Barns fornavn")) childFirstname = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("Barns efternavn")) childLastname = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("Forældre fornavn")) parentFirstname = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("Forældre efternavn")) parentLastname = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("Fødselsdato")) birthdate = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("Telefon")) phone = Integer.parseInt(lineArray[1]);
            if (lineArray[0].equalsIgnoreCase("Adresse")) address = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("E-mail")) email = lineArray[1];

            if (id != -1 && !childFirstname.equalsIgnoreCase("") && !childLastname.equalsIgnoreCase("")
                    && !parentFirstname.equalsIgnoreCase("") && !parentLastname.equalsIgnoreCase("")
                    && !birthdate.equalsIgnoreCase("") && phone != -1 && !address.equalsIgnoreCase("")
                    && !email.equalsIgnoreCase("")){

                Waitlist waitlist = new Waitlist(id, childFirstname, childLastname, parentFirstname, parentLastname,
                        birthdate, phone, address, email);
                waitlistArray.add(waitlist);

                id = -1;
                childFirstname = "";
                childLastname = "";
                parentFirstname = "";
                parentLastname = "";
                birthdate = "";
                phone = -1;
                address = "";
                email = "";
            }
        }
        return waitlistArray;
    }

    public void waitlistFileWriter(ArrayList<Waitlist> waitlistArray){
        FileWriter fileWriter;
        try
        {
            fileWriter = new FileWriter("src/Waitlist");
            writeWaitlistInfo(fileWriter, waitlistArray);
            fileWriter.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void writeWaitlistInfo(FileWriter fileWriter, ArrayList<Waitlist> waitlistArray){
        for (int i = 0; i < waitlistArray.size(); i++){
            try
            {
                fileWriter.write("ID: " + waitlistArray.get(i).getId() + "\n");
                fileWriter.write("Barns fornavn: " + waitlistArray.get(i).getChildFirstname() + "\n");
                fileWriter.write("Barns efternavn: " + waitlistArray.get(i).getChildLastname() + "\n");
                fileWriter.write("Forældre fornavn: " + waitlistArray.get(i).getParentFirstname() + "\n");
                fileWriter.write("Forældre efternavn: " + waitlistArray.get(i).getParentLastname() + "\n");
                fileWriter.write("Fødselsdato: " + waitlistArray.get(i).getBirthdate() + "\n");
                fileWriter.write("Telefon: " + waitlistArray.get(i).getPhone() + "\n");
                fileWriter.write("Adresse: " + waitlistArray.get(i).getAddress() + "\n");
                fileWriter.write("E-mail: " + waitlistArray.get(i).getEmail() + "\n");
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

}
