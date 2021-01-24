package ru.javawebinar.basejava.storage;

import java.util.HashMap;

public class MapStorageUuidTest extends AbstractStorageTest {

    MapStorageUuidTest() {
        super(new MapStorageUuid(new HashMap<>()));
    }
}
