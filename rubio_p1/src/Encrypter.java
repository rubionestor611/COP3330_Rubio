public class Encrypter {
    public String encrypt(String numString){
        //convert string to integer
        int number = Integer.parseInt(numString);
        //initialize array of ints
        int[] numArray = initializenumArray(number, numString.length());
        //swap first and third then second and fourth digits
        swap(numArray, 0, 2);
        swap(numArray, 1, 3);
        //apply modification to each integer in array per instructions
        for(int y = 0; y < numArray.length; y++){
            numArray[y] = modify(numArray[y]);
        }
        //turn array of ints into string to return
        return arraytoString(numArray);
    }
    private static int[] initializenumArray(int num, int len){
        //create array w/ enough room for 4 digits
        int[] ret = new int[len];
        //break integer into 4 separate digits making each int in array
        //one of the digits in num
        for(int i = len - 1; i >= 0; i--){
            ret[i] = num % 10;
            num /= 10;
        }

        return ret;
    }
    private static void swap(int[] array, int lo, int hi){
        //basic int swap but within integer array
        int x = array[lo];

        array[lo] = array[hi];

        array[hi] = x;
    }
    private static int modify(int a){
        //modifications per instructions
        a += 7;

        a = a % 10;

        return a;
    }
    private static String arraytoString(int[] numArray){
        //turn array into string for return
        String ret = "";

        for(int y = 0; y < numArray.length; y++){
            ret += String.valueOf(numArray[y]);
        }

        return ret;
    }
}
