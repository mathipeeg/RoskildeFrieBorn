package StaffMembers;

import Members.Child;
import News.News;
import Tools.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class StaffOptions
{
    Scanner intScan = new Scanner(System.in); //for ints
    Scanner stringScan = new Scanner(System.in); // for Strings

    Staff staff = new Staff();
    HelpingMethods help = new HelpingMethods();

    public void checkChild()
    {
        System.out.println("Venligst indtast Barnets ID: ");
        Checked checked = new Checked();
        Date date = new Date();
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM-yyyy");

        int childId = intScan.nextInt();
        if(help.checkId(childId, true, false, false, false)){
            Child child = help.getChild(childId);
            checked.setId(Checked.checkedKidsArray.size());
            checked.setChildId(childId);
            checked.setCheckIn(hourFormat.format(date));
            checked.setDate(dateFormat.format(date));
            checked.setAllHours("0");
            checked.setCheckOut("0");

            Checked.checkedKidsArray.add(checked);
            checked.checkedFileWriter(Checked.checkedKidsArray);
            System.out.println(child.getFirstname() + " er indtjekket");
        } else{
            System.out.println("ID eksisterer ikke. Prøv med et andet ID.");
        }
    }

    public void checkOutChild()
    {
        Date date = new Date();
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
        System.out.println("Venligst indtast barnets ID");
        int childId = intScan.nextInt();
        if (help.checkId(childId, true, false, false, false)){
            Checked checked = help.getCheckedChild(childId);
            String checkOut = hourFormat.format(date);

            int checkInHrs = splitTime(checked.getCheckIn(), true);
            int checkInMins = splitTime(checked.getCheckIn(), false);
            int checkOutHrs = splitTime(checkOut, true);
            int checkOutMins = splitTime(checkOut, false);
            int newHour = checkOutHrs - checkInHrs;
            int newMins;
            if (checkInMins > checkOutMins){ newMins = checkInMins - checkOutMins; }
            else { newMins = checkOutMins - checkInMins; }
            checked.setCheckOut(checkOut);
            checked.setAllHours(newHour + ":" + newMins);

            checked.checkedFileWriter(Checked.checkedKidsArray);
            System.out.println("Barn udtjekket.");
        } else {
            System.out.println("ID eksisterer ikke. Prøv med et andet.");
        }
    }

    public int splitTime(String time, boolean hour){
        if (hour){
            return Integer.parseInt(time.split(":")[0]);
        }
        return Integer.parseInt(time.split(":")[1]);
    }

    public void createStaff()
    {
        System.out.println("Du har valgt at oprette en medarbejder");
        Staff newStaff = new Staff();

        newStaff.setId(Staff.staffArray.size());
        newStaff.setFirstname(help.validateStuff("fornavn", "Hint: Store forbogstaver", help.nameRegex));
        newStaff.setLastname(help.validateStuff("efternavn", "Hint: Store forbogstaver", help.nameRegex));
        newStaff.setEmail(help.validateStuff("email", "Hint: hej@hotmail.com", help.emailRegex));
        newStaff.setPhone(Integer.parseInt(help.validateStuff("telefon", "Hint: 12345678", help.numberRegex)));
        System.out.println("Venligst indtast adresse");
        newStaff.setAddress(stringScan.nextLine());
        newStaff.setRole("Staff");
        System.out.println("Venligst indtast password");
        newStaff.setPassword(stringScan.nextLine());
        Staff.staffArray.add(newStaff);
        staff.staffFileWriter(Staff.staffArray);
        System.out.println("Din medarbejder er blevet oprettet, tillykke");
    }

    public void deleteStaff()
    {
        System.out.println("Indtast ID på medarbejder der skal slettes");
        int id = intScan.nextInt();
        int staffIndex = help.getIndexStaff(id, Staff.staffArray);
        Staff.staffArray.remove(staffIndex);
        staff.staffFileWriter(Staff.staffArray);
        System.out.println("Du har nu slettet en medarbejder");
    }

    public void editStaff()
    {
        System.out.println("Du vil aendre en medarbejders oplysninger -> Indtast medarbejder ID");
        int id = intScan.nextInt();
        Staff staff = help.getStaff(id);
        System.out.println("Her er dine valgmuligheder \n1) Fornavn \n2) Efternavn \n3) Email \n4) " +
                "Telefon \n5) Adresse \n6) Stilling \n7) Kodeord");
        int choice = intScan.nextInt();
        switch (choice)
        {
            case 1:
                staff.setFirstname(help.validateStuff("fornavn", "Hint: Store forbogstaver", help.nameRegex));
                System.out.println("Fornavn er aendret \n");
                break;
            case 2:
                staff.setLastname(help.validateStuff("efternavn", "Hint: Store forbogstaver", help.nameRegex));
                System.out.println("Efternavn er aendret \n");
                break;
            case 3:
                staff.setEmail(help.validateStuff("email", "Hint: abc@hotmail.com", help.emailRegex));
                System.out.println("Email er aendret \n");
                break;
            case 4:
                staff.setPhone(Integer.parseInt(help.validateStuff("telefon", "Hint: 12345678", help.numberRegex)));
                System.out.println("Telefonnummer er aendret \n");
                break;
            case 5:
                staff.setAddress("Venligst indtast adresse" + stringScan.nextLine());
                System.out.println("Adressen er aendret \n");
                break;
            case 6:
                staff.setRole("Venligst indtast stilling" + stringScan.nextLine());
                System.out.println("Stilling er aendret \n");
                break;
            case 7:
                staff.setPassword(help.validateStuff("password", "Hint: Stort, småt, tal og tegn", help.passRegex));
                System.out.println("Kodeord er aendret \n");
                break;
            default:
                System.out.println("Aint gonna happen");
                break;
        }
        staff.staffFileWriter(Staff.staffArray);
    }
}