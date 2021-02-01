package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializer.JsonStreamSerializer;

public class JsonFileStorageTest extends AbstractStorageTest {

    JsonFileStorageTest() {
        super(
                new FileStorage(STORAGE_DIR, new JsonStreamSerializer())
        );
    }
}
