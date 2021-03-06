import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private ArrayList<TaskList> tasklist = new ArrayList<>();

    public static void main(String[] args) {
        MainMenu();
    }

    public static void MainMenu() {
        printMainMenu();
        TaskList tl;
        int input = getInput(1, 3);
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
                try {
                    tl = new TaskList();
                    tl.loadList(getFileName());
                    System.out.println("task list has been loaded");
                    listOperationMenu(tl);
                } catch (FileNotFoundException e) {
                    System.out.println("File does not exist. Tasklist could not be loaded\n");
                } catch (Exception e) {
                    System.out.println("Problem loading list\n");
                }

            }
            printMainMenu();
            input = getInput(1, 3);
        }
        System.out.println("Goodbye, thank you for using Nestor's To-Do list app.");
    }

    public static void listOperationMenu(TaskList tl) throws Exception {
        printListOperationMenu();
        int input = getInput(1, 8);
        while (input != 8) {
            if (input == 1) {
                tl.printTaskList();
            }
            if (input == 2) {
                try {
                    if (addtoTaskList(tl)) {
                        System.out.println("Task created and added to the list.");
                    }
                } catch (IllegalArgumentException i) {
                    System.out.println("Title needs to be at least one character long.\nTask not created.");
                } catch (DateTimeException d) {
                    System.out.println("DueDate needs to be a real date in YYYY-MM-DD format.\nTask not created.");
                }
            }
            if (input == 3) {
                try {
                    updateTask(tl);
                } catch (IndexOutOfBoundsException iob) {
                    System.out.println("Not a valid index for a task on the list.");
                } catch (IllegalArgumentException ill) {
                    System.out.println("Title needs to be at least one character in length.\n" +
                            "Task not updated.");
                } catch (DateTimeException d) {
                    System.out.println("Due date needs to be a real date in a valid YYYY-MM-DD format.\n" +
                            "Task not updated.");
                } catch (Exception e) {
                    System.out.println("Need a valid integer as index of your list to edit a task.");
                }
            }
            if (input == 4) {
                try {
                    removefromList(tl);
                } catch (IndexOutOfBoundsException iob) {
                    System.out.println("Not a valid index for a task on the list.");
                }
            }
            if (input == 5) {
                try {
                    markAsComplete(tl);
                } catch (IndexOutOfBoundsException i) {
                    System.out.println("Not a valid index for a task on the list.");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Unable to find task with provided index. Please provide an integer corresponding to the correct index");
                }
            }
            if (input == 6) {
                //unmark as completed
                try {
                    unmarkAsComplete(tl);
                } catch (IndexOutOfBoundsException i) {
                    System.out.println("Not a valid index for a task on the list.");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Unable to find task with provided index. Please provide an integer corresponding to the correct index");
                }
            }
            if (input == 7) {
                if (tl.savetoFile()) {
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

    public static String getFileName() {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the file name to add: ");
        String ret = s.next();
        return ret;
    }

    public static void printMainMenu() {
        System.out.println("Main Menu:\n" +
                "_________");
        System.out.println("1) create a new list");
        System.out.println("2) load an existing list");
        System.out.println("3) quit");
    }

    public static boolean addtoTaskList(TaskList tl) throws IllegalArgumentException, DateTimeException {
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

    public static String[] getTaskInfoFromUser() {
        Scanner s = new Scanner(System.in);
        String[] ret = new String[3];
        System.out.print("Task Title: ");
        ret[0] = s.nextLine();
        System.out.print("Task Description: ");
        ret[1] = s.nextLine();
        System.out.print("Task Due Date (YYYY-MM-DD): ");
        ret[2] = s.nextLine();
        return ret;
    }

    public static void unmarkAsComplete(TaskList tl) throws IndexOutOfBoundsException {
        ArrayList<Integer> al = tl.printCompletedReturnCompleteList();
        if (al.size() != 0) {
            System.out.print("Enter the index of the task you wish to mark as incomplete: ");
            Scanner s = new Scanner(System.in);
            int index = s.nextInt();
            if (index < 0 || index > al.size()) {
                throw new IndexOutOfBoundsException();
            }
            int indexinlist = al.get(index);
            tl.getItem(indexinlist).incomplete();
            System.out.println("\nTask marked as incomplete.\n");
        }
    }

    public static void markAsComplete(TaskList tl) throws IndexOutOfBoundsException {
        ArrayList<Integer> al = tl.printUncompletedReturnIncompleteList();
        if (al.size() != 0) {
            System.out.print("Enter the index of the task you wish to mark as complete: ");
            Scanner s = new Scanner(System.in);
            int index = s.nextInt();
            if (index < 0 || index > al.size()) {
                throw new IndexOutOfBoundsException();
            }
            int indexinlist = al.get(index);
            tl.getItem(indexinlist).complete();
            System.out.println("\nTask marked as complete.\n");
        }

    }

    public static void removefromList(TaskList tl) throws IndexOutOfBoundsException {
        Scanner s = new Scanner(System.in);
        tl.printTaskList();
        if (tl.Size() != 0) {
            System.out.print("Please enter the index of the task you wish to delete: ");
            int index = s.nextInt();
            if (index < 0 || index >= tl.Size()) {
                throw new IndexOutOfBoundsException();
            }
            tl.removeTask(index);
            System.out.println("Task removed");
        }
    }

    public static void updateTask(TaskList tl) throws IndexOutOfBoundsException, IllegalArgumentException, DateTimeException {
        Scanner s = new Scanner(System.in);
        tl.printTaskList();
        if (tl.Size() != 0) {
            System.out.print("Please enter the index of the task you wish to edit: ");
            int index = s.nextInt();
            if (index < 0 || index >= tl.Size()) {
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
    public static String[] getUpdatedTaskInfoFromUser(int tasknumber){
        Scanner s = new Scanner(System.in);
        String[] ret = new String[3];
        System.out.print("Enter the new title for task " + tasknumber + ": ");
        ret[0] = s.nextLine();
        System.out.print("Enter the new task description for task " + tasknumber + ": ");
        ret[1] = s.nextLine();
        System.out.print("Enter a new task due date (YYYY-MM-DD) for task " + tasknumber +": ");
        ret[2] = s.nextLine();
        return ret;
    }
}