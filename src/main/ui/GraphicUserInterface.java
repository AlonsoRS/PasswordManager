package ui;

import model.User;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;



public class GraphicUserInterface extends JFrame implements ActionListener {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    public static final int SEPARATION_BETWEEN_FIELDS = 40;

    JFrame frame;
    ManageModel manager;
    JPanel panel;

    JTextField collectionField;
    JTextField usernameField;
    JTextField passwordField;
    JTextField websiteField;



    public GraphicUserInterface() {
        manager = new ManageModel();
        panel = new JPanel();

        initializeFrame();
        createMenu();
        displayWindow();

    }





    //EFFECTS: Creates MenuBar with Menus and Items
    // Code taken from createAndShowGUI() in
    // https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TopLevelDemoProject/src/components/TopLevelDemo.java
    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setOpaque(true);
        menuBar.setBackground(new Color(154, 165, 127));
        menuBar.setPreferredSize(new Dimension(200, 40));
        JMenu optionsMenu = new JMenu("Options");
        JMenu fileMenu = new JMenu("File");
        initializeMenuItems(optionsMenu, fileMenu);
        menuBar.add(optionsMenu);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
    }


    //EFFECTS: initializes options to choose from menu
    // Code partially extracted from MenuLookDemo class in
    //https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/MenuLookDemoProject/src/components/MenuLookDemo.java
    private void initializeMenuItems(JMenu optionsMenu, JMenu fileMenu) {
        JMenuItem item = new JMenuItem("Display Collections");
        item.addActionListener(this);
        optionsMenu.add(item);
        item = new JMenuItem("Add a new Collection");
        item.addActionListener(this);
        optionsMenu.add(item);
        item = new JMenuItem("Add a new user to a Collection");
        item.addActionListener(this);
        optionsMenu.add(item);
        item = new JMenuItem("Search Users by username");
        item.addActionListener(this);
        optionsMenu.add(item);
        item = new JMenuItem("Quit");
        item.addActionListener(this);
        optionsMenu.add(item);
        item = new JMenuItem("Save to File");
        item.addActionListener(this);
        fileMenu.add(item);
        item = new JMenuItem("Load from File");
        item.addActionListener(this);
        fileMenu.add(item);
    }


    //EFFECTS: displays interface on screen
    //Code taken from ButtonDemo class in:
    //https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ButtonDemoProject/src/components/ButtonDemo.java
    private void displayWindow() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    //EFFECT: initializes JFrame
    //Code taken from ButtonDemo class in:
    //https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ButtonDemoProject/src/components/ButtonDemo.java
    private void initializeFrame() {
        frame = new JFrame("Password Manager");
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case ("Display Collections"):
                displayCollections();
                break;
            case ("Add a new Collection"):
                addNewCollection();
                break;
            case ("Add a new user to a Collection"):
                addUserToCollection();
                break;
            case ("Search Users by username"):
                searchUsersByUsername();
                break;
            case ("Quit"):
                quit();
                break;
            case ("Save to File"):
                saveToFile();
                break;
            case ("Load from File"):
                saveFromFile();
                break;
            case ("Create Collection"):
                createCollection();
                break;
            case ("Add User to Collection"):
                createUserAndAddToCollection();
                break;
        }
    }

    //THIS IS NOT WORKINGGGGGGGGGGGGG ------------------
    //EFFECTS: creates User with inputs form user and adds the User to a Colleciton
    private void createUserAndAddToCollection() {
        User user = manager.createUser(usernameField.getText(), passwordField.getText(), websiteField.getText());
        manager.addNewUserToCollection(collectionField.getText(), user);

    }

    //EFFECTS: creates a Collection with the name give by the user
    private void createCollection() {
        manager.addNewCollection(collectionField.getText());
    }


    //interact with model
    private void saveFromFile() {

    }

    private void saveToFile() {
    }

    private void quit() {
    }

    private void searchUsersByUsername() {
    }

    private void createJTextField(JTextField field, String name) {
        field = new JTextField(10);
        field.setActionCommand(name);
        field.addActionListener(this);

    }

    //EFFECTS: adds a new Collection
    // Code partially extracted from TextSamplerDemo() constructor in:
    // https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TextSamplerDemoProject/src/components/TextSamplerDemo.java
    private void addUserToCollection() {
        frame.remove(panel);
        //Create Username field
        collectionField = new JTextField(10);
        collectionField.setActionCommand("Collection");
        collectionField.addActionListener(this);

        //Create Username field
        usernameField = new JTextField(10);
        usernameField.setActionCommand("Username");
        usernameField.addActionListener(this);

        //Create a password field.
        passwordField = new JPasswordField(10);
        passwordField.setActionCommand("Password");
        passwordField.addActionListener(this);

        //Create website field.
        JTextField websiteField = new JPasswordField(10);
        websiteField.setActionCommand("Website");
        websiteField.addActionListener(this);

        //Create some labels for the fields.
        JLabel collectionFieldLabel = new JLabel("Collection: ");
        collectionFieldLabel.setLabelFor(collectionField);
        JLabel usernameFieldLabel = new JLabel("Username: ");
        usernameFieldLabel.setLabelFor(usernameField);
        JLabel passwordFieldLabel = new JLabel("Password: ");
        passwordFieldLabel.setLabelFor(passwordField);
        JLabel websiteFieldLabel = new JLabel("Website: ");
        websiteFieldLabel.setLabelFor(websiteField);

        //Lay out the text controls and the labels.
        //createAndShowGUI() method in
        //https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/SpringDemo2Project/src/layout/SpringDemo2.java
        SpringLayout layout = new SpringLayout();
        panel = new JPanel(layout);
        panel.setBackground(Color.LIGHT_GRAY);

        panel.add(collectionFieldLabel);
        panel.add(collectionField);
        panel.add(usernameFieldLabel);
        panel.add(usernameField);
        panel.add(passwordFieldLabel);
        panel.add(passwordField);
        panel.add(websiteFieldLabel);
        panel.add(websiteField);

        //COLLECTION
        layout.putConstraint(SpringLayout.WEST, collectionFieldLabel,
                5,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, collectionFieldLabel,
                5,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, collectionField,
                5,
                SpringLayout.EAST, collectionFieldLabel);
        layout.putConstraint(SpringLayout.NORTH, collectionField,
                5,
                SpringLayout.NORTH, panel);

        //USERNAME
        layout.putConstraint(SpringLayout.WEST, usernameFieldLabel,
                5,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, usernameFieldLabel,
                SEPARATION_BETWEEN_FIELDS, SpringLayout.NORTH, collectionFieldLabel);

        layout.putConstraint(SpringLayout.WEST, usernameField,
                5,
                SpringLayout.EAST, usernameFieldLabel);
        layout.putConstraint(SpringLayout.NORTH, usernameField,
                SEPARATION_BETWEEN_FIELDS,
                SpringLayout.NORTH, collectionField);

        //PASSWORD
        layout.putConstraint(SpringLayout.WEST, passwordFieldLabel,
                5,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, passwordFieldLabel,
                SEPARATION_BETWEEN_FIELDS, SpringLayout.NORTH, usernameFieldLabel);

        layout.putConstraint(SpringLayout.WEST, passwordField,
                5,
                SpringLayout.EAST, passwordFieldLabel);
        layout.putConstraint(SpringLayout.NORTH, passwordField,
                SEPARATION_BETWEEN_FIELDS,
                SpringLayout.NORTH, usernameField);

        //WEBSITE
        layout.putConstraint(SpringLayout.WEST, websiteFieldLabel,
                5,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, websiteFieldLabel,
                SEPARATION_BETWEEN_FIELDS, SpringLayout.NORTH, passwordFieldLabel);

        layout.putConstraint(SpringLayout.WEST, websiteField,
                5,
                SpringLayout.EAST, websiteFieldLabel);
        layout.putConstraint(SpringLayout.NORTH, websiteField,
                SEPARATION_BETWEEN_FIELDS,
                SpringLayout.NORTH, passwordField);

        //button stuff
        JButton button = new JButton("Add User to Collection");
        button.addActionListener(this);

        layout.putConstraint(SpringLayout.WEST, button,
                5,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, button,
                SEPARATION_BETWEEN_FIELDS,
                SpringLayout.NORTH, websiteField);
        panel.add(button);

        frame.add(panel);

        displayWindow();


    }

    //EFFECTS: adds a new Collection
    // Code partially extracted from TextSamplerDemo() constructor in:
    // https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TextSamplerDemoProject/src/components/TextSamplerDemo.java
    private void addNewCollection() {
        frame.remove(panel);
        //Create Collection field
        collectionField = new JTextField(10);
        collectionField.setActionCommand("Collection");
        collectionField.addActionListener(this);
        

        //Create some labels for the fields.
        JLabel collectionFieldLabel = new JLabel("Collection: ");
        collectionFieldLabel.setLabelFor(collectionField);


        //Lay out the text controls and the labels.
        //createAndShowGUI() method in
        //https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/SpringDemo2Project/src/layout/SpringDemo2.java
        SpringLayout layout = new SpringLayout();
        panel = new JPanel(layout);
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(collectionFieldLabel);
        panel.add(collectionField);
        //Adjust constraints for the label so it's at (5,5).
        layout.putConstraint(SpringLayout.WEST, collectionFieldLabel,
                5,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, collectionFieldLabel,
                5,
                SpringLayout.NORTH, panel);

        //Adjust constraints for the text field so it's at
        //(<label's right edge> + 5, 5).
        layout.putConstraint(SpringLayout.WEST, collectionField,
                5,
                SpringLayout.EAST, collectionFieldLabel);
        layout.putConstraint(SpringLayout.NORTH, collectionField,
                5,
                SpringLayout.NORTH, panel);

        //button stuff
        JButton button = new JButton("Create Collection");
        button.addActionListener(this);

        layout.putConstraint(SpringLayout.WEST, button,
                5,
                SpringLayout.EAST, collectionField);
        layout.putConstraint(SpringLayout.NORTH, button,
                5,
                SpringLayout.SOUTH, collectionField);

        panel.add(button);

        frame.add(panel);

        displayWindow();

    }

    // EFFECTS: displays current collections. If there are no collections return
    // Code partially extract4ed from:
    // https://docs.oracle.com/javase/tutorial/uiswing/examples/components/SimpleTableDemoProject/src/components/SimpleTableDemo.java
    private void displayCollections() {
        frame.remove(panel);
        panel = new JPanel(new GridBagLayout());

        ArrayList<Object [][]> collectionsInDataFormat = manager.getCollectionsInDataFormat();
        if (collectionsInDataFormat == null) {
            return;
        }

        String[] columnNames = {"Username", "Password", "Website"};
        int index = 0;

        for (Object [][] collection : collectionsInDataFormat) {
            JTable table = new JTable(collection, columnNames);
            JTableHeader firstRow = table.getTableHeader();
            firstRow.setBackground(Color.LIGHT_GRAY);

            table.setPreferredScrollableViewportSize(new Dimension(WIDTH / manager.getNumberOfCollections(),
                    (int) (HEIGHT * 0.8)));
            table.setFillsViewportHeight(true);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBorder(BorderFactory.createTitledBorder(manager.getCollectionNameAt(index)));
            panel.add(scrollPane);
            index++;
        }
        panel.setBackground(Color.WHITE);
        frame.add(panel);
        displayWindow();
    }


}
