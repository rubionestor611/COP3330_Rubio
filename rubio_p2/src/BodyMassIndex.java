public class BodyMassIndex {
    public double bmiRes;

    BodyMassIndex(double height, double weight){
        bmiRes = (703 * weight)/(Math.pow(height, 2));
        //System.out.println("bmi res % 1 " + bmiRes % 1);
        //calculateBMI(height, weight)
    }
    public static double calculateBMI(double height, double weight){
        return (703 * weight)/(Math.pow(height, 2));
    }

}
