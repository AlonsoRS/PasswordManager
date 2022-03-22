package model;

import org.json.JSONObject;

import java.time.LocalDate;

//Represents a User having a username, password, website and date of creation
public class User {
    public static final int FIELDS = 3;
    private String username;
    private Password password;
    private String website;
    private LocalDate date;


    // REQUIRES: username and password are not the empty string or null
    // MODIFIES: this
    // EFFECT: website is set to the empty string
    //         this.username is set to username
    //         this.password value is set to password
    //         sets date to current date
    public User(String username, String password) {
        this.website = "";
        this.username = username;
        this.password = new Password(password);
        this.date = LocalDate.now();
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password.getPassword();
    }

    public String getWebsite() {
        return website;
    }

    public LocalDate getDate() {
        return date;
    }

    // REQUIRES: website is an actual website
    // MODIFIES: this
    // EFFECTS: sets this.website to website
    public void setWebsite(String website) {
        this.website = website;
    }

    // MODIFIES: this
    // EFFECTS: sets this.username to name
    public void setUsername(String name) {
        this.username = name;
    }

    // MODIFIES: this
    // EFFECTS: Changes this.password value to password
    public void setPassword(String password) {
        this.password.setPassword(password);
    }


    // EFFECTS: returns this as JSON object
    // Modified code from Thingy class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("username", username);
        json.put("password", password.getPassword());
        json.put("website", website);

        return json;
    }
}
