package ru.nsu.fit.jul.calc.commands;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.jul.calc.CalcContext;

import static org.junit.jupiter.api.Assertions.*;

public class AddTests {
    @Test
    public void testAdd() throws CommandException {
        CalcContext context = new CalcContext();
        Add add = new Add();
        context.getStack().push(1.5);
        context.getStack().push(2.5);
        add.execute(null, context);
        assertEquals(4.0, context.getStack().pop());
    }
    @Test
    public void testEmptyAdd() {
        CalcContext context = new CalcContext();
        Add add = new Add();
        assertThrows(CommandException.class, () -> add.execute(null, context));
    }
}
