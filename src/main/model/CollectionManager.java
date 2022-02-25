package model;


import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;

// Manages the different Collections created by a User
public class CollectionManager {
    private ArrayList<Collection> list;

    //EFFECTS: creates an empty list of Collections
    public CollectionManager() {
        list = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds newCollection to the list of Collections
    public void addCollection(Collection newCollection) {
        list.add(newCollection);

    }

    //EFFECTS: returns list of Collection
    public ArrayList<Collection> getCollections() {
        return list;
    }

    //EFFECTS: Returns all the Collections that have at least 1 User that matches searchUsername,
    //         else returns empty list
    public ArrayList<Collection> findUser(String searchUsername) {
        ArrayList<Collection> result = new ArrayList<>();

        for (Collection each : list) {
            ArrayList<User> temp = each.getUserByUsername(searchUsername);
            if (!temp.isEmpty()) {
                result.add(each);
            }
        }

        return result;
    }

    // EFFECTS: returns Collection that matches collectionName in list
    //          if Collection is not found, return null
    public Collection findCollection(String collectionName) {
        for (Collection collection : list) {
            if (collection.getCollectionName().equals(collectionName)) {
                return collection;
            }
        }
        return null;
    }

    // EFFECTS: returns this as JSON object
    // Modified code from WorkRoom class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        return json; //stub
    }
}
