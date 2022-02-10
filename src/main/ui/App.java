package ui;

import model.Collection;
import model.CollectionManager;
import model.User;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

// Class structure and code modified from TellerApp
// Manages console based interface for the Password Manager app
public class App {
    CollectionManager manager;
    Scanner input;

    // EFFECT: Initializes fields and starts the app
    public App() {
        manager = new CollectionManager();
        input = new Scanner(System.in);

        runPasswordManager();
    }


    // MODIFIES: this
    // EFFECTS:processes user's input
    //Code extracted from TellerApp
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

    // EFFECTS:
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
    //           -Only the Users that match username
    private void printUserInCollection(ArrayList<Collection> collections, String username) {
        for (Collection coll: collections) {
            System.out.print(coll.getCollectionName() + "\n");
            printUsers(coll.getUserByUsername(username));
        }
    }


    //EFFECT: adds a new User to a collection
    private void addNewUserToCollection() {
        Collection collection = selectCollection(chooseCollection());

        if (collection == null) {
            System.out.print("Collection not found, returning to main menu");
            return;
        }

        collection.addUser(createUser());
        System.out.print("User added successfully!");

    }

    // EFFECT: returns chosen collection
    private Collection selectCollection(String chosenCollection) {
        return manager.findCollection(chosenCollection);
    }

    // EFFECTS: Records user selection of Collection
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
    // EFFECTS: creates a new Collection with input as the collection name
    private void addNewCollection() {
        System.out.print("Name of new collection: ");
        String collectionName = input.next();

        manager.addCollection(new Collection(collectionName));

        System.out.println("Collection created!");
    }

    //EFFECTS prints each Collection's name with their User's information
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

    //CITE???
    //EFFECTS: displays the options available for the user of the App
    private void displayMenu() {
        System.out.println("Options:");
        System.out.println("\ta." + "Display Collections");
        System.out.println("\tb." + "Add a new Collection");
        System.out.println("\tc." + "Add a new user to a Collection");
        System.out.println("\td." + "Search Users by username");
        System.out.println("\te." + "quit");

    }

}
