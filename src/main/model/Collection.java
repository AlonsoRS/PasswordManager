package model;

import java.util.ArrayList;

//Represents a collection of Users
public class Collection {


    // MODIFIES: this
    // EFFECT: initializes empty list
    //         name is set to collectionName
    public Collection(String collectionName){
        //stub

    }

    public ArrayList<User> getCollection() {
        return null; // stub
    }

    public String getCollectionName() {
        return null; // stub
    }

    // MODIFIES: this
    // EFFECT: sets name to newName
    public void setCollectionName(String newName) {
        // stub
    }

    // MODIFIES: this
    // EFFECT: adds newUser to Users
    public void addUser(User newUser) {
        //stub
    }

    // EFFECTS: returns all the User that have usernameSearch as their username in Users
    //          else, returns null
    public ArrayList<User> getUserByUsername(String usernameSearch) {
        return null; //stub
    }
}
