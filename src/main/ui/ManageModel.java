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
        //DELETE AFTER TEST ===========================-------------------------------------------------
        Collection c1 = new Collection("Banking");
        Collection c2 = new Collection("Fun");
        c1.addUser(new User("jake", "paul"));
        c1.addUser(new User("asd", "asd"));
        c2.addUser(new User("jake", "paul"));

        manager.addCollection(c1);
        manager.addCollection(c2);
        manager.addCollection(c1);
        //DELETE AFTER TEST ===========================-------------------------------------------------
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }
//
//
//    // MODIFIES:
//    // EFFECTS:
//    // Code taken from WorkRoomApp class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
//    private void saveCollections() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(manager);
//            jsonWriter.close();
//            System.out.println("Saved Collections to " + JSON_STORE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE);
//        }
//    }
//
//    // MODIFIES:
//    // EFFECTS:
//    // Code taken from WorkRoomApp class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
//    private void loadCollections() {
//        try {
//            manager = jsonReader.read();
//            System.out.println("Loaded Collections from " + JSON_STORE);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        }
//    }
//
//    // EFFECTS: Prints only the Users that match user input with their respective Collection name
//    private void searchUser() {
//        System.out.print("Username to search: ");
//        String username = input.next();
//
//        ArrayList<Collection> found = manager.findUser(username);
//        if (found.isEmpty()) {
//            System.out.println("User not found");
//            return;
//        }
//        printUserInCollection(found, username);
//    }
//
//    // EFFECTS: From given Collections, prints:
//    //           -the Collection name
//    //           -the Users that match username
//    private void printUserInCollection(ArrayList<Collection> collections, String username) {
//        for (Collection coll: collections) {
//            System.out.print(coll.getCollectionName() + "\n");
//            printUsers(coll.getUserByUsername(username));
//        }
//    }
//
//    //MODIFIES: this
//    //EFFECT: if no Collections have been added or Collection is not found, return
//    //        else, adds a new User to a Collection
//    private void addNewUserToCollection() {
//        if (manager.getCollections().isEmpty()) {
//            System.out.println("No collections have been created");
//            return;
//        }
//
//        Collection collection = selectCollection(chooseCollection());
//
//        if (collection == null) {
//            System.out.print("Collection not found, returning to main menu");
//            return;
//        }
//
//        collection.addUser(createUser());
//        System.out.print("User added successfully!\n");
//
//    }
//
//    // EFFECT: Finds and returns Collection with chosenCollection as name from manager
//    private Collection selectCollection(String chosenCollection) {
//        return manager.findCollection(chosenCollection);
//    }
//
//
//
//    // MODIFIES: this
//    // EFFECT: creates new User
//    private User createUser() {
//        String username;
//        String password;
//        String website;
//
//        System.out.println("Enter the new User's information: ");
//        System.out.print("\tusername: ");
//        username = input.next();
//        System.out.print("\tpassword: ");
//        password = input.next();
//        System.out.print("\twebsite: ");
//        website = input.next();
//
//        User user = new User(username, password);
//        user.setWebsite(website);
//
//        return user;
//
//    }
//
//    // MODIFIES: this
//    // EFFECTS: creates a new Collection with input as the collection name,
//    //          and adds new Collection to manager
//    private void addNewCollection() {
//        System.out.print("Name of new collection: ");
//        String collectionName = input.next();
//
//        manager.addCollection(new Collection(collectionName));
//
//        System.out.println("Collection created!");
//    }
//

    //EFFECTS: returns Collections in format for data, if there is no data available returns null
    public ArrayList<Object [][]> getCollectionsInDataFormat() {
        if (manager.getCollections().isEmpty()) {
            return null;
        }
        ArrayList result = new ArrayList<Object [][]>();

        for (Collection col : manager.getCollections()) {
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



}
