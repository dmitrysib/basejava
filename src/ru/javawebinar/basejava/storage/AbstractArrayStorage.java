package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract void saveElement(int index, Resume resume);

    protected abstract void deleteElement(int index);

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
    protected List<Resume> getArrayList() {
        return new ArrayList<>(Arrays.asList(Arrays.copyOf(storage, size)));
    }

    @Override
    protected Resume executeGet(Integer key) {
        return storage[key];
    }

    @Override
    protected void executeUpdate(Integer key, Resume resume) {
        storage[key] = resume;
    }

    @Override
    protected void executeSave(Integer key, Resume resume) {
        if (size == AbstractArrayStorage.STORAGE_LIMIT) {
            throw new StorageException("Storage overflow");
        }
        saveElement(key, resume);
        size++;
    }

    @Override
    protected void executeDelete(Integer key) {
        deleteElement(key);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean isExit(Integer key) {
        return key >= 0;
    }
}
