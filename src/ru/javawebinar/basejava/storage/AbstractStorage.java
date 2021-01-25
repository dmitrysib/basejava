package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage<SearchKey> implements Storage {

    protected abstract SearchKey getKey(String key);

    protected abstract boolean isExit(SearchKey key);

    protected abstract Resume executeGet(SearchKey key);

    protected abstract void executeUpdate(SearchKey key, Resume resume);

    protected abstract void executeSave(SearchKey key, Resume resume);

    protected abstract void executeDelete(SearchKey key);

    protected abstract List<Resume> getArrayList();

    @Override
    public Resume get(String uuid) {
        SearchKey key = getExistKey(uuid);
        return executeGet(key);
    }

    @Override
    public void delete(String uuid) {
        SearchKey key = getExistKey(uuid);
        executeDelete(key);
    }

    @Override
    public void update(Resume resume) {
        SearchKey key = getExistKey(resume.getUuid());
        executeUpdate(key, resume);
    }

    @Override
    public void save(Resume resume) {
        SearchKey key = getNotExistKey(resume.getUuid());
        executeSave(key, resume);
    }

    private SearchKey getExistKey(String uuid) {
        SearchKey key = getKey(uuid);
        if (!isExit(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private SearchKey getNotExistKey(String uuid) {
        SearchKey key = getKey(uuid);
        if (isExit(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = getArrayList();
        Collections.sort(result);
        return result;
    }
}