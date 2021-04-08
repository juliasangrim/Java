package ru.nsu.fit.jul.calc.commands;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.jul.calc.CalcContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SqrtTests {
    @Test
    public void testSqrt() throws CommandException {
        CalcContext context = new CalcContext();
        Sqrt sqrt = new Sqrt();
        context.getStack().push(4.0);
        sqrt.execute(null, context);
        assertEquals(2.0, context.getStack().pop());
    }
    @Test
    public void testEmptySqrt() {
        CalcContext context = new CalcContext();
        Sqrt sqrt = new Sqrt();
        assertThrows(CommandException.class, () -> sqrt.execute(null, context));
    }
}
