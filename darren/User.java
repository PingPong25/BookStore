import java.util.Date;

public class User {
    private String username;
    private String password;
    private char gender;
    private String phoneNo;
    private Date birthDate;

    // Constructor
    public User(String username, String password, char gender, String phoneNo, Date birthDate) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.phoneNo = phoneNo;
        this.birthDate = birthDate;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public char getGender() {
        return gender;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    // toString method to display user details
    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + 
               ", gender=" + gender + ", phoneNo=" + phoneNo + 
               ", birthDate=" + birthDate + "]";
    }
}
