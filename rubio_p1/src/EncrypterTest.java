import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncrypterTest {

    @Test
    void encrypt() {
        Encrypter myEncrypter = new Encrypter();
        assertEquals("0189", myEncrypter.encrypt("1234"));
    }

    @Test
    void encrypt0000(){
        Encrypter myEncrypter = new Encrypter();
        assertEquals("7777", myEncrypter.encrypt("0000"));
    }
    @Test
    void encrypt5432(){
        Encrypter myEncrypter = new Encrypter();
        assertEquals("0921", myEncrypter.encrypt("5432"));
    }
}