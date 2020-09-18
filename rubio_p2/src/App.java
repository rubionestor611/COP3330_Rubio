import java.util.ArrayList;
import java.util.Scanner;

public class App {
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

    public static double getUserHeight(){
        double input;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter user height: ");
        input = in.nextDouble();
        //user input control, ensuring input is not less than 0
        while(input < 0){
            System.out.print("Please provide an accurate height greater than 0: ");
            input = in.nextDouble();
        }
        return input;
    }

    public static double getUserWeight(){
        double input;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter user weight: ");
        input = in.nextDouble();
        //user input control, ensuring input is not less than 0
        while(input < 0){
            System.out.print("Please provide an accurate weight greater than 0: ");
            input = in.nextDouble();
        }
        return input;
    }

    public static void displayBmiStatistics(ArrayList<BodyMassIndex> bmiData){
        if(bmiData.size() == 0){
            System.out.println("\nNo values were provided so no average can be calculated.");
            return;
        }
        double averageBMI = 0;
        //add every recorded BMI in the ArrayList
        for(BodyMassIndex b: bmiData){
            averageBMI += b.bmiRes;
        }
        //divide by # of items/BMIs in list
        //to finalize the average
        averageBMI /= bmiData.size();

        System.out.println("Average BMI for the population provided:\n");
        System.out.println("\t" + averageBMI);
    }

    public static boolean moreInput(){
        //Y/N
        Scanner in = new Scanner(System.in);
        System.out.print("Do you want to add a user's information to calculate their BMI? (Y/N): ");
        String input = in.nextLine();
        while(input.length() > 1){
            System.out.print("Please provide a Y or N (Yes or No) answer.\n" +
                    "Do you want to add another user's information to calculate their BMI?: ");
            input = in.nextLine();
        }
        return input.equalsIgnoreCase("Y");
    }

    public static void displayBmiInfo(BodyMassIndex userbmi){
        System.out.println("User's calculated BMI is\n\t" + userbmi.bmiRes);

        String category = chooseCategory(userbmi.bmiRes);

        System.out.println("\nThis qualifies this user in the " + category + " category.\n");

        //displayCategories();
    }

    public static String chooseCategory(double bmi){
        if(bmi < 18.5){
            return "Underweight";
        }else if(bmi >= 18.5 && bmi < 25){
            return "Normal Weight";
        }else if(bmi >= 25 && bmi < 30){
            return "Overweight";
        }
        return "Obesity";
    }

    public static void displayCategories(){
        System.out.println("Here is some helpful information on how\nthe BMI information gets categorized: ");
        System.out.println(
                "\tUnderweight <= 18.5 \n" +
                "\tNormal weight = 18.5 – 24.9 \n" +
                "\tOverweight = 25 – 29.9 \n" +
                "\tObesity = BMI of 30 or greater\n");
    }
}
