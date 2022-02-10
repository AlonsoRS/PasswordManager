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
    // EFFECTS:
    //Code extracted from TellerApp
    private void runPasswordManager() {
        String option = null;
        while (true) {
            displayMenu();
            option = input.next();

            if (option.equals("e")) {
                break;
            }

            processOption(option);
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

    private void searchUser() {
    }

    //EFFECT: adds a new User to a collection
    private void addNewUserToCollection() {
        selectCollection(chooseCollection());



    }

    private void selectCollection(String chooseCollection) {
    }

    // EFFECTS: Records user selection of Collection
    private String chooseCollection() {
        System.out.println("Choose a Collection for the new User: ");
        printCollectionNames();
        String selection = input.next();

        return selection; //stub
    }

    // EFFECTS: prints the names of all Collections
    private void printCollectionNames() {
        for (Collection each : manager.getCollections()) {
            System.out.println("\t" + each.getCollectionName());
        }
    }

    // EFFECT: creates new User
    private User createUser() {
        String username = null;
        String password = null;
        String website = null;

        System.out.println("Enter the new User's information: ");
        System.out.println("username: ");
        username = input.next();
        System.out.println("password: ");
        password = input.next();
        System.out.println("website: ");
        website = input.next();

        User user = new User(username, password);
        user.setWebsite(website);

        return user;

    }

    // MODIFIES: this
    // EFFECTS: creates a new Collection with input as the collection name
    private void addNewCollection() {
        System.out.println("Name of new collection: ");

        String collectionName = input.next();
        manager.addCollection(new Collection(collectionName));

        System.out.println("Collection created!");
    }

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

    private void printUser(User user) {
        System.out.println("\t" + " Username: " + user.getUsername());
        System.out.println("\t" + " Password: " +  user.getPassword());
        System.out.println("\t" + " Website: " + user.getWebsite());
    }

    //EFFECTS: displays the options available for the user
    private void displayMenu() {
        System.out.println("Options:");
        System.out.println("a." + "Display Collections");
        System.out.println("b." + "Add a new Collection");
        System.out.println("c." + "Add a new user to a Collection");
        System.out.println("d." + "Search Users by username");
        System.out.println("e." + "quit");

    }

}
