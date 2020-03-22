import Members.Child;
import Members.Parent;
import Organising.Schedule;
import Organising.Waitlist;
import StaffMembers.Staff;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main
{
    public static void main(String[] args)
    {
        Child child = new Child();
        Parent parent = new Parent();
        Staff staff = new Staff();
        Waitlist waitlist = new Waitlist();
        Schedule schedule = new Schedule();
        Menu menu = new Menu();

        Child.childArray = child.initialiseInfo();
        Parent.parentArray = parent.initialiseInfo();
        Staff.staffArray = staff.initialiseInfo();
        Waitlist.waitlistArray = waitlist.initialiseInfo();
        Schedule.scheduleArray = schedule.initialiseInfo();

//        Date date = new Date();
//        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
//        System.out.println("hour: " + hourFormat.format(date));
        menu.menu();
    }
}
