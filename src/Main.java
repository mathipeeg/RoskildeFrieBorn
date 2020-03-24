import Members.Child;
import Members.Parent;
import Organising.Checked;
import Organising.News;
import Organising.Schedule;
import Organising.Waitlist;
import StaffMembers.Options;
import StaffMembers.Staff;

import java.text.ParseException;

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
        News news = new  News();
        Options options = new Options();
        Child.childArray = child.initialiseInfo();
        Parent.parentArray = parent.initialiseInfo();
        Staff.staffArray = staff.initialiseInfo();
        Waitlist.waitlistArray = waitlist.initialiseInfo();
        Schedule.scheduleArray = schedule.initialiseInfo();
        Checked.checkedKidsArray = checked.initialiseInfo();
        options.wipeArray();
        News.newsArray = news.initialiseInfo();
        menu.menu();
    }
}
