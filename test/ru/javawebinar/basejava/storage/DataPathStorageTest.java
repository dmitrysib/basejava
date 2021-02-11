package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializer.DataStreamSerializer;

import static ru.javawebinar.basejava.TestData.*;

public class DataPathStorageTest extends AbstractStorageTest {

    DataPathStorageTest() {
        super(
                new PathStorage(STORAGE_DIR, new DataStreamSerializer())
        );
    }
}