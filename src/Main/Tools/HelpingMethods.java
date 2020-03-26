package Main.Tools;

import Main.Models.*;
import Main.Models.Updates;

import java.text.*;
import java.util.*;

public class HelpingMethods {

    public String birthdayRegex = "^(0[1-9]|[12][0-9]|3[01])[/](0[1-9]|1[012])[-](19|20)\\d\\d$"; // dd/MM-yyyy
    public String shiftTimeRegex = "(1[0-9]|[1-9]|2[0-4])-(1[0-9]|[1-9]|2[0-4])"; // 7-12
    public String nameRegex = "[A-ZÆØÅ][a-zæøå-]{0,25}"; // stort forbogs. og bindestreg
    public String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"; //Skal starte med bogstav, indeholde @, og slutte med "." og 2 - 6 bogstaver
    public String numberRegex = "([0-9]{8})";
    public String dateRegex = "([0-9]{2})";
    public String accountRegex = "([0-9]{14})";
    public String idRegex = "([0-9])";
    public String passRegex = "[a-zA-ZÆØÅæøå0-9!?]{8,16}";
    public String shiftRegex = "[0-3][0-9], (1[0-9]||[1-9]||2[0-4])-(1[0-9]||[1-9]||2[0-4])";

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


    public Checked getCheckedChild(int id){
        for (Checked checked : Checked.checkedKidsArray) {
            if (id == checked.getChildId()){
                return checked;
            }
        }
        return null;
    }

    public Updates getUpdates(int id)
    {
        for (int i = 0; i < Updates.updatesArray.size(); i++)
        {
            if (Updates.updatesArray.get(i).getId() == id)
            {
                return Updates.updatesArray.get(i);
            }
        }
        return null;
    }

    public Updates getUpdates(String title){
        for (Updates update :
                Updates.updatesArray) {
            if (update.getHeadLine().equalsIgnoreCase(title)) {
                return update;
            }
        }
        return null;
    }

    public int getIndexUpdates(int id, ArrayList<Updates> array){
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }

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

    public String validateStuff(String attribute, String hint, String regex, boolean multiple) {
        Scanner scanner = new Scanner(System.in);
        String returnMe = "";
        String input = "";
        while (!input.equalsIgnoreCase("done")) {
            System.out.println("Venligst indtast " + attribute);
            input = scanner.nextLine();
            if (input.matches(regex)) {
                returnMe += input + ", ";
                continue;
            }
            if(!input.equalsIgnoreCase("done")){
                System.out.println(hint);
            }
        }
        return returnMe.substring(0, returnMe.length() - 2);
    }

    public void wipeArray (){
        Date date = new Date ();
        Date former = null;
        Date current = null;

        if (Checked.checkedKidsArray.size() != 0){
            Checked checked = new Checked();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM-yyyy");
            String currentDate = simpleDateFormat.format(date);

            try {
                current = new SimpleDateFormat("dd/mm-yyyy").parse(currentDate);
                former = new SimpleDateFormat("dd/mm-yyyy").parse(Checked.checkedKidsArray.get(0).getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(former.before(current))
            {
                Checked.checkedKidsArray.clear();
                checked.checkedFileWriter(Checked.checkedKidsArray);
            }
        }
    }

    public boolean checkId(int id, String object){
        if (object.equalsIgnoreCase("child")){
            for (Child child :
                    Child.childArray) {
                if (child.getId() == id) {
                    return true;
                }
            }
        } else if(object.equalsIgnoreCase("parent")) {
            for (Parent parent :
                    Parent.parentArray) {
                if (parent.getId() == id) {
                    return true;
                }
            }
        } else if (object.equalsIgnoreCase("updates")){
            for (Updates updates :
                    Updates.updatesArray) {
                if (updates.getId() == id){
                    return true;
                }
            }
        } else if (object.equalsIgnoreCase("waitlist")) {
            for (Waitlist waitlist :
                    Waitlist.waitlistArray) {
                if (waitlist.getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }
}