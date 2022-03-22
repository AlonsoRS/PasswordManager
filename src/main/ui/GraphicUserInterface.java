package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;



public class GraphicUserInterface extends JFrame implements ActionListener {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    JFrame frame;
    ManageModel manager;
    JPanel panel;

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

        JMenuItem item = new JMenuItem("Display Collections");
        item.addActionListener(this);
        optionsMenu.add(item);
        item = new JMenuItem("Add a new Collection");
        item.addActionListener(this); //CREATE ANOTHER CLASS FOR THIS?
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


        menuBar.add(optionsMenu);
        menuBar.add(fileMenu);

        frame.setJMenuBar(menuBar);
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
        }
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

    private void addUserToCollection() {
    }

    //EFFECTS: adds a new Collection
    // Code partially extracted from TextSamplerDemo() constructor in:
    // https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TextSamplerDemoProject/src/components/TextSamplerDemo.java
    private void addNewCollection() {
        frame.remove(panel);
        //Create Username field
        JTextField usernameField = new JTextField(10);
        usernameField.setActionCommand("Username");
        usernameField.addActionListener(this);

        //Create a password field.
        JPasswordField passwordField = new JPasswordField(10);
        passwordField.setActionCommand("Password");
        passwordField.addActionListener(this);

        //Create website field.
        JTextField websiteField = new JPasswordField(10);
        websiteField.setActionCommand("Website");
        websiteField.addActionListener(this);

        //Create some labels for the fields.
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
        panel.add(usernameFieldLabel);
        panel.add(usernameField);
        //Adjust constraints for the label so it's at (5,5).
        layout.putConstraint(SpringLayout.WEST, usernameFieldLabel,
                5,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, usernameFieldLabel,
                5,
                SpringLayout.NORTH, panel);

        //Adjust constraints for the text field so it's at
        //(<label's right edge> + 5, 5).
        layout.putConstraint(SpringLayout.WEST, usernameField,
                5,
                SpringLayout.EAST, usernameFieldLabel);
        layout.putConstraint(SpringLayout.NORTH, usernameField,
                5,
                SpringLayout.NORTH, panel);

        frame.add(panel);

        displayWindow();

        JLabel[] labels = {usernameFieldLabel, passwordFieldLabel, websiteFieldLabel};
        JTextField[] textFields = {usernameField, passwordField, websiteField};


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
            table.setPreferredScrollableViewportSize(new Dimension(WIDTH / manager.getNumberOfCollections(),
                    (int) (HEIGHT * 0.8)));
            table.setFillsViewportHeight(true);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBorder(BorderFactory.createTitledBorder(manager.getCollectionNameAt(index)));
            panel.add(scrollPane);
            index++;
        }
        panel.setBackground(Color.RED);
        frame.add(panel);
        displayWindow();
    }


}
