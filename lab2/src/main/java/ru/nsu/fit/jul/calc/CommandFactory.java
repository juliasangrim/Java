package ru.nsu.fit.jul.calc;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CommandFactory {

    public static class Helper {
        private static final CommandFactory factory = new CommandFactory();
    }

    private final Map<String, Command> commands = new HashMap<>();

    public static CommandFactory getInstance() {
        return Helper.factory;
    }
    private CommandFactory() {
        try (var in = Main.class.getResourceAsStream("commands.properties")) {
            Properties props = new Properties();
            props.load(in);
            for (var nameCommand : props.stringPropertyNames()) {
                String className = props.getProperty(nameCommand);
                var command = (Command) Class.forName(className).newInstance();
                commands.put(nameCommand, command);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Command findCommandByName(String name) {
        return commands.get(name);
    }
}
