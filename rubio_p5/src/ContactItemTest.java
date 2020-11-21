import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactItemTest {
    @Test
    public void creationFailsWithAllBlankValues(){
        assertThrows(InstantiationError.class, () -> {
            ContactItem c = new ContactItem("","","","");
        });
    }
    @Test
    public void creationSucceedsWithBlankEmail(){
        assertDoesNotThrow(()->{
            ContactItem c = new ContactItem("Nestor","Rubio","0123456789","");
        });
    }
    @Test
    public void creationSucceedsWithBlankFirstName(){
        assertDoesNotThrow(()->{
            ContactItem c = new ContactItem("","Rubio","0123456789","neee@nope.net");
        });
    }
    @Test
    public void creationSucceedsWithBlankLastName(){
        assertDoesNotThrow(()->{
            ContactItem c = new ContactItem("Nestor","","0123456789","neee@nope.net");
        });
    }
    @Test
    public void creationSucceedsWithBlankPhone(){
        assertDoesNotThrow(()->{
            ContactItem c = new ContactItem("Nestor","Rubio","","neee@nope.net");
        });
    }
    @Test
    public void creationSucceedsWithNonBlankValues(){
        assertDoesNotThrow(()->{
            ContactItem c = new ContactItem("Nestor","Rubio","0123456789","neee@nope.net");
        });
    }
    @Test
    public void editingFailsWithAllBlankValues(){
        ContactItem c = new ContactItem("Nestor","Rubio","0123456789","neee@nope.net");
        assertThrows(IllegalArgumentException.class, () -> {
            c.update("","","","");
        });
    }
    @Test
    public void editingSucceedsWithBlankEmail(){
        ContactItem c = new ContactItem("Nestor","Rubio","0123456789","neee@nope.net");
        assertDoesNotThrow(()->{
            c.update("First","Last","9876543210","");
        });
    }
    @Test
    public void editingSucceedsWithBlankFirstName(){
        ContactItem c = new ContactItem("Nestor","Rubio","0123456789","neee@nope.net");
        assertDoesNotThrow(()->{
            c.update("","Last","9876543210","nope@neee.net");
        });
    }
    @Test
    public void editingSucceedsWithBlankLastName(){
        ContactItem c = new ContactItem("Nestor","Rubio","0123456789","neee@nope.net");
        assertDoesNotThrow(()->{
            c.update("First","","9876543210","nope@neee.net");
        });
    }
    @Test
    public void editingSucceedsWithBlankPhone(){
        ContactItem c = new ContactItem("Nestor","Rubio","0123456789","neee@nope.net");
        assertDoesNotThrow(()->{
            c.update("First","Last","","nope@neee.net");
        });
    }
    @Test
    public void editingSucceedsWithNonBlankValues(){
        ContactItem c = new ContactItem("Nestor","Rubio","0123456789","neee@nope.net");
        assertDoesNotThrow(()->{
            c.update("First","Last","9876543210","nope@neee.net");
        });
    }
    @Test
    public void testToString(){
        ContactItem c = new ContactItem("Nestor","Rubio","0123456789","neee@nope.net");
        assertEquals("Nestor Rubio: 0123456789: neee@nope.net", c.toString());
    }

}