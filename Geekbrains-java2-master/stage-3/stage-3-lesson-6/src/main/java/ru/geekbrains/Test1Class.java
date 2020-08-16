package ru.geekbrains;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public class Test1Class {

    private final Logger logger = LogManager.getLogger(Test1Class.class);

    public void testLog() {
        logger.fatal(this.getClass().getName() + " Fatal message");
        logger.error(this.getClass().getName() + " Error message");
        logger.warn(this.getClass().getName() + " Warn message");
        logger.info(this.getClass().getName() + " Info message");
        logger.debug(this.getClass().getName() + " Debug message");
        logger.trace(this.getClass().getName() + " Trace message");
    }
}
