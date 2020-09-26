import java.util.ArrayList;
import java.util.InputMismatchException;
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
        try{
            Scanner in = new Scanner(System.in);
            System.out.print("Enter user height in inches: ");
            String input = in.nextLine();

            return Double.parseDouble(input);
        }catch (Exception e){
            //any exception is strictly the result of wrong formatting or overflowing the space
            //allocated for a double
            System.out.println("\nYou need to provide a decimal value representing\n" +
                    "the user's height in inches.\n");
            return getUserHeight();
        }
    }

    public static double getUserWeight(){
        try{
            Scanner in = new Scanner(System.in);
            System.out.print("Enter user weight in pounds (lbs): ");
            String input = in.nextLine();

            return Double.parseDouble(input);
        }catch (Exception e){
            //any exception is strictly the result of wrong formatting or overflowing the space
            //allocated for a double
            System.out.println("\nYou need to provide a decimal value representing\n" +
                    "the user's weight in pounds (lbs).\n");
            return getUserWeight();
        }
    }

    public static void displayBmiStatistics(ArrayList<BodyMassIndex> bmiData){
        if(bmiData.size() == 0){
            //if no data was to be found in the arrayList
            System.out.println("\nNo values were provided so no average can be calculated.");
            return;
        }
        double averageBMI = 0.0;

        for(BodyMassIndex b: bmiData){
            averageBMI += b.calculateBMI();
        }

        averageBMI /= bmiData.size();

        System.out.println("Average BMI for the population provided(based on height and weight values provided):\n");
        System.out.println("\t" + String.format("%.1f",averageBMI) + "\n");

        System.out.print("This means the average person in the provided\n" +
                "population would be in the " + BodyMassIndex.bmiCategory(averageBMI)+
                " category.\n");
    }

    public static boolean moreInput(){
        Scanner in = new Scanner(System.in);
        System.out.print("Do you want to add a user's information to calculate their BMI? (Y/N): ");
        String input = in.nextLine();
        //while input is longer than 1 and it is not an "n" or "y" response, it will keep asking
        while(input.length() != 1 ||
                (!input.equalsIgnoreCase("y") &&
                        !input.equalsIgnoreCase("n"))){
            System.out.print("Please provide a Y or N (Yes or No) answer.\n" +
                    "Do you want to add a user's information to calculate their BMI?: ");
            input = in.nextLine();
        }
        return input.equalsIgnoreCase("Y");
    }

    public static void displayBmiInfo(BodyMassIndex userBMI) {
        System.out.println("User's calculated BMI is\n\t" + String.format("%.1f", userBMI.calculateBMI()));

        String category = userBMI.bmiCategory();

        System.out.println("\nThis qualifies this user in the " + category + " category.\n");
    }
}
