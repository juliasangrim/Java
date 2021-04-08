package ru.nsu.fit.jul.calc.commands;

import ru.nsu.fit.jul.calc.CalcContext;
import ru.nsu.fit.jul.calc.Command;

import java.util.Deque;
import java.util.Map;

public class Define implements Command {
    public void execute(String[] args, CalcContext context) throws CommandException {
        if (args.length == 1) {
            throw new CommandException("No arguments\n");
        }
        if (args.length == 2) {
            throw new CommandException("No second argument\n");
        }
        if (!isItNumeric(args[1]) && isItNumeric(args[2])) {
            Map<String, Double> param = context.getParams();
            Double value = Double.valueOf(args[2]);
            param.put(args[1], value);
        } else {
            throw new CommandException("Wrong arguments.\n");
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
