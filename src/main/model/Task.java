package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Date;

// Contain fields that represent a task with to-do name (String), deadline (String), and status (Boolean).
public class Task implements Writable {
    private String todoName;     // a string for the name of the task.
    private String deadline;     // the date that the task needs to be finished on.
    private Boolean status;      // true if to-do is finished, false if it is not yet finished or overdue.

    //REQUIRES: Task name length > 0
    //MODIFIES: this
    //EFFECTS: initializes a new task with given todoName, due date and status.
    public Task(String todoName, String deadline, Boolean status) {
        this.todoName = todoName;
        this.deadline = deadline;
        this.status = status;
    }

    //EFFECTS: return task's name
    public String getTodoName() {
        return todoName;
    }

    //EFFECTS: return task's deadline
    public String getDeadline() {
        return deadline;
    }

    //EFFECTS: return task's status
    public Boolean getStatus() {
        return status;
    }

    //MODIFIES: this
    //EFFECTS: sets task's status to given status
    public void setStatus(Boolean status) {
        this.status = status;
    }

    //EFFECTS: returns the fields in the task as a JSON array
    //REFERENCES: JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", todoName);
        json.put("deadline", deadline);
        json.put("status", status);
        return json;
    }
}
