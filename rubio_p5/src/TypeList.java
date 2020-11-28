import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public abstract class TypeList {
    //abstract list for lower level lists
    protected ArrayList<TypeItem> list;
    //inherited methods
    public int size(){
        return this.list.size();
    }
    abstract public void printList();
    public boolean isEmpty(){
        return this.list.isEmpty();
    }
    abstract public void loadList(String filename) throws FileNotFoundException, Exception ;
    public boolean savetoFile(String filename)throws Exception{
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
    protected abstract void writeText(PrintWriter pw) throws Exception;

}
