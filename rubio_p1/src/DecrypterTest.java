import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecrypterTest {
    @Test
    void decrypt(){
        Decrypter myDecrypter = new Decrypter();
        assertEquals("1234", myDecrypter.decrypt("0189"));
    }

}