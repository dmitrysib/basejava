package ru.javawebinar.basejava;

import ru.javawebinar.basejava.storage.SqlStorage;
import ru.javawebinar.basejava.storage.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final File PROPS = new File("config/resumes.properties");
    private static final Config INSTANCE = new Config();

    private final String storageDirectory;
    private final Storage sqlStorage;

    private Config() {
        try (var is = new FileInputStream(PROPS)) {
            Properties properties = new Properties();
            properties.load(is);

            storageDirectory = properties.getProperty("storage.dir");
            sqlStorage = new SqlStorage(properties.getProperty("db.url"), properties.getProperty("db.username"), properties.getProperty("db.password"));
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

    public Storage getSqlStorage() {
        return sqlStorage;
    }
}
