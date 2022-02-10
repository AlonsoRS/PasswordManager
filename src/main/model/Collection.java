package model;

import java.util.ArrayList;

//Represents a collection of Users
public class Collection {
    private ArrayList<User> users;
    private String name;

    // MODIFIES: this
    // EFFECT: initializes empty list
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
    // EFFECT: adds newUser to Users
    public void addUser(User newUser) {
        users.add(newUser);
    }

    // EFFECTS: returns all the Users that have usernameSearch as their username
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
}
