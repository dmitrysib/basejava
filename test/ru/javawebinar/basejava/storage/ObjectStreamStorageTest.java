package ru.javawebinar.basejava.storage;

class ObjectStreamStorageTest extends AbstractFileStorageTest {

    ObjectStreamStorageTest() {
        super(new ObjectStreamStorage(FILE_STORAGE_DIR));
    }
}