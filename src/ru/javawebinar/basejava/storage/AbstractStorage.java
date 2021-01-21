package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.*;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract int eIndex(String uuid);

    protected abstract Resume eGet(String searchKey);

    protected abstract void eUpdate(String searchKey, Resume resume);

    protected abstract void eSave(String searchKey, Resume resume);

    protected abstract void eDelete(String searchKey);

    // test for maps....
    protected String getIndex(String uuid, boolean exist) {
        int index = eIndex(uuid);
        if (!exist && index < 0) {
            throw new NotExistStorageException(uuid);
        } else if (exist && index >= 0) {
            throw new ExistStorageException(uuid);
        }
        return Integer.toString(index);
    }
    // test for maps....

    @Override
    public Resume get(String uuid) {
        return eGet(getIndex(uuid, false));
    }

    @Override
    public void delete(String uuid) {
        eDelete(getIndex(uuid, false));
    }

    @Override
    public void update(Resume resume) {
        eUpdate(getIndex(resume.getUuid(), false), resume);
    }

    @Override
    public void save(Resume resume) {
        eSave(getIndex(resume.getUuid(), true), resume);
    }
}