import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DateTimeException;

class TaskListTest {
    @Test
    public void addingItemsIncreasesSize(){
        TaskList tl =new TaskList();
        assertEquals(0,tl.size);
        tl.addTask(new TaskItem("Title", "Desc", "07-21-2020"));
        assertEquals(1,tl.size());
    }
    @Test
    public void completingTaskItemChangesStatus(){
        TaskList tl =new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "07-21-2020"));
        assertEquals("[07-21-2020] Title: Desc", tl.getItem(0).toString());
        tl.completeTask(0);
        assertEquals(true, tl.isTaskComplete(0));
    }
    @Test
    public void completingTaskItemFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "07-21-2020"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.completeTask(1);
        });
    }
    @Test
    public void editingItemDescriptionFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "07-21-2020"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.updateTask(1, "T", "", "07-25-2021");
        });
    }
    @Test
    public void editingItemDescriptionSucceedsWithExpectedValue(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "07-21-2020"));
        assertEquals("Desc", tl.getItem(0).getDescription());
        tl.updateTask(0, "Title", "Description", "07-21-2020");
        assertEquals("Description", tl.getItem(0).getDescription());
    }
    @Test
    public void editingItemDueDateSucceedsWithExpectedValue(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "07-21-2020"));
        assertEquals("07-21-2020", tl.getItem(0).getDueDate());
        tl.updateTask(0, "Title", "Description", "02-17-2019");
        assertEquals("02-17-2019", tl.getItem(0).getDueDate());
    }
    @Test
    public void editingItemTitleFailsWithEmptyString(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "07-21-2020"));
        assertThrows(IllegalArgumentException.class, () -> {
            tl.updateTask(0,"", "", "07-21-2020");
        });
    }
    @Test
    public void editingItemTitleFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "07-21-2020"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.updateTask(1,"T", "", "07-21-2020");
        });
    }
    @Test
    public void editingItemTitleSucceedsWithExpectedValue(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "07-21-2020"));
        assertEquals("Title", tl.getItem(0).getTitle());
        tl.updateTask(0, "UpdatedTitle", "Description", "02-17-2019");
        assertEquals("UpdatedTitle", tl.getItem(0).getTitle());
    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidDateFormat(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "07-21-2020"));
        assertThrows(DateTimeException.class, () -> {
            tl.updateTask(0,"T", "", "02172020");
        });
    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "07-21-2020"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.updateTask(1,"Title", "", "");
        });
    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidYYYMMDD(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "07-21-2020"));
        assertThrows(DateTimeException.class, () -> {
            tl.updateTask(0,"T", "", "02-30-2020");
        });
    }
    @Test
    public void gettingItemDescriptionFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "07-21-2020"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.getItemDescription(1);
        });
    }
    @Test
    public void gettingItemDescriptionSucceedsWithValidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("String", "Success", "02-09-2025"));
        assertEquals("Success", tl.getItemDescription(0));
    }
    @Test
    public void gettingItemDueDateFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "07-21-2020"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.getItemDueDate(1);
        });
    }
    @Test
    public void gettingItemDueDateSucceedsWithValidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("String", "Success", "02-09-2025"));
        assertEquals("02-09-2025", tl.getItemDueDate(0));
    }
    @Test
    public void gettingItemTitleFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "07-21-2020"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.getItemTitle(1);
        });
    }
    @Test
    public void gettingItemTitleSucceedsWithValidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("String", "Success", "02-09-2025"));
        assertEquals("String", tl.getItemTitle(0));
    }
    @Test
    public void newListIsEmpty(){
        TaskList tl = new TaskList();
        assertEquals(true, tl.isEmpty());
    }
    @Test
    public void removingItemsDecreasesSize(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("String", "Success", "02-09-2025"));
        tl.addTask(new TaskItem("String1", "Success1", "02-10-2025"));
        assertEquals(2, tl.Size());
        tl.removeTask(1);
        assertEquals(1, tl.Size());
    }
    @Test
    public void removingItemsFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "07-21-2020"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.removeTask(1);
        });
    }
    @Test
    public void savedTaskListCanBeLoaded(){
        TaskList tl = new TaskList();
        tl.loadList("NestorsSampleTextFile00.txt");
        assertEquals(3,tl.Size());
    }
    @Test
    public void uncompletingTaskItemChangesStatus(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "07-21-2020"));
        tl.completeItem(0);
        assertEquals(true, tl.isItemComplete(0));
        tl.uncompleteItem(0);
        assertEquals(false, tl.isItemComplete(0));
    }
    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "07-21-2020"));
        tl.completeItem(0);
        assertEquals(true, tl.isItemComplete(0));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.uncompleteItem(1);
        });
    }

}