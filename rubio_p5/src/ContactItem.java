import java.util.InputMismatchException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class ContactItem {
    private String firstname;
    private String lastname;
    private String phonenumber;
    private String email;
    public ContactItem(String firstname,String lastname, String phone, String email) throws IllegalArgumentException, InputMismatchException, InstantiationError {
        if(firstname.length()+lastname.length()+phone.length()+email.length() == 0){
            throw new InstantiationError();
        }
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phonenumber = phone;
    }
    public void update(String firstname,String lastname, String phone, String email) throws IllegalArgumentException{
        if(firstname.length()+lastname.length()+phone.length()+email.length() == 0){
            throw new IllegalArgumentException();
        }
        if(firstname.length() > 0){
            setFirstName(firstname);
        }
        if(lastname.length() > 0){
            setLastName(lastname);
        }
        if(phonenumber.length() > 0){
            this.setPhonenumber(phone);
        }
        if(isValidEmail(email)){
            this.email = email;
        }
    }
    private static boolean isValidEmail(String email){
        Pattern p = Pattern.compile("^(.+)@(.+)$");
        Matcher m = p.matcher(email);
        return m.matches();
    }
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }
    public String getLastName() {
        return this.lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString(){
        return this.firstname + " " +this.lastname + ": " + this.phonenumber + ": " + this.email;
    }
    public String toWriteFormat(){
        return this.firstname + "::" + this.lastname  + "::" + this.phonenumber
                + "::" + this.email;
    }
}
