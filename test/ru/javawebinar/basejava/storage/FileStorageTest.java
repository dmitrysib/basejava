package ru.javawebinar.basejava.storage;

class FileStorageTest extends AbstractFileStorageTest {

    FileStorageTest() {
        super(new FileStorage("storage"));
    }
}