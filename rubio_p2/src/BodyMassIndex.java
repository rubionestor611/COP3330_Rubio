public class BodyMassIndex {
    private double height;
    private double weight;

    public BodyMassIndex(double height, double weight){
        this.height = height;
        this.weight = weight;
    }
    public double getHeight(){
        return this.height;
    }
    public double getWeight(){
        return this.weight;
    }
    public double calculateBMI(){
        return (703 * this.weight)/(Math.pow(this.height, 2));
    }
    public static double calculateBMI(double height, double weight){
        return (703 * weight)/(Math.pow(height, 2));
    }
    //Overloading bmiCategory method
    public String bmiCategory(){
        //sends the double value to precision of ten's place to get
        //same precision the categories use
        return bmiCategory(Double.parseDouble(String.format("%.1f", this.calculateBMI())));
    }
    public static String bmiCategory(double height, double weight){
        //sends the double value to precision of ten's place to get
        //same precision the categories use
        return bmiCategory(Double.parseDouble(String.format("%.1f", BodyMassIndex.calculateBMI(height, weight))));
    }
    public static String bmiCategory(double bmi){
        if(bmi < 18.5){
            return "Underweight";
        }else if(bmi >= 18.5 && bmi < 25){
            return "Normal Weight";
        }else if(bmi >= 25 && bmi < 30){
            return "Overweight";
        }
        return "Obese";
    }

}
