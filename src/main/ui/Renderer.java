package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Renderer extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    JFrame frame;

    public Renderer() {
        initializeFrame();
        createMenu();
        displayWindow();
    }


    //EFFECTS: Creates MenuBar with Menus and Items
    // Code taken from createAndShowGUI() from
    // https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TopLevelDemoProject/src/components/TopLevelDemo.java
    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setOpaque(true);
        menuBar.setBackground(new Color(154, 165, 127));
        menuBar.setPreferredSize(new Dimension(200, 40));

        JMenu optionsMenu = new JMenu("Options");
        JMenu fileMenu = new JMenu("File");

        JMenuItem item = new JMenuItem("Display Collections");
        optionsMenu.add(item);
        item = new JMenuItem("Add a new Collection");
        optionsMenu.add(item);
        item = new JMenuItem("Add a new user to a Collection");
        optionsMenu.add(item);
        item = new JMenuItem("Search Users by username");
        optionsMenu.add(item);
        item = new JMenuItem("Quit");
        optionsMenu.add(item);
        item = new JMenuItem("Save to File");
        fileMenu.add(item);
        item = new JMenuItem("Load from File");
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


}
