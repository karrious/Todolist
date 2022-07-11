package ui;

import model.TodoList;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;

// REFERENCES: https://www.youtube.com/watch?v=Kmgo00avvEw&t=10072s
//             https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
//             https://tjisblogging.blogspot.com/2013/04/how-to-set-background-image-to-jframe.html
//             https://docs.oracle.com/javase/tutorial/uiswing/components/table.html#simple
//             https://stackoverflow.com/questions/5752307/how-to-retrieve-value-from-jtextfield-in-java-swing
//             SimpleDrawingPlayer
//             Java Oracle Doc

// Graphical implementation of the main menu of the to-do list app
public class MainMenu extends JFrame implements ActionListener {

    private static final int B_WIDTH = 300;
    private static final int B_HEIGHT = 20;
    private static final int B_POSITION = 150;

    private TodoList todoList;
    private ListGraphics listGraphics;


    // EFFECTS: initializes to-do list main menu graphics
    public MainMenu() {
        super("TodoList Application!");
        initializeFields();
        initializeGraphics();
        initializeBackground();
        labelsAndButtons();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes the fields when starting program
    private void initializeFields() {
        todoList = new TodoList();
    }

    // MODIFIES: this
    // EFFECTS: initializes the graphics when starting program
    private void initializeGraphics() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(15, 15, 15, 15));
        setLayout(null);
        pack();
        setLocationRelativeTo(null);
        setResizable(true);
    }

    // EFFECTS: initializes the background when starting program
    private void initializeBackground() {
        try {
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/ui/images/background.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the buttons and labels when starting the program
    private void labelsAndButtons() {
        label();
        accessListButton();
        quitButton();
    }

    // MODIFIES: this
    // EFFECTS: creates a label at the top
    private void label() {
        JLabel choiceLabel = new JLabel("Please select one of the following:", JLabel.CENTER);
        choiceLabel.setBounds(30, 20, 300, 20);
        add(choiceLabel);
        choiceLabel.setForeground(Color.black);
    }

    // MODIFIES: this
    // EFFECTS: creates the button to view the to-do list
    private void accessListButton() {
        JButton accessListButton = new JButton("view and modify tasks");
        accessListButton.setActionCommand("accessListButton");
        accessListButton.addActionListener(this);
        add(accessListButton);
        accessListButton.setBounds(B_POSITION, 80, B_WIDTH, B_HEIGHT);
        accessListButton.setForeground(Color.black);
    }

    // MODIFIES: this
    // EFFECTS: creates the button to quit the application
    private void quitButton() {
        JButton quitButton = new JButton("quit app");
        quitButton.setActionCommand("quitButton");
        quitButton.addActionListener(this);
        add(quitButton);
        quitButton.setBounds(B_POSITION, 160, B_WIDTH, B_HEIGHT);
        quitButton.setForeground(Color.black);
    }

    // MODIFIES: this, listGraphics
    // EFFECTS: take user interaction and respond accordingly
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("accessListButton")) {
            listGraphics = new ListGraphics(todoList);
        } else if (action.equals("quitButton")) {
            todoList.printLog();
            System.exit(0);
        }
    }
}

