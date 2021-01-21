package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int eIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid))
                return i;
        }
        return -1;
    }

    @Override
    protected void aSave(int index, Resume resume) {
        storage[size] = resume;
    }

    @Override
    protected void aDelete(int index) {
        storage[index] = storage[size - 1];
    }
}
