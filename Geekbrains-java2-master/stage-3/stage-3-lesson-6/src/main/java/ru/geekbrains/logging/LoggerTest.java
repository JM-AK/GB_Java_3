package ru.geekbrains.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerTest {

    private static final Logger logger = LogManager.getLogger("ru.geekbrains.logging.LoggerTest");

    public static void main(String[] args) {
        // off
        logger.fatal("Fatal message");
        logger.error("Error message");
        logger.warn("Warn message");
        logger.info("Info message");
        logger.debug("Debug message");
        logger.trace("Trace message");
        // all
    }

}
