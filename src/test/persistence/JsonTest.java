package persistence;

import model.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTask(String todoName, String deadline, Boolean status, Task task) {
        assertEquals(todoName, task.getTodoName());
        assertEquals(deadline, task.getDeadline());
        assertEquals(status, task.getStatus());
    }
}
