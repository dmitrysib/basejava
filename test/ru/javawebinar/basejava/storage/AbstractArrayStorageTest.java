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
        assertEquals(resume, storage.get(UUID_1));
    }

    @Test
    void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }

    @Test
    void updateNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.update(new Resume("uuid4")));
    }

    @Test
    void delete() {
        storage.delete(UUID_1);
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1));
    }

    @Test
    void deleteNoExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get("uuid4"));
    }

    @Test
    void save() {
        Resume resume = new Resume("uuid4");
        storage.save(resume);
        assertEquals(resume, storage.get("uuid4"));
    }

    @Test
    void saveExist() {
        assertThrows(ExistStorageException.class, () -> storage.save(new Resume(UUID_1)));
    }

    @Test
    void clear() {
        storage.clear();
        assertEquals(0, storage.getAll().length);
    }

    @Test
    void getAll() {
        assertEquals(Resume[].class, storage.getAll().getClass());
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

        assertThrows(StorageException.class, () -> storage.save(new Resume()));
    }
}