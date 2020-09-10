public class Main {
    public static void main(String[] args){
        System.out.println("Encrypting 1234");
        Encrypter myEncrypter = new Encrypter();
        String num = myEncrypter.encrypt("1111");
        System.out.println(num);
        System.out.println("now for decrypter");
        Decrypter myDecrypter = new Decrypter();
        String str = myDecrypter.decrypt("8888");
        System.out.println(str);
    }
}
