package StaffMembers;

import Members.Child;
import Members.Parent;
import Organising.Checked;
import Organising.Schedule;
import Organising.Waitlist;

import java.nio.charset.CharacterCodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Options
{
    Scanner scanner = new Scanner(System.in);
    Scanner s = new Scanner(System.in);
    Parent parent = new Parent();
    Child child = new Child();
    Staff staff = new Staff();
    Schedule schedule = new Schedule();
    Waitlist waitlist = new Waitlist();

    String birthdayRegex = "^(0[1-9]|[12][0-9]|3[01])[/](0[1-9]|1[012])[-](19|20)\\d\\d$"; // dd/MM-yyyy
    String shiftTimeRegex = "(1[0-9]|[1-9]|2[0-4])-(1[0-9]|[1-9]|2[0-4])"; // 7-12
    String nameRegex = "[A-ZÆØÅ][a-zæøå-]{0,25}"; // stort forbogs. og bindestreg
    String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"; //Skal starte med bogstav, indeholde @, og slutte med "." og 2 - 6 bogstaver
    String numberRegex = "([0-9]{8})";
    String dateRegex = "([0-9]{2})";
    String accountRegex = "([0-9]{14})";
    String idRegex = "([0-9])";
    String passRegex = "[a-zA-ZÆØÅæøå0-9!?]{8,16}";

    public String validateStuff(String attribute, String hint, String regex) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Venligst indtast " + attribute);
            String k = scanner.nextLine();
            if (k.matches(regex)) {
                //Hvis k overholder regex, then return k.
                return k;
            }
            System.out.println(hint);
        }
    }

    public void editParent(int parentId) {
        int id = -1;
        if (parentId == -1) {
            System.out.println("Lad os ændre en forældre! \nIndtast ID på forældre, der skal ændres");
            id = scanner.nextInt();
        } else{
            id = parentId;
        }
        Parent parent = getParent(id);
        System.out.println("Hvilken info skal ændres? \n1) Fornavn \n2) Efternavn \n3) Forældre ID \n3 Email \n4) Telefonnummer \5) Kontonummer \6) Adresse \7) Password");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                parent.setFirstname(validateStuff("fornavn på forældre", "Hint: Store forbogstaver", nameRegex));
                parent.parentFileWriter(Parent.parentArray);
                System.out.println("Fornavn er ændret!");
                break;
            case 2:
                parent.setLastname(validateStuff("efternavn på forældre", "Hint: Store forbogstaver", nameRegex));
                parent.parentFileWriter(Parent.parentArray);
                System.out.println("Efternavn er ændret!");
                break;
            case 3:
                parent.setEmail(validateStuff("e-mail","Hint: eksempel@gmail.com", emailRegex));
                parent.parentFileWriter(Parent.parentArray);
                System.out.println("Email er ændret");
                break;
            case 4:
                parent.setPhone(Integer.parseInt(validateStuff("telefon", "Hint: 91827384", numberRegex)));
                parent.parentFileWriter(Parent.parentArray);
                System.out.println("Telefonnummer er ændret");
                break;
            case 5:
                parent.setAccount(validateStuff("konto nr.", "Hint: 12345678901234", accountRegex));
                parent.parentFileWriter(Parent.parentArray);
                System.out.println("Kontonummer er ændret");
                break;
            case 6:
                System.out.println("Venligst indtast adresse");
                parent.setAddress(s.nextLine());
            default:
                System.out.println("Mærkeligt input mand");
                break;
        }
    }

    public void abortParent() {

        System.out.println("Indtast ID der skal slettes");
        int parentId = scanner.nextInt();
        int parenti = getIndexParent(parentId, Parent.parentArray);
        Child.childArray.removeIf(child -> child.getParentId() == parentId);
        Parent.parentArray.remove(parenti);

        child.childFileWriter(Child.childArray);
        parent.parentFileWriter(Parent.parentArray);
        System.out.println("YOU AND YOUR KID HAS BEEN ABO--- DELETED");
    }

    public void createParent() {
        System.out.println("Lad os oprette dig som forældre! Woooo");
        Parent newParent = new Parent();
        Child newChild = new Child();

        newParent.setId(Parent.parentArray.size());
        newParent.setFirstname(validateStuff("Dit fornavn", "Hint: Store forbogstaver", nameRegex));
        newParent.setLastname(validateStuff("Dit efternavn", "Hint: Store forbogstaver", nameRegex));
        newParent.setEmail(validateStuff("e-mail", "Hint: eksempel@gmail.com", emailRegex));
        newParent.setPhone(Integer.parseInt(validateStuff("telefon", "Hint: 91827384", numberRegex)));
        newParent.setAccount(validateStuff("konto nr.", "Hint: 12345678901234", accountRegex));
        System.out.println("Venligst indtast adresse");
        newParent.setAddress(s.nextLine());
        newParent.setRole("Parent");
        Parent.parentArray.add(newParent);

        newChild.setId(Child.childArray.size());
        newChild.setBirthdate(validateStuff("fødselsdato","Hint: dd/MM-yyyy", birthdayRegex));
        newChild.setFirstname(validateStuff("fornavn på barn", "Hint: Store forbogstaver", nameRegex));
        newChild.setLastname(validateStuff("efternavn på barn", "Hint: Store forbogstaver", nameRegex));
        newChild.setParentId(newParent.getId());
        Child.childArray.add(newChild);

        parent.parentFileWriter(Parent.parentArray);
        child.childFileWriter(Child.childArray);
        System.out.println("Du er blevet oprettet! Tillykke!");
    }

    public void createShift(String monthString) {
        while (true) {
            // TODO: 22-03-2020 Opret mere end én vagt på en gang?
            Schedule newShift = new Schedule();
            Date date = new Date();
            SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

            newShift.setId(Integer.parseInt(validateStuff("medarbejderens ID", "Hint: kun tal", idRegex)));
            String day = validateStuff("dato for vagten", "Hint: dd", dateRegex);
            String year = yearFormat.format(date);
            newShift.setDate(day + "/" + monthString + "-" + year);
            newShift.setTime(validateStuff("tidspunkt for vagten", "Ex. 7-12, 7-17", shiftTimeRegex));
            newShift.setHours(Integer.parseInt(newShift.getTime().split("-")[1]) - Integer.parseInt(newShift.getTime().split("-")[0]));
            newShift.setBreakTime(Integer.parseInt(validateStuff("pause længde i minutter", "Hint: Kun tal", dateRegex)));
            Schedule.scheduleArray.add(newShift);
            schedule.scheduleFileWriter(Schedule.scheduleArray);
            System.out.println("Vagten er oprettet. Vil du oprette flere vagter? \n1) Ja 2) Nej");
            int choice = scanner.nextInt();
            if (choice == 2){
                break;
            }
        }
    }

    public String getEmptyTimetable(String currentMonth) {
        boolean foundEmpty;
        int monthCount;

        while (true) {
            foundEmpty = true;
            for (int i = 0; i < Schedule.scheduleArray.size(); i++) {
                if (currentMonth.equalsIgnoreCase(Schedule.scheduleArray.get(i).getDate().split("/")[1].split("-")[0])) {
                    foundEmpty = false;
                    monthCount = Integer.parseInt(currentMonth);
                    monthCount++;
                    if (monthCount < 10) {
                        currentMonth = "0" + monthCount;
                    }
                    break;
                }
            }
            if (foundEmpty)
                return currentMonth;
            }
        }

    public void viewTimetable(String month) {
        String temp = "";

        for (Schedule schedule: Schedule.scheduleArray) {
            String monthi = splitMe(schedule, false);
            if (monthi.equalsIgnoreCase(month)){
                Staff staff = getStaff(schedule.getId());
                if(!temp.equalsIgnoreCase(splitMe(schedule, true))){
                    System.out.println("\n");
                    System.out.println("Dato : " + schedule.getDate());
                }
                System.out.println(staff.getFirstname());
                System.out.println("Tid: " +  schedule.getTime());
                temp = splitMe(schedule, true);
            }
        }
        System.out.println("Vil du oprette en ny vagt? \n 1) Ja 2) Nej");
        int choice = scanner.nextInt();
        if (choice == 1){
            createShift(month);
        }
    }

    public String splitMe(Schedule schedule, boolean isDay)
    {
        if (!isDay)
        {
            return schedule.getDate().split("/")[1].split("-")[0];
        }
        return schedule.getDate().split("/")[0];
    }

    public void checkChild ()
    {
        System.out.println("Venligst indtast Barnets ID: ");
        Checked checked = new Checked();
        Date date = new Date();
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM-yyyy");

        int childId = scanner.nextInt();
        Child child = getChild(childId);
        checked.setId(Checked.checkedKidsArray.size());
        checked.setChildId(childId);
        checked.setCheckIn(hourFormat.format(date));
        checked.setDate(dateFormat.format(date));
        checked.setAllHours("0");
        checked.setCheckOut("0");

        Checked.checkedKidsArray.add(checked);
        checked.checkedFileWriter(Checked.checkedKidsArray);
        System.out.println(child.getFirstname() + " er indtjekket");
    }

    public void checkOutChild()
    {
        Date date = new Date();
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
        System.out.println("Venligst indtast barnets ID");
        int childId = scanner.nextInt();

        Checked checked = getCheckedChild(childId);
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
        System.out.println("Tjek ud er blevet oprettet.");
    }

    public Checked getCheckedChild(int id){
        for (Checked checked : Checked.checkedKidsArray) {
            if (id == checked.getChildId()){
                return checked;
            }
        }
        return null;
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
        newStaff.setFirstname(validateStuff("fornavn", "Hint: Store forbogstaver", nameRegex));
        newStaff.setLastname(validateStuff("efternavn", "Hint: Store forbogstaver", nameRegex));
        newStaff.setEmail(validateStuff("email", "Hint: hej@hotmail.com", emailRegex));
        newStaff.setPhone(Integer.parseInt(validateStuff("telefon", "Hint: 12345678", numberRegex)));
        System.out.println("Venligst indtast adresse");
        newStaff.setAddress(s.nextLine());
        newStaff.setRole("Staff");
        System.out.println("Venligst indtast password");
        newStaff.setPassword(s.nextLine());
        Staff.staffArray.add(newStaff);
        staff.staffFileWriter(Staff.staffArray);
        System.out.println("Din medarbejder er blevet oprettet, tillykke");
    }

    public void deleteStaff()
    {
        System.out.println("Indtast ID på medarbejder der skal slettes");
        int id = scanner.nextInt();
        int staffIndex = getIndexStaff(id, Staff.staffArray);
        Staff.staffArray.remove(staffIndex);
        staff.staffFileWriter(Staff.staffArray);
        System.out.println("Du har nu slettet en medarbejder");
    }

    public void editStaff()
    {
        System.out.println("Du vil aendre en medarbejders oplysninger -> Indtast medarbejder ID");
        int id = scanner.nextInt();
        Staff staff = getStaff(id);
        System.out.println("Her er dine valgmuligheder \n1) Fornavn \n2) Efternavn \n3) Email \n4) " +
                "Telefon \n5) Adresse \n6) Stilling \n7) Kodeord");
        int choice = scanner.nextInt();
        switch (choice)
        {
            case 1:
                staff.setFirstname(validateStuff("fornavn", "Hint: Store forbogstaver", nameRegex));
                staff.staffFileWriter(Staff.staffArray);
                System.out.println("Fornavn er aendret \n");
                break;
            case 2:
                staff.setLastname(validateStuff("efternavn", "Hint: Store forbogstaver", nameRegex));
                staff.staffFileWriter(Staff.staffArray);
                System.out.println("Efternavn er aendret \n");
                break;
            case 3:
                staff.setEmail(validateStuff("email", "Hint: abc@hotmail.com", emailRegex));
                staff.staffFileWriter(Staff.staffArray);
                System.out.println("Email er aendret \n");
                break;
            case 4:
                staff.setPhone(Integer.parseInt(validateStuff("telefon", "Hint: 12345678", numberRegex)));
                staff.staffFileWriter(Staff.staffArray);
                System.out.println("Telefonnummer er aendret \n");
                break;
            case 5:
                staff.setAddress("Venligst indtast adresse" + s.nextLine());
                staff.staffFileWriter(Staff.staffArray);
                System.out.println("Adressen er aendret \n");
                break;
            case 6:
                staff.setRole("Venligst indtast stilling" + s.nextLine());
                staff.staffFileWriter(Staff.staffArray);
                System.out.println("Stilling er aendret \n");
                break;
            case 7:
                staff.setPassword(validateStuff("password", "Hint: Stort, småt, tal og tegn", passRegex));
                staff.staffFileWriter(Staff.staffArray);
                System.out.println("Kodeord er aendret \n");
                break;
            default:
                System.out.println("Aint gonna happen");
                break;
        }
    }


    public void editChild()
    {
        System.out.println("Lad os ændre et barn! \nIndtast ID på barn, der skal ændres");
        int id = scanner.nextInt();
        Child child = getChild(id);
        System.out.println("Hvilken info skal ændres? \n1) Fornavn \n2) Efternavn \n3) Forældre ID");
        int choice = scanner.nextInt();
        switch (choice)
        {
            case 1:
                child.setFirstname(validateStuff("fornavn på barn", "Hint: Store forbogstaver", nameRegex));
                child.childFileWriter(Child.childArray);
                System.out.println("Fornavn er ændret!");
                break;
            case 2:
                child.setLastname(validateStuff("efternavn på barn", "Hint: Store forbogstaver", nameRegex));
                child.childFileWriter(Child.childArray);
                System.out.println("Efternavn er ændret!");
                break;
            case 3:
                child.setParentId(Integer.parseInt(validateStuff("forældre ID", "Hint: Kun tal", idRegex)));
                child.childFileWriter(Child.childArray);
                System.out.println("Forældre ID er rettet!");
                break;
            default:
                System.out.println("Mærkeligt input mand");
                break;
        }
    }

    public void abortChild()
    {
        System.out.println("Indtast ID på barn der skal slettes");
        int id = scanner.nextInt();
        int parentId = -1;
        for (int i = 0; i < Child.childArray.size(); i++)
        {
            if (Child.childArray.get(i).getId() == id)
            {
                parentId = Child.childArray.get(i).getParentId();
            }
        }
        Child.childArray.remove(id);
        Parent.parentArray.remove(parentId);
        child.childFileWriter(Child.childArray);
        parent.parentFileWriter(Parent.parentArray);
        System.out.println("YOUR KID HAS BEEN ABO--- DELETED");
    }
    public void abortChildFromWaitlist()
    {
        System.out.println("Indtast ID på barn der skal slettes");
        int id = scanner.nextInt();
        int childId = -1;
        for (int i = 0; i < Waitlist.waitlistArray.size(); i++)
        {
            if (Waitlist.waitlistArray.get(i).getId() == id)
            {
                childId = Waitlist.waitlistArray.get(i).getId();
            }
        }
        int childIndex = getIndexChild(childId, Child.childArray);
        Waitlist.waitlistArray.remove(childIndex);
        waitlist.waitlistFileWriter(Waitlist.waitlistArray);
        System.out.println("THE KID HAS BEEN ABO--- DELETED");
    }

    public void createChild()
    {
        System.out.println("Lad os oprette et barn! Woooo");
        //Nye objekter
        Child newChild = new Child();
        Parent newParent = new Parent();
        //New parents
        newParent.setId(Parent.parentArray.size());
        newParent.setFirstname(validateStuff("fornavn på forældre", "Hint: Store forbogstaver", nameRegex));
        newParent.setLastname(validateStuff("efternavn på forældre", "Hint: Store forbogstaver", nameRegex));
        newParent.setEmail(validateStuff("e-mail", "Hint: eksempel@gmail.com", emailRegex));
        newParent.setPhone(Integer.parseInt(validateStuff("telefon", "Hint: 91827384", numberRegex)));
        newParent.setAccount(validateStuff("konto nr.", "Hint: 12345678901234", accountRegex));
        System.out.println("Venligst indtast adresse");
        newParent.setAddress(s.nextLine());
        newParent.setRole("Parent");
        Parent.parentArray.add(newParent);
        //New child
        newChild.setId(Child.childArray.size());
        newChild.setBirthdate(validateStuff("fødselsdato", "Hint: dd/MM-yyyy", birthdayRegex));
        newChild.setFirstname(validateStuff("fornavn på barn", "Hint: Store forbogstaver", nameRegex));
        newChild.setLastname(validateStuff("efternavn på barn", "Hint: Store forbogstaver", nameRegex));
        newChild.setParentId(newParent.getId());
        Child.childArray.add(newChild);

        parent.parentFileWriter(Parent.parentArray);
        child.childFileWriter(Child.childArray);
        System.out.println("Barn er blevet oprettet! Tillykke!");
    }

    public void createChildWaitlist()
    {
        System.out.println("Lad os oprette et barn på ventelisten");
        Waitlist newWaitlist = new Waitlist();

        //Add parent
        newWaitlist.setParentFirstname(validateStuff("fornavn på forældre", "Hint: Store forbogstaver", nameRegex));
        newWaitlist.setParentLastname(validateStuff("efternavn på forældre", "Hint: Store forbogstaver", nameRegex));
        newWaitlist.setEmail(validateStuff("e-mail", "Hint: eksempel@gmail.com", emailRegex));
        newWaitlist.setPhone(Integer.parseInt(validateStuff("telefon", "Hint: 91827384", numberRegex)));
        System.out.println("Venligst indtast adresse");
        newWaitlist.setAddress(s.nextLine());
        //Add child
        newWaitlist.setId(Waitlist.waitlistArray.size());
        newWaitlist.setBirthdate(validateStuff("fødselsdato på barn", "Hint: dd/MM-yyyy", birthdayRegex));
        newWaitlist.setChildFirstname((validateStuff("fornavn på barn", "Hint: Store forbogstaver", nameRegex)));
        newWaitlist.setChildLastname(validateStuff("efternavn på barn", "Hint: Store forbogstaver", nameRegex));
        Waitlist.waitlistArray.add(newWaitlist);

        waitlist.waitlistFileWriter(Waitlist.waitlistArray);

        System.out.println("Barn er blevet oprettet på venteliste!");
    }

    public void getWaitlist()
    {
        for (int i = 0; i < Waitlist.waitlistArray.size(); i++)
        {
            System.out.println("Barnets ID: " + Waitlist.waitlistArray.get(i).getId());
            System.out.println("Barnets navn: " + Waitlist.waitlistArray.get(i).getChildFirstname() +
                    " " + Waitlist.waitlistArray.get(i).getChildLastname());
            System.out.println("Foedsels dato (DD/MM-YYYY): " + Waitlist.waitlistArray.get(i).getBirthdate());
            System.out.println("-------------------------------------------------");
            System.out.println("Foraeldre navn: " + Waitlist.waitlistArray.get(i).getParentFirstname() +
                    " " + Waitlist.waitlistArray.get(i).getParentLastname());
            System.out.println(" Tlf.: " + Waitlist.waitlistArray.get(i).getPhone() +
                    " Adresse: " + Waitlist.waitlistArray.get(i).getAddress());
            System.out.println("E-mail: " + Waitlist.waitlistArray.get(i).getEmail());
            System.out.println();
        }
    }

    public Parent getParent(int parentId)
    {
        for (int i = 0; i < Parent.parentArray.size(); i++)
        {
            if (Parent.parentArray.get(i).getId() == parentId)
            {
                return Parent.parentArray.get(i);
            }
        }
        return null;
    }

    public Child getChild(int id)
    {
        for (int i = 0; i < Child.childArray.size(); i++)
        {
            if (Child.childArray.get(i).getId() == id)
            {
                return Child.childArray.get(i);
            }
        }
        return null;
    }

    public Staff getStaff(int id)
    {
        for (int i = 0; i < Staff.staffArray.size(); i++)
        {
            if (Staff.staffArray.get(i).getId() == id)
            {
                return Staff.staffArray.get(i);
            }
        }
        return null;
    }

    public int getIndexChild(int id, ArrayList<Child> array){
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }

    public int getIndexParent(int id, ArrayList<Parent> array){
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }

    public int getIndexStaff(int id, ArrayList<Staff> array){
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }
}