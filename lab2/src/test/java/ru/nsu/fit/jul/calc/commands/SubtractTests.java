package ru.nsu.fit.jul.calc.commands;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.jul.calc.CalcContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SubtractTests {
    @Test
    public void testSubtract() throws CommandException {
        CalcContext context = new CalcContext();
        Subtract sub = new Subtract();
        context.getStack().push(1.5);
        context.getStack().push(2.5);
        sub.execute(null, context);
        assertEquals(1.0, context.getStack().pop());
    }
    @Test
    public void testEmptySubtract() {
        CalcContext context = new CalcContext();
        Subtract sub = new Subtract();
        assertThrows(CommandException.class, () -> sub.execute(null, context));
    }
}
