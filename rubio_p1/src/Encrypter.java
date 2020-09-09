public class Encrypter {
    public String encrypt(String num){
        //convert string to integer
        int number = Integer.parseInt(num);
        //initialize array of ints
        int[] numArray = initializenumArray(number);
        //swap first and third then second and fourth digits
        swap(numArray, 0, 2);
        swap(numArray, 1, 3);
        //apply modification to each integer in array per instructions
        for(int y = 0; y < 4; y++){
            numArray[y] = modify(numArray[y]);
        }
        //turn array of ints into string to return
        return finalizeEncryptedData(numArray);
    }
    private static int[] initializenumArray(int num){
        //create array w/ enough room for 4 digits
        int[] ret = new int[4];
        //break integer into 4 separate digits making each int in array
        //one of the digits in num
        for(int i = 3; i >= 0; i--){
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
    private static String finalizeEncryptedData(int[] numArray){
        //turn array into string for return
        String ret = "";
        for(int y = 0; y < 4; y++){
            ret += String.valueOf(numArray[y]);
        }
        return ret;
    }
}
