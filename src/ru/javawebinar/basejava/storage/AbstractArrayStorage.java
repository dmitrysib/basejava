package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
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
    protected List<Resume> getArrayCopy() {
        return new ArrayList<>(Arrays.asList(Arrays.copyOf(storage, size)));
    }

    @Override
    protected Resume executeGet(Object key) {
        return storage[(int) key];
    }

    @Override
    protected void executeUpdate(Object key, Resume resume) {
        storage[(int) key] = resume;
    }

    @Override
    protected void executeSave(Object key, Resume resume) {
        if (size == AbstractArrayStorage.STORAGE_LIMIT) {
            throw new StorageException("Storage overflow");
        }
        saveElement((int) key, resume);
        size++;
    }

    @Override
    protected void executeDelete(Object key) {
        deleteElement((int) key);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean keyIsExist(Object key) {
        return (int) key > -1;
    }

    @Override
    protected boolean keyIsNotExist(Object key) {
        return (int) key < 0;
    }
}
