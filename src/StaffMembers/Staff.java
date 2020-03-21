package StaffMembers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Staff
{
    public static ArrayList<Staff> staffArray = new ArrayList<>();
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private int phone;
    private String address;
    private String role;
    private String password;

    public Staff(int id, String firstname, String lastname, String email, int phone, String address, String role, String password)
    {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.password = password;
    }

    public Staff()
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

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public ArrayList<Staff> initialiseInfo() {
        Scanner input = null;
        try
        {
            input = new Scanner(new File("src/StaffMembers/Staff"));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        int id = -1;
        String firstname = "";
        String lastname = "";
        String email = "";
        int phone = -1;
        String address = "";
        String role = "";
        String password = "";

        while (input.hasNextLine()) {
            String[] lineArray = input.nextLine().split(": ");

            if (lineArray[0].equalsIgnoreCase("ID")) id = Integer.parseInt(lineArray[1]);
            if (lineArray[0].equalsIgnoreCase("Fornavn")) firstname = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("Efternavn")) lastname = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("E-mail")) email = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("Telefon")) phone = Integer.parseInt(lineArray[1]);
            if (lineArray[0].equalsIgnoreCase("Adresse")) address = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("Stilling")) role = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("Password")) password = lineArray[1];

            if (id != -1 && !firstname.equalsIgnoreCase("") && !lastname.equalsIgnoreCase("")
                    && !email.equalsIgnoreCase("") && phone != -1 && !address.equalsIgnoreCase("")
                    && !role.equalsIgnoreCase("") && !password.equalsIgnoreCase(""))
            {
                Staff staff = new Staff(id, firstname, lastname, email, phone, address, role, password);
                staffArray.add(staff);

                id = -1;
                firstname = "";
                lastname = "";
                email = "";
                phone = -1;
                address = "";
                role = "";
                password = "";
            }
        }
        return staffArray;
    }

    public void staffFileWriter(ArrayList<Staff> staffArray){
        FileWriter fileWriter;
        try
        {
            fileWriter = new FileWriter("src/StaffMembers/Staff");
            writeStaffInfo(fileWriter, staffArray);
            fileWriter.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void writeStaffInfo(FileWriter fileWriter, ArrayList<Staff> staffArray){
        for (int i = 0; i < staffArray.size(); i++){
            try
            {
                fileWriter.write("ID: " + staffArray.get(i).getId()+ "\n");
                fileWriter.write("Fornavn: " + staffArray.get(i).getFirstname() + "\n");
                fileWriter.write("Efternavn: " + staffArray.get(i).getLastname() + "\n");
                fileWriter.write("E-mail: " + staffArray.get(i).getEmail() + "\n");
                fileWriter.write("Telefon: " + staffArray.get(i).getPhone() + "\n");
                fileWriter.write("Adresse: " + staffArray.get(i).getAddress() + "\n");
                fileWriter.write("Stilling: " + staffArray.get(i).getRole() + "\n");
                fileWriter.write("Password: " + staffArray.get(i).getPassword() + "\n");
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
