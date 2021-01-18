package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStorageTest extends AbstractArrayStorageTest {
    private ArrayStorage storage;

    ArrayStorageTest() {
        super(new ArrayStorage());
    }

    @BeforeEach
    void prepareArray() {
        storage = null;
        try {
            Field field = getClass().getSuperclass().getDeclaredField("storage");
            field.setAccessible(true);
            storage = (ArrayStorage) field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            //
        }
    }

    @Test
    void getIndex() {
        assertTrue(storage.getIndex("uuid3") == 1 && storage.getIndex("uuid0") < 0);
    }

    @Test
    void saveElement() {
        storage.save(new Resume("uuid0"));
        assertEquals(3, storage.getIndex("uuid0"));
    }

    @Test
    void deleteElement() {
        storage.delete("uuid1");
        assertEquals(0, storage.getIndex("uuid2"));
    }
}
