package ui;

import model.Collection;

import java.util.ArrayList;
import java.util.Scanner;

// Class structure taken from...
// Manages console based interface for the Password Manager app
public class App {
    // CITE THIS???

    // EFFECT: Initializes the password manager app
    public App() {
        runPasswordManager();
    }


    //
    private void runPasswordManager() {
        Scanner input = new Scanner(System.in);
        displayMenu();

    }

    //EFFECTS: displays the options available for the user
    private void displayMenu() {
        System.out.println("Options:");
        System.out.println("a." + "Display Collections");
        System.out.println("b." + "Add a new Collection");
        System.out.println("c." + "Add a new user to a Collection");
        System.out.println("d." + "Search Users by username");
    }

}
