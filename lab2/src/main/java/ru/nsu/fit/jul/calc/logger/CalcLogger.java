package ru.nsu.fit.jul.calc.logger;

import java.io.IOException;
import java.util.logging.*;

public class CalcLogger {

    static public void setup() throws IOException {
        // get the global logger to configure it
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        // suppress the logging output to the console
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }

        logger.setLevel(Level.CONFIG);
        FileHandler fileTxt = new FileHandler("Logging.txt");
        // create a TXT formatter
        SimpleFormatter formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);
    }
}
