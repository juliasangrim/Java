package ru.nsu.fit.jul.calc.commands;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.jul.calc.CalcContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PopTests {
    @Test
    public void testPop() throws CommandException {
        CalcContext context = new CalcContext();
        Pop pop = new Pop();
        context.getStack().push(2.0);
        context.getStack().push(3.0);
        pop.execute(null, context);
        assertEquals(2.0, context.getStack().pop());
    }
    @Test
    public void testEmptyPop() {
        CalcContext context = new CalcContext();
        Pop pop = new Pop();
        assertThrows(CommandException.class, ()->pop.execute(null, context));
    }
}
