import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class TaskList {
    ArrayList<TaskItem> list = new ArrayList<>();

    public TaskList(){
        System.out.println(findNextFileName());
    }
    public static String findNextFileName(){
        try{
            String task = "task";
            File file = new File("TaskList.java");
            int count = 0;
            while(file.exists()){
                task = "task";
                task += String.valueOf(count);
                task += ".txt";
                file = new File(task);
            }
            if(file.createNewFile()){
                System.out.println("Task List Created : " + task);
                return task;
            }else{
                return null;
            }
        } catch(Exception e){
            System.out.println("Error occured when creating new file");
            return null;
        }

    }
    public void addTask(TaskItem t){
        this.list.add(t);
    }

    public void print(){
        System.out.println("Current tasks\n" +
                           "_____________");
        int len = this.list.size();
        for(int i = 0; i < len; i++){
            System.out.print(i);
            System.out.println(") ");
            this.list.get(i).printTask();
        }
    }
}
