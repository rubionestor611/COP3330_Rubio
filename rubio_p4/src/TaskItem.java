import java.util.Scanner;

public class TaskItem {
    private String title;
    private String description;
    private String dueDate;
    public TaskItem(){
        this.title = getResponse("Task Title: ");
        this.description = getResponse("Task Description: ");
        this.dueDate = getResponse("Task Due Date: ");
    }
    public TaskItem(String title, String desc, String dueDate){
        this.title = title;
        this.description = desc;
        this.dueDate = dueDate;
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
        this.dueDate = duedate;
    }
    public String getDueDate(){
        return this.dueDate;
    }
}
