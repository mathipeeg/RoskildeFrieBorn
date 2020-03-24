package Organising;

import Members.Child;
import Members.Parent;
import StaffMembers.Staff;

import java.util.ArrayList;

public class GetMethods {

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

    public News getNews (int id)
    {
        for (int i = 0; i < News.newsArray.size(); i++)
        {
            if (News.newsArray.get(i).getId() == id)
            {
                return News.newsArray.get(i);
            }
        }
        return null;
    }

    public int getIndexNews(int id, ArrayList<News> array){
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }

}
