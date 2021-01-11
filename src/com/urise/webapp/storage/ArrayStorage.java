package com.urise.webapp.storage;

import java.util.Arrays;
import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10_000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int pos = position(r.getUuid());
        if (pos > -1 ) {
            storage[pos] = r;
        } else {
            System.out.println("Update ERROR: Resume not found");
        }
    }

    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("Save ERROR: storage is full");
        } else if (position(r.getUuid()) > -1 ) {
            System.out.println("Save ERROR: Resume exists in database");
        }
        else {
            storage[size++] = r;
        }
    }

    public Resume get(String uuid) {
        int pos = position(uuid);
        if (pos > -1 ) {
            return storage[pos];
        }
        System.out.println("Get ERROR: Resume not found");
        return null;
    }

    public void delete(String uuid) {
        int pos = position(uuid);
        if (pos > -1 ) {
            storage[pos] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Delete ERROR: Resume not found");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int position(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
