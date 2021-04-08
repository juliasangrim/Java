package ru.nsu.fit.jul.calc.commands;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.jul.calc.CalcContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class PrintTests {
    @Test
    public void testPrint() throws CommandException {
        CalcContext context = new CalcContext();
        Print print = new Print();
        context.getStack().push(5.0);
        print.execute(null, context);
        assertEquals(5.0, context.getStack().pop());
    }

    @Test
    public void testPrintEmpty() throws CommandException {
        CalcContext context = new CalcContext();
        Print print = new Print();
        assertThrows(CommandException.class, ()->print.execute(null, context));
    }

}
