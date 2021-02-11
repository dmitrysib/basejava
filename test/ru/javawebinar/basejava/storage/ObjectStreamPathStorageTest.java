package ru.javawebinar.basejava.storage;

import static ru.javawebinar.basejava.TestData.*;

class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new ObjectStreamPathStorage(STORAGE_DIR));
    }
}