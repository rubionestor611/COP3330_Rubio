import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactList extends TypeList{
    public ContactList(){
        this.list = new ArrayList<TypeItem>();
    }

    public void printList(){
        System.out.println("Current Contacts\n" +
                           "_____________\n");
        int len = size();
        for(int i = 0; i < len; i++){
            System.out.println(i + ") Name: " + getContact(i).getFullName() + "\n" +
                                "Phone: " + getContact(i).getPhonenumber() + "\n" +
                                "Email: " + getContact(i).getEmail());
        }
        System.out.print("\n");
    }

    public ContactItem getContact(int index){
        return (ContactItem)this.list.get(index);
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
    public String getContactFullName(int index){
        return getContact(index).getFullName();
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
        if(filename.length() >= 4 && !filename.substring(filename.length() - 4).equals(".txt")){
            filename += ".txt";
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

    protected void writeText(PrintWriter pw) throws Exception{
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
}