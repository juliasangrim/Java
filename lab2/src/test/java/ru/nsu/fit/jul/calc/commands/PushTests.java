package ru.nsu.fit.jul.calc.commands;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.jul.calc.CalcContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PushTests {
    @Test
    public void testPush() throws CommandException {
        CalcContext context = new CalcContext();
        Push push = new Push();
        String[] args = new String[2];
        args[1] = "0.12";
        push.execute(args, context);
        assertEquals(0.12, context.getStack().pop());
    }

    @Test
    public void testPushDefine() throws CommandException {
        CalcContext context = new CalcContext();
        Push push = new Push();
        context.getParams().put("a", 5.0);
        String[] args = new String[2];
        args[1] = "a";
        push.execute(args, context);
        assertEquals(5.0, context.getStack().pop());
    }

    @Test
    public void testPushEmpty() throws CommandException {
        CalcContext context = new CalcContext();
        Push push = new Push();
        String[] args = new String[1];
        assertThrows(CommandException.class, ()->push.execute(args, context));
    }
}
