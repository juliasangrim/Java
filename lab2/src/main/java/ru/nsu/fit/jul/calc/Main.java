package ru.nsu.fit.jul.calc;

import ru.nsu.fit.jul.calc.logger.CalcLogger;

import java.util.logging.*;


import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        CalcLogger.setup();
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.info("Start the program.");
        Reader input = new InputStreamReader(System.in);
        if (args.length != 0) {
            try {
                input = new FileReader(args[0]);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            logger.info("Open the file " + args[0] + ".");
        } else {
            logger.info("Open the console.");
        }
        CalcContext context = new CalcContext();
        try (BufferedReader reader = new BufferedReader(input)) {
            CommandFactory factory = CommandFactory.getInstance();

            System.out.println("To exit the calculator enter EXIT!\n");
            System.out.println("Enter commands:\n");
            while (true) {
                String arg = reader.readLine();
                if (arg == null || arg.isEmpty()) {
                    break;
                }

                if (arg.startsWith("#")) {
                    continue;
                }
                if (arg.equals("EXIT")) {
                    break;
                }
                var cmdArgs = arg.split(" ");
                var command = factory.findCommandByName(cmdArgs[0]);
                if (command == null) {
                    System.out.println("No such command\n");
                    logger.info("The user input the wrong command.");
                    continue;
                }
                command.execute(cmdArgs, context);
                logger.info("The result of command " + cmdArgs[0] + " is received.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Caught the exception with message ", e);
        }
        logger.info("Program is ended.");
    }
}
