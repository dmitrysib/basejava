package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Config;

public class SqlStorageTest extends AbstractStorageTest {

    SqlStorageTest() {
        super(new SqlStorage(Config.getInstance().getDbUrl(), Config.getInstance().getDbUsername(), Config.getInstance().getDbPassword()));
    }
}
