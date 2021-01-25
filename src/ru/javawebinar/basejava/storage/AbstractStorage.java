package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.*;
import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getKey(String key);

    protected abstract boolean isExit(Object key);

    protected abstract Resume executeGet(Object key);

    protected abstract void executeUpdate(Object key, Resume resume);

    protected abstract void executeSave(Object key, Resume resume);

    protected abstract void executeDelete(Object key);

    protected abstract List<Resume> getArrayList();

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

    private Object getExistKey(String uuid) {
        Object key = getKey(uuid);
        if (!isExit(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private Object getNotExistKey(String uuid) {
        Object key = getKey(uuid);
        if (isExit(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = getArrayList();
        result.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        return result;
    }
}