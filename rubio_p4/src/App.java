import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private ArrayList<TaskList> tasklist = new ArrayList<>();

    public static void main(String[] args){
        MainMenu();
    }
    public static void MainMenu(){
        printMainMenu();
        int input = getInput(1,3);
        while(input != 3) {
            if (input == 1) {
                //create new task list by adding to arraylist
                //addNewList();
                listOperationMenu();
            }
            if (input == 2) {
                //load existing tasklist
                //enter filename to load->tasks.txt
                //if successful, task file has been loaded
                listOperationMenu();
            }
            printMainMenu();
            input = getInput(1,3);
        }
        System.out.println("Goodbye, thank you for using Nestor's To-Do list app.");
    }
    public static void listOperationMenu(){
        printListOperationMenu();
        int input = getInput(1,8);
        while(input != 8){
            if(input == 1){
                //view list
                //print list
            }
            if(input == 2){
                //add item to list
                //list->add
            }
            if(input == 3){
                //list->find item->edit
            }
            if(input == 4){
                //list->find item->remove
            }
            if(input == 5){
                //mark item as completed
            }
            if(input == 6){
                //unmark as completed
            }
            if(input == 7){
                //save current list
                //update list
            }
            if(input != 8){
                input = getInput(1,8);
            }
        }
        //back to main menu
    }
    public static void printListOperationMenu(){
        System.out.println("List Operation Menu\n" +
                           "_________");
        System.out.println("1) view the list\n" +
                           "2) add an item\n" +
                           "3) edit an item\n" +
                           "4) remove an item\n" +
                           "5) mark an item as completed\n" +
                           "6) unmark an item as completed\n" +
                           "7) save the current list\n" +
                           "8) quit to the main menu\n");
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