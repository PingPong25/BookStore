import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Registration {
    private String username;
    private String password;
    private char gender;
    private String phoneNo;
    private Date birthDate;

    // Constructor
    public Registration() {
        // Empty constructor, allowing for multiple registrations
    }

    // Method to set registration details
    public void setDetails(String username, String password, char gender, String phoneNo, LocalDate birthDate) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.phoneNo = phoneNo;
        // Convert LocalDate to Date
        this.birthDate = Date.from(birthDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    // Method to register a user
    public User register() {
        return new User(username, password, gender, phoneNo, birthDate);
    }

    // Getters for fields (optional, if needed)
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

    // toString method to display registration details
    @Override
    public String toString() {
        return "Registration [username=" + username + ", password=" + password + 
               ", gender=" + gender + ", phoneNo=" + phoneNo + 
               ", birthDate=" + birthDate + "]";
    }
}
