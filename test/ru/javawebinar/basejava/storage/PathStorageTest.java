package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serialization.ObjectStreamSerialization;

public class PathStorageTest extends AbstractStorageTest {

    PathStorageTest() {
        super(
                new PathStorage(STORAGE_DIR, new ObjectStreamSerialization())
        );
    }
}
