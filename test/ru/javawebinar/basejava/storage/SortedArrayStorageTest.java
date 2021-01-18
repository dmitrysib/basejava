package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {
    private SortedArrayStorage storage;

    SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @BeforeEach
    void prepareArray() {
        storage = null;
        try {
            Field field = getClass().getSuperclass().getDeclaredField("storage");
            field.setAccessible(true);
            storage = (SortedArrayStorage) field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            //
        }
    }

    /*
    И тут я внезапно прочитал "тестировать правильность сортировки не надо"
    но раз уже сделано. Ну и было интересно
     */
    @Test
    void binarySortedTree() {
        Resume[] array = storage.getAll();
        for (int i = 0; i < array.length - 1; i++) {
            if(array[i] == null || array[i].compareTo(array[i + 1]) >= 0 ) {
                fail("No valid binary tree");
            }
        }
    }

    @Test
    void getIndex() {
        assertTrue(storage.getIndex("uuid2") == 1 && storage.getIndex("uuid4") == -4);
    }

    @Test
    void saveElement() {
        storage.save(new Resume("uuid0"));
        assertEquals(0, storage.getIndex("uuid0"));
    }

    @Test
    void deleteElement() {
        storage.save(new Resume("uuid0"));
        storage.delete("uuid0");
        assertEquals(1, storage.getIndex("uuid2"));
    }
}
