import org.junit.jupiter.api.Test;

import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.*;

class TaskItemTest {
    @Test
    public void creatingTaskItemFailsWithInvalidDueDate() {
        assertThrows(Exception.class, () -> {
            TaskItem t = new TaskItem("T", "D", "20200821");
            App.isValidTaskInfo(t.getTitle(),t.getDueDate());
        });
    }

    @Test
    public void creatingTaskItemFailsWithInvalidTitle() {
        assertThrows(Exception.class, () -> {
            if (new TaskItem("", "D", "2020-07-21").getTitle().length()==0){
                throw new Exception();
            }
        });
    }

    @Test
    public void creatingTaskItemSucceedsWithValidDueDate() {
        TaskItem t = new TaskItem("Task", "Task new", "2020-07-21");
        assertEquals("[2020-07-21] Task: Task new", t.toString());
    }

    @Test
    public void creatingTaskItemSucceedsWithValidTitle() {
        TaskItem t = new TaskItem("Hello", "Howdy", "2020-07-21");
        assertEquals("[2020-07-21] Hello: Howdy", t.toString());
    }

    @Test
    public void settingTaskItemDueDateFailsWithInvalidDate() {
        TaskItem t = new TaskItem("Hello", "Howdy", "2020-07-21");
        assertThrows(DateTimeException.class, () -> {
            t.updateTask("Hello", "Howdy", "2020-02-30");
            App.isValidTaskInfo(t.getTitle(),t.getDueDate());
        });
    }

    @Test
    public void settingTaskItemDueDateSucceedsWithValidDate() {
        TaskItem t = new TaskItem("Hello", "Howdy", "2020-07-21");
        t.updateTask("Hello","Howdy","2020-02-28");
        assertEquals("[2020-02-28] Hello: Howdy", t.toString());
    }

    @Test
    public void settingTaskItemTitleFailsWithInvalidTitle() {
        TaskItem t = new TaskItem("Hello", "Howdy", "2020-07-21");
        assertThrows(IllegalArgumentException.class, () -> {
            t.updateTask("", "Howdy", "2020-07-21");
            App.isValidTaskInfo(t.getTitle(),t.getDueDate());
        });
    }

    @Test
    public void settingTaskItemTitleSucceedsWithValidTitle() {
        TaskItem t = new TaskItem("Hello", "Howdy", "2020-07-21");
        t.updateTask("Howdy Pard'ner", "Howdy", "2020-07-21");
        assertEquals("Howdy Pard'ner", t.getTitle());
    }
    @Test
    public void settingTaskItemtoCompleteSucceeds(){
        TaskItem t = new TaskItem("Howdy", "Hello", "2020-07-21");
        t.complete();
        assertEquals("[2020-07-21] *** Howdy: Hello", t.toString());
    }
    @Test
    public void settingTaskItemtoIncompleteSucceeds(){
        TaskItem t = new TaskItem("Howdy", "Hello", "2020-07-21");
        t.complete();
        t.incomplete();
        assertEquals("[2020-07-21] Howdy: Hello", t.toString());
    }
}
