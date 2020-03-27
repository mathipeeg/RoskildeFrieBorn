package Main.Tools;

import Main.Models.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ScheduleOptions {

    HelpingMethods help = new HelpingMethods();
    Schedule schedule = new Schedule();

    Scanner intScan = new Scanner(System.in);

    public void createShift(String monthString) {
        while (true) {
            String day = "";
            Schedule newShift = new Schedule();
            Date date = new Date();
            SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
            String year = yearFormat.format(date);
            int memberId = (Integer.parseInt(help.validateStuff("medarbejderens ID", "Hint: kun tal", help.idRegex)));
            System.out.println("Vil du oprette flere identiske vagter over flere datoer? \n1) Ja 2) Nej");
            int choice = intScan.nextInt();
            if (choice == 1){
                createMultipleShifts(newShift, day, memberId, monthString, year);
            } else if (choice == 2) {
                newShift.setId(memberId);
                day = help.validateStuff("dato for vagten", "Hint: dd", help.dateRegex);
                newShift.setDate(day + "/" + monthString + "-" + year);
                newShift.setTime(help.validateStuff("tidspunkt for vagten", "Ex. 7-12, 7-17", help.shiftTimeRegex));
                newShift.setHours(Integer.parseInt(newShift.getTime().split("-")[1]) - Integer.parseInt(newShift.getTime().split("-")[0]));
                newShift.setBreakTime(Integer.parseInt(help.validateStuff("pause længde i minutter", "Hint: Kun tal", help.dateRegex)));
                Schedule.scheduleArray.add(newShift);
            }
            choice = 0;
            System.out.println("Vagten er oprettet. Vil du oprette flere vagter? \n1) Ja 2) Nej");
            choice = intScan.nextInt();
            if (choice == 2){
                break;
            }
        }
        schedule.scheduleFileWriter(Schedule.scheduleArray);
    }

    public void createMultipleShifts(Schedule newShift, String day, int memberId, String monthString, String year){
        String[] shift = help.validateStuff("dag og tid (dd, (t)t-tt)", "Hint: ex. 12, 7-17", help.shiftRegex, true).split(", ");
        System.out.println("Hej breaktime pls, <TAL>");
        for (int i = 0; i < shift.length; i+=2) {
            newShift = new Schedule();
            System.out.println(shift[i] + " " + shift[i+1]);
            day = shift[i];
            newShift.setId(memberId);
            newShift.setDate(day + "/" + monthString + "-" + year);
            newShift.setTime(shift[i+1]);
            newShift.setHours(Integer.parseInt(shift[i+1].split("-")[1]) - Integer.parseInt(shift[i+1].split("-")[0]));
            newShift.setBreakTime(Integer.parseInt(help.validateStuff("pause længde i minutter", "Hint: Kun tal", help.dateRegex)));
            Schedule.printSchedule(newShift);
            Schedule.scheduleArray.add(newShift);
        }
    }

    public String getEmptyTimetable(String currentMonth) {
        boolean foundEmpty;
        int monthCount;

        while (true) {
            foundEmpty = true;
            for (int i = 0; i < Schedule.scheduleArray.size(); i++) {
                if (currentMonth.equalsIgnoreCase(splitMe(Schedule.scheduleArray.get(i), false))) {
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
                Staff staff = help.getStaff(schedule.getId());
                if(!temp.equalsIgnoreCase(splitMe(schedule, true))){
                    System.out.println("\nDato : " + schedule.getDate());
                }
                System.out.println(staff.getFirstname());
                System.out.println("Tid: " +  schedule.getTime());
                temp = splitMe(schedule, true);
            }
        }
        System.out.println("\nVil du oprette en ny vagt? \n 1) Ja 2) Nej");
        int choice = intScan.nextInt();
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
}
