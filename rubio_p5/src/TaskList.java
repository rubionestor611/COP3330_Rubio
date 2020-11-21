import java.io.*;
import java.nio.file.FileSystems;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.*;

public class TaskList {
    private ArrayList<TaskItem> list = new ArrayList<>();

    public TaskList() {
    }

    public void addTask(TaskItem t) {
        this.list.add(t);
    }

    public int size() {
        return this.list.size();
    }
    public void completeTask(int index){
        getItem(index).complete();
    }
    public void uncompleteTask(int index){
        getItem(index).incomplete();
    }
    public String getTaskTitle(int index){
        return getItem(index).getTitle();
    }
    public String getTaskDescription(int index){
        return getItem(index).getDescription();
    }
    public String getTaskDueDate(int index){
        return getItem(index).getDueDate();
    }
    public boolean isTaskComplete(int index){
        return getItem(index).isComplete();
    }
    public void printTaskList() {
        System.out.println("Current tasks\n" +
                "_____________");
        System.out.println(this.toString());
    }

    public ArrayList<Integer> printCompletedReturnCompleteList() {
        int count = 0;
        ArrayList<Integer> al = new ArrayList<>();
        int len = this.size();
        System.out.println("Completed Tasks\n" +
                           "_____________");
        for (int i = 0; i < len; i++) {
            TaskItem t = this.getItem(i);
            if (t.isComplete()) {
                al.add(i);
                System.out.println(count + ") " + t.regardlessofCompletion());
                count++;
            }
        }
        System.out.println("");
        return al;
    }
    public ArrayList<Integer> printUncompletedReturnIncompleteList() {
        int count = 0;
        ArrayList<Integer> al = new ArrayList<>();
        int len = this.size();
        System.out.println("Uncompleted Tasks\n" +
                           "_____________");
        for (int i = 0; i < len; i++) {
            TaskItem t = this.getItem(i);
            if (!t.isComplete()) {
                al.add(i);
                System.out.println(count + ") " + t.regardlessofCompletion());
                count++;
            }
        }
        System.out.println("");
        return al;
    }
    public int indexof(TaskItem t){
        return this.list.indexOf(t);
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
    public void updateTask(int index, String title, String desc, String duedate){
        getItem(index).updateTask(title,desc,duedate);
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public void removeTask(int index) {
        this.list.remove(index);
    }

    public void loadList(String filename) throws FileNotFoundException, Exception {
        if(!filename.substring(filename.length() - 4).equals(".txt")){
            filename +=".txt";
        }
        try {
            File file = new File("src/" + filename);
            if(!file.exists()){
                throw new FileNotFoundException("File does not exist");
            }
            Scanner s = new Scanner(file.getAbsoluteFile());
            while (s.hasNextLine()) {
                String[] row = s.nextLine().split("::");
                //title, description, date
                if(row[0].length() != 0 && isValidDate(row[2])){
                    this.list.add(new TaskItem(row[0], row[1], row[2]));
                    if(row.length == 4){
                        this.list.get(this.list.size()-1).complete();
                    }
                }

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
                pw.println(this.getItem(i).toWriteFormat());
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
    private static boolean isValidDate(String Date) {
        //ideal date in "YYYY/MM/DD" format
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        try {
            df.parse(Date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

}
