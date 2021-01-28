package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.storage.AbstractStorage;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerUtil {
    public static final String LOG_FILE = "basejava.log";

    public static Logger initLogger() {
        Logger logger = Logger.getLogger(AbstractStorage.class.getName());
        try {
            var fh = new FileHandler(LOG_FILE);
            logger.addHandler(fh);
            fh.setFormatter(new  SimpleFormatter());
            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return logger;
    }
}
