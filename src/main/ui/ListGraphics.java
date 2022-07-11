package ui;

import model.Task;
import model.TodoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;

// REFERENCES: https://www.youtube.com/watch?v=Kmgo00avvEw&t=10072s
//             https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
//             https://tjisblogging.blogspot.com/2013/04/how-to-set-background-image-to-jframe.html
//             https://docs.oracle.com/javase/tutorial/uiswing/components/table.html#simple
//             https://stackoverflow.com/questions/5752307/how-to-retrieve-value-from-jtextfield-in-java-swing
//             SimpleDrawingPlayer
//             Java Oracle Doc

// Graphical implementation of the task menu of the to-do list app
public class ListGraphics extends JFrame implements ActionListener {
    private TodoList todoList = new TodoList();
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private DefaultTableModel tableModel;
    private JTable table;
    final String[] columnLabels = new String[] {
            "Todo Name",
            "Due Date",
            "Finished?"
    };

    private static final String JSON_STORE = "./data/todoList.json";

    // MODIFIES: this
    // EFFECTS: initializes view list menu graphics and initialize fields
    public ListGraphics(TodoList tdl) {
        this.todoList = tdl;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setTitle("my todo list");
        initializeGraphics();
        initializeBackground();
        initializeTable();
        buttons();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes the graphics when starting program
    private void initializeGraphics() {
        setPreferredSize(new Dimension(550, 800));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    // EFFECTS: initializes the background when starting program
    private void initializeBackground() {
        try {
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/ui/images/background1.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes to-do list table when starting program
    private void initializeTable() {
        tableModel = new DefaultTableModel(null, columnLabels) {};
        table = new JTable(tableModel);
        add(new JScrollPane(table));
        setLayout(new FlowLayout());

        for (int i = 0; i < todoList.sizeOfTodoList(); i++) {
            Task task = todoList.getTask(i);
            Object[] tableRow = new Object[] {
                    task.getTodoName(), // name column
                    task.getDeadline(), // due date column
                    task.getStatus(), // status column need to delete!
            };
            tableModel.addRow(tableRow);
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the buttons and labels when starting the program
    private void buttons() {
        addTaskButton();
        viewNumberOfTasksButton();
        changeTaskButton();
        saveButton();
        loadButton();
    }

    // MODIFIES: this
    // EFFECTS: creates the button to add new task
    private void addTaskButton() {
        JButton addTaskButton = new JButton("Add new task");
        addTaskButton.setActionCommand("addTaskButton");
        addTaskButton.addActionListener(this);
        add(addTaskButton);
        addTaskButton.setForeground(Color.black);
    }

    // MODIFIES: this
    // EFFECTS: creates the button to view number of task
    private void viewNumberOfTasksButton() {
        JButton viewNumberOfTaskButton = new JButton("View number of tasks");
        viewNumberOfTaskButton.setActionCommand("viewNumberOfTasksButton");
        viewNumberOfTaskButton.addActionListener(this);
        add(viewNumberOfTaskButton);
        viewNumberOfTaskButton.setForeground(Color.black);
    }

    // MODIFIES: this
    // EFFECTS: creates the button to remove or mark task as finished
    private void changeTaskButton() {
        JButton markTaskAsDoneButton = new JButton("Remove or Mark a task as finished");
        markTaskAsDoneButton.setActionCommand("removeOrMarkTaskAsDoneButton");
        markTaskAsDoneButton.addActionListener(this);
        add(markTaskAsDoneButton);
        markTaskAsDoneButton.setForeground(Color.black);
    }

    // MODIFIES: this
    // EFFECTS: creates the button to save current to-do list
    private void saveButton() {
        JButton saveButton = new JButton("save all tasks in list currently");
        saveButton.setActionCommand("saveButton");
        saveButton.addActionListener(this);
        add(saveButton);
        saveButton.setForeground(Color.black);
    }

    // MODIFIES: this
    // EFFECTS: creates the button to load previous to-do list
    private void loadButton() {
        JButton loadButton = new JButton("load all saved tasks in list");
        loadButton.setActionCommand("loadButton");
        loadButton.addActionListener(this);
        add(loadButton);
        loadButton.setForeground(Color.black);
    }

    // MODIFIES: this, AddTaskGraphics, ChangeTaskGraphics
    // EFFECTS: take user interaction and respond accordingly
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("addTaskButton")) {
            new AddTaskGraphics(this, todoList);
        } else if (action.equals("viewNumberOfTasksButton")) {
            viewNumberOfTask();
        } else if (action.equals("removeOrMarkTaskAsDoneButton")) {
            new ChangeTaskGraphics(this, todoList);
        } else if (action.equals("saveButton")) {
            saveTodoList();
        } else if (action.equals("loadButton")) {
            loadTodoList();
        }
    }

    // EFFECTS: helper function to view number of tasks
    private void viewNumberOfTask() {
        JOptionPane.showMessageDialog(null, "There are " + todoList.sizeOfTodoList() + " tasks in todo list");
    }

    // MODIFIES: this
    // EFFECTS: helper function to save to-do list
    public void saveTodoList() {
        try {
            jsonWriter.open();
            jsonWriter.write(todoList);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "todo list saved!");
        } catch (FileNotFoundException exception) {
            JOptionPane.showMessageDialog(null, "todo list not found");
        }
    }

    // MODIFIES: this
    // EFFECTS: helper function to load previous to-do list
    public void loadTodoList() {
        try {
            todoList = jsonReader.read();
            dispose();
            new ListGraphics(todoList);
            JOptionPane.showMessageDialog(null, "todo list loaded!");
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(null, "todo list not found");
        }
    }
}
