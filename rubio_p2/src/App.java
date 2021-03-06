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
        double ret;
        try{
            Scanner in = new Scanner(System.in);
            System.out.print("Enter user height in inches: ");
            //scanning string line to ensure something like "70.25 something" doesn't pass as "70.25"
            String input = in.nextLine();
            ret = Double.parseDouble(input);
            while (ret <= 0){
                System.out.print("Please provide a positive value for the user's height in inches :");
                input = in.nextLine();
                ret = Double.parseDouble(input);
            }
        }catch (Exception e){
            //any exception is strictly the result of wrong formatting or overflowing the space
            //allocated for a double
            System.out.println("\nYou need to provide a decimal value representing\n" +
                    "the user's height in inches.\n");
            return getUserHeight();
        }
        return ret;
    }

    public static double getUserWeight(){
        double ret;
        try{
            Scanner in = new Scanner(System.in);
            System.out.print("Enter user weight in pounds (lbs): ");
            //scanning string line to ensure something like "70.25 something" doesn't pass as "70.25"
            String input = in.nextLine();
            ret = Double.parseDouble(input);
            while (ret <= 0){
                System.out.print("Please provide a positive value for the user's weight in pounds (lbs) :");
                input = in.nextLine();
                ret = Double.parseDouble(input);
            }
        }catch (Exception e){
            //any exception is strictly the result of wrong formatting or overflowing the space
            //allocated for a double
            System.out.println("\nYou need to provide a decimal value representing\n" +
                    "the user's weight in pounds (lbs).\n");
            return getUserWeight();
        }
        return ret;
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
        //"BodyMassIndex.bmiCategory(Double.parseDouble(String.format("%.1f", averageBMI)))"
        //will send in a decimal value to the ten's place, to better use the categories given by the NHLBI
        System.out.print("This means the average person in the provided\n" +
                "population would be in the " + BodyMassIndex.bmiCategory(Double.parseDouble(String.format("%.1f", averageBMI)))+
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

        System.out.println("\nThis qualifies this user in the " + userBMI.bmiCategory() + " category.\n");
    }
}
