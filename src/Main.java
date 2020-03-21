import Members.Child;
import Members.Parent;
import Organising.Schedule;
import Organising.Waitlist;
import StaffMembers.Staff;

public class Main
{
    public static void main(String[] args)
    {
        //Grands push!
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
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM-yyyy");
//        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
//        System.out.println("date: " + simpleDateFormat.format(date));
//        System.out.println("Month? " + monthFormat.format(date));
        menu.menu();
    }
}
