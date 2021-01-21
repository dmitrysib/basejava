package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract void saveResume(int index, Resume resume);

    protected abstract void deleteResume(int index);

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
    protected Resume getElement(int index) {
        return storage[index];
    }

    @Override
    protected void updateElement(int index, Resume resume) {
        storage[index] = resume;
    }

    @Override
    protected void saveElement(int index, Resume resume) {
        if (size == AbstractArrayStorage.STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        saveResume(index, resume);
        size++;
    }

    @Override
    protected void deleteElement(int index) {
        deleteResume(index);
        storage[size - 1] = null;
        size--;
    }
}
