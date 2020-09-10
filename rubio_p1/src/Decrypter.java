public class Decrypter {
    public String decrypt(String number){
        //convert string to integer
        int numInput = Integer.parseInt(number);
        //initialize integer array
        int[] numArray = initializeNumArray(numInput);
        //modify each integer in array as to decrypt as per instructions
        for(int x = 0; x < 4; x++){
            numArray[x] = modify(numArray[x]);
        }
        //swap integers in array as instructions specify
        swap(numArray, 0, 2);
        swap(numArray, 1, 3);
        //return string form of new array
        return arraytoString(numArray);
    }
    private static int[] initializeNumArray(int num){
        //converts intger to integer array of size 4 as we expect in instructions
        int[] ret = new int[4];
        //take remainders and add to integer array, works in decimal numbers when modding by 10
        //and intger-dividing by 10 to move on to the next remainder
        for(int i = 3; i >= 0; i--){
            ret[i] = num % 10;
            num /= 10;
        }

        return ret;
    }
    private static int modify(int a){
        //reverse of encryption method provided in instruction
        if(a >= 7){
            return a - 7;
        }else{
            return a + 3;
        }
    }
    private static void swap(int[] a, int lo, int hi){
        //simple swap of int values in integer array
        int x = a[lo];
        a[lo] = a[hi];
        a[hi] = x;
    }
    public static void printintArray(int[] a){
        //public method for printing array used to show array was of correct values
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i]);
        }
    }
    private static String arraytoString(int[] a){
        //concatonates the value of int rray to string to form one string result with all integers
        //the array contains
        String ret = "";
        for(int i = 0; i < a.length; i++){
            ret += String.valueOf(a[i]);
        }
        return ret;
    }
}
