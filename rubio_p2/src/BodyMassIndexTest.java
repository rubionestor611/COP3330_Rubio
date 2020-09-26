import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class BodyMassIndexTest {
//Math.ceil(b.bmiRes * 10) /10 to round up to decimals place
    //following are the 4 tests for different categories
    @Test
    public void getUnderweight(){
        BodyMassIndex user = new BodyMassIndex(69.04, 120.35);
        assertEquals("Underweight", user.bmiCategory());
    }
    @Test
    public void getNormal_Weight(){
        BodyMassIndex user = new BodyMassIndex(70.01, 150);
        assertEquals("Normal Weight", user.bmiCategory());
    }
    @Test
    public void getOverweight(){
        BodyMassIndex user = new BodyMassIndex(69.87, 190.08);
        assertEquals("Overweight", user.bmiCategory());
    }
    @Test
    public void getObese(){
        BodyMassIndex user = new BodyMassIndex(60.85, 180);
        assertEquals("Obese", user.bmiCategory());
    }
    //following tests 1 per public method in BodyMassIndex
    @Test
    public void calculateBMI1(){
        BodyMassIndex user = new BodyMassIndex(70, 180.56);
        assertEquals(25.8, user.calculateBMI(), .15);
    }
    @Test
    public void BMIcategorycall(){
        BodyMassIndex user = new BodyMassIndex(68, 150);
        assertEquals("Normal Weight", user.bmiCategory());
    }
    @Test
    public void getBMIResTest(){
        BodyMassIndex user = new BodyMassIndex(85,260);
        assertEquals(25.3, user.calculateBMI(), .1);
    }

}