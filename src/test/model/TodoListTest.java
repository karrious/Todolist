package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoListTest {
    TodoList todoList;
    Task cpscMidterm;
    Task cpscLab;
    Task cpscLecTicket;
    Task sleep;

    @BeforeEach
    public void setup(){
        todoList = new TodoList();
        cpscMidterm = new Task("CPSC210 Midterm", "2021-Oct-27", false);
        cpscLab = new Task("CPSC210 Lab", "2021-Oct-22", false);
        cpscLecTicket = new Task ("CPSC210 PreLecture Ticket", "2021-Oct-11", true);
        sleep = new Task ("SLEEP", "2021-Oct-15", false);
    }

    @Test
    public void testAddTask(){
        assertEquals(0, todoList.sizeOfTodoList());
        todoList.addTask(cpscMidterm);
        assertEquals(1, todoList.sizeOfTodoList());
    }

    @Test
    public void testRemoveTask() {
        todoList.addTask(cpscMidterm);
        todoList.addTask(sleep);
        todoList.addTask(cpscLecTicket);
        todoList.addTask(cpscLab);

        assertEquals(4, todoList.sizeOfTodoList());
        todoList.removeTask("CPSC210 Lab");
        assertEquals(3, todoList.sizeOfTodoList());
        assertFalse(todoList.removeTask("CPSC210"));
        todoList.removeTask("CPSC210 Lab");
        assertEquals(3, todoList.sizeOfTodoList());
        todoList.contain(cpscLecTicket);
        todoList.contain(cpscMidterm);
        todoList.contain(sleep);
        todoList.removeTask("CPSC210 PreLecture Ticket");
        assertEquals(2, todoList.sizeOfTodoList());
        todoList.removeTask("CPSC210 Midterm");
        todoList.removeTask("SLEEP");
        assertEquals(0, todoList.sizeOfTodoList());
    }


    @Test
    public void testCompleteTask(){
        todoList.addTask(cpscMidterm);
        todoList.addTask(sleep);
        todoList.addTask(cpscLecTicket);
        todoList.addTask(cpscLab);
        todoList.completeTask("CPSC210 Midterm");

        assertTrue(cpscMidterm.getStatus());
        assertTrue(todoList.completeTask("CPSC210 Midterm"));
        todoList.completeTask("CPSC210 PreLecture Ticket");
        assertTrue(cpscLecTicket.getStatus());
        assertTrue(todoList.completeTask("CPSC210 PreLecture Ticket"));
        assertFalse(sleep.getStatus());
        todoList.completeTask("CPSC210 Lab");
        assertTrue(cpscLab.getStatus());
        assertFalse(todoList.completeTask("CPSC210"));
    }

    @Test
    public void testViewTodoList(){
        todoList.addTask(cpscLab);
        String shouldLookLike = "CPSC210 Lab Due on: 2021-Oct-22 Completion status: false\n";
        assertEquals(shouldLookLike, todoList.viewTodoList());

        todoList.addTask(cpscLecTicket);
        String shouldLookLike2 = "CPSC210 Lab Due on: 2021-Oct-22 Completion status: false\n" +
                "CPSC210 PreLecture Ticket Due on: 2021-Oct-11 Completion status: true\n";
        assertEquals(shouldLookLike2, todoList.viewTodoList());
    }


    @Test
    public void testSizeOfTodoList(){
        assertEquals(0, todoList.sizeOfTodoList());
        todoList.addTask(cpscLecTicket);
        todoList.addTask(cpscLab);
        assertEquals(2, todoList.sizeOfTodoList());
    }


    @Test
    public void testContain() {
        todoList.addTask(cpscMidterm);
        todoList.addTask(sleep);
        assertTrue(todoList.contain(cpscMidterm));
        assertTrue(todoList.contain(sleep));
        assertFalse(todoList.contain(cpscLecTicket));
    }

    @Test
    public void testGetTask() {
        todoList.addTask(cpscMidterm);
        todoList.addTask(sleep);
        assertEquals("CPSC210 Midterm", todoList.getTask(0));

    }

}