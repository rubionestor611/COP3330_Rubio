import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskApp {
    private Scanner scan = new Scanner(System.in);
    public void run(){
        main(new String[1]);
    }
    public void main(String[] args) {
        this.MainMenu();
    }
    private void MainMenu() {
        printMainMenu();
        TaskList tl;
        int input = this.getInput(1, 3);
        while (input != 3) {
            if (input == 1) {
                tl = new TaskList();
                try {
                    System.out.println("New task list created.\n");
                    listOperationMenu(tl);
                } catch (Exception e) {
                    System.out.println("Problem in the list operations menu.\n");
                }
            }
            if (input == 2) {
                //load existing tasklist
                String filename = "";
                try {
                    tl = new TaskList();
                    filename = getFileName();
                    tl.loadList(filename);
                    System.out.println("task list has been loaded");
                    listOperationMenu(tl);
                } catch (FileNotFoundException e) {
                    System.out.println(filename + " does not exist. Tasklist could not be loaded\n");
                } catch (Exception e) {
                    System.out.println("Problem loading list from " + filename + ".\n");
                }

            }
            printMainMenu();
            input = getInput(1, 3);
        }
    }

    private void listOperationMenu(TaskList tl) throws Exception {
        printListOperationMenu();
        int input = getInput(1, 8);
        while (input != 8) {
            if (input == 1) {
                tl.printList();
            }
            if (input == 2) {
                trytoAddtoList(tl);
            }
            if (input == 3) {
                trytoUpdateTask(tl);
            }
            if (input == 4) {
                trytoRemovefromList(tl);
            }
            if (input == 5) {
                trytoMarkTaskasComplete(tl);
            }
            if (input == 6) {
                trytoMarkTaskasIncomplete(tl);
            }
            if (input == 7) {
                if (tl.savetoFile(getNameofFiletoSaveList())) {
                    System.out.println("Task list has been saved.\n");
                } else {
                    System.out.println("Problem saving the tasklist.\n");
                }
            }
            if (input != 8) {
                printListOperationMenu();
                input = getInput(1, 8);
            }
        }
        //back to main menu
    }

    public static void printListOperationMenu() {
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

    private int getInput(int low, int high) {
        int ret = -1;
        try {
            while (ret < low || ret > high) {
                System.out.print("Please enter a valid number between " + low +
                        " and " + high + ": ");
                ret = scan.nextInt();
                scan.nextLine();
            }
        } catch (Exception e) {
            scan.nextLine();
            System.out.println("Your input needs to be a valid " +
                    "integer between " + low + " and " + high);
            ret = getInput(low, high);
        }
        return ret;

    }
    private String getNameofFiletoSaveList(){
        System.out.print("Enter the name of the file you want this list saved under: ");
        String ret = scan.nextLine();
        scan.reset();
        return ret;
    }
    private String getFileName() {
        System.out.print("Enter the file name to load list from: ");
        String ret = scan.nextLine();
        scan.reset();
        return ret;
    }

    public static void printMainMenu() {
        System.out.println("Main Menu:\n" +
                "_________");
        System.out.println("1) create a new list");
        System.out.println("2) load an existing list");
        System.out.println("3) quit");
    }

    private boolean addtoTaskList(TaskList tl) throws IllegalArgumentException, DateTimeException {
        String[] taskInfo = getTaskInfoFromUser();

        if (taskInfo[0].length() == 0) {
            throw new IllegalArgumentException();
        }
        if (!isValidDate(taskInfo[2])) {
            throw new DateTimeException("fail");
        }
        TaskItem t = new TaskItem(taskInfo[0], taskInfo[1], taskInfo[2]);
        tl.addTask(t);
        return true;
    }

    private String[] getTaskInfoFromUser() {
        String[] ret = new String[3];
        System.out.print("Task Title: ");
        ret[0] = scan.nextLine();
        scan.reset();
        System.out.print("Task Description: ");
        ret[1] = scan.nextLine();
        scan.reset();
        System.out.print("Task Due Date (YYYY-MM-DD): ");
        ret[2] = scan.nextLine();
        scan.reset();
        return ret;
    }

    private void unmarkAsComplete(TaskList tl) throws IndexOutOfBoundsException {
        ArrayList<Integer> al = tl.printCompletedReturnCompleteList();
        if (al.size() != 0) {
            System.out.print("Enter the index of the task you wish to mark as incomplete: ");
            int index = scan.nextInt();
            scan.reset();
            if (index < 0 || index > al.size()) {
                throw new IndexOutOfBoundsException();
            }
            int indexinlist = al.get(index);
            tl.getItem(indexinlist).incomplete();
            System.out.println("\nTask marked as incomplete.\n");
        }
    }

    private void markAsComplete(TaskList tl) throws IndexOutOfBoundsException {
        ArrayList<Integer> al = tl.printUncompletedReturnIncompleteList();
        if (al.size() != 0) {
            System.out.print("Enter the index of the task you wish to mark as complete: ");
            int index = scan.nextInt();
            scan.reset();
            if (index < 0 || index > al.size()) {
                throw new IndexOutOfBoundsException();
            }
            int indexinlist = al.get(index);
            tl.getItem(indexinlist).complete();
            System.out.println("\nTask marked as complete.\n");
        }
    }

    private void removefromList(TaskList tl) throws IndexOutOfBoundsException {
        tl.printList();
        if (tl.size() != 0) {
            try{
                System.out.print("Please enter the index of the task you wish to delete: ");
                int index = scan.nextInt();
                scan.reset();
                if (index < 0 || index >= tl.size()) {
                    throw new IndexOutOfBoundsException();
                }
                tl.removeTask(index);
                System.out.println("Task removed");
            }catch(IndexOutOfBoundsException i){
                scan.nextLine();
                System.out.println("Index out of bounds. Please provide an integer within range of list shown.");
            }catch(Exception e){
                scan.nextLine();
                System.out.println("Only valid integers accepted.");
            }

        }
    }

    private void updateTask(TaskList tl) throws IndexOutOfBoundsException, IllegalArgumentException, DateTimeException {
        tl.printList();
        if (tl.size() != 0) {
            System.out.print("Please enter the index of the task you wish to edit: ");
            int index = scan.nextInt();
            scan.reset();
            if (index < 0 || index >= tl.size()) {
                throw new IndexOutOfBoundsException();
            }
            String[] newtaskinfo = getUpdatedTaskInfoFromUser(index);
            if (isValidTaskInfo(newtaskinfo[0], newtaskinfo[2])) {
                tl.updateTask(index, newtaskinfo[0], newtaskinfo[1], newtaskinfo[2]);
                System.out.println("Task updated");
            }
        }
    }

    public static boolean isValidTaskInfo(String title, String duedate) throws IllegalArgumentException, DateTimeException {
        if (title.length() == 0) {
            throw new IllegalArgumentException();
        }
        if (!isValidDate(duedate)) {
            throw new DateTimeException("");
        }
        return true;
    }

    private static boolean isValidDate(String Date) {
        //ideal date in "YYYY/MM/DD" format
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        try {
            df.parse(Date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    private String[] getUpdatedTaskInfoFromUser(int tasknumber){
        String[] ret = new String[3];
        scan.nextLine();
        System.out.print("Enter the new title for task " + tasknumber + ": ");
        ret[0] = scan.nextLine();
        scan.reset();
        System.out.print("Enter the new task description for task " + tasknumber + ": ");
        ret[1] = scan.nextLine();
        scan.reset();
        System.out.print("Enter a new task due date (YYYY-MM-DD) for task " + tasknumber +": ");
        ret[2] = scan.nextLine();
        scan.reset();

        return ret;
    }
    private void trytoAddtoList(TaskList tl){
        try {
            if (addtoTaskList(tl)) {
                System.out.println("Task created and added to the list.");
            }
        } catch (IllegalArgumentException i) {
            System.out.println("Title needs to be at least one character long.\nTask not created.");
        } catch (DateTimeException d) {
            System.out.println("Due Date needs to be a real date in YYYY-MM-DD format.\nTask not created.");
        }
    }
    private void trytoUpdateTask(TaskList tl){
        try {
            updateTask(tl);
        } catch (IndexOutOfBoundsException iob) {
            scan.nextLine();
            System.out.println("Not a valid index for a task on the list.");
        } catch (IllegalArgumentException ill) {
            scan.nextLine();
            System.out.println("Title needs to be at least one character in length.\n" +
                    "Task not updated.");
        } catch (DateTimeException d) {
            scan.nextLine();
            System.out.println("Due date needs to be a real date in a valid YYYY-MM-DD format.\n" +
                    "Task not updated.");
        } catch (Exception e) {
            scan.nextLine();
            System.out.println("Need a valid integer as index of your list to edit a task.");
        }
    }
    private void trytoRemovefromList(TaskList tl){
        try {
            removefromList(tl);
        } catch (IndexOutOfBoundsException iob) {
            scan.nextLine();
            System.out.println("Not a valid index for a task on the list.");
        }catch(Exception e){
            scan.nextLine();
            System.out.println("Only valid integers accepted.\n");
        }
    }
    private void trytoMarkTaskasComplete(TaskList tl){
        try {
            markAsComplete(tl);
        } catch (IndexOutOfBoundsException i) {
            scan.nextLine();
            System.out.println("Not a valid index for a task on the list.");
        } catch (Exception e) {
            scan.nextLine();
            System.out.println("Unable to find task with provided index. Please provide an integer corresponding to the correct index");
        }
    }

    private void trytoMarkTaskasIncomplete(TaskList tl){
        try {
            unmarkAsComplete(tl);
        } catch (IndexOutOfBoundsException i) {
            scan.nextLine();
            System.out.println("Not a valid index for a task on the list.");
        } catch (Exception e) {
            scan.nextLine();
            System.out.println("Unable to find task with provided index. Please provide an integer corresponding to the correct index");
        }
    }
}
