public class Decrypter {
    public String decrypt(String number){
        int numInput = Integer.parseInt(number);
        int[] numArray = initializeNumArray(numInput);
        for(int x = 0; x < 4; x++){
            numArray[x] = modify(numArray[x]);
        }
        swap(numArray, 0, 2);
        swap(numArray, 1, 3);

        //printintArray(numArray);
        return arraytoString(numArray);
    }
    private static int[] initializeNumArray(int num){
        int[] ret = new int[4];
        for(int i = 3; i >= 0; i--){
            ret[i] = num % 10;
            num /= 10;
        }
        return ret;
    }
    private static int modify(int a){
        if(a >= 7){
            return a - 7;
        }else{
            return a + 3;
        }
    }
    private static void swap(int[] a, int lo, int hi){
        int x = a[lo];
        a[lo] = a[hi];
        a[hi] = x;
    }
    public static void printintArray(int[] a){
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i]);
        }
    }
    private static String arraytoString(int[] a){
        String ret = "";
        for(int i = 0; i < a.length; i++){
            ret += String.valueOf(a[i]);
        }
        return ret;
    }
}
