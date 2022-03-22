package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import model.*;


public class GraphicUserInterface extends JFrame implements ActionListener {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    JFrame frame;
    ManageModel manager;
    JPanel panel;

    public GraphicUserInterface() {
        manager = new ManageModel();
        panel = new JPanel(new GridBagLayout());
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

    private void addNewCollection() {
    }


    //Code partially extract4ed from:
    // https://docs.oracle.com/javase/tutorial/uiswing/examples/components/SimpleTableDemoProject/src/components/SimpleTableDemo.java
    private void displayCollections() {
        ArrayList<Object [][]> collectionsInDataFormat = manager.getCollectionsInDataFormat();
        if (collectionsInDataFormat == null) {
            return;
        }

        String[] columnNames = {"Username", "Password", "Website",};
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
