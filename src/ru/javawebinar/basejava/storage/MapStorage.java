package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage;

    public MapStorage(Map<String, Resume> storage) {
        this.storage = storage;
    }

    @Override
    protected Object getNotExistIndex(String searchKey) {
        super.getNotExistIndex(searchKey);
        return searchKey;
    }

    @Override
    protected Object getExistIndex(String searchKey) {
        super.getExistIndex(searchKey);
        return searchKey;
    }

    @Override
    protected int eIndex(String searchKey) {
        return storage.containsKey(searchKey) ? 1 : -1;
    }

    @Override
    protected Resume eGet(Object searchKey) {
        String key = (String) searchKey;
        return storage.get(key);
    }

    @Override
    protected void eUpdate(Object searchKey, Resume resume) {
        String key = (String) searchKey;
        storage.replace(key, resume);
    }

    @Override
    protected void eSave(Object searchKey, Resume resume) {
        String key = (String) searchKey;
        storage.put(key, resume);
    }

    @Override
    protected void eDelete(Object searchKey) {
        String key = (String) searchKey;
        storage.remove(key);
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
