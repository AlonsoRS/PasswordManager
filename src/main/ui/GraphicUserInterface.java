package ui;

import model.Event;
import model.EventLog;
import model.User;
import model.Collection;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


// This class in responsible for the graphic interface of the program

public class GraphicUserInterface extends JFrame implements ActionListener {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    public static final int SEPARATION_BETWEEN_FIELDS = 40;
    ManageModel manager;
    JFrame frame;
    JPanel panel;
    JMenuBar menuBar;
    SpringLayout springLayout;

    JTextField collectionField;
    JTextField usernameField;
    JTextField passwordField;
    JTextField websiteField;

    JLabel collectionFieldLabel;
    JLabel usernameFieldLabel;
    JLabel passwordFieldLabel;
    JLabel websiteFieldLabel;

    // EFFECTS: constructs the graphic interface
    public GraphicUserInterface() {
        initializeFields();
        initializeGraphics();
        updateWindow();


    }

    // MODIFIES: this
    // EFFECTS: initializes graphics
    private void initializeGraphics() {
        panel = new JPanel();
        createJTextFields();
        createJLabels();
        initializeFrame();
        createMenu();
        displayBackgroundImage();


    }

    // EFFECTS: initializes non-graphic related fields
    private void initializeFields() {
        manager = new ManageModel();
    }


    // MODIFIES: this
    // EFFECTS: Creates MenuBar with Menus and Items
    // Code partially taken from createAndShowGUI() in
    // https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TopLevelDemoProject/src/components/TopLevelDemo.java
    private void createMenu() {
        menuBar = new JMenuBar();
        menuBar.setOpaque(true);
        menuBar.setPreferredSize(new Dimension(200, 40));
        JMenu optionsMenu = new JMenu("Options");
        JMenu fileMenu = new JMenu("File");
        initializeMenuItems(optionsMenu, fileMenu);
        menuBar.add(optionsMenu);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
    }

    // MODIFIES: this
    // EFFECTS: displays background image
    private void displayBackgroundImage() {
        frame.remove(panel);
        panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(createImageIcon("./data/background.png"));
        panel.add(label);
        frame.add(panel);
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
        item = new JMenuItem("Save to File", createImageIcon("./data/saveIcon.png"));
        item.addActionListener(this);
        fileMenu.add(item);
        item = new JMenuItem("Load from File", createImageIcon("./data/loadIcon.png"));
        item.addActionListener(this);
        fileMenu.add(item);
    }


    // REQUIRES: location exists in ./data directory
    // EFFECTS: returns ImageIcon with image of given location
    private ImageIcon createImageIcon(String location) {
        return new ImageIcon(location);
    }


    // MODIFIES: this
    // EFFECTS: displays GUI on screen
    //Code taken from ButtonDemo class in:
    //https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ButtonDemoProject/src/components/ButtonDemo.java
    private void updateWindow() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    // MODIFIES: this
    //EFFECT: initializes JFrame
    //Code taken from ButtonDemo class in:
    //https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ButtonDemoProject/src/components/ButtonDemo.java
    private void initializeFrame() {
        frame = new JFrame("Password Manager");
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    }

    // EFFECTS: returns true if action event comes from an option in the menu
    boolean isOptionMenu(ActionEvent ae) {
        return ae.getActionCommand().equals("Display Collections")
                || ae.getActionCommand().equals("Add a new Collection")
                || ae.getActionCommand().equals("Add a new user to a Collection")
                || ae.getActionCommand().equals("Search Users by username")
                || ae.getActionCommand().equals("Save to File")
                || ae.getActionCommand().equals("Load from File")
                || ae.getActionCommand().equals("Quit");
    }



    @Override
    // EFFECTS: chooses what menu or button option to do based on what the user selected
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

    // MODIFIES: this
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
            case ("Quit"):
                quit();
                break;
            case ("Save to File"):
                saveToFile();
                break;
            case ("Load from File"):
                loadFromFile();
                break;
        }
    }


    // EFFECTS: quits application
    private void quit() {
        printEventLog(EventLog.getInstance());
        System.exit(0);
    }


    // EFFECTS: prints event log in console
    // Code taken from print log method in ScreenPrinter class in:
    // https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
    private void printEventLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS chooses what operation to perform based on button pressed
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

    // MODIFIES: this
    // EFFECTS: displays found users, if there are none return
    private void displayFoundUser(ArrayList<Object[][]> collectionsInDataFormat) {
        frame.remove(panel);
        if (collectionsInDataFormat == null) {
            return;
        }
        displayCollectionsInDataFormat(collectionsInDataFormat);

    }

    // MODIFIES: this
    // EFFECTS: searches for Username inputted by user
    private ArrayList<Object [][]> searchForUser() {
        ArrayList<Collection> foundUsers = manager.searchUser(usernameField.getText());
        return manager.getCollectionsInDataFormat(foundUsers);


    }

    // MODIFIES: this
    //EFFECTS: creates User with inputs form user and adds the User to a Collection
    private void createUserAndAddToCollection() {
        User user = manager.createUser(usernameField.getText(), passwordField.getText(), websiteField.getText());
        manager.addNewUserToCollection(collectionField.getText(), user);

    }

    // MODIFIES: this
    // EFFECTS: creates a Collection with the name give by the user
    private void createCollection() {
        manager.addNewCollection(collectionField.getText());
    }

    // MODIFIES: this
    // EFFECTS: loads Collections from file
    private void loadFromFile() {
        manager.loadCollections();

    }

    // MODIFIES: this
    // EFFECTS: saves Collections to file
    private void saveToFile() {
        manager.saveCollections();
    }

    // MODIFIES: this
    // EFFECTS: displays the panel to search for a user by username
    private void displaySearchUsersByUsername() {
        frame.remove(panel);
        displayLabelTextFieldButton(usernameFieldLabel, usernameField, "Search User");
        updateWindow();
    }

    // MODIFIES: this
    // EFFECTS: displays given labels and text fields along with a button of name buttonName
    // Code partially extracted from createAndShowGUI() method in
    //https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/SpringDemo2Project/src/layout/SpringDemo2.java
    private void displayLabelTextFieldButton(JLabel label, JTextField textField, String buttonName) {
        createSpringLayout();
        panel.add(label);
        panel.add(textField);
        placeCollectionTextAndInput(label, textField);
        JButton button = new JButton(buttonName);
        button.addActionListener(this);
        springLayout.putConstraint(SpringLayout.WEST, button, 5, SpringLayout.EAST, textField);
        springLayout.putConstraint(SpringLayout.NORTH, button, 5, SpringLayout.SOUTH, textField);
        panel.add(button);
        frame.add(panel);
    }




    // MODIFIES: this
    // EFFECTS: displays panel to add a user to a collection
    private void displayAddUserToCollection() {
        frame.remove(panel);
        createSpringLayout();
        addAllJTextAndJLabelsToPanel();
        placeCollectionTextAndInput(collectionFieldLabel, collectionField);
        placeBelowAndNextTo(usernameFieldLabel, collectionFieldLabel, usernameField, collectionField);
        placeBelowAndNextTo(passwordFieldLabel, usernameFieldLabel, passwordField, usernameField);
        placeBelowAndNextTo(websiteFieldLabel, passwordFieldLabel, websiteField, passwordField);
        placeButtonBelowWebsiteField();
        frame.add(panel);
        updateWindow();
    }

    // REQUIRES: websiteField is already in placed in panel
    // MODIFIES: this
    // EFFECTS: creates and places button below websiteField
    private void placeButtonBelowWebsiteField() {
        JButton button = new JButton("Add User to Collection");
        button.addActionListener(this);
        springLayout.putConstraint(SpringLayout.WEST, button, 5, SpringLayout.WEST, panel);
        springLayout.putConstraint(SpringLayout.NORTH, button, SEPARATION_BETWEEN_FIELDS,
                SpringLayout.NORTH, websiteField);
        panel.add(button);
    }

    // MODIFIES: this
    // EFFECTS: initializes springLayout, sets the panel to it and sets background color for panel
    private void createSpringLayout() {
        springLayout = new SpringLayout();
        panel = new JPanel(springLayout);
        panel.setBackground(Color.LIGHT_GRAY);
    }

    // MODIFIES:this
    // EFFECTS: adds all JLabels and JText fields to panel
    private void addAllJTextAndJLabelsToPanel() {
        panel.add(collectionFieldLabel);
        panel.add(collectionField);
        panel.add(usernameFieldLabel);
        panel.add(usernameField);
        panel.add(passwordFieldLabel);
        panel.add(passwordField);
        panel.add(websiteFieldLabel);
        panel.add(websiteField);
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECT: places :
    //          -labelA below labelB and next to panel
    //          -fieldA below fieldB and next to labelA
    // Code partially extracted from createAndShowGUI() method in
    //https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/SpringDemo2Project/src/layout/SpringDemo2.java
    private void placeBelowAndNextTo(JLabel labelA, JLabel labelB,
                                     JTextField fieldA, JTextField fieldB) {
        springLayout.putConstraint(SpringLayout.WEST, labelA, 5, SpringLayout.WEST, panel);
        springLayout.putConstraint(SpringLayout.NORTH, labelA, SEPARATION_BETWEEN_FIELDS, SpringLayout.NORTH,
                labelB);
        springLayout.putConstraint(SpringLayout.WEST, fieldA, 5, SpringLayout.EAST, labelA);
        springLayout.putConstraint(SpringLayout.NORTH, fieldA, SEPARATION_BETWEEN_FIELDS, SpringLayout.NORTH,
                fieldB);
    }

    // MODIFIES: this
    // EFFECT: places collection JLabel and JTextField in Panel
    // Code partially extracted from createAndShowGUI() method in
    //https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/SpringDemo2Project/src/layout/SpringDemo2.java
    private void placeCollectionTextAndInput(JLabel collectionFieldLabel, JTextField collectionField) {
        springLayout.putConstraint(SpringLayout.WEST, collectionFieldLabel, 5, SpringLayout.WEST, panel);
        springLayout.putConstraint(SpringLayout.NORTH, collectionFieldLabel, 5, SpringLayout.NORTH, panel);
        springLayout.putConstraint(SpringLayout.WEST, collectionField, 5, SpringLayout.EAST, collectionFieldLabel);
        springLayout.putConstraint(SpringLayout.NORTH, collectionField, 5, SpringLayout.NORTH, panel);
    }

    // MODIFIES: this
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

    // MODIFIES: this
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

    // MODIFIES: this
    //EFFECTS: adds a new Collection
    private void displayAddNewCollection() {
        frame.remove(panel);
        displayLabelTextFieldButton(collectionFieldLabel, collectionField, "Create Collection");
        frame.add(panel);
        updateWindow();

    }

    // MODIFIES: this
    // EFFECTS: displays current collections. If there are no collections return
    private void displayCollections() {
        frame.remove(panel);
        ArrayList<Object [][]> collectionsInDataFormat = manager.getCollectionsInDataFormat(manager.getCollections());
        if (collectionsInDataFormat == null) {
            return;
        }
        displayCollectionsInDataFormat(collectionsInDataFormat);
    }


    // MODIFIES: this
    // EFFECTS: Given collections in JTable data format, display them on window
    // Code partially extracted from:
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
