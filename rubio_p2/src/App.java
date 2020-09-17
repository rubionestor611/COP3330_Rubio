import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        ArrayList<BodyMassIndex> bmiData = new ArrayList<BodyMassIndex>();
        while (moreInput()) {
            double height = getUserHeight();
            double weight = getUserWeight();

            BodyMassIndex bmi = new BodyMassIndex(height, weight);
            bmiData.add(bmi);

            displayBmiInfo(bmi);
        }

        displayBmiStatistics(bmiData);
    }
    public static double getUserHeight(){
        double ret;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter user height: ");
        ret = in.nextDouble();
        while(ret < 0){
            System.out.print("Please provide an accurate height greater than 0: ");
            ret = in.nextDouble();
        }
        return ret;
    }
    public static double getUserWeight(){
        double ret;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter user weight: ");
        ret = in.nextDouble();
        while(ret < 0){
            System.out.print("Please provide an accurate weight greater than 0: ");
            ret = in.nextDouble();
        }
        return ret;
    }
    public static void displayBmiStatistics(ArrayList<BodyMassIndex> bmiData){
        double averageBMI = 0;
        for(BodyMassIndex b: bmiData){
            averageBMI += b.bmiRes;
        }
        averageBMI /= bmiData.size();
        System.out.println("Average BMI for the populations provided:\n");
        System.out.println("\t" + averageBMI);
    }
    public static boolean moreInput(){
        Scanner in = new Scanner(System.in);
        System.out.print("Do you want to add a user's information to calculate their BMI? (Y/N): ");
        String input = in.nextLine();
        return input.equalsIgnoreCase("Y");
    }
    public static void displayBmiInfo(BodyMassIndex userbmi){
        System.out.println("User's calculated BMI is " + userbmi.bmiRes);
        double bmi = userbmi.bmiRes;
        String category = "";
        if(bmi < 18.5){
            category = "Underweight";
        }else if(bmi >= 18.5 && bmi < 25){
            category = "Normal Weight";
        }else if(bmi >= 25 && bmi < 30){
            category = "Overweight";
        }else if(bmi >= 30){
            category = "Obesity";
        }
        System.out.print("This qualifies this user in the " + category + " category.\n");
        displayCategories();
    }
    public static void displayCategories(){
        System.out.println("Here is some helpful information on how\nthe BMI information gets catrgorized: ");
        System.out.println(
                "\tUnderweight <= 18.5 \n" +
                "\tNormal weight = 18.5 – 24.9 \n" +
                "\tOverweight = 25 – 29.9 \n" +
                "\tObesity = BMI of 30 or greater\n");
    }
    /*
    public static void main(String[] args) {
    ArrayList<BodyMassIndex> bmiData = new ArrayList<BodyMassIndex>();

    while (moreInput()) {
        double height = getUserHeight();
        double weight = getUserWeight();

        BodyMassIndex bmi = new BodyMassIndex(height, weight);
        bmiData.add(bmi);

        displayBmiInfo(bmi);
    }

    displayBmiStatistics(bmiData);
}
     */
}
