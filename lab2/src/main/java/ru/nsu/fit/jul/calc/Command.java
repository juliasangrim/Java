package ru.nsu.fit.jul.calc;

import ru.nsu.fit.jul.calc.commands.CommandException;

public interface Command {
    void execute(String[] args, CalcContext context) throws CommandException;
}
