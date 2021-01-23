package ru.javawebinar.basejava.storage;

import java.util.HashMap;

public class MapStorageResumeTest extends AbstractStorageTest {

    MapStorageResumeTest() {
        super(new MapStorageResume(new HashMap<>()));
    }
}
