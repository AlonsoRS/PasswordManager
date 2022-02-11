package model;

// Represents the password of a User
public class Password {
    private String password;

    // MODIFIES: this
    // EFFECT: this.password is set to password
    public Password(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    // MODIFIES: this
    // EFFECT: this.password is set to password
    public void setPassword(String password) {
        this.password = password;
    }

    // REQUIRES: length > 0
    // MODIFIES: THIS
    // EFFECTS: generates random password of specified length
    //    public void randomPassword(int length) {
    //        // stub
    //    }
}
