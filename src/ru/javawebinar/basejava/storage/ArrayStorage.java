package ru.javawebinar.basejava.storage;

import java.util.Arrays;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid))
                return i;
        }
        return -1;
    }

    @Override
    public void save(Resume resume) {
        if (getIndex(resume.getUuid()) > -1) {
            System.out.println("Save ERROR: Resume  with UUID " + resume.getUuid() + " exists in database");
        } else if (size == storage.length) {
            System.out.println("Save ERROR: storage is full");
        } else {
            storage[size++] = resume;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Delete ERROR: Resume with UUID " + uuid + " not found");
        }
    }
}
