package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    void storageOverflowTest() {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("Resume Test" + i));
            }
        } catch (StorageException e) {
            fail("Overflow before time!");
        }

        assertThrows(StorageException.class, () -> storage.save(new Resume("Additional Resume")));
    }
}
