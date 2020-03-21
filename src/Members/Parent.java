package Members;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parent
{
    public static ArrayList<Parent> parentArray = new ArrayList<>();
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private int phone;
    private String account;
    private String address;
    private String role;

    public Parent(int id, String firstname, String lastname, String email, int phone, String account, String address, String role)
    {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.account = account;
        this.address = address;
        this.role = role;
    }

    public Parent()
    {
    }

    public static ArrayList<Parent> getParentArray()
    {
        return parentArray;
    }

    public static void setParentArray(ArrayList<Parent> parentArray)
    {
        Parent.parentArray = parentArray;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getPhone()
    {
        return phone;
    }

    public void setPhone(int phone)
    {
        this.phone = phone;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public ArrayList<Parent> initialiseInfo()
    {
        Scanner input = null;
        try
        {
            input = new Scanner(new File("src/Members/Parents"));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }


        int id = -1;
        String firstname = "";
        String lastname = "";
        String email = "";
        int phone = -1;
        String account = "";
        String address = "";
        String role = "";

        while (input.hasNextLine())
        {
            String[] lineArray = input.nextLine().split(": ");

            if (lineArray[0].equalsIgnoreCase("ID")) id = Integer.parseInt(lineArray[1]);
            if (lineArray[0].equalsIgnoreCase("Fornavn")) firstname = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("Efternavn")) lastname = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("E-mail")) email = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("Telefon")) phone = Integer.parseInt(lineArray[1]);
            if (lineArray[0].equalsIgnoreCase("Konto nr.")) account = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("Adresse")) address = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("Stilling")) role = lineArray[1];

            if (id != -1 && !firstname.equalsIgnoreCase("") && !lastname.equalsIgnoreCase("")
                    && !email.equalsIgnoreCase("") && phone != -1 && !account.equalsIgnoreCase("") && !address.equalsIgnoreCase("")
                    && !role.equalsIgnoreCase(""))
            {
                Parent parent = new Parent(id, firstname, lastname, email, phone, account, address, role);
                parentArray.add(parent);

                id = -1;
                firstname = "";
                lastname = "";
                email = "";
                phone = -1;
                account = "";
                address = "";
                role = "";
            }
        }
        return parentArray;
    }

    public void parentFileWriter(ArrayList<Parent> parentArray){
        FileWriter fileWriter;
        try
        {
            fileWriter = new FileWriter("src/Members/Parents");
            writeParentInfo(fileWriter, parentArray);
            fileWriter.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void writeParentInfo(FileWriter fileWriter, ArrayList<Parent> parentArray){
        for (int i = 0; i < parentArray.size(); i++){
            try
            {
                fileWriter.write("ID: " + parentArray.get(i).getId() + "\n");
                fileWriter.write("Fornavn: " + parentArray.get(i).getFirstname() + "\n");
                fileWriter.write("Efternavn: " + parentArray.get(i).getLastname() + "\n");
                fileWriter.write("E-mail: " + parentArray.get(i).getEmail() + "\n");
                fileWriter.write("Telefon: " + parentArray.get(i).getPhone() + "\n");
                fileWriter.write("Konto nr.: " + parentArray.get(i).getAccount() + "\n");
                fileWriter.write("Adresse: " + parentArray.get(i).getAddress() + "\n");
                fileWriter.write("Stilling: " + parentArray.get(i).getRole() + "\n");
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
