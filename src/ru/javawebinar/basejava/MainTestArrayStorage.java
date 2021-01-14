package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.AbstractArrayStorage;
import ru.javawebinar.basejava.storage.ArrayStorage;
import ru.javawebinar.basejava.storage.SortedArrayStorage;

/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();
    static final SortedArrayStorage SORTED_ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {

        //Test SortedArrayStorage
        Resume r10 = new Resume();
        r10.setUuid("uuid10");
        Resume r11 = new Resume();
        r11.setUuid("uuid11");
        Resume r12 = new Resume();
        r12.setUuid("uuid12");
        Resume r13 = new Resume();
        r13.setUuid("uuid13");
        Resume r14 = new Resume();
        r14.setUuid("uuid14");

        SORTED_ARRAY_STORAGE.save(r11);
        SORTED_ARRAY_STORAGE.save(r13);
        SORTED_ARRAY_STORAGE.save(r12);
        SORTED_ARRAY_STORAGE.save(r14);
        SORTED_ARRAY_STORAGE.save(r10);

        printAll(SORTED_ARRAY_STORAGE);

        SORTED_ARRAY_STORAGE.delete(r13.getUuid());

        printAll(SORTED_ARRAY_STORAGE);

        SORTED_ARRAY_STORAGE.delete(r11.getUuid());

        printAll(SORTED_ARRAY_STORAGE);

        System.out.println();

        //Test SortedArrayStorage

        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        Resume r3 = new Resume();
        r3.setUuid("uuid3");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        ARRAY_STORAGE.update(r2);
        Resume r4 = new Resume();
        r4.setUuid("uuid4");
        ARRAY_STORAGE.update(r4);

        printAll(ARRAY_STORAGE);
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll(ARRAY_STORAGE);
        ARRAY_STORAGE.clear();
        printAll(ARRAY_STORAGE);

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll(AbstractArrayStorage array) {
        System.out.println("\nGet All");
        for (Resume r : array.getAll()) {
            System.out.println(r);
        }
    }
}
