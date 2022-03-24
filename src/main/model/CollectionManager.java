package model;


import org.json.JSONArray;
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

    //EFFECTS: Returns all New Collections with only found Users
    public ArrayList<Collection> findUserReturnNewCollections(String searchUsername) {
        ArrayList<Collection> result = new ArrayList<>();

        for (Collection each : list) {
            ArrayList<User> temp = each.getUserByUsername(searchUsername);
            if (!temp.isEmpty()) {
                result.add(createCollectionFromUsers(each.getCollectionName(), temp));
            }
        }

        return result;
    }

    // EFFECTS: returns a collection with given users and collectionName
    private Collection createCollectionFromUsers(String collectionName, ArrayList<User> users) {
        Collection collection = new Collection(collectionName);
        for (User user : users) {
            collection.addUser(user);
        }
        return collection;
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
        json.put("list", collectionsToJson());
        return json;
    }

    // EFFECTS: returns Collections on this CollectionManager as a JSON array
    // Modified code from WorkRoom class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private JSONArray collectionsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Collection c : list) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
