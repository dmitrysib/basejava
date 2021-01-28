package ru.javawebinar.basejava.storage;

import static org.junit.jupiter.api.Assertions.*;

class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new ObjectStreamPathStorage(STORAGE_DIR));
    }
}