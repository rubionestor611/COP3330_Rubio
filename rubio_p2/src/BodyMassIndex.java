public class BodyMassIndex {
    public double bmiRes;

    BodyMassIndex(double height, double weight){
        bmiRes = calculateBMI(height, weight);
    }
    public static double calculateBMI(double height, double weight){
        return (703 * weight)/(Math.pow(height, 2));
    }


}
