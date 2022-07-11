package persistence;

import model.Task;
import model.TodoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

//REFERENCES: JsonSerializationDemo
public class JsonReaderTest extends JsonTest{

    //REFERENCES: JsonSerializationDemo
    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TodoList tdl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    //REFERENCES: JsonSerializationDemo
    @Test
    public void testReaderEmptyTodoList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTodolist.json");
        try {
            TodoList tdl = reader.read();
            assertEquals(0, tdl.sizeOfTodoList());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    //REFERENCES: JsonSerializationDemo
    @Test
    public void testReaderGeneralTodoList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTodoList.json");
        try {
            TodoList tdl = reader.read();
            List<Task> todoList = tdl.getAllTasks();
            assertEquals(2, todoList.size());
            checkTask("CPSC210 Midterm", "2021-Oct-27", false, todoList.get(0));
            checkTask("CPSC210 Lab", "2021-Oct-22", false, todoList.get(1));
        } catch (IOException e) {
           fail("Couldn't read from file");
        }
    }


}
