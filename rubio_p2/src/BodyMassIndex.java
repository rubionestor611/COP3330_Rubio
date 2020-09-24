public class BodyMassIndex {
    private double bmiRes;
    private String category;

    BodyMassIndex(double height, double weight){
        this.bmiRes = calculateBMI(height,weight);
        this.category = bmiCategory();
    }
    BodyMassIndex(double bmi){
        this.bmiRes = bmi;
        this.category = bmiCategory();
    }
    public double calculateBMI(double height, double weight){
        return (703 * weight)/(Math.pow(height, 2));
    }
    public double getBMIRes(){
        return this.bmiRes;
    }
    public String bmiCategory(){
        if(this.bmiRes < 18.5){
            return "Underweight";
        }else if(this.bmiRes >= 18.5 && this.bmiRes < 25){
            return "Normal Weight";
        }else if(this.bmiRes >= 25 && this.bmiRes < 30){
            return "Overweight";
        }
        return "Obese";
    }

}
