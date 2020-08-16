package ru.geekbrains2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.lookup.StrSubstitutor;
import ru.geekbrains.Test1Class;

import java.util.HashMap;

public class Test2Class {

    private final Logger logger = LogManager.getLogger(Test2Class.class);

    public void testLog() {

        HashMap<String, String> map = new HashMap<>();
        map.put("animal", "some animal value");
        map.put("target", "lazy target");
        StrSubstitutor sub = new StrSubstitutor(map);
        logger.info(sub.replace("The ${animal} jumped over the ${target}"));

        map.put("target2", "lazy2 target");
        logger.info(sub.replace("The ${animal} jumped over the ${target} ${target2}"));


        try {
            throw new RuntimeException("Something goes wrong...");
        } catch (Exception e) {
            logger.error("Ошибка: {}", e.getMessage(), e);
        }

        logger.fatal(this.getClass().getName() + " Fatal message");
        logger.error(this.getClass().getName() + " Error message");
        logger.warn(this.getClass().getName() + " Warn message");
        logger.info(this.getClass().getName() + " Info message");
        logger.debug(this.getClass().getName() + " Debug message");
        logger.trace(this.getClass().getName() + " Trace message");
    }
}
