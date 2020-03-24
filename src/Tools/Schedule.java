package Tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Schedule
{
    public static ArrayList<Schedule> scheduleArray = new ArrayList<>();
    private int id;
    private String date;
    private String time;
    private int hours;
    private int breakTime;

    public Schedule(int id, String date, String time, int hours, int breakTime)
    {
        this.id = id;
        this.date = date;
        this.time = time;
        this.hours = hours;
        this.breakTime = breakTime;
    }

    public Schedule()
    {
    }

    public static ArrayList<Schedule> getScheduleArray()
    {
        return scheduleArray;
    }

    public static void setScheduleArray(ArrayList<Schedule> scheduleArray)
    {
        Schedule.scheduleArray = scheduleArray;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public int getHours()
    {
        return hours;
    }

    public void setHours(int hours)
    {
        this.hours = hours;
    }

    public int getBreakTime()
    {
        return breakTime;
    }

    public void setBreakTime(int breakTime)
    {
        this.breakTime = breakTime;
    }

    public ArrayList<Schedule> initialiseInfo()
    {
        Scanner input = null;
        try
        {
            input = new Scanner(new File("src/Organising/Schedule"));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        int id = -1;
        String date = "";
        String time = "";
        int hours = -1;
        int breakTime = -1;

        while (input.hasNextLine())
        {
            String[] lineArray = input.nextLine().split(": ");

            if (lineArray[0].equalsIgnoreCase("Medarbejder ID")) id = Integer.parseInt(lineArray[1]);
            if (lineArray[0].equalsIgnoreCase("Dato")) date = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("Tid")) time = lineArray[1];
            if (lineArray[0].equalsIgnoreCase("Timer")) hours = Integer.parseInt(lineArray[1]);
            if (lineArray[0].equalsIgnoreCase("Pause (min)")) breakTime = Integer.parseInt(lineArray[1]);

            if (id != -1 && !date.equalsIgnoreCase("") && !time.equalsIgnoreCase("")
                    && hours != -1 && breakTime != -1){
                Schedule schedule = new Schedule(id, date, time, hours, breakTime);
                scheduleArray.add(schedule);

                id = -1;
                date = "";
                time = "";
                hours = -1;
                breakTime = -1;
            }
        }
        return scheduleArray;
    }

    public void scheduleFileWriter(ArrayList<Schedule> scheduleArray){
        FileWriter fileWriter;
        try
        {
            fileWriter = new FileWriter("src/Organising/Schedule");
            writeScheduleInfo(fileWriter, scheduleArray);
            fileWriter.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void writeScheduleInfo(FileWriter fileWriter, ArrayList<Schedule> scheduleArray){
        for (int i = 0; i < scheduleArray.size(); i++){
            try
            {
                fileWriter.write("Medarbejder ID: " + scheduleArray.get(i).getId() + "\n");
                fileWriter.write("Dato: " + scheduleArray.get(i).getDate() + "\n");
                fileWriter.write("Tid: " + scheduleArray.get(i).getTime() + "\n");
                fileWriter.write("Timer: " + scheduleArray.get(i).getHours() + "\n");
                fileWriter.write("Pause (min): " + scheduleArray.get(i).getBreakTime() + "\n\n");
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void printSchedule(Schedule schedule){
        System.out.println("Schedule id: " + schedule.getId());
        System.out.println("Schedule date: " + schedule.getDate());
        System.out.println("Schedule time: " + schedule.getTime());
        System.out.println("Schedule hours: " + schedule.getHours());
    }
}
