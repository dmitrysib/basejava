package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.ResumeTestData;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class AbstractStorageTest {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_1 = ResumeTestData.generateResume(UUID_1, UUID_1);
    private static final Resume RESUME_2 = ResumeTestData.generateResume(UUID_2, UUID_2);
    private static final Resume RESUME_3 = ResumeTestData.generateResume(UUID_3, UUID_3);
    private static final Resume RESUME_4 = ResumeTestData.generateResume(UUID_4, UUID_4);
    protected final Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() {
        storage.clear();
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        storage.save(RESUME_1);
    }

    @Test
    void size() {
        assertEquals(3, storage.size());
    }

    @Test
    void get() {
        //Resume resume = ResumeTestData.generateResume(UUID_1, UUID_1);
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test
    void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_4));
    }

    @Test
    void update() {
        Resume resume = ResumeTestData.generateResume(UUID_1, UUID_1);
        storage.update(resume);
        assertEquals(resume, storage.get(UUID_1));
    }

    @Test
    void updateNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.update(RESUME_4));
    }

    @Test
    void delete() {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1));
    }

    @Test
    void deleteNoExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_4));
    }

    @Test
    void save() {
        storage.save(RESUME_4);
        assertEquals(4, storage.size());
        assertEquals(RESUME_4, storage.get(UUID_4));
    }

    @Test
    void saveExist() {
        assertThrows(ExistStorageException.class, () -> storage.save(RESUME_1));
    }

    @Test
    void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    void getAllSorted() {
        List<Resume> expected = new ArrayList<>(Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
        List<Resume> result = storage.getAllSorted();
        assertEquals(expected, result);
    }
}