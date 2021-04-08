package ru.nsu.fit.jul.calc.commands;

import ru.nsu.fit.jul.calc.CalcContext;
import ru.nsu.fit.jul.calc.Command;

import java.util.Deque;

public class Subtract implements Command {
    @Override
    public void execute(String[] args, CalcContext context) throws CommandException{
        Deque<Double> stack = context.getStack();
        if (stack.size() < 2) {
            throw new CommandException("Subtract command expects 2 values in stack.");
        }
        var arg1 = stack.pop();
        var arg2 = stack.pop();
        stack.add(arg1 - arg2);
    }
}
