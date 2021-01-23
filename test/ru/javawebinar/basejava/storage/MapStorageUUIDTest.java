package ru.javawebinar.basejava.storage;

import java.util.HashMap;

public class MapStorageUUIDTest extends AbstractStorageTest {

    MapStorageUUIDTest() {
        super(new MapStorageUUID(new HashMap<>()));
    }
}
