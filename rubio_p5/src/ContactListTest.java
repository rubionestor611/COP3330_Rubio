import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactListTest {
    @Test
    public void addingItemsIncreasesSize(){
        ContactList c = new ContactList();
        assertEquals(0, c.size());
        c.addContact(new ContactItem("Nestor","Rubio","3216549870","nessssss@smail.ney"));
        assertEquals(1, c.size());
    }
    @Test
    public void editingItemsFailsWithAllBlankValues(){
        ContactList c = new ContactList();
        c.addContact(new ContactItem("Nestor","Rubio","3216549870","nessssss@smail.ney"));
        assertThrows(IllegalArgumentException.class,() -> {
            c.updateContact(0,"","", "","");
        } );
    }
    @Test
    public void editingItemsFailsWithInvalidIndex(){
        ContactList c = new ContactList();
        c.addContact(new ContactItem("Nestor","Rubio","3216549870", "nessssss@smail.ney"));
        assertThrows(IndexOutOfBoundsException.class,() -> {
            c.updateContact(1,"Chris", "Last", "3216549870","cman@smail.ney");
        } );
    }
    @Test
    public void editingSucceedsWithBlankFirstName(){
        ContactList c = new ContactList();
        c.addContact(new ContactItem("Nestor","Rubio","3216549870", "nessssss@smail.ney"));
        c.updateContact(0,"","Last", "3216548870", "nes55s@smail.ney");
        assertEquals("Nestor", c.getContactFirstName(0));
    }
    @Test
    public void editingSucceedsWithBlankLastName(){
        ContactList c = new ContactList();
        c.addContact(new ContactItem("Nestor","Rubio","3216549870", "nessssss@smail.ney"));
        c.updateContact(0,"First","", "3216548870", "nes55s@smail.ney");
        assertEquals("Rubio", c.getContactLastName(0));
    }
    @Test
    public void editingSucceedsWithBlankPhone(){
        ContactList c = new ContactList();
        c.addContact(new ContactItem("Nestor","Rubio","3216549870", "nessssss@smail.ney"));
        c.updateContact(0,"First","Last", "", "nes55s@smail.ney");
        assertEquals("3216549870", c.getContactLastName(0));
    }
    @Test
    public void editingSucceedsWithNonBlankValues(){
        ContactList c = new ContactList();
        c.addContact(new ContactItem("Nestor","Rubio","3216549870", "nessssss@smail.ney"));
        c.updateContact(0,"First","Last", "0123456789", "nes55s@smail.ney");
        assertEquals("First Last: 0123456789: nes55s@smail.ney", c.contacttoString(0));
    }
    @Test
    public void newListIsEmpty(){
        ContactList c = new ContactList();
        assertEquals(true, c.isEmpty());
    }
    @Test
    public void removingItemsDecreasesSize(){
        ContactList c = new ContactList();
        assertEquals(0, c.size());
        c.addContact(new ContactItem("Nestor","Rubio","3216549870","nessssss@smail.ney"));
        assertEquals(1, c.size());
        c.removeContact(0);
        assertEquals(0, c.size());
    }
    @Test
    public void removingItemsFailsWithInvalidIndex(){
        ContactList c = new ContactList();
        c.addContact(new ContactItem("Nestor","Rubio","3216549870","nessssss@smail.ney"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            c.removeContact(1);
        });
    }
    @Test
    public void savedContactListCanBeLoaded(){
        ContactList c = new ContactList();
        assertDoesNotThrow(()->{
            c.loadList("NestorSampleContactFile00.txt");
            assertEquals(2,c.size());
        });
    }

}