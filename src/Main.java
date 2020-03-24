import java.text.ParseException;

public class Main
{
    public static void main(String[] args) throws ParseException {

        //Nyt push kl 15.00

        Controller controller = new Controller();
        Menu menu = new Menu();

        controller.initArrays();
        menu.menu();
    }
}
