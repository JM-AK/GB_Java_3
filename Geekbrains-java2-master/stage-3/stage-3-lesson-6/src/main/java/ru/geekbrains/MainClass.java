package ru.geekbrains;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.*;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import ru.geekbrains2.Test2Class;

import java.io.IOException;

public class MainClass {
    static {
        //configureLogger();
        //configureLoggerPro();
    }

    public static void main(String[] args) {
        Test1Class test1 = new Test1Class();
        Test2Class test2 = new Test2Class();
        test1.testLog();
        test2.testLog();
    }

    private static void configureLogger() {
        ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();

        builder.addProperty("basePath", "logs");

        /*Создание layout'а для ведения логов*/
        LayoutComponentBuilder standardLayout = builder.newLayout("PatternLayout");
        standardLayout.addAttribute("pattern", "%-5p %d{yyyy-MM-dd HH:mm:ss} [%t] %C (%F:%L) - TT %m%n");

        /*Создание аппендера в консоль*/
        AppenderComponentBuilder consoleAppender = builder.newAppender("stdout", "Console");
        consoleAppender.addAttribute("target", "SYSTEM_OUT");
        consoleAppender.add(standardLayout);
        builder.add(consoleAppender);


        /*Создание аппендера в файл*/
        AppenderComponentBuilder fileAppender = builder.newAppender("file", "File");
        fileAppender.addAttribute("fileName", "${basePath}/logfile.log");
        fileAppender.add(standardLayout);
        builder.add(fileAppender);


        RootLoggerComponentBuilder rootLogger = builder.newRootLogger(Level.INFO);
        rootLogger.add(builder.newAppenderRef("stdout"));
        rootLogger.add(builder.newAppenderRef("file"));
        builder.add(rootLogger);

        LoggerComponentBuilder mainLoggerComponent = builder.newLogger("ru.geekbrains", Level.DEBUG);
        builder.add(mainLoggerComponent);
        Configurator.initialize(builder.build());
        try {
            builder.writeXmlConfiguration(System.out);
            System.out.println("===================================");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void configureLoggerPro() {
        ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();

        builder.addProperty("basePath", "logs");

        /*Создание layout'а для ведения логов*/
        LayoutComponentBuilder standardLayout = builder.newLayout("PatternLayout");
        standardLayout.addAttribute("pattern", "%d{yyyy-MM-dd HH:mm:ss,SSS} %-5level ${PID:- } --- [%t] %-40.40logger{39} : %m%n%throwable");


        /*Создание аппендера в консоль*/
        AppenderComponentBuilder consoleAppender = builder.newAppender("stdout", "Console");
        consoleAppender.addAttribute("target", "SYSTEM_OUT");
        consoleAppender.add(standardLayout);
        builder.add(consoleAppender);


        /*Создание аппендера в файл*/
        AppenderComponentBuilder fileAppender = builder.newAppender("file", "File");
        fileAppender.addAttribute("fileName", "${basePath}/log-file.log");
        fileAppender.add(standardLayout);
        builder.add(fileAppender);

        FilterComponentBuilder filter = builder.newFilter("FLOW", Filter.Result.ACCEPT, Filter.Result.DENY);

        ComponentBuilder sizeTriggeringPolicies = builder.newComponent("Policies")
                .addComponent(builder
                        .newComponent("SizeBasedTriggeringPolicy")
                        .addAttribute("size", "1M")
                );

        /*Создание аппендера в файл с ротированием*/
        AppenderComponentBuilder rollingFileAppender = builder.newAppender("rollingFile", "RollingFile");
        rollingFileAppender.addAttribute("fileName", "${basePath}/rolling.log");
        rollingFileAppender.addAttribute("filePattern", "${basePath}/rolling-%d{MM-dd-yy}.log.gz");
        rollingFileAppender.add(standardLayout);
        rollingFileAppender.addComponent(sizeTriggeringPolicies);
        builder.add(rollingFileAppender);

        RootLoggerComponentBuilder rootLogger = builder.newRootLogger(Level.INFO);
        rootLogger.addAttribute("additivity", "true");
        rootLogger.add(builder.newAppenderRef("stdout"));
        rootLogger.add(builder.newAppenderRef("file"));
        builder.add(rootLogger);

        LoggerComponentBuilder mainLoggerComponent = builder.newLogger("ru.geekbrains", Level.DEBUG);
        mainLoggerComponent.addAttribute("additivity", "false");
        mainLoggerComponent.add(builder.newAppenderRef("rollingFile"));
        builder.add(mainLoggerComponent);
        Configurator.initialize(builder.build());
        try {
            builder.writeXmlConfiguration(System.out);
            System.out.println("===================================");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
