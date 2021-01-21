package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract int getIndex(String uuid);

    protected abstract void saveResumeToArray(int index, Resume resume);

    protected abstract void deleteResumeFromArray(int index);

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected Resume getElement(int index, String uuid) {
        return storage[index];
    }

    @Override
    protected void updateElement(int index, Resume resume) {
        storage[index] = resume;
    }

    @Override
    protected void saveElement(int index, Resume resume) {
        saveResumeToArray(index, resume);
        size++;
    }

    @Override
    protected void deleteElement(int index, String uuid) {
        deleteResumeFromArray(index);
        storage[size - 1] = null;
        size--;
    }
}
