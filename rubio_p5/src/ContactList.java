import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactList {
    private ArrayList<ContactItem> list = new ArrayList<>();
    public ContactList(){
    }
    public void printlist(){
        System.out.println("Current Contacts\n" +
                           "_____________\n");
        int len = size();
        for(int i = 0; i < len; i++){
            System.out.println(i + ") Name: " + getContact(i).getFullName() + "\n" +
                                "Phone: " + getContact(i).getPhonenumber() + "\n" +
                                "Email: " + getContact(i).getEmail());
        }
    }
    public int size(){
        return list.size();
    }
    public boolean isEmpty(){
        return this.list.isEmpty();
    }
    private ContactItem getContact(int index){
        return this.list.get(index);
    }
    public void addContact(ContactItem c){
        list.add(c);
    }
    public void updateContact(int index, String first, String last, String phone, String email) {
        try{
            this.getContact(index).update(first,last, phone, email);
        }catch(IndexOutOfBoundsException i){
            System.out.println(index + " is not a valid index. No contacts updated.");
            throw i;
        }catch(IllegalArgumentException e){
            System.out.println("WARNING: CANNOT UPDATE WITH ALL BLANK VALUES.\n" +
                               "TASK NOT UPDATED");
            throw e;
        }

    }
    public String getContactFirstName(int index){
        return this.getContact(index).getFirstName();
    }
    public String getContactLastName(int index){
        return this.getContact(index).getLastName();
    }
    public String getContactPhoneNumber(int index){
        return this.getContact(index).getPhonenumber();
    }
    public String getContactEmail(int index){
        return this.getContact(index).getEmail();
    }
    public void removeContact(int index){
        this.list.remove(index);
    }
    public String contacttoString(int index){
        return getContact(index).toString();
    }
    public void loadList(String filename) throws FileNotFoundException, Exception {
        if(!filename.substring(filename.length() - 4).equals(".txt")){
            filename +=".txt";
        }
        try {
            File file = new File("src/" + filename);
            int failedload = 0;
            if(!file.exists()){
                throw new FileNotFoundException("File does not exist");
            }
            Scanner s = new Scanner(file.getAbsoluteFile());
            while (s.hasNextLine()) {
                String[] row = s.nextLine().split("::");
                try{
                    ContactItem c = new ContactItem(row[0],row[1],row[2],row[3]);
                    addContact(c);
                }catch(InstantiationError e){
                    failedload++;
                }
            }
            if(failedload != 0){
                System.out.println("Could not load " + failedload + " of the contacts on the list");
            }
        } catch (FileNotFoundException fnf) {
            throw fnf;
        }
    }
    public void writeToFile(String filename) throws Exception{
        File file = new File("src/" + filename);
        try{
            if(file.exists()){
                file.delete();
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                PrintWriter pw = new PrintWriter(fw);
                writeText(pw);
                fw.close();
            }else{
                if(file.createNewFile()){
                    writeToFile(filename);
                }
            }
        }catch(Exception e){
            throw e;
        }
    }
    private void writeText(PrintWriter pw) throws Exception{
        try{
            int len = this.size();
            for(int i = 0; i < len; i++){
                pw.println(this.getContact(i).toWriteFormat());
            }
            pw.close();
        }catch(Exception e){
            throw new Exception("append failed");
        }
    }
    public boolean savetoFile() throws Exception {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the filename to save as: ");
        String filename = s.nextLine();
        try{
            if(filename.length() >= 5 && filename.substring(filename.length() - 4).equals(".txt")){
                this.writeToFile(filename);
                return true;
            }else{
                this.writeToFile(filename + ".txt");
                return true;
            }
        }catch(Exception e){
            return false;
        }
    }
}