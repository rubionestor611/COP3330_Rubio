import java.util.ArrayList;

public class TaskList {
    ArrayList<TaskItem> list = new ArrayList<>();

    public void addTask(TaskItem t){
        this.list.add(t);
    }

    @Override
    public String toString(){
        String ret = "";
        for(TaskItem t : this.list){
            ret += "Title: " + t.getTitle() + " ";
            ret += "Desc: " + t.getDescription() + " ";
            ret += "DueDate: " + t.getDueDate() + " ";
            ret += "\n";
        }
        return ret;
    }
}
