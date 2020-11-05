import org.junit.jupiter.api.Test;

import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.*;

class TaskItemTest {
    @Test
    public void creatingTaskItemFailsWithInvalidDueDate(){
        assertThrows(DateTimeException.class, () -> {
            new TaskItem("T", "D", "20200821");
        });
    }
    @Test
    public void creatingTaskItemFailsWithInvalidTitle(){
        assertThrows(IllegalArgumentException.class, () -> {
            new TaskItem("", "D", "2020-07-21");
        });
    }
    @Test
    public void creatingTaskItemSucceedsWithValidDueDate(){
        TaskItem t = new TaskItem("Task", "Task new", "2020-07-21");
        assertEquals("[2020-07-21] Task: Task new", t.toString());
    }
    @Test
    public void creatingTaskItemSucceedsWithValidTitle(){
        TaskItem t = new TaskItem("Hello", "Howdy", "2020-07-21");
        assertEquals("[2020-07-21] Hello: Howdy", t.toString());
    }
    @Test
    public void settingTaskItemDueDateFailsWithInvalidDate(){
        TaskItem t = new TaskItem("Hello", "Howdy", "2020-07-21");
        assertThrows(DateTimeException.class, () -> {
            t.setDueDate("2020-02-30");
        });
    }
}
/*



settingTaskItemDueDateSucceedsWithValidDate()
settingTaskItemTitleFailsWithInvalidTitle()
settingTaskItemTitleSucceedsWithValidTitle()
 */