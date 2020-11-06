import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    @Test
    public void addingTaskItemsIncreasesSize(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Howdy", "Hello", "2020-01-27"));
        assertEquals(1,tl.Size());
    }
    @Test
    public void completingTaskItemChangesStatus(){
        TaskList tl = new TaskList();
        for (int i = 0; i < 4; i++) {
            tl.addTask(new TaskItem("h", "d", "2020-11-06"));
            tl.getItem(i).complete();
        }
        assertEquals("0) [2020-11-06] *** h: d\n" +
                             "1) [2020-11-06] *** h: d\n" +
                             "2) [2020-11-06] *** h: d\n" +
                             "3) [2020-11-06] *** h: d\n", tl.toString());
    }
    /*
    @Test
    public void completingTaskItemFailsWithInvalidIndex(){

    }
    @Test
    public void editingTaskItemChangesValues(){

    }

    @Test
    public void editingTaskItemDescriptionChangesValue(){

    }
    @Test
    public void editingTaskItemDescriptionFailsWithInvalidIndex(){

    }
    @Test
    public void editingTaskItemDueDateChangesValue(){

    }
    */


}