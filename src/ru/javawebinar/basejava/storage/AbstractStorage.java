package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.*;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getKey(Object key);

    protected abstract boolean keyIsNotExist(Object key);

    protected abstract boolean keyIsExist(Object key);

    protected abstract Resume executeGet(Object key);

    protected abstract void executeUpdate(Object key, Resume resume);

    protected abstract void executeSave(Object key, Resume resume);

    protected abstract void executeDelete(Object key);

    private Object getExistKey(Object key) {
        Object result = getKey(key);
        if (keyIsNotExist(result)) {
            throw new NotExistStorageException(key);
        }
        return result;
    }

    private Object getNotExistKey(Object key) {
        Object result = getKey(key);
        if (keyIsExist(result)) {
            throw new ExistStorageException(key);
        }
        return result;
    }

    @Override
    public Resume get(String uuid) {
        Object key = getExistKey(uuid);
        return executeGet(key);
    }

    @Override
    public void delete(String uuid) {
        Object key = getExistKey(uuid);
        executeDelete(key);
    }

    @Override
    public void update(Resume resume) {
        Object key = getExistKey(resume.getUuid());
        executeUpdate(key, resume);
    }

    @Override
    public void save(Resume resume) {
        Object key = getNotExistKey(resume.getUuid());
        executeSave(key, resume);
    }
}