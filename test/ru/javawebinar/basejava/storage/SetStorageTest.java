package ru.javawebinar.basejava.storage;

import java.util.HashSet;

public class SetStorageTest extends AbstractStorageTest {

    SetStorageTest() {
        super(new SetStorage(new HashSet<>()));
    }
}
