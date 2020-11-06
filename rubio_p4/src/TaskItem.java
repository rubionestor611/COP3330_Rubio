
import java.time.DateTimeException;
import java.util.Scanner;

public class TaskItem {
    private String title;
    private String description;
    private String dueDate;
    private boolean Complete;
    public TaskItem(String title, String desc, String dueDate) throws DateTimeException,
            IllegalArgumentException{
        if(!isValidDate(dueDate)){
            throw new DateTimeException("You entered an invalid due date. Task not created");
        }
        if(title.length() == 0){
            throw new IllegalArgumentException("Title must be at least 1 character long; task not created");
        }
        this.title = title;
        this.description = desc;
        this.dueDate = dueDate;
        this.Complete = false;
    }
    public void updateTask(String title, String desc, String dueDate) throws DateTimeException,
            IllegalArgumentException{
        try{
            this.setDueDate(dueDate);
            this.setDescription(desc);
            this.setTitle(title);
        }catch(IllegalArgumentException i){
            throw i;
        }catch(DateTimeException d){
            throw d;
        }catch(Exception e){
            throw e;
        }
    }

    public static String getResponse(String s){

        String ret  = "";
        try{
            Scanner scan = new Scanner(System.in);
            System.out.print(s);
            while(ret.length() <= 0){
                ret = scan.nextLine();
            }
        }catch(Exception E){
            return getResponse(s);
        }
        return ret;
    }
    public void setTitle(String title){
        if(title.length() == 0){
            throw new IllegalArgumentException("Title entered needs to be at least one character in length. Task title not updated.");
        }
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDueDate(String duedate){
            if(isValidDate(duedate)){
                this.dueDate = duedate;
                return;
            } else{
                throw new DateTimeException("You entered an invalid due date. Task due date not updated.");
            }
    }
    public String getDueDate(){
        return this.dueDate;
    }
    @Override
    public String toString(){

        if(this.Complete){
            return ("[" + this.dueDate + "] " + "*** " +
                    this.title + ": " + this.description);
        }
        return ("[" + this.dueDate + "] " +
                this.title + ": " + this.description);
    }
    private static boolean isValidDate(String Date){
        //ideal date YYYY-MM-DD
                   //0123456789
        try{
            if(Date.charAt(4) != '-' || Date.charAt(7) != '-'){
                System.out.println("not right -'s");
                return false;
            }
            int month = Integer.parseInt(Date.substring(5,7));
            if(month > 12 || month < 1){
                System.out.println(month);
                System.out.println("months not right");
                return false;
            }
            int day = Integer.parseInt(Date.substring(8,10));
            if(day < 1 || day > 31){
                System.out.println("days not right");
                return false;
            }
            if(month == 2 && day > 28){
                System.out.println("days not right");
                return false;
            }
        } catch(Exception e){
            System.out.println("exception found");
            return false;
        }
        return true;
    }
    public void complete(){
        this.Complete = true;
    }
    public void incomplete(){
        this.Complete = false;
    }
}
