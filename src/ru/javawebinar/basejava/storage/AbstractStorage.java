package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SearchKey> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract SearchKey getKey(String key);

    protected abstract boolean isExit(SearchKey key);

    protected abstract Resume doGet(SearchKey key);

    protected abstract void doUpdate(SearchKey key, Resume resume);

    protected abstract void doSave(SearchKey key, Resume resume);

    protected abstract void doDelete(SearchKey key);

    protected abstract List<Resume> getList();

    @Override
    public Resume get(String uuid) {
        SearchKey key = getExistKey(uuid);
        return doGet(key);
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SearchKey key = getExistKey(uuid);
        doDelete(key);
    }

    @Override
    public void update(Resume resume) {
        LOG.info("Update " + resume);
        SearchKey key = getExistKey(resume.getUuid());
        doUpdate(key, resume);
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save " + resume);
        SearchKey key = getNotExistKey(resume.getUuid());
        doSave(key, resume);
    }

    private SearchKey getExistKey(String uuid) {
        SearchKey key = getKey(uuid);
        if (!isExit(key)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private SearchKey getNotExistKey(String uuid) {
        SearchKey key = getKey(uuid);
        if (isExit(key)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = getList();
        Collections.sort(result);
        return result;
    }
}