import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        openMainMenu();
    }

    private static void openMainMenu(){
        ContactApp c = new ContactApp();
        TaskApp t = new TaskApp();
        int choice  = getUserChoice(1,3);
        while(choice != 3){
            if(choice == 1){
                t.run();
            }
            if(choice == 2){
                c.run();
            }
            if(choice != 3){
                choice = getUserChoice(1,3);
            }
        }
        System.out.println("Thanks for using the app!");
    }
    private static void printMainMenu(){
        System.out.println("Select Your Application\n" +
                           "_______________________");
        System.out.println("1) task list\n" +
                "2) contact list\n" +
                "3) quit");
    }
    private static int getUserChoice(int low, int high){
        printMainMenu();
        int ret = -1;
        scanner = new Scanner(System.in);
        try {
            while (ret < low || ret > high) {
                System.out.print("Please enter a valid number between " + low +
                        " and " + high + ": ");
                ret = scanner.nextInt();
            }
        } catch (Exception e) {
            System.out.println("Your input needs to be a valid " +
                    "integer between " + low + " and " + high + "\n");
            ret = getUserChoice(low, high);
        }
        return ret;
    }
}