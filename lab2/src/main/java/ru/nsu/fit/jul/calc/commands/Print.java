package ru.nsu.fit.jul.calc.commands;

import ru.nsu.fit.jul.calc.CalcContext;
import ru.nsu.fit.jul.calc.Command;

import java.util.Deque;

public class Print implements Command {

    @Override
    public void execute(String[] args, CalcContext context) throws CommandException {
        Deque<Double> stack = context.getStack();
        if (stack.size() < 1) {
            throw new CommandException("Stack is empty.");
        }
        System.out.println(stack.peekLast());
    }
}
