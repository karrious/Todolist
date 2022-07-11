package persistence;

import model.Task;
import model.TodoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    //REFERENCES: JsonSerializationDemo
    @Test
    void testWriterInvalidFile() {
        try {
            TodoList tdl = new TodoList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    //REFERENCES: JsonSerializationDemo
    @Test
    void testWriterEmptyWorkroom() {
        try {
            TodoList tdl = new TodoList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTodoList.json");
            writer.open();
            writer.write(tdl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTodoList.json");
            tdl = reader.read();
            assertEquals(0, tdl.sizeOfTodoList());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    //REFERENCES: JsonSerializationDemo
    @Test
    void testWriterGeneralTodoList() {
        try {
            TodoList tdl = new TodoList();
            tdl.addTask(new Task("CPSC210 Midterm", "2021-Oct-27", false));
            tdl.addTask(new Task("CPSC210 Lab", "2021-Oct-22", false));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTodoList.json");
            writer.open();
            writer.write(tdl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTodoList.json");
            tdl = reader.read();
            List<Task> todoList = tdl.getAllTasks();
            assertEquals(2, todoList.size());
            checkTask("CPSC210 Midterm", "2021-Oct-27", false, todoList.get(0));
            checkTask("CPSC210 Lab", "2021-Oct-22", false, todoList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    }
