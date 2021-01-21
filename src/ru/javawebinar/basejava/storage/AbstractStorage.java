package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.*;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract int eIndex(String uuid);

    protected abstract Resume eGet(int index);

    protected abstract void eUpdate(int index, Resume resume);

    protected abstract void eSave(int index, Resume resume);

    protected abstract void eDelete(int index);

    // test for...
    private int getIndex(String uuid, boolean exist) {
        int index = eIndex(uuid);
        if (!exist && index < 0) {
            throw new NotExistStorageException(uuid);
        } else if (exist && index >= 0) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }

    @Override
    public Resume get(String uuid) {
        return eGet(getIndex(uuid, false));
    }

    @Override
    public void update(Resume resume) {
        eUpdate(getIndex(resume.getUuid(), false), resume);
    }

    @Override
    public void delete(String uuid) {
        eDelete(getIndex(uuid, false));
    }

    @Override
    public void save(Resume resume) {
        eSave(getIndex(resume.getUuid(), true), resume);
    }
}