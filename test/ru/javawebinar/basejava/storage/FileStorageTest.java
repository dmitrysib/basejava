package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serialization.ObjectStreamSerialization;

public class FileStorageTest extends AbstractStorageTest {

    FileStorageTest() {
        super(
                new FileStorage("storage", new ObjectStreamSerialization())
        );
    }
}
