package persistence;

import model.Task;
import model.TodoList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//Represents a reader that reads To-do List from JSON data stored in file
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    //REFERENCES: JsonSerializationDemo
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads to-do list from file and returns it;
    //throws IOException if an error occurs reading data from file
    //REFERENCES: JsonSerializationDemo
    public TodoList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTodoList(jsonObject);
    }

    //EFFECTS: reads source file as string and returns it
    //REFERENCES: JsonSerializationDemo
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses to-do list from JSON object and returns it
    //REFERENCES: JsonSerializationDemo
    private TodoList parseTodoList(JSONObject jsonObject) {
        TodoList tdl = new TodoList();
        addTodoList(tdl, jsonObject);
        return tdl;
    }

    // MODIFIES: tdl
    // EFFECTS: parses to-do list from JSON object and adds them to to-do list
    // REFERENCES: JsonSerializationDemo
    private void addTodoList(TodoList tdl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("todoList");
        for (Object json : jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            addTask(tdl, nextTask);
        }
    }

    // MODIFIES: tdl
    // EFFECTS: parses task from JSON object and adds it to to-do list
    // REFERENCES: JsonSerializationDemo
    private void addTask(TodoList tdl, JSONObject jsonObject) {
        String todoName = jsonObject.getString("name");
        String deadline = jsonObject.getString("deadline");
        Boolean status = jsonObject.getBoolean("status");
        Task task = new Task(todoName, deadline, status);
        tdl.addTask(task);
    }

}
