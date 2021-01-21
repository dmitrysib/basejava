package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract void aSave(int index, Resume resume);

    protected abstract void aDelete(int index);

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
    protected Resume eGet(String searchKey) {
        return storage[Integer.parseInt(searchKey)];
    }

    @Override
    protected void eUpdate(String searchKey, Resume resume) {
        storage[Integer.parseInt(searchKey)] = resume;
    }

    @Override
    protected void eSave(String searchKey, Resume resume) {
        if (size == AbstractArrayStorage.STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        aSave(Integer.parseInt(searchKey), resume);
        size++;
    }

    @Override
    protected void eDelete(String searchKey) {
        aDelete(Integer.parseInt(searchKey));
        storage[size - 1] = null;
        size--;
    }
}
