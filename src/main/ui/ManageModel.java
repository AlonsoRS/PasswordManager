package ui;

import model.Collection;
import model.CollectionManager;
import model.User;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


// Class to manage GUI and Model interactions
public class ManageModel {
    private static final String JSON_STORE = "./data/users.json";
    private CollectionManager manager;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: Starts the app
    public ManageModel() {
        initializeFields();
    }

    // MODIFIES: this
    // EFFECTS: initializes fields
    // Modified code from WorkRoomApp class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void initializeFields() {
        manager = new CollectionManager();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }


    // MODIFIES: this
    // EFFECTS: saves Collections to file
    // Code taken from WorkRoomApp class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void saveCollections() {
        try {
            jsonWriter.open();
            jsonWriter.write(manager);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            return;
        }
    }

    // MODIFIES: this
    // EFFECTS: loads Collections to file
    // Code taken from WorkRoomApp class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void loadCollections() {
        try {
            manager = jsonReader.read();
        } catch (IOException e) {
            return;
        }
    }

    // EFFECTS: Returns Collections with Users that match username.
    //          If none found, return null
    public ArrayList<Collection> searchUser(String username) {
        ArrayList<Collection> found = manager.findUserReturnNewCollections(username);
        if (found.isEmpty()) {
            return null;
        }
        return found;
    }


    //MODIFIES: this
    //EFFECT: if no Collections have been added or Collection is not found, return
    //        else, adds a new User to a Collection
    public void addNewUserToCollection(String collectionName, User user) {
        if (manager.getCollections().isEmpty()) {
            return;
        }

        Collection collection = selectCollection(collectionName);

        if (collection == null) {
            return;
        }

        collection.addUser(user);

    }

    // EFFECT: Finds and returns Collection with chosenCollection as name from manager
    private Collection selectCollection(String chosenCollection) {
        return manager.findCollection(chosenCollection);
    }



    // MODIFIES: this
    // EFFECT: creates new User
    public User createUser(String username, String password, String website) {

        User user = new User(username, password);
        user.setWebsite(website);

        return user;

    }


    // MODIFIES: this
    // EFFECTS: creates a new Collection with input as the collection name,
    //          and adds new Collection to manager
    public void addNewCollection(String collectionName) {
        manager.addCollection(new Collection(collectionName));
    }


    //EFFECTS: returns Collections in format for data, if there is no data available returns null
    public ArrayList<Object [][]> getCollectionsInDataFormat(ArrayList<Collection> collections) {
        if (collections.isEmpty() || collections == null) {
            return null;
        }
        ArrayList result = new ArrayList<Object [][]>();

        for (Collection col : collections) {
            result.add(getUsersInDataFormat(col));
        }

        return result;
    }

    // EFFECTS: returns Users in data format
    private Object [][] getUsersInDataFormat(Collection collection) {
        Object [][] users = new Object[collection.getUsers().size()][User.FIELDS];
        int index = 0;

        for (User user : collection.getUsers()) {
            users[index++] = getUserInDataFormat(user);
        }
        return users;
    }

    // EFFECTS: returns User in data format {Username, Password, website}
    private Object [] getUserInDataFormat(User user) {
        Object [] userData = {user.getUsername(), user.getPassword(), user.getWebsite()};
        return userData;

    }

    // EFFECTS: returns the collection size at position index
    public int getNumberOfCollections() {
        return manager.getCollections().size();
    }

    // EFFECTS: returns the collection name at position index
    public String getCollectionNameAt(int index) {
        return manager.getCollections().get(index).getCollectionName();
    }

    // EFFECTS: returns collections
    public ArrayList<Collection> getCollections() {
        return manager.getCollections();
    }



}
