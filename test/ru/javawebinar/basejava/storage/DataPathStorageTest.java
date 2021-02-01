package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializer.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest {

    DataPathStorageTest() {
        super(
                new PathStorage(STORAGE_DIR, new DataStreamSerializer())
        );
    }
}