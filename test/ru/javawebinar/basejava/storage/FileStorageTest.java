package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializer.ObjectStreamSerializer;

import static ru.javawebinar.basejava.TestData.*;

public class FileStorageTest extends AbstractStorageTest {

    FileStorageTest() {
        super(
                new FileStorage(STORAGE_DIR, new ObjectStreamSerializer())
        );
    }
}
