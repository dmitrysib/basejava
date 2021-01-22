package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.*;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract int eIndex(String uuid);

    protected abstract Resume eGet(Object searchKey);

    protected abstract void eUpdate(Object searchKey, Resume resume);

    protected abstract void eSave(Object searchKey, Resume resume);

    protected abstract void eDelete(Object searchKey);

    protected Object getExistIndex(String searchKey) {
        int index = eIndex(searchKey);
        if (index < 0) {
            throw new NotExistStorageException(searchKey);
        }
        return index;
    }

    protected Object getNotExistIndex(String searchKey) {
        int index = eIndex(searchKey);
        if (index >= 0) {
            throw new ExistStorageException(searchKey);
        }
        return index;
    }

    @Override
    public Resume get(String uuid) {
        return eGet(getExistIndex(uuid));
    }

    @Override
    public void delete(String uuid) {
        eDelete(getExistIndex(uuid));
    }

    @Override
    public void update(Resume resume) {
        eUpdate(getExistIndex(resume.getUuid()), resume);
    }

    @Override
    public void save(Resume resume) {
        eSave(getNotExistIndex(resume.getUuid()), resume);
    }
}