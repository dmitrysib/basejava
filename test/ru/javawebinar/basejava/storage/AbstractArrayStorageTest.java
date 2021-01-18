package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_3));
        storage.save(new Resume(UUID_2));
    }

    @Test
    void size() {
        assertEquals(3, storage.size());
    }

    @Test
    void get() {
        Resume resume = new Resume(UUID_1);
        assertEquals(storage.get(UUID_1), resume);
    }

    @Test
    void getNotExist() {
        Exception exception = assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
        assertTrue(exception.getMessage().contains("Resume dummy not exist"));
    }

    @Test
    void update() {
        Exception exception = assertThrows(NotExistStorageException.class, () -> storage.update(new Resume("uuid4")));
        assertTrue(exception.getMessage().contains("Resume uuid4 not exist"));
    }

    @Test
    void delete() {
        storage.delete(UUID_1);
        Exception exception = assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1));
        assertTrue(exception.getMessage().contains("Resume " + UUID_1 + " not exist"));
    }

    @Test
    void save() {
        Exception exception = assertThrows(ExistStorageException.class, () -> storage.save(new Resume(UUID_1)));
        assertTrue(exception.getMessage().contains("Resume " + UUID_1 + " already exist"));
    }

    @Test
    void clear() {
        storage.clear();
        assertEquals(0, storage.getAll().length);
    }

    @Test
    void getAll() {
        assertEquals(3, storage.getAll().length);
    }

    @Test
    void storageOverflowTest() {
        storage.clear();
        int i = 0;
        try {
            for (; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            fail("Overflow before time!");
        }

        Exception exception = assertThrows(StorageException.class, () -> storage.save(new Resume()));
        assertTrue(exception.getMessage().contains("Storage overflow"));
    }
}