package ru.nsu.fit.jul.calc.commands;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.jul.calc.CalcContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DivideTests {
    @Test
    public void testDivide() throws CommandException {
        CalcContext context = new CalcContext();
        Divide divide = new Divide();
        context.getStack().push(4.0);
        context.getStack().push(2.0);
        divide.execute(null, context);
        assertEquals(0.5, context.getStack().pop());
    }

    @Test
    public void testDivideEmpty() {
        CalcContext context = new CalcContext();
        Divide divide = new Divide();
         assertThrows(CommandException.class, ()->divide.execute(null, context));
    }
}
