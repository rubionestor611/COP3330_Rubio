import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

    @Test
    public void completingTaskItemFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        for (int i = 0; i < 4; i++) {
            tl.addTask(new TaskItem("h", "d", "2020-11-06"));
            tl.getItem(i).complete();
        }
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.getItem(4).complete();
        });

    }

    @Test
    public void editingTaskItemChangesValues(){
        TaskList tl = new TaskList();
        for (int i = 0; i < 4; i++) {
            tl.addTask(new TaskItem("h", "d", "2020-11-06"));
        }
        tl.updateTask(1,"Title", "Desc", "2020-07-21");
        assertEquals("[2020-07-21] Title: Desc", tl.getItem(1).toString());
    }
    @Test
    public void editingTaskItemDescriptionChangesValue(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("t", "d", "2020-02-28"));
        tl.updateTask(0,"t", "D", "2020-02-28");
        assertEquals("D", tl.getItem(0).getDescription());
    }

    @Test
    public void editingTaskItemDescriptionFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("T", "D", "2020-07-21"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.updateTask(4,"Title", "Desc", "2020-11-06");
        });
    }

    @Test
    public void editingTaskItemDueDateChangesValue(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("T", "D", "2020-07-21"));
        tl.updateTask(0,"T","D","2020-08-31");
        assertEquals("[2020-08-31] T: D",tl.getItem(0).toString());
    }


    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("t", "desc","2020-07-15"));
        assertThrows(IndexOutOfBoundsException.class, () ->{
           tl.updateTask(1,"title", "d", "2020-02-01");
        });
    }

    @Test
    public void editingTaskItemTitleChangesValue(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("h", "d", "2020-11-06"));
        tl.updateTask(0,"Title","desc","2020-01-29");
        assertEquals("[2020-01-29] Title: desc", tl.getItem(0).toString());
    }

    @Test
    public void editingTaskItemTitleFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("h", "d", "2020-11-06"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.updateTask(4,"Title","desc","2020-10-15");
        });
    }

    @Test
    public void gettingTaskItemDescriptionFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("h", "d", "2020-11-06"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.getItem(4).getDescription();
        });
    }

    @Test
    public void gettingTaskItemDescriptionSucceedsWithValidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("t", "d", "2020-02-28"));
        assertEquals("d", tl.getItem(0).getDescription());
    }

    @Test
    public void gettingTaskItemDueDateFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("T", "D", "2020-07-21"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.getItem(4).getDueDate();
        });
    }

    @Test
    public void gettingTaskItemDueDateSucceedsWithValidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("T", "D", "2020-07-21"));
        assertEquals("2020-07-21",tl.getItem(0).getDueDate());
    }

    @Test
    public void gettingTaskItemTitleFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("T", "D", "2020-07-21"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.getItem(4).getTitle();
        });
    }
    @Test
    public void gettingTaskItemTitleSucceedsWithValidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("T", "D", "2020-07-21"));
        assertEquals("T",tl.getItem(0).getTitle());
    }
    @Test
    public void newTaskListIsEmpty(){
        TaskList tl = new TaskList();
        assertEquals(true,tl.isEmpty());
    }
    @Test
    public void removingTaskItemsDecreasesSize(){
        TaskList tl = new TaskList();
        for (int i = 0; i < 4; i++) {
            tl.addTask(new TaskItem("h", "d", "2020-11-06"));
        }
        tl.removeTask(1);
        assertEquals(3,tl.Size());
    }
    @Test
    public void removingTaskItemsFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        for (int i = 0; i < 4; i++) {
            tl.addTask(new TaskItem("h", "d", "2020-11-06"));
        }
        assertThrows(IndexOutOfBoundsException.class, () -> {
           tl.removeTask(5);
        });
    }
    @Test
    public void loadingListFailsInvalidName(){
        TaskList tl = new TaskList();
        assertThrows(FileNotFoundException.class, () -> {
            tl.loadList("Strings.txt");
        });
    }
    @Test
    public void savedTaskListCanBeLoaded(){
        TaskList tl = new TaskList();
        File f = new File("src/TestTasks.txt");
        try{
            tl.loadList("NestorsTestTextFile00.txt");
            assertEquals("Title", tl.getItem(0).getTitle());
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }

    }
    @Test
    public void uncompletingTaskItemChangesStatus(){
        TaskList tl = new TaskList();
        try{
            tl.loadList("NestorsTestTextFile00.txt");
            tl.getItem(0).complete();
            assertEquals("[2020-07-21] *** Title: Desc", tl.getItem(0).toString());
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }
    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        try{
            tl.loadList("NestorsTestTextFile00.txt");

            assertThrows(IndexOutOfBoundsException.class, () -> {
                tl.getItem(4).incomplete();
            });
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }
    @Test
    public void canFullyReadinTasks(){
        TaskList tl = new TaskList();
        try{
            tl.loadList("NestorsSampleText00001.txt");
            assertEquals(4, tl.Size());
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }
    @Test
    public void canWritetoFile(){
        TaskList tl = new TaskList();
        try{
            for (int i = 0; i < 4; i++) {
                tl.addTask(new TaskItem("happy", "desc", "2020-11-06"));
            }
            String testfile = "NestorsSampleText00001.txt";
            tl.writeToFile(testfile);
            Scanner s = new Scanner(new File("src/" + testfile));
            assertEquals("happy::desc::2020-11-06",s.nextLine());
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }
    @Test
    public void updatesCompletedTasksWhenWriting(){
        TaskList tl = new TaskList();
        try{
            for (int i = 0; i < 4; i++) {
                tl.addTask(new TaskItem("happy", "desc", "2020-11-06"));
                if(i%2 == 0){
                    tl.getItem(i).complete();
                }
            }
            String testfile = "NestorsSampleText00002.txt";
            tl.writeToFile(testfile);
            Scanner s = new Scanner(new File("src/" + testfile));
            assertEquals("happy::desc::2020-11-06::c",s.nextLine());
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }
}