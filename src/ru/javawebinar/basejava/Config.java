package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final String CONFIG_FILE = "config/resumes.properties";
    private static final File PROPS = new File(CONFIG_FILE);
    private static final Config INSTANCE = new Config();

    private final String storageDirectory;

    private final String dbUrl;
    private final String dbUsername;
    private final String dbPassword;

    private Config() {
        try (var is = new FileInputStream(PROPS)) {
            Properties properties = new Properties();
            properties.load(is);

            storageDirectory = properties.getProperty("storage.dir");

            dbUrl = properties.getProperty("db.url");
            dbUsername = properties.getProperty("db.username");
            dbPassword = properties.getProperty("db.password");

            System.out.println(dbUrl + ", " + dbUsername + ", " + dbPassword);

        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }

    public static Config getInstance() {
        return INSTANCE;
    }

    public String getStorageDirectory() {
        return storageDirectory;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }
}
