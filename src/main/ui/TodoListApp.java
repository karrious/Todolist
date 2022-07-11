package ui;

import model.Task;
import model.TodoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//creates a console based application for users for the to-do list app
public class TodoListApp {
    private static final String JSON_STORE = "./data/TodoList.json";
    private Scanner input;
    private TodoList todoList;
    boolean notToQuit;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //MODIFIES: this
    //EFFECTS: initializes a new to-do list
    //REFERENCES: TellerApp, JsonSerializationDemo
    public TodoListApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        todoList = new TodoList();
        notToQuit = true;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTodoList();
    }

    //MODIFIES: this
    //EFFECTS: takes user input
    //REFERENCES: TellerApp
    public void runTodoList() {
        String entered = null;

        while (notToQuit) {
            menu();
            entered = input.next();
            operate(entered);
        }
    }

    //REQUIRES: user input as a string needs to be given as a parameter
    //MODIFIES: this
    //EFFECTS: will take user input and run different functions of to-do list app
    //REFERENCES: TellerApp
    private void operate(String entered) {
        if (entered.equals("c")) {
            addTaskApp();
        } else if (entered.equals("r")) {
            removeTaskApp();
        } else if (entered.equals("m")) {
            completeTaskApp();
        } else if (entered.equals("vl")) {
            viewTodoListApp();
        } else if (entered.equals("vn")) {
            sizeOfTodoListApp();
        } else if (entered.equals("s")) {
            saveTodoList();
        } else if (entered.equals("l")) {
            loadTodoList();
        } else if (entered.equals("q")) {
            quitApp();
        } else {
            System.out.println("Please choose from one of the above options");
        }
    }

    //EFFECTS: displays menu options
    //REFERENCES: TellerApp
    private void menu() {
        System.out.println("\nChoose one of the following by entering numbers from 1-5");
        System.out.println("\tc  -> Create new task");
        System.out.println("\tr  -> Remove task");
        System.out.println("\tm  -> Mark task as completed");
        System.out.println("\tvl -> View to-do list");
        System.out.println("\tvn -> View number of tasks on list");
        System.out.println("\ts  -> Save todo list to file");
        System.out.println("\tl  -> Load todo list from file");
        System.out.println("\tq  -> Quit");
    }

    //MODIFIES: this
    //EFFECTS: add entered task to list of to-dos
    private void addTaskApp() {
        System.out.println("\nEnter name of task: ");
        String name = input.next();
        System.out.println("\nEnter task deadline: ");
        String dueDate = input.next();
        System.out.println("\nEnter task status (true for completed, false other wise): ");
        Boolean status = Boolean.valueOf(input.next());
        todoList.addTask(new Task(name, dueDate, status));
        System.out.println("\n");
    }

    //MODIFIES: this
    //EFFECTS: remove entered task from list of to-dos if present in the list
    private void removeTaskApp() {
        System.out.println("\n" + todoList.viewTodoList());
        System.out.println("Enter name of the task you would like to remove: ");
        String name = input.next();
        if (todoList.removeTask(name)) {
            System.out.println("\ntask successfully removed");
        } else {
            System.out.println("\ntask not found in todo list");
        }
        System.out.println("\n");
    }

    //MODIFIES: this
    //EFFECTS: change status of entered task to true if present in to-do list
    private void completeTaskApp() {
        System.out.println("\n" + todoList.viewTodoList());
        System.out.println("\nEnter name of the task you would like to mark as complete: ");
        String name = input.next();
        if (todoList.completeTask(name)) {
            System.out.println("\ntask completed!");
        } else {
            System.out.println("\ntask not found in todo list");
        }
        System.out.println("\n");
    }

    //EFFECTS: view all to-dos in the list
    private void viewTodoListApp() {
        System.out.println("\n" + todoList.viewTodoList() + "\n");
    }

    //EFFECTS: output number of to-dos in list
    private void sizeOfTodoListApp() {
        System.out.println("\nThere are " + todoList.sizeOfTodoList() + " tasks in todo list\n");
    }

    // EFFECTS: saves to-do List to file
    //REFERENCES: JsonSerializationDemo
    private void saveTodoList() {
        try {
            jsonWriter.open();
            jsonWriter.write(todoList);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads to-do List from file
    //REFERENCES: JsonSerializationDemo
    private void loadTodoList() {
        try {
            todoList = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //MODIFIES: notToQuit
    //EFFECTS: quit execution of the app
    private void quitApp() {
        notToQuit = false;
        System.out.println("\nSee you next time!");
    }

}
