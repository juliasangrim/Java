package ru.nsu.fit.jul.calc.commands;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.jul.calc.CalcContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DefineTests {
    @Test
    public void testDefine () throws CommandException {
           CalcContext context = new CalcContext();
           Define define = new Define();
           String[] args = new String[3];
           args[1] = "a";
           args[2] = "3";
           define.execute(args, context);
           Push push = new Push();
           push.execute(args, context);
           assertEquals(3.0, context.getStack().pop());
    }

    @Test
    public void testDefineEmptySecondArgument () {
        CalcContext context = new CalcContext();
        Define define = new Define();
        String[] args = new String[2];
        args[1] = "a";
        assertThrows(CommandException.class, () -> define.execute(args, context));
    }

    @Test
    public void testDefineBothSecondArgument () {
        CalcContext context = new CalcContext();
        Define define = new Define();
        String[] args = new String[1];
        assertThrows(CommandException.class, () -> define.execute(args, context));
    }

    @Test
    public void testDefineWrongFirstArgument () {
        CalcContext context = new CalcContext();
        Define define = new Define();
        String[] args = new String[3];
        args[1] = "1";
        args[2] = "2";
        assertThrows(CommandException.class, () -> define.execute(args, context));
    }

    @Test
    public void testDefineWrongSecondArgument () {
        CalcContext context = new CalcContext();
        Define define = new Define();
        String[] args = new String[3];
        args[1] = "a";
        args[2] = "a";
        assertThrows(CommandException.class, () -> define.execute(args, context));
    }

}
