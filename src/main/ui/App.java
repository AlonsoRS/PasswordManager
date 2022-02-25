package ui;

import model.Collection;
import model.CollectionManager;
import model.User;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Manages console based interface for the Password Manager app
public class App {
    private static final String JSON_STORE = "./data/users.json";
    private CollectionManager manager;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: Starts the app
    public App() {
        initializeFields();
        runPasswordManager();
    }

    // MODIFIES: this
    // EFFECTS: initializes fields
    // Modified code from WorkRoomApp class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void initializeFields() {
        manager = new CollectionManager();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }


    // MODIFIES: this
    // EFFECTS: processes user's input
    // Modified code from TellerApp class: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    private void runPasswordManager() {
        String option;

        while (true) {
            displayMenu();
            option = input.next();
            option = option.toLowerCase();

            if (option.equals("q")) {
                break;
            }
            processOption(option);
            System.out.print("\n");
        }

        System.out.println("App closed");

    }

    // MODIFIES: this
    // EFFECTS: process user's chosen option
    // Code taken from TellerApp class: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    private void processOption(String option) {
        if (option.equals("a")) {
            displayCollections();
        }
        if (option.equals("b")) {
            addNewCollection();
        }
        if (option.equals("c")) {
            addNewUserToCollection();
        }
        if (option.equals("d")) {
            searchUser();
        }
        if (option.equals("e")) {
            saveCollections();
        }
        if (option.equals("f")) {
            loadCollections();
        }
    }

    // MODIFIES:
    // EFFECTS:
    // Code taken from WorkRoomApp class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void saveCollections() {
        try {
            jsonWriter.open();
            jsonWriter.write(manager);
            jsonWriter.close();
            System.out.println("Saved Collections to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES:
    // EFFECTS:
    // Code taken from WorkRoomApp class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void loadCollections() {
        try {
            manager = jsonReader.read();
            System.out.println("Loaded Collections from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: Prints only the Users that match user input with their respective Collection name
    private void searchUser() {
        System.out.print("Username to search: ");
        String username = input.next();

        ArrayList<Collection> found = manager.findUser(username);
        if (found.isEmpty()) {
            System.out.println("User not found");
            return;
        }
        printUserInCollection(found, username);
    }

    // EFFECTS: From given Collections, prints:
    //           -the Collection name
    //           -the Users that match username
    private void printUserInCollection(ArrayList<Collection> collections, String username) {
        for (Collection coll: collections) {
            System.out.print(coll.getCollectionName() + "\n");
            printUsers(coll.getUserByUsername(username));
        }
    }

    //MODIFIES: this
    //EFFECT: if no Collections have been added or Collection is not found, return
    //        else, adds a new User to a Collection
    private void addNewUserToCollection() {
        if (manager.getCollections().isEmpty()) {
            System.out.println("No collections have been created");
            return;
        }

        Collection collection = selectCollection(chooseCollection());

        if (collection == null) {
            System.out.print("Collection not found, returning to main menu");
            return;
        }

        collection.addUser(createUser());
        System.out.print("User added successfully!\n");

    }

    // EFFECT: Finds and returns Collection with chosenCollection as name from manager
    private Collection selectCollection(String chosenCollection) {
        return manager.findCollection(chosenCollection);
    }

    // MODIFIES: this
    // EFFECTS: Returns user's selection of Collection
    private String chooseCollection() {
        System.out.println("Choose a Collection for the new User: ");
        printCollectionNames();
        return input.next();
    }


    // EFFECTS: prints the names of all Collections
    private void printCollectionNames() {
        int count = 0;
        for (Collection each : manager.getCollections()) {
            count++;
            System.out.println("\t" + count + ". " + each.getCollectionName());
        }
    }

    // MODIFIES: this
    // EFFECT: creates new User
    private User createUser() {
        String username;
        String password;
        String website;

        System.out.println("Enter the new User's information: ");
        System.out.print("\tusername: ");
        username = input.next();
        System.out.print("\tpassword: ");
        password = input.next();
        System.out.print("\twebsite: ");
        website = input.next();

        User user = new User(username, password);
        user.setWebsite(website);

        return user;

    }

    // MODIFIES: this
    // EFFECTS: creates a new Collection with input as the collection name,
    //          and adds new Collection to manager
    private void addNewCollection() {
        System.out.print("Name of new collection: ");
        String collectionName = input.next();

        manager.addCollection(new Collection(collectionName));

        System.out.println("Collection created!");
    }

    //EFFECTS: prints all Collection's name with their User's information
    private void displayCollections() {
        if (manager.getCollections().isEmpty()) {
            System.out.println("No collections have been created");
            return;
        }

        for (Collection col : manager.getCollections()) {
            System.out.println(col.getCollectionName());
            printUsers(col.getUsers());
        }
    }

    //EFFECTS: prints information of Users
    private void printUsers(ArrayList<User> users) {
        for (User user : users) {
            printUser(user);
        }
    }


    //EFFECT: prints given User
    private void printUser(User user) {
        System.out.println("\t" + " Username: " + user.getUsername());
        System.out.println("\t" + " Password: " +  user.getPassword());
        System.out.println("\t" + " Website: " + user.getWebsite());
        System.out.print("\n");
    }


    //EFFECTS: displays the options available for the user of the App
    //Code taken from TellerApp class: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    private void displayMenu() {
        System.out.println("Options:");
        System.out.println("\ta." + "Display Collections");
        System.out.println("\tb." + "Add a new Collection");
        System.out.println("\tc." + "Add a new user to a Collection");
        System.out.println("\td." + "Search Users by username");
        System.out.println("\te." + "Save Collections");
        System.out.println("\tf." + "Load Collections");
        System.out.println("\tq." + "Quit");

    }

}
