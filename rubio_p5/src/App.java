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
            }else{
                c.run();
            }
            choice = getUserChoice(1,3);
        }
        System.out.println("Thanks for using the app!");
    }
    private static void printMainMenu(){
        System.out.println("Main Menu\n" +
                "_________");
        System.out.println("1) task list\n" +
                "2) contact list\n" +
                "3) quit");
    }
    private static int getUserChoice(int low, int high){
        printMainMenu();
        System.out.print("> ");
        int choice;
        try{
            choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
        }catch(Exception e){
            return getUserChoice(low,high);
        }
    }
}