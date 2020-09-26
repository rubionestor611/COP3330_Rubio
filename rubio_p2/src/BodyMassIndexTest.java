import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class BodyMassIndexTest {
    //following are the 4 tests for different categories
    @Test
    public void getUnderweightTest(){
        BodyMassIndex user = new BodyMassIndex(69.04, 120.35);
        assertEquals("Underweight", user.bmiCategory());
    }
    @Test
    public void getNormal_WeightTest(){
        BodyMassIndex user = new BodyMassIndex(70.01, 150);
        assertEquals("Normal Weight", user.bmiCategory());
    }
    @Test
    public void getOverweightTest(){
        BodyMassIndex user = new BodyMassIndex(69.87, 190.08);
        assertEquals("Overweight", user.bmiCategory());
    }
    @Test
    public void getObeseTest(){
        BodyMassIndex user = new BodyMassIndex(60.85, 180);
        assertEquals("Obese", user.bmiCategory());
    }
    //following tests for public method in BodyMassIndex
    @Test
    public void constructorTest(){
        BodyMassIndex b = new BodyMassIndex(56.87, 87.59);
        assertNotEquals(b, null);
    }
    @Test
    public void calculateBMI1(){
        BodyMassIndex user = new BodyMassIndex(70, 180.56);
        assertEquals(25.8, user.calculateBMI(), .15);
    }
    @Test
    public void BMIcategoryTest(){
        BodyMassIndex user = new BodyMassIndex(68, 150);
        assertEquals("Normal Weight", user.bmiCategory());
    }
    @Test
    public void CalculateBMI2(){
        BodyMassIndex user = new BodyMassIndex(85,260);
        assertEquals(25.3, user.calculateBMI(), .1);
    }
    @Test
    public void getHeightTest(){
        BodyMassIndex b = new BodyMassIndex(56.897, 87.56);
        assertEquals(56.897, b.getHeight());
    }
    @Test
    public void getWeightTest(){
        BodyMassIndex b = new BodyMassIndex(56.897, 87.56);
        assertEquals(87.56, b.getWeight());
    }
    @Test
    public void staticCategoryCallTest(){
        assertEquals("Obese", BodyMassIndex.bmiCategory(35.97));
    }

}