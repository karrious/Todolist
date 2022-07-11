package ui;

import model.TodoList;

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
public class ChangeTaskGraphics extends JFrame implements ActionListener {
    private TodoList todoList;
    private ListGraphics listGraphics;
    private JLabel label;
    private JTextField taskNameField;

    // EFFECTS: initializes change task menu graphics
    public ChangeTaskGraphics(ListGraphics listGraphics, TodoList tdl) {
        super("Change Task");
        initializeFields(listGraphics, tdl);
        initializeGraphics();
        buttons();
        pack();
        setVisible(true);
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
        setPreferredSize(new Dimension(450, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(15, 15, 15, 15));
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: initializes the buttons and labels when starting the program
    private void buttons() {
        addTaskNameField();
        removeTaskButton();
        markTaskAsDoneButton();
    }

    // MODIFIES: this
    // EFFECTS: creates the label and field to enter task name
    private void addTaskNameField() {
        label = new JLabel("enter task name");
        label.setBounds(48, 40, 400, 20);
        add(label);
        label.setForeground(Color.darkGray);

        taskNameField = new JTextField(5);
        taskNameField.setBounds(50, 70, 325, 20);
        add(taskNameField);
    }

    // MODIFIES: this
    // EFFECTS: creates the button to remove task from to-do list
    private void removeTaskButton() {
        JButton removeTaskButton = new JButton("Remove task");
        removeTaskButton.setActionCommand("removeTaskButton");
        removeTaskButton.addActionListener(this);
        add(removeTaskButton);
        removeTaskButton.setBounds(50, 120, 130, 20);
        removeTaskButton.setForeground(Color.black);
    }

    // MODIFIES: this
    // EFFECTS: creates the button to mark task as finished in to-do list
    private void markTaskAsDoneButton() {
        JButton markTaskAsDoneButton = new JButton("Mark task as finished");
        markTaskAsDoneButton.setActionCommand("markTaskAsDoneButton");
        markTaskAsDoneButton.addActionListener(this);
        add(markTaskAsDoneButton);
        markTaskAsDoneButton.setBounds(200, 120, 180, 20);
        markTaskAsDoneButton.setForeground(Color.black);
    }

    // MODIFIES: this, listGraphics
    // EFFECTS: take user interaction and respond accordingly
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("removeTaskButton")) {
            removeTask();
        } else if (action.equals("markTaskAsDoneButton")) {
            markTaskAsDone();
        }
    }

    // MODIFIES: this
    // EFFECTS: helper function to remove task from to-do list
    private void removeTask() {
        String name = taskNameField.getText();
        if (todoList.removeTask(name)) {
            dispose();
            listGraphics.dispose();
            new ListGraphics(todoList);
            JOptionPane.showMessageDialog(null, "task successfully removed");
        } else {
            JOptionPane.showMessageDialog(null,"task not found in todo list");
        }
    }

    // MODIFIES: this
    // EFFECTS: helper function to mark task as done from to-do list
    private void markTaskAsDone() {
        String name = taskNameField.getText();
        if (todoList.completeTask(name)) {
            dispose();
            listGraphics.dispose();
            new ListGraphics(todoList);
            JOptionPane.showMessageDialog(null, "task completed!");
        } else {
            JOptionPane.showMessageDialog(null,"task not found in todo list");
        }
    }
}
