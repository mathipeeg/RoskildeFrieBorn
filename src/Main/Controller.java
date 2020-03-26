package Main;

import Main.Models.Child;
import Main.Models.Parent;
import Main.Models.Checked;
import Main.Tools.HelpingMethods;
import Main.Models.Schedule;
import Main.Models.Waitlist;
import Main.Models.Updates;
import Main.Models.Staff;

public class Controller {

    public void initArrays() {
        Child child = new Child();
        Parent parent = new Parent();
        Staff staff = new Staff();
        Waitlist waitlist = new Waitlist();
        Schedule schedule = new Schedule();
        Checked checked = new Checked();
        Updates updates = new Updates();
        HelpingMethods help = new HelpingMethods();

        Child.childArray = child.initialiseInfo();
        Parent.parentArray = parent.initialiseInfo();
        Staff.staffArray = staff.initialiseInfo();
        Waitlist.waitlistArray = waitlist.initialiseInfo();
        Schedule.scheduleArray = schedule.initialiseInfo();
        Checked.checkedKidsArray = checked.initialiseInfo();
        Updates.updatesArray = updates.initialiseInfo();

        //Tjekker om det er en ny dag, og wiper checkedKids array hvis det er.
        help.wipeArray();
    }
}
