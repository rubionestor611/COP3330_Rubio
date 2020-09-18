import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BodyMassIndexTest {
//Math.ceil(b.bmiRes * 10) /10 to round up to decimals place

    @org.junit.jupiter.api.Test
    void calculateBMITwoNormalInputs() {
        BodyMassIndex b = new BodyMassIndex(69.1,150);
        assertEquals(22.1,Math.ceil(b.bmiRes * 10) /10);
    }
}