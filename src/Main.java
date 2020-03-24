import Members.Child;
import Members.Parent;
import Organising.Checked;
import Organising.Schedule;
import Organising.Waitlist;
import StaffMembers.AdminOptions;
import StaffMembers.Staff;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main
{
    public static void main(String[] args) throws ParseException {
        Child child = new Child();
        Parent parent = new Parent();
        Staff staff = new Staff();
        Waitlist waitlist = new Waitlist();
        Schedule schedule = new Schedule();
        Menu menu = new Menu();
        Checked checked = new Checked();
        AdminOptions adminOptions = new AdminOptions();
        Child.childArray = child.initialiseInfo();
        Parent.parentArray = parent.initialiseInfo();
        Staff.staffArray = staff.initialiseInfo();
        Waitlist.waitlistArray = waitlist.initialiseInfo();
        Schedule.scheduleArray = schedule.initialiseInfo();
        Checked.checkedKidsArray = checked.initialiseInfo();
        adminOptions.wipeArray();
//        Date date = new Date();
//        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
//        System.out.println("hour: " + hourFormat.format(date));
        menu.menu();
    }
}
