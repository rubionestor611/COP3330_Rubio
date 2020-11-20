public class ContactItem {
    private String name;
    private String phonenumber;
    private String email;
    public ContactItem(String name, String phone, String email){
        this.name = name;
        this.email = email;
        this.phonenumber = phone;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString(){
        return this.name + this.phonenumber + this.email;
    }
}
