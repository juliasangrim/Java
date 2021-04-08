package ru.nsu.fit.jul.calc.commands;

import ru.nsu.fit.jul.calc.CalcContext;
import ru.nsu.fit.jul.calc.Command;

import java.util.Deque;
import java.util.Map;

public class Push implements Command {
    @Override
    public void execute(String[] args, CalcContext context) throws CommandException {
        Deque<Double> stack = context.getStack();
        Map<String, Double> param = context.getParams();
        if (args.length == 1) {
            throw new CommandException("No argument.\n");
        }
        if (isItNumeric(args[1])) {
            stack.add(Double.valueOf(args[1]));
        } else {
            var value = param.get(args[1]);
            stack.add(value);
        }
    }
    public boolean isItNumeric (String s){
        try {
            Double.valueOf(s);

        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
