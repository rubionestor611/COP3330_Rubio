import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecrypterTest {
    @Test
    void decrypt(){
        Decrypter myDecrypter = new Decrypter();
        assertEquals("1234", myDecrypter.decrypt("0189"));
    }
    @Test
    void decrypt8888(){
        Decrypter myDecrypter = new Decrypter();
        assertEquals("1111", myDecrypter.decrypt("8888"));
    }
    @Test
    void decrypt4568(){
        Decrypter myDecrypter = new Decrypter();
        assertEquals("9178", myDecrypter.decrypt("4568"));
    }
}