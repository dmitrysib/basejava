package ru.javawebinar.basejava.storage;

import java.util.HashMap;

public class MapStorageFullNameTest extends AbstractStorageTest {

    MapStorageFullNameTest() {
        super(new MapStorageFullName(new HashMap<>()));
    }
}
