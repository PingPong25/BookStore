import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Profile {
    private final List<User> userList;  // Marked final based on hint

    // Constructor
    public Profile() {
        this.userList = new ArrayList<>();
    }

    // Create profile method
    public void profile(String username, String password, char gender, String phoneNo, Date birthDate) {
        User user = new User(username, password, gender, phoneNo, birthDate);
        userList.add(user);
    }

    // Update profile method
    public void updateProfile(String username, String password, char gender, String phoneNo, Date birthDate) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                user.setPassword(password);
                user.setGender(gender);
                user.setPhoneNo(phoneNo);
                user.setBirthDate(birthDate);
                System.out.println("Profile updated for: " + username);
                return;
            }
        }
        System.out.println("User not found: " + username);
    }

    // Save user profile
    public void save(User user) {
        userList.add(user);
    }

    // Save user by username
    public void save(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                userList.add(user);
                return;
            }
        }
        System.out.println("User not found: " + username);
    }

    // Methods to get updated details
    public String getUpdatedUsername(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user.getUsername();
            }
        }
        return null;
    }

    public String getUpdatedPassword(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user.getPassword();
            }
        }
        return null;
    }

    public char getUpdatedGender(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user.getGender();
            }
        }
        return '\0';  // Default invalid character
    }

    public String getUpdatedPhoneNo(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user.getPhoneNo();
            }
        }
        return null;
    }

    public Date getUpdatedBirthDate(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user.getBirthDate();
            }
        }
        return null;
    }
}
