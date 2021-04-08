package ru.nsu.fit.jul.calc.commands;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.jul.calc.CalcContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MultiplyTests {
    @Test
    public void multiplyTest() throws CommandException {
        CalcContext context = new CalcContext();
        Multiply mul = new Multiply();
        context.getStack().push(2.0);
        context.getStack().push(3.0);
        mul.execute(null, context);
        assertEquals(6.0, context.getStack().pop());
    }

    @Test
    public void multiplyEmptyTest() {
        CalcContext context = new CalcContext();
        Multiply mul = new Multiply();
        assertThrows(CommandException.class, ()->mul.execute(null, context));
    }
}
