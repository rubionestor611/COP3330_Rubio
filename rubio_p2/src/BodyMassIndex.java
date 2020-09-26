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
    public String bmiCategory(){
        double bmi = calculateBMI();
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
