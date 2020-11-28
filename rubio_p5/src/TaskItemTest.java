import org.junit.jupiter.api.Test;

import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.*;

class TaskItemTest {
    @Test
    public void abstractionSucceeds(){
        TypeItem t = new TaskItem("Title","","2020-08-10");
        assertEquals("Title::::2020-08-10",t.toWriteFormat());
    }
    @Test
    public void constructorFailsWithInvalidDueDate(){
        assertThrows(DateTimeException.class, () -> {
                TaskItem t = new TaskItem("Title", "Desc", "2020-02-30");
        });
    }
    @Test
    public void constructorFailsWithInvalidTitle(){
        assertThrows(IllegalArgumentException.class, () -> {
                TaskItem t = new TaskItem("", "Desc", "2020-02-04");
        });
    }
    @Test
    public void constructorSucceedsWithValidDueDate(){
        TaskItem t = new TaskItem("Title", "Desc", "2020-02-04");
        assertEquals("2020-02-04", t.getDueDate());
    }
    @Test
    public void constructorSucceedsWithValidTitle(){
        TaskItem t = new TaskItem("Title", "Desc", "2020-02-04");
        assertEquals("Title", t.getTitle());
    }
    @Test
    public void editingDescriptionSucceedsWithExpectedValue(){
        TaskItem t = new TaskItem("Title", "Desc", "2020-02-04");
        assertEquals("Desc", t.getDescription());
        t.updateTask("Title2","Description","2020-02-04");
        assertEquals("Description", t.getDescription());
    }
    @Test
    public void editingDueDateFailsWithInvalidDateFormat(){
        TaskItem t = new TaskItem("Hello", "Desc", "2020-02-04");
        assertThrows(DateTimeException.class, () -> {
            t.updateTask("Hello","", "02-28-2020");
        });
    }
    @Test
    public void editingDueDateFailsWithInvalidYYYYMMDD(){
        TaskItem t = new TaskItem("Title", "Desc", "2020-02-04");
        assertThrows(DateTimeException.class, () -> {
                t.updateTask("Title","", "2020-01-35");
        });
    }
    @Test
    public void editingDueDateSucceedsWithExpectedValue(){
        TaskItem t = new TaskItem("Title", "Desc", "2020-02-04");
        assertEquals("2020-02-04", t.getDueDate());
        t.updateTask("Title1","","2020-08-10");
        assertEquals("2020-08-10", t.getDueDate());
    }
    @Test
    public void editingTitleFailsWithEmptyString(){
        TaskItem t = new TaskItem("Title", "Desc", "2020-02-04");
        assertThrows(IllegalArgumentException.class, () -> {
                t.updateTask("","", "2020-01-10");
        });
    }
    @Test
    public void editingTitleSucceedsWithExpectedValue(){
        TaskItem t = new TaskItem("Title", "Desc", "2020-02-04");
        assertEquals("Title", t.getTitle());
        t.updateTask("Title2","","2020-08-10");
        assertEquals("Title2", t.getTitle());
    }
}