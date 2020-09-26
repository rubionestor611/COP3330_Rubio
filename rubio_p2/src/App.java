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
        System.out.print("Enter user height in inches: ");
        input = in.nextDouble();
        //user input control, ensuring input is not less than 0
        while(input < 0){
            System.out.print("Please provide an accurate height greater than 0 in inches: ");
            input = in.nextDouble();
        }
        return input;
    }

    public static double getUserWeight(){
        double input;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter user weight in pounds: ");
        input = in.nextDouble();
        //user input control, ensuring input is not less than 0
        while(input < 0){
            System.out.print("Please provide an accurate weight greater than 0 in pounds: ");
            input = in.nextDouble();
        }
        return input;
    }

    public static void displayBmiStatistics(ArrayList<BodyMassIndex> bmiData){
        if(bmiData.size() == 0){
            System.out.println("\nNo values were provided so no average can be calculated.");
            return;
        }
        double averageHeight = 0;
        double averageWeight = 0;
        //add every recorded BMI in the ArrayList
        for(BodyMassIndex b: bmiData){
            averageHeight += b.getHeight();
            averageWeight += b.getWeight();
        }
        //divide by # of items/BMIs in list
        //to finalize the average
        averageHeight /= bmiData.size();
        averageWeight /= bmiData.size();
        BodyMassIndex averageBMIres = new BodyMassIndex(averageHeight, averageWeight);

        System.out.println("Average BMI for the population provided(based on height and weight values provided):\n");
        System.out.println("\t" + String.format("%.1f",averageBMIres.calculateBMI()) + "\n");

        System.out.print("This means the average person in the provided\n" +
                "population would be in the " + averageBMIres.bmiCategory()+
                " category.\n");
    }

    public static boolean moreInput(){
        Scanner in = new Scanner(System.in);
        System.out.print("Do you want to add a user's information to calculate their BMI? (Y/N): ");
        String input = in.nextLine();
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
