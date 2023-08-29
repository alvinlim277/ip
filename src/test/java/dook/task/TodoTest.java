package dook.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {

    @Test
    public void getSaveableString_normalInput_formattedCorrectly() {
        Todo todo = new Todo ("ABC", false);
        String result = todo.getSaveableString();
        assertEquals(result, "T// //ABC");
    }

    @Test
    public void toString_normalInput_formattedCorrectly() {
        Todo todo = new Todo ("DEF", true);
        String result = todo.toString();
        assertEquals(result, "[T][X] DEF");
    }
}
