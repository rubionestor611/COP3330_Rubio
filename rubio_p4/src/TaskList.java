import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<TaskItem> list = new ArrayList<>();

    public TaskList(){

    }

    public void addTask(TaskItem t){
        this.list.add(t);
    }
    public int Size(){
        return this.list.size();
    }
    public void printTaskList(){
        System.out.println("Current tasks\n" +
                           "_____________");
        System.out.println(this.toString());
    }
    @Override
    public String toString(){
        String ret = "";
        int len = this.list.size();
        for(int i = 0; i < len; i++){
            ret += Integer.toString(i);
            ret += ") ";
            ret += this.list.get(i).toString();
            ret += "\n";
        }
        return ret;
    }
    public TaskItem getItem(int index){
        return this.list.get(index);
    }
    public void savetoFile(String filename){

    }
}
