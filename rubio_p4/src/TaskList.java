import java.io.*;
import java.nio.file.FileSystemAlreadyExistsException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private ArrayList<TaskItem> list = new ArrayList<>();

    public TaskList() {

    }

    public void addTask(TaskItem t) {
        this.list.add(t);
    }

    public int Size() {
        return this.list.size();
    }

    public void printTaskList() {
        System.out.println("Current tasks\n" +
                "_____________");
        System.out.println(this.toString());
    }

    public void printCompleted() {
        int count = 0;
        int len = this.Size();
        System.out.println("Completed Tasks" +
                "_____________");
        for (int i = 0; i < len; i++) {
            TaskItem t = this.getItem(i);
            if (t.isComplete()) {
                System.out.println(count + ") " + t.regardlessofCompletion());
                count++;
            }
        }
    }

    @Override
    public String toString() {
        String ret = "";
        int len = this.list.size();
        for (int i = 0; i < len; i++) {
            ret += Integer.toString(i);
            ret += ") ";
            ret += this.list.get(i).toString();
            ret += "\n";
        }
        return ret;
    }

    public TaskItem getItem(int index) {
        return this.list.get(index);
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public void removeTask(int index) {
        this.list.remove(index);
    }

    public void loadList(String filename) throws FileNotFoundException, Exception {
        try {
            File file = new File("src/" + filename);
            if(!file.exists()){
                throw new FileNotFoundException("File does not exist");
            }
            Scanner s = new Scanner(file.getAbsoluteFile());
            while (s.hasNextLine()) {
                String[] row = s.nextLine().split("::");
                //title, description, date
                this.list.add(new TaskItem(row[0], row[1], row[2]));
                if(row.length == 4){
                    this.list.get(this.list.size()-1).complete();
                }
            }
        } catch (FileNotFoundException fnf) {
            throw fnf;
        }
    }
    public void writeToFile(String filename) throws Exception{
        File file = new File("src/" + filename);
        try{
            if(file.delete()){
                FileWriter fw = new FileWriter(file);
                PrintWriter pw = new PrintWriter(fw);
                writeText(pw);
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
            int len = this.Size();
            for(int i = 0; i < len; i++){
                pw.println(this.getItem(i).toWriteFormat());
            }
            pw.close();
        }catch(Exception e){
            throw new Exception("append failed");
        }
    }
}
