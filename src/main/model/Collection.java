package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

//Represents a collection of Users
public class Collection {
    private ArrayList<User> users;
    private String name;

    // MODIFIES: this
    // EFFECT: initializes users as empty list
    //         name is set to collectionName
    public Collection(String collectionName) {
        users = new ArrayList<>();
        name = collectionName;

    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public String getCollectionName() {
        return name;
    }

    // MODIFIES: this
    // EFFECT: sets name to newName
    public void setCollectionName(String newName) {
        name = newName;
    }

    // MODIFIES: this
    // EFFECT: adds newUser to Users and logs the event
    public void addUser(User newUser) {
        users.add(newUser);
        EventLog.getInstance().logEvent(new Event("Added User \""
                + newUser.getUsername() + "\" to the \"" + getCollectionName() + "\" Collection"));
    }

    // EFFECTS: returns all the Users in Collection that have usernameSearch as their username
    //          else, returns empty list
    public ArrayList<User> getUserByUsername(String usernameSearch) {
        ArrayList<User> result = new ArrayList<>();
        for (User user : users) {
            if (user.getUsername().equals(usernameSearch)) {
                result.add(user);
            }
        }
        return result;
    }

    // EFFECTS: returns this as JSON object
    // Modified code from Thingy class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("users", usersToJson());
        return json;
    }

    // EFFECTS: returns Users on this Collection as a JSON array
    // Modified code from WorkRoom class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private JSONArray usersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (User u : users) {
            jsonArray.put(u.toJson());
        }

        return jsonArray;
    }

    // MODIFIES: this
    // EFFECTS: adds user to collection but doesn't log the event
    public void addUserNoLogEvent(User user) {
        users.add(user);
    }
}
