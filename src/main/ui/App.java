package ui;

import model.Collection;
import model.CollectionManager;
import model.User;

import java.util.ArrayList;
import java.util.Scanner;

// Manages console based interface for the Password Manager app
public class App {
    CollectionManager manager;
    Scanner input;


    // EFFECTS: Starts the app
    public App() {
        initializeFields();
        runPasswordManager();
    }

    // MODIFIES: this
    // EFFECTS: initializes fields
    private void initializeFields() {
        manager = new CollectionManager();
        input = new Scanner(System.in);
    }


    // MODIFIES: this
    // EFFECTS: processes user's input
    // Modified code from TellerApp: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    private void runPasswordManager() {
        String option;

        while (true) {
            displayMenu();
            option = input.next();

            if (option.equals("e")) {
                break;
            }
            processOption(option);
            System.out.print("\n");
        }

        System.out.println("App closed");

    }

    // MODIFIES: this
    // EFFECTS: process user's chosen option
    // Code taken from TellerApp: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
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
    }

    // EFFECTS: Prints only the Users that match user input with their respective Collection name
    private void searchUser() {
        System.out.print("Username to search: ");
        String username = input.next();
        printUserInCollection(manager.findUser(username), username);
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
    //EFFECT: adds a new User to a Collection
    private void addNewUserToCollection() {
        Collection collection = selectCollection(chooseCollection());

        if (collection == null) {
            System.out.print("Collection not found, returning to main menu");
            return;
        }

        collection.addUser(createUser());
        System.out.print("User added successfully!");

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
        for (Collection each : manager.getCollections()) {
            System.out.println("\t" + each.getCollectionName());
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
    //Code taken from TellerApp: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    private void displayMenu() {
        System.out.println("Options:");
        System.out.println("\ta." + "Display Collections");
        System.out.println("\tb." + "Add a new Collection");
        System.out.println("\tc." + "Add a new user to a Collection");
        System.out.println("\td." + "Search Users by username");
        System.out.println("\te." + "quit");

    }

}
