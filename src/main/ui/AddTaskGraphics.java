package ui;

import model.Task;
import model.TodoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// REFERENCES: https://www.youtube.com/watch?v=Kmgo00avvEw&t=10072s
//             https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
//             https://tjisblogging.blogspot.com/2013/04/how-to-set-background-image-to-jframe.html
//             https://docs.oracle.com/javase/tutorial/uiswing/components/table.html#simple
//             https://stackoverflow.com/questions/5752307/how-to-retrieve-value-from-jtextfield-in-java-swing
//             SimpleDrawingPlayer
//             Java Oracle Doc

// Graphical implementation of the add task frame of the to-do list app
public class AddTaskGraphics extends JFrame implements ActionListener {
    private TodoList todoList;
    private ListGraphics listGraphics;
    private JTextField taskNameField;
    private JTextField taskDeadlineField;
    private JTextField taskStatusField;
    private JRadioButton finished;
    private JRadioButton notFinished;
    private JLabel label;
    private boolean status;
    private JButton addButton;

    // EFFECTS: initializes add task frame of to-do list application
    public AddTaskGraphics(ListGraphics listGraphics, TodoList tdl) {
        super("Add Task");
        initializeFields(listGraphics, tdl);
        initializeGraphics();
        buttons();
    }

    // MODIFIES: this
    // EFFECTS: initializes the fields when starting program
    private void initializeFields(ListGraphics listGraphics, TodoList tdl) {
        this.listGraphics = listGraphics;
        todoList =  tdl;
    }

    // MODIFIES: this
    // EFFECTS: initializes the graphics when starting program
    private void initializeGraphics() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(15, 15, 15, 15));
        setLayout(null);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: initializes the buttons, field, and labels when starting the program
    private void buttons() {
        addTaskNameField();
        addTaskDeadlineField();
        addTaskStatusField();
        addButton();
    }

    // MODIFIES: this
    // EFFECTS: create a label and field to enter task name
    private void addTaskNameField() {
        label = new JLabel("enter task name");
        label.setBounds(48, 40, 400, 20);
        add(label);
        label.setForeground(Color.darkGray);

        taskNameField = new JTextField(5);
        taskNameField.setBounds(50, 70, 300, 20);
        add(taskNameField);
    }

    // MODIFIES: this
    // EFFECTS: create a label and field to enter task deadline
    private void addTaskDeadlineField() {
        label = new JLabel("enter task deadline");
        label.setBounds(50, 100, 600, 20);
        add(label);
        label.setForeground(Color.darkGray);

        taskDeadlineField = new JTextField(5);
        taskDeadlineField.setBounds(50, 130,300,20);
        add(taskDeadlineField);
    }

    // MODIFIES: this
    // EFFECTS: create a JRadio Button to select task status
    private void addTaskStatusField() {
        finished = new JRadioButton("true");
        notFinished = new JRadioButton("false");
        finished.setActionCommand("true");
        notFinished.setActionCommand("false");
        ButtonGroup group = new ButtonGroup();
        group.add(finished);
        group.add(notFinished);
        finished.setBounds(50, 160, 100, 20);
        notFinished.setBounds(200, 160, 100, 20);
        finished.addActionListener(this);
        notFinished.addActionListener(this);
        add(finished);
        add(notFinished);
        this.setStatus();
    }

    // MODIFIES: this
    // EFFECTS: create a button to add task name
    private void addButton() {
        addButton = new JButton("Add task");
        addButton.setActionCommand("addTask");
        addButton.addActionListener(this);
        add(addButton);
        addButton.setBounds(145,210,100,20);
        addButton.setForeground(Color.darkGray);
        enableEvents(AWTEvent.KEY_EVENT_MASK);
    }

    // MODIFIES: this
    // EFFECTS: take user interaction from JButton
    public void setStatus() {
        this.finished.addActionListener(e -> {
            String action = e.getActionCommand();
            status = action.equals("true");
        });
    }

    // MODIFIES: this, listGraphics
    // EFFECTS: take user interaction and respond accordingly
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("addTask")) {
            this.addButtonAction(status);
        }
    }

    // MODIFIES: this, listGraphics
    // EFFECTS: add given task to list of to-do
    private void addButtonAction(boolean status) {
        String name = taskNameField.getText();
        String deadline = taskDeadlineField.getText();
        todoList.addTask(new Task(name, deadline, status));
        dispose();
        listGraphics.dispose();
        new ListGraphics(todoList);
    }
}
