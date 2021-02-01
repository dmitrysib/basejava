package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializer.XmlStreamSerializer;

public class PathStorageTest extends AbstractStorageTest {

    PathStorageTest() {
        super(
                new PathStorage(STORAGE_DIR, new XmlStreamSerializer())
        );
    }
}
