public class Main {
    public static void main(String[] args){
        Encrypter myEncrypter = new Encrypter();
        String num = myEncrypter.encrypt("1234");
        System.out.println(num);
    }
}
