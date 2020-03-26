package Main.Models;

public class CurrentUser {

    public int id;
    public String firstname;
    public String lastname;
    public String role;

    public CurrentUser(int id, String firstname, String lastname, String role) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
    }
}
