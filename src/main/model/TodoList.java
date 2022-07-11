package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//a to-do list that has a list of tasks
public class TodoList implements Writable {
    private final ArrayList<Task> todoList;

    public static final String PATH = "./data/savedTodos.json";

    //MODIFIES: this
    //EFFECTS: creates new empty to-do list
    public TodoList() {
        todoList = new ArrayList<>();
    }

    //REQUIRES: task needs to be given as a parameter
    //MODIFIES: this
    //EFFECTS: add given task to list of to-do list
    public void addTask(Task task) {
        Event event = new Event("task: " + task.getTodoName()
                + ", deadline " + task.getDeadline() + ", finished " + task.getStatus() + ", added to list.\n");
        EventLog.getInstance().logEvent(event);
        todoList.add(task);
    }

    //REQUIRES: task name as a string needs to be given as a parameter
    //MODIFIES: this
    //EFFECTS: if given task name is present in the list, it would return true and remove the task,
    //         else return false.
    public Boolean removeTask(String taskName) {
        for (Task taskNext : todoList) {
            if (taskNext.getTodoName().equals(taskName)) {
                todoList.remove(taskNext);
                Event event = new Event("task: " + taskNext.getTodoName()
                        + ", deadline " + taskNext.getDeadline() + ", finished " + taskNext.getStatus()
                        + ", removed from list. \n");
                EventLog.getInstance().logEvent(event);
                return true;
            }
        }
        return false;
    }

    //REQUIRES: task name as a string needs to be given as a parameter
    //MODIFIES: this
    //EFFECTS: if given task name is present in the list, it would return true and set task status as true,
    //         else return false.
    public Boolean completeTask(String taskName) {
        for (Task taskNext : todoList) {
            if (taskNext.getTodoName().equals(taskName)) {
                taskNext.setStatus(true);
                Event event = new Event("task: " + taskNext.getTodoName()
                        + ", deadline " + taskNext.getDeadline() + ", finished " + taskNext.getStatus()
                        + ", marked as finished.\n");
                EventLog.getInstance().logEvent(event);
                return true;
            }
        }
        return false;
    }

    //MODIFIES: listTodo
    //EFFECTS: prints out every task in to-do list with name, due date, and status
    public String viewTodoList() {
        StringBuilder listTodo = new StringBuilder();
        for (Task task : todoList) {
            String toAppend =
                    (task.getTodoName() + " Due on: " + task.getDeadline() + " Completion status: " + task.getStatus());
            listTodo.append(toAppend).append("\n");
        }
        return listTodo.toString();
    }

    //EFFECTS: return number of to-dos in list
    public int sizeOfTodoList() {
        return todoList.size();
    }

    //REQUIRES: task needs to be given as a parameter
    //EFFECTS: if given task name is present in the list, it would return true, else return false.
    public boolean contain(Task task)  {
        return todoList.contains(task);
    }

    //EFFECTS: returns an unmodifiable list of tasks in this to-do list
    public List<Task> getAllTasks() {
        return Collections.unmodifiableList(todoList);
    }


    //EFFECTS: returns the fields in task as a JSON array
    //REFERENCES: JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("todoList", todoListToJson());
        return json;
    }

    //EFFECTS: returns tasks in this to-do list as a JSON array
    //REFERENCES: JsonSerializationDemo
    public JSONArray todoListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Task t : todoList) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }

    //EFFECTS: return the task at index i
    public Task getTask(int i) {
        return todoList.get(i);
    }

    //EFFECTS: print log in console
    public void printLog() {
        for (Event event: EventLog.getInstance()) {
            System.out.println(event.toString());
        }
    }
}
