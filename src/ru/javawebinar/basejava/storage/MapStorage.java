package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage;

    MapStorage(Map<String, Resume> storage) {
        this.storage = storage;
    }

    @Override
    protected int eIndex(String uuid) {
        return storage.containsKey(uuid) ? 1 : -1;
    }

    protected String selectIndexOrKey(int index, String searchKey) {
        return searchKey;
    }

    @Override
    protected Resume eGet(String searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected void eUpdate(String searchKey, Resume resume) {
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    protected void eSave(String searchKey, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void eDelete(String searchKey) {
        storage.remove(searchKey);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(Resume[]::new);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
