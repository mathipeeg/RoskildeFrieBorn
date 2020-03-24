import Members.Child;
import Members.Parent;
import News.News;
import Tools.*;
import StaffMembers.Staff;

public class Controller {

    public void initArrays() {
        Child child = new Child();
        Parent parent = new Parent();
        Staff staff = new Staff();
        Waitlist waitlist = new Waitlist();
        Schedule schedule = new Schedule();
        Checked checked = new Checked();
        News news = new  News();
        HelpingMethods help = new HelpingMethods();

        Child.childArray = child.initialiseInfo();
        Parent.parentArray = parent.initialiseInfo();
        Staff.staffArray = staff.initialiseInfo();
        Waitlist.waitlistArray = waitlist.initialiseInfo();
        Schedule.scheduleArray = schedule.initialiseInfo();
        Checked.checkedKidsArray = checked.initialiseInfo();
        News.newsArray = news.initialiseInfo();

        //Tjekker om det er en ny dag, og wiper checkedKids array hvis det er.
        help.wipeArray();
    }
}
