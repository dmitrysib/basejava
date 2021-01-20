package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.AbstractArrayStorage;
import ru.javawebinar.basejava.storage.ArrayStorage;
import ru.javawebinar.basejava.storage.ListStorage;
import ru.javawebinar.basejava.storage.SortedArrayStorage;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();
    static final SortedArrayStorage SORTED_ARRAY_STORAGE = new SortedArrayStorage();

    static final ListStorage LIST_STORAGE = new ListStorage(new ArrayList<>());

    public static void main(String[] args) {

        //Test SortedArrayStorage
        Resume r10 = new Resume("uuid10");
        Resume r11 = new Resume("uuid11");
        Resume r12 = new Resume("uuid12");
        Resume r13 = new Resume("uuid13");
        Resume r14 = new Resume("uuid14");

        LIST_STORAGE.save(r10);
        LIST_STORAGE.save(r11);
        LIST_STORAGE.save(r12);
        LIST_STORAGE.save(r13);
        LIST_STORAGE.save(r14);
        printAll(LIST_STORAGE);

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

        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid3");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        ARRAY_STORAGE.update(r2);
        Resume r4 = new Resume("uuid4");
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

    static void printAll(ListStorage array) {
        System.out.println("\nGet All");
        for (Resume r : array.getAll()) {
            System.out.println(r);
        }
    }
}
