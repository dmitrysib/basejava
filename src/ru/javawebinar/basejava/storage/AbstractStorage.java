package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.*;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract int getIndex(String uuid);

    protected abstract Resume executeGet(Object searchKey);

    protected abstract void executeUpdate(Object searchKey, Resume resume);

    protected abstract void executeSave(Object searchKey, Resume resume);

    protected abstract void executeDelete(Object searchKey);

    protected Object getExistIndex(String searchKey) {
        int index = getIndex(searchKey);
        if (index < 0) {
            throw new NotExistStorageException(searchKey);
        }
        return index;
    }

    protected Object getNotExistIndex(String searchKey) {
        int index = getIndex(searchKey);
        if (index >= 0) {
            throw new ExistStorageException(searchKey);
        }
        return index;
    }

    @Override
    public Resume get(String uuid) {
        return executeGet(getExistIndex(uuid));
    }

    @Override
    public void delete(String uuid) {
        executeDelete(getExistIndex(uuid));
    }

    @Override
    public void update(Resume resume) {
        executeUpdate(getExistIndex(resume.getUuid()), resume);
    }

    @Override
    public void save(Resume resume) {
        executeSave(getNotExistIndex(resume.getUuid()), resume);
    }
}