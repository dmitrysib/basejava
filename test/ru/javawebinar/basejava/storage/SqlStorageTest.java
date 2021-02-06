package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Config;

public class SqlStorageTest extends AbstractStorageTest {

    SqlStorageTest() {
        super(Config.getInstance().getSqlStorage());
    }
}
