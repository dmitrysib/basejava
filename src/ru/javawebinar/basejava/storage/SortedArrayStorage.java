package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            System.out.println("Save ERROR: Resume  with UUID " + resume.getUuid() + " exists in database");
        } else if (size == storage.length) {
            System.out.println("Save ERROR: storage is full");
        } else {
            size++;
            index = -index - 1;
            for (int i = size - 1; i >= 0; i--) {
                if (i > index)
                    storage[i] = storage[i - 1];
                else if (i == index)
                    storage[i] = resume;
                else
                    break;
            }
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Delete ERROR: Resume with UUID " + uuid + " not found");
        } else {
            for (int i = 0; i < size - 1; i++) {
                if (i >= index)
                    storage[i] = storage[i + 1];
            }
            storage[size - 1] = null;
            size--;
        }
    }
}
