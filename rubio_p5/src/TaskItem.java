
public class TaskItem {
    private String title;
    private String description;
    private String dueDate;
    private boolean Complete;
    public TaskItem(String title, String desc, String dueDate) {
        this.title = title;
        this.description = desc;
        this.dueDate = dueDate;
        this.Complete = false;
    }
    public void updateTask(String title, String desc, String dueDate) {
            this.setDueDate(dueDate);
            this.setDescription(desc);
            this.setTitle(title);
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
    @Override
    public String toString(){
        if(this.Complete){
            return ("[" + this.dueDate + "] " + "*** " +
                    this.title + ": " + this.description);
        }
        return regardlessofCompletion();
    }
    public String regardlessofCompletion(){
        return ("[" + this.dueDate + "] " +
                this.title + ": " + this.description);
    }
    public void complete(){
        this.Complete = true;
    }
    public void incomplete(){
        this.Complete = false;
    }
    public boolean isComplete(){
        return this.Complete;
    }
    public String toWriteFormat(){
        String ret = this.title + "::" + this.description + "::" + this.dueDate;
        if(this.Complete){
            ret += "::c";
        }
        return ret;
    }
}
