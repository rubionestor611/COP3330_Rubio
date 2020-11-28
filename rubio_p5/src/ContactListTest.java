import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactListTest {

    @Test
    public void abstractionSucceeds(){
        TypeList cl = new ContactList();
        assertEquals(true, cl.isEmpty());
    }
    @Test
    public void addingItemsIncreasesSize(){
        ContactList c = new ContactList();
        assertEquals(0, c.size());
        c.addContact(new ContactItem("Nestor","Rubio","321-654-9870","nessssss@smail.ney"));
        assertEquals(1, c.size());
    }
    @Test
    public void editingItemsFailsWithAllBlankValues(){
        ContactList c = new ContactList();
        c.addContact(new ContactItem("Nestor","Rubio","321-654-9870","nessssss@smail.ney"));
        assertThrows(IllegalArgumentException.class,() -> {
            c.updateContact(0,"","", "","");
        } );
    }
    @Test
    public void editingItemsFailsWithInvalidIndex(){
        ContactList c = new ContactList();
        c.addContact(new ContactItem("Nestor","Rubio","321-654-9870", "nessssss@smail.ney"));
        assertThrows(IndexOutOfBoundsException.class,() -> {
            c.updateContact(1,"Chris", "Last", "321-654-9870","cman@smail.ney");
        } );
    }
    @Test
    public void editingSucceedsWithBlankFirstName(){
        ContactList c = new ContactList();
        c.addContact(new ContactItem("Nestor","Rubio","321-654-9870", "nessssss@smail.ney"));
        c.updateContact(0,"","Last", "321-654-8870", "nes55s@smail.ney");
        assertEquals("Nestor", c.getContactFirstName(0));
    }
    @Test
    public void editingSucceedsWithBlankLastName(){
        ContactList c = new ContactList();
        c.addContact(new ContactItem("Nestor","Rubio","321-654-9870", "nessssss@smail.ney"));
        c.updateContact(0,"First","", "321-654-8870", "nes55s@smail.ney");
        assertEquals("Rubio", c.getContactLastName(0));
    }
    @Test
    public void editingSucceedsWithBlankPhone(){
        ContactList c = new ContactList();
        c.addContact(new ContactItem("Nestor","Rubio","321-654-9870", "nessssss@smail.ney"));
        c.updateContact(0,"First","Last", "", "nes55s@smail.ney");
        assertEquals("Last", c.getContactLastName(0));
    }
    @Test
    public void editingSucceedsWithBlankPhoneandEmail(){
        ContactList c = new ContactList();
        c.addContact(new ContactItem("Nestor","Rubio","321-654-9870", "nessssss@smail.ney"));
        c.updateContact(0,"NO", "Way", "", "");
        assertEquals("NO::Way::321-654-9870::nessssss@smail.ney::",c.getContact(0).toWriteFormat());
    }
    @Test
    public void editingSucceedsWithBlankFirstandLastNames(){
        ContactList c = new ContactList();
        c.addContact(new ContactItem("Nestor","Rubio","321-654-9870", "nessssss@smail.ney"));
        c.updateContact(0,"", "", "000-000-0000", "new@email.net");
        assertEquals("Nestor::Rubio::000-000-0000::new@email.net::",c.getContact(0).toWriteFormat());
    }
    @Test
    public void editingSucceedsWithNonBlankValues(){
        ContactList c = new ContactList();
        c.addContact(new ContactItem("Nestor","Rubio","321-654-9870", "nessssss@smail.ney"));
        c.updateContact(0,"First","Last", "012-345-6789", "nes55s@smail.ney");
        assertEquals("First Last: 012-345-6789: nes55s@smail.ney", c.contacttoString(0));
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
        c.addContact(new ContactItem("Nestor","Rubio","321-654-9870","nessssss@smail.ney"));
        assertEquals(1, c.size());
        c.removeContact(0);
        assertEquals(0, c.size());
    }
    @Test
    public void removingItemsFailsWithInvalidIndex(){
        ContactList c = new ContactList();
        c.addContact(new ContactItem("Nestor","Rubio","321-654-9870","nessssss@smail.ney"));
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