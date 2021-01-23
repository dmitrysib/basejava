package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Object getKey(String key) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(key))
                return i;
        }
        return -1;
    }

    @Override
    protected void saveElement(int index, Resume resume) {
        storage[size] = resume;
    }

    @Override
    protected void deleteElement(int index) {
        storage[index] = storage[size - 1];
    }
}
