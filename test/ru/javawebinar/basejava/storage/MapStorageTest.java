package ru.javawebinar.basejava.storage;

import java.util.HashMap;

public class MapStorageTest extends AbstractStorageTest {

    MapStorageTest() {
        super(new MapStorage(new HashMap<>()));
    }
}
