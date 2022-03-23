package ui;

import model.User;
import model.Collection;

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

    JLabel collectionFieldLabel;
    JLabel usernameFieldLabel;
    JLabel passwordFieldLabel;
    JLabel websiteFieldLabel;



    public GraphicUserInterface() {

        initializeFields();
        initializeGraphics();
        updateWindow();

    }

    // EFFECTS: initializes graphics
    private void initializeGraphics() {
        panel = new JPanel();
        createJTextFields();
        createJLabels();
        initializeFrame();
        createMenu();

    }

    // EFFECTS: initializes fields
    private void initializeFields() {
        manager = new ManageModel();

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
    private void updateWindow() {
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

    // EFFECTS: returns true if action event comes from an option in the menu
    boolean isOptionMenu(ActionEvent ae) {
        return ae.getActionCommand().equals("Display Collections")
                || ae.getActionCommand().equals("Add a new Collection")
                || ae.getActionCommand().equals("Add a new user to a Collection")
                || ae.getActionCommand().equals("Search Users by username")
                || ae.getActionCommand().equals("Save to File")
                || ae.getActionCommand().equals("Load from File");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (isOptionMenu(ae)) {
            chooseOptionMenu(ae);
            return;
        }
        if (isButton(ae)) {
            chooseButton(ae);
            return;
        }
    }

    // EFFECTS: returns true if action event comes from an option in the menu
    private boolean isButton(ActionEvent ae) {
        return ae.getActionCommand().equals("Create Collection")
                || ae.getActionCommand().equals("Add User to Collection")
                || ae.getActionCommand().equals("Search User");
    }

    //EFFECTS chooses what operation to perform based on menu item selected
    private void chooseOptionMenu(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case ("Display Collections"):
                displayCollections();
                break;
            case ("Add a new Collection"):
                displayAddNewCollection();
                break;
            case ("Add a new user to a Collection"):
                displayAddUserToCollection();
                break;
            case ("Search Users by username"):
                displaySearchUsersByUsername();
                break;
            case ("Save to File"):
                saveToFile();
                break;
            case ("Load from File"):
                loadFromFile();
                break;
        }
    }


    //EFFECTS chooses what operation to perform based on button pressed
    private void chooseButton(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case ("Create Collection"):
                createCollection();
                break;
            case ("Add User to Collection"):
                createUserAndAddToCollection();
                break;
            case ("Search User"):
                displayFoundUser(searchForUser());
                break;
        }
    }

    //EFFECTS: displays found users, if there are none return
    private void displayFoundUser(ArrayList<Object[][]> collectionsInDataFormat) {
        frame.remove(panel);
        if (collectionsInDataFormat == null) {
            return;
        }
        displayCollectionsInDataFormat(collectionsInDataFormat);

    }

    //EFFECTS: searches for Username inputted by user
    private ArrayList<Object [][]> searchForUser() {
        ArrayList<Collection> foundUsers = manager.searchUser(usernameField.getText());
        return manager.getCollectionsInDataFormat(foundUsers);


    }

    //EFFECTS: creates User with inputs form user and adds the User to a Collection
    private void createUserAndAddToCollection() {
        User user = manager.createUser(usernameField.getText(), passwordField.getText(), websiteField.getText());
        manager.addNewUserToCollection(collectionField.getText(), user);

    }

    //EFFECTS: creates a Collection with the name give by the user
    private void createCollection() {
        manager.addNewCollection(collectionField.getText());
    }

    // EFFECTS: loads Collections from file
    private void loadFromFile() {
        manager.loadCollections();

    }

    // EFFECTS: saves Collections to file
    private void saveToFile() {
        manager.saveCollections();
    }


    // EFFECTS: displays the panel to search for a user by username
    private void displaySearchUsersByUsername() {
        frame.remove(panel);
        displayLabelTextFieldButton(usernameFieldLabel, usernameField, "Search User");
        updateWindow();
    }

    // EFFECTS: displays given labels and text fields along with a button of name buttonName
    // Code partially extracted from createAndShowGUI() method in
    //https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/SpringDemo2Project/src/layout/SpringDemo2.java
    private void displayLabelTextFieldButton(JLabel label, JTextField textField, String buttonName) {
        SpringLayout layout = new SpringLayout();
        panel = new JPanel(layout);
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(label);
        panel.add(textField);
        layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, label, 5, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, textField, 5, SpringLayout.EAST, label);
        layout.putConstraint(SpringLayout.NORTH, textField, 5, SpringLayout.NORTH, panel);
        JButton button = new JButton(buttonName);
        button.addActionListener(this);
        layout.putConstraint(SpringLayout.WEST, button, 5, SpringLayout.EAST, textField);
        layout.putConstraint(SpringLayout.NORTH, button, 5, SpringLayout.SOUTH, textField);
        panel.add(button);
        frame.add(panel);
    }


    //EFFECTS: displays panel to add a user to a collection
    // Code partially extracted from TextSamplerDemo() constructor in:
    // https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TextSamplerDemoProject/src/components/TextSamplerDemo.java
    private void displayAddUserToCollection() {
        frame.remove(panel);
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
        layout.putConstraint(SpringLayout.WEST, collectionFieldLabel, 5, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, collectionFieldLabel, 5, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, collectionField, 5, SpringLayout.EAST, collectionFieldLabel);
        layout.putConstraint(SpringLayout.NORTH, collectionField, 5, SpringLayout.NORTH, panel);
        //USERNAME
        layout.putConstraint(SpringLayout.WEST, usernameFieldLabel, 5, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, usernameFieldLabel, SEPARATION_BETWEEN_FIELDS, SpringLayout.NORTH,
                collectionFieldLabel);
        layout.putConstraint(SpringLayout.WEST, usernameField, 5, SpringLayout.EAST, usernameFieldLabel);
        layout.putConstraint(SpringLayout.NORTH, usernameField, SEPARATION_BETWEEN_FIELDS, SpringLayout.NORTH,
                collectionField);
        //PASSWORD
        layout.putConstraint(SpringLayout.WEST, passwordFieldLabel, 5, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, passwordFieldLabel, SEPARATION_BETWEEN_FIELDS, SpringLayout.NORTH,
                usernameFieldLabel);
        layout.putConstraint(SpringLayout.WEST, passwordField, 5, SpringLayout.EAST, passwordFieldLabel);
        layout.putConstraint(SpringLayout.NORTH, passwordField, SEPARATION_BETWEEN_FIELDS, SpringLayout.NORTH,
                usernameField);
        //WEBSITE
        layout.putConstraint(SpringLayout.WEST, websiteFieldLabel, 5, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, websiteFieldLabel, SEPARATION_BETWEEN_FIELDS, SpringLayout.NORTH,
                passwordFieldLabel);
        layout.putConstraint(SpringLayout.WEST, websiteField, 5, SpringLayout.EAST, websiteFieldLabel);
        layout.putConstraint(SpringLayout.NORTH, websiteField, SEPARATION_BETWEEN_FIELDS, SpringLayout.NORTH,
                passwordField);
        //button stuff
        JButton button = new JButton("Add User to Collection");
        button.addActionListener(this);
        layout.putConstraint(SpringLayout.WEST, button, 5, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, button, SEPARATION_BETWEEN_FIELDS, SpringLayout.NORTH, websiteField);
        panel.add(button);
        frame.add(panel);
        updateWindow();
    }

    // EFFECTS: creates JLabels
    // Code partially extracted from TextSamplerDemo() constructor in:
    // https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TextSamplerDemoProject/src/components/TextSamplerDemo.java
    private void createJLabels() {
        collectionFieldLabel = new JLabel("Collection: ");
        collectionFieldLabel.setLabelFor(collectionField);
        usernameFieldLabel = new JLabel("Username: ");
        usernameFieldLabel.setLabelFor(usernameField);
        passwordFieldLabel = new JLabel("Password: ");
        passwordFieldLabel.setLabelFor(passwordField);
        websiteFieldLabel = new JLabel("Website: ");
        websiteFieldLabel.setLabelFor(websiteField);
    }

    // EFFECTS: creates JText fields
    // Code partially extracted from TextSamplerDemo() constructor in:
    // https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TextSamplerDemoProject/src/components/TextSamplerDemo.java
    private void createJTextFields() {
        collectionField = new JTextField(10);
        collectionField.setActionCommand("Collection");
        collectionField.addActionListener(this);
        usernameField = new JTextField(10);
        usernameField.setActionCommand("Username");
        usernameField.addActionListener(this);
        passwordField = new JPasswordField(10);
        passwordField.setActionCommand("Password");
        passwordField.addActionListener(this);
        websiteField = new JTextField(10);
        websiteField.setActionCommand("Website");
        websiteField.addActionListener(this);
    }

    //EFFECTS: adds a new Collection
    private void displayAddNewCollection() {
        frame.remove(panel);
        displayLabelTextFieldButton(collectionFieldLabel, collectionField, "Create Collection");
        frame.add(panel);
        updateWindow();

    }

    // EFFECTS: displays current collections. If there are no collections return
    private void displayCollections() {
        frame.remove(panel);
        ArrayList<Object [][]> collectionsInDataFormat = manager.getCollectionsInDataFormat(manager.getCollections());
        if (collectionsInDataFormat == null) {
            return;
        }
        displayCollectionsInDataFormat(collectionsInDataFormat);
    }


    // EFFECTS: Given collections in JTable data format, display them on window
    // Code partially extract4ed from:
    // https://docs.oracle.com/javase/tutorial/uiswing/examples/components/SimpleTableDemoProject/src/components/SimpleTableDemo.java
    private void displayCollectionsInDataFormat(ArrayList<Object[][]> collectionsInDataFormat) {
        panel = new JPanel(new GridBagLayout());
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
        updateWindow();
    }


}
