import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.util.Scanner;

public class ContactApp {
    private Scanner scanner = new Scanner(System.in);
    public void run(){
        main(new String[1]);
    }
    public void main(String[] args){
        this.mainMenu();
    }
    public void mainMenu(){
        printMainMenu();
        ContactList cl;
        int choice = getIntfromUser(1,3);
        while(choice != 3){
            if(choice == 1){
                try{
                    cl = new ContactList();
                    System.out.println("New contact list created");
                    listOperationsMenu(cl);
                }catch(Exception e) {
                    System.out.println("Problem in the operations menu.");
                }
            }
            if(choice == 2){
                cl = new ContactList();
                String filename = getFileNameFromUser();
                try{
                    cl.loadList(filename);
                    System.out.println("Contact List has been loaded.");
                    listOperationsMenu(cl);

                }catch(FileNotFoundException f){
                    System.out.println(filename + " does not exist");
                }catch(Exception e){
                    System.out.println("Problem in the list Operations Menu");
                }
            }
            if(choice != 3){
                printMainMenu();
                choice = getIntfromUser(1,3);
            }
        }
    }

    private void listOperationsMenu(ContactList cl) {
        int choice = getListOperationChoice();
        while(choice != 6){
            if(choice == 1){
                cl.printlist();
            }else if(choice == 2){
                addtoContactList(cl);
            }else if(choice == 3){
                System.out.println("edit an item");
            }else if(choice == 4){
                System.out.println("remove an item");
            }else if(choice == 5){
                System.out.println("save current list");
            }
            if(choice != 6){
                choice = getListOperationChoice();
            }
        }
    }

    private int getIntfromUser(int low, int high) {
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
            ret = getIntfromUser(low, high);
        }
        return ret;
    }
    private int getListOperationChoice(){
        printListOperationsMenu();
        return getIntfromUser(1,6);
    }

    private static void printListOperationsMenu(){
        System.out.println("List Operation Menu\n" +
                "---------\n" +
                "\n" +
                "1) view the list\n" +
                "2) add an item\n" +
                "3) edit an item\n" +
                "4) remove an item\n" +
                "5) save the current list\n" +
                "6) quit to the main menu");
    }
    private String getFileNameFromUser() {
        System.out.println("Enter the name of the file you're list is saved in: ");
        String ret = scanner.nextLine();
        scanner.nextLine();
        return ret;
    }

    public static void printMainMenu() {
        System.out.println("Main Menu:\n" +
                "_________");
        System.out.println("1) create a new list");
        System.out.println("2) load an existing list");
        System.out.println("3) quit");
    }
    public void addtoContactList(ContactList cl){
        try{
            String[] contactinfo = getContactInfoFromUser();
            ContactItem contacttoadd = new ContactItem(contactinfo[0],contactinfo[1],contactinfo[2],contactinfo[3]);
            cl.addContact(contacttoadd);
        }catch(InstantiationError i){
            System.out.println("All values cannot be blank at least one contact field must have content.");
        }catch(Exception e){
            System.out.println("Returning to List Operations Menu.\n");
        }/*catch(IllegalArgumentException i){

        }catch(DateTimeException d){

        }catch(InstantiationError i){

        }*/
    }
    public String[] getContactInfoFromUser(){
        String[] ret = new String[4];
        System.out.print("Please enter the contact's first name: ");
        scanner.nextLine();
        ret[0] = scanner.nextLine();
        scanner.reset();
        System.out.print("Please enter the contact's last name: ");
        ret[1] = scanner.nextLine();
        scanner.reset();
        System.out.print("Please enter the contact's phone number: ");
        ret[2] = scanner.nextLine();
        scanner.reset();
        System.out.print("Please enter the contact's email address: ");
        ret[3] = scanner.nextLine();
        scanner.reset();
        return ret;
    }
}
