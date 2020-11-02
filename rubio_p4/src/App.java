import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        ArrayList<TaskList> tasklist = new ArrayList<>();
        TaskList t = new TaskList();
        TaskItem ti = new TaskItem();
        t.addTask(ti);
        System.out.println(t);
        MainMenu();
    }
    public static void MainMenu(){
        printMainMenu();
        switch(getInput(1 , 3)){
            case 1:
                //create new task list by adding to arraylist
                break;
            case 2:
                //load existing tasklist
                break;
            case 3:
                System.out.println("Goodbye, thank you for using Nestor's To-Do list app.");
                return;
        }
    }
    public static int getInput(int low, int high) {
        Scanner scan = new Scanner(System.in);
        int ret = -1;
        try {
            while (ret < low || ret > high) {
                System.out.print("Please enter a valid number between " + low +
                        " and " + high + ": ");
                ret = scan.nextInt();
            }
        } catch (Exception e) {
            System.out.println("Your input needs to be a valid " +
            "integer between " + low + " and " + high);
            ret = getInput(low, high);
        }
        return ret;

    }
    public static void printMainMenu(){
        System.out.println("Main Menu:\n" +
                           "_________");
        System.out.println("1) create a new list");
        System.out.println("2) load an existing list");
        System.out.println("3) quit");
    }
}