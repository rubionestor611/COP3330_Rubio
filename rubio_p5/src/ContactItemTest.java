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
    public void creationSucceedsbutwithBlankPhoneNumberandEmail(){
        ContactItem c = new ContactItem("Me", "Way", "3523523523", "notgonnaprovideone");
        assertEquals("Me Way: : ", c.toString());
    }
    @Test
    public void creationSkipsPhoneifWrongFormat(){
        ContactItem c = new ContactItem("First", "Last", "3216549870", "x@y.z");
        assertEquals("", c.getPhonenumber());
    }
    @Test
    public void creationSkipsEmailifWrongFormat(){
        ContactItem c = new ContactItem("First", "Last", "321-654-9870", "x@y");
        assertEquals("", c.getEmail());
    }
    @Test
    public void creationSucceedsWithBlankEmail(){
        assertDoesNotThrow(()->{
            ContactItem c = new ContactItem("Nestor","Rubio","012-345-6789","");
        });
    }
    @Test
    public void creationSucceedsWithBlankFirstName(){
        assertDoesNotThrow(()->{
            ContactItem c = new ContactItem("","Rubio","012-345-6789","neee@nope.net");
        });
    }
    @Test
    public void creationSucceedsWithBlankLastName(){
        assertDoesNotThrow(()->{
            ContactItem c = new ContactItem("Nestor","","012-345-6789","neee@nope.net");
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
            ContactItem c = new ContactItem("Nestor","Rubio","012-345-6789","neee@nope.net");
        });
    }
    @Test
    public void editingFailsWithAllBlankValues(){
        ContactItem c = new ContactItem("Nestor","Rubio","012-345-6789","neee@nope.net");
        assertThrows(IllegalArgumentException.class, () -> {
            c.update("","","","");
        });
    }
    @Test
    public void editingSucceedsWithBlankEmail(){
        ContactItem c = new ContactItem("Nestor","Rubio","012-345-6789","neee@nope.net");
        assertDoesNotThrow(()->{
            c.update("First","Last","987-654-3210","");
        });
    }
    @Test
    public void editingSucceedsWithBlankFirstName(){
        ContactItem c = new ContactItem("Nestor","Rubio","012-345-6789","neee@nope.net");
        assertDoesNotThrow(()->{
            c.update("","Last","987-654-3210","nope@neee.net");
        });
    }
    @Test
    public void editingSucceedsWithBlankLastName(){
        ContactItem c = new ContactItem("Nestor","Rubio","012-345-6789","neee@nope.net");
        assertDoesNotThrow(()->{
            c.update("First","","987-654-3210","nope@neee.net");
        });
    }
    @Test
    public void editingSucceedsWithBlankPhone(){
        ContactItem c = new ContactItem("Nestor","Rubio","012-345-6789","neee@nope.net");
        assertDoesNotThrow(()->{
            c.update("First","Last","","nope@neee.net");
        });
    }
    @Test
    public void editingSucceedsWithNonBlankValues(){
        ContactItem c = new ContactItem("Nestor","Rubio","012-345-6789","neee@nope.net");
        assertDoesNotThrow(()->{
            c.update("First","Last","987-654-3210","nope@neee.net");
        });
    }
    @Test
    public void testToString(){
        ContactItem c = new ContactItem("Nestor","Rubio","012-345-6789","neee@nope.net");
        assertEquals("Nestor Rubio: 012-345-6789: neee@nope.net", c.toString());
    }

}