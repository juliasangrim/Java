package ru.nsu.fit.jul.calc.commands;

import ru.nsu.fit.jul.calc.CalcContext;
import ru.nsu.fit.jul.calc.Command;

import java.util.Deque;

public class Sqrt implements Command {
    @Override
    public void execute(String[] args, CalcContext context) throws CommandException{
        Deque<Double> stack = context.getStack();
        if (stack.size() < 1) {
            throw new CommandException("Empty stack.");
        }
        var arg1 = stack.pop();
        arg1 = Math.sqrt(arg1);
        stack.add(arg1);
    }
}
