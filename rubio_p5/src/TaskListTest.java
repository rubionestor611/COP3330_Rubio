import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DateTimeException;

class TaskListTest {
    @Test
    public void addingItemsIncreasesSize(){
        TaskList tl =new TaskList();
        assertEquals(0,tl.size());
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-21"));
        assertEquals(1,tl.size());
    }
    @Test
    public void completingTaskItemChangesStatus(){
        TaskList tl =new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-21"));
        assertEquals("[2020-07-21] Title: Desc", tl.getItem(0).toString());
        tl.completeTask(0);
        assertEquals(true, tl.isTaskComplete(0));
    }
    @Test
    public void completingTaskItemFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-21"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.completeTask(1);
        });
    }
    @Test
    public void editingItemDescriptionFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-21"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.updateTask(1, "T", "", "2021-07-05");
        });
    }
    @Test
    public void editingItemDescriptionSucceedsWithExpectedValue(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-21"));
        assertEquals("Desc", tl.getItem(0).getDescription());
        tl.updateTask(0, "Title", "Description", "2020-07-21");
        assertEquals("Description", tl.getItem(0).getDescription());
    }
    @Test
    public void editingItemDueDateSucceedsWithExpectedValue(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-21"));
        assertEquals("2020-07-21", tl.getItem(0).getDueDate());
        tl.updateTask(0, "Title", "Description", "2019-02-17");
        assertEquals("2019-02-17", tl.getItem(0).getDueDate());
    }
    @Test
    public void editingItemTitleFailsWithEmptyString(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-21"));
        assertThrows(IllegalArgumentException.class, () -> {
            tl.updateTask(0,"", "", "2020-07-21");
        });
    }
    @Test
    public void editingItemTitleFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-21"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.updateTask(1,"T", "", "2020-07-24");
        });
    }
    @Test
    public void editingItemTitleSucceedsWithExpectedValue(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-21"));
        assertEquals("Title", tl.getItem(0).getTitle());
        tl.updateTask(0, "UpdatedTitle", "Description", "2019-02-17");
        assertEquals("UpdatedTitle", tl.getItem(0).getTitle());
    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidDateFormat(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-21"));
        assertThrows(DateTimeException.class, () -> {
            tl.updateTask(0,"T", "", "02172020");
        });
    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-21"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.updateTask(1,"Title", "", "");
        });
    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidYYYMMDD(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-21"));
        assertThrows(DateTimeException.class, () -> {
            tl.updateTask(0,"T", "", "2020-02-30");
        });
    }
    @Test
    public void gettingItemDescriptionFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-21"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.getTaskDescription(1);
        });
    }
    @Test
    public void gettingItemDescriptionSucceedsWithValidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("String", "Success", "2025-02-09"));
        assertEquals("Success", tl.getTaskDescription(0));
    }
    @Test
    public void gettingItemDueDateFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-21"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.getTaskDueDate(1);
        });
    }
    @Test
    public void gettingItemDueDateSucceedsWithValidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("String", "Success", "2025-02-09"));
        assertEquals("2025-02-09", tl.getTaskDueDate(0));
    }
    @Test
    public void gettingItemTitleFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-21"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.getTaskTitle(1);
        });
    }
    @Test
    public void gettingItemTitleSucceedsWithValidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("String", "Success", "2020-07-21"));
        assertEquals("String", tl.getTaskTitle(0));
    }
    @Test
    public void newListIsEmpty(){
        TaskList tl = new TaskList();
        assertEquals(true, tl.isEmpty());
    }
    @Test
    public void removingItemsDecreasesSize(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("String", "Success", "2025-02-09"));
        tl.addTask(new TaskItem("String1", "Success1", "2025-02-10"));
        assertEquals(2, tl.size());
        tl.removeTask(1);
        assertEquals(1, tl.size());
    }
    @Test
    public void removingItemsFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-21"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.removeTask(1);
        });
    }
    @Test
    public void savedTaskListCanBeLoaded(){
        TaskList tl = new TaskList();
        try{
            tl.loadList("NestorsTestTextFile00.txt");
            assertEquals(3,tl.size());
        }catch(Exception e){
            System.out.println(e.getMessage());
            assertEquals(0,1);
        }

    }
    @Test
    public void uncompletingTaskItemChangesStatus(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-21"));
        tl.completeTask(0);
        assertEquals(true, tl.isTaskComplete(0));
        tl.uncompleteTask(0);
        assertEquals(false, tl.isTaskComplete(0));
    }
    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-21"));
        tl.completeTask(0);
        assertEquals(true, tl.isTaskComplete(0));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.uncompleteTask(1);
        });
    }

}