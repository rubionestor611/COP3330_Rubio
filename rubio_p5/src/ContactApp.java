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
                    System.out.println(e.getMessage());
                    e.printStackTrace();
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
                cl.printList();
            }else if(choice == 2){
                addtoContactList(cl);
            }else if(choice == 3){
                editContactinList(cl);
            }else if(choice == 4){
                removeContactfromList(cl);
            }else if(choice == 5){
                saveContactList(cl);
            }
            if(choice != 6){
                choice = getListOperationChoice();
            }
        }
    }

    private void saveContactList(ContactList cl) {
        System.out.print("Enter the name you wish to save this contact list under: ");
        scanner.nextLine();
        String filename = scanner.nextLine();
        try{
            if(cl.savetoFile(filename)){
                System.out.println("List saved successfully");
            }else{
                throw new Exception();
            }
        }catch(Exception e){
            System.out.println("Problem saving list.");
        }
    }

    private void removeContactfromList(ContactList cl) {
        if(cl.size() == 0){
            return;
        }
        int chosenindex = 0;
        scanner.nextLine();
        cl.printList();
        System.out.print("Please enter the index of the contact you wish to delete: ");
        try{
            chosenindex = scanner.nextInt();
            if(chosenindex < 0 || chosenindex >= cl.size()){
                throw new IndexOutOfBoundsException();
            }
            cl.removeContact(chosenindex);
            System.out.println("Contact " + chosenindex + " removed.");
        }catch(IndexOutOfBoundsException ind){
            System.out.println(chosenindex + " is not a valid index. No contacts removed.");
        }catch(Exception e){
            System.out.println("Please provide a valid integer indicating the contact to remove. No contact deleted.");
        }
    }

    private void editContactinList(ContactList cl) {
        if(cl.size() == 0){
            return;
        }
        int chosenindex = 0;
        scanner.nextLine();
        cl.printList();
        System.out.print("Please enter the index of the contact you wish to update: ");
        try{
            chosenindex = scanner.nextInt();
            if(chosenindex < 0 || chosenindex >= cl.size()){
                throw new IndexOutOfBoundsException();
            }
            String[] newcontactinfo = getUpdatedContactInfofromUser(chosenindex);
            cl.updateContact(chosenindex, newcontactinfo[0],newcontactinfo[1],newcontactinfo[2],newcontactinfo[3]);
            System.out.println("Contact for " + cl.getContactFullName(chosenindex) + " updated.");
        }catch(IndexOutOfBoundsException ind){
            System.out.println(chosenindex + " is not a valid index. No contacts updated.");
        }catch(IllegalArgumentException i){
            System.out.println("All blank values is not valid info for the contact.\nContact " + chosenindex + " will not be updated.");
        }catch(Exception e){
            System.out.println("Please provide a valid integer to update it. No contact updated.");
        }
    }

    private String[] getUpdatedContactInfofromUser(int index) {
        String[] ret = new String[4];
        System.out.print("Please enter a new first name for contact " + index + ": ");
        scanner.nextLine();
        ret[0] = scanner.nextLine();
        scanner.reset();
        System.out.print("Please enter a new last name for contact " + index + ": ");
        ret[1] = scanner.nextLine();
        scanner.reset();
        System.out.print("Please enter a new phone number for contact " + index + ": ");
        ret[2] = scanner.nextLine();
        scanner.reset();
        System.out.print("Please enter a new email address for contact " + index + ": ");
        ret[3] = scanner.nextLine();
        scanner.reset();
        return ret;
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
        System.out.println("\nList Operation Menu\n" +
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
        System.out.print("Enter the name of the file your list is saved in: ");
        scanner.nextLine();
        String ret = scanner.nextLine();
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
            System.out.println("Contact for " + cl.getContactFullName(cl.size() - 1) + " added.");
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
        System.out.print("Please enter the contact's phone number(xxx-xxx-xxx): ");
        ret[2] = scanner.nextLine();
        scanner.reset();
        System.out.print("Please enter the contact's email address (x@y.z): ");
        ret[3] = scanner.nextLine();
        scanner.reset();
        return ret;
    }
}
