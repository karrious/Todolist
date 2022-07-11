package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    Task testTask;

    @BeforeEach
    public void setup(){
        testTask = new Task("cs project", "2021-Oct-13", true);
    }

    @Test
    public void testTasksConstructor(){
        assertEquals("cs project", testTask.getTodoName());
        assertEquals("2021-Oct-13", testTask.getDeadline());
        assertTrue(testTask.getStatus());
    }

    @Test
    public void testGetTodoName() {
        assertEquals("cs project", testTask.getTodoName());
    }

    @Test
    public void testGetDeadline() {
        assertEquals("2021-Oct-13", testTask.getDeadline());
    }

    @Test
    public void testGetStatus() {
        assertTrue(testTask.getStatus());
    }

    @Test
    public void testSetStatus() {
        testTask.setStatus(false);
        assertFalse(testTask.getStatus());
    }
}
