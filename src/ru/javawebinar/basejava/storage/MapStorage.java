package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
    protected int getIndex(String searchKey) {
        for (Map.Entry<String, Resume> entry: storage.entrySet()) {
            if (entry.getKey().equals(searchKey)) {
                return 1;
            }
        }
        return -1;
    }

    @Override
    protected Resume executeGet(Object searchKey) {
        String key = (String) searchKey;
        return storage.get(key);
    }

    @Override
    protected void executeUpdate(Object searchKey, Resume resume) {
        String key = (String) searchKey;
        storage.replace(key, resume);
    }

    @Override
    protected void executeSave(Object searchKey, Resume resume) {
        String key = (String) searchKey;
        storage.put(key, resume);
    }

    @Override
    protected void executeDelete(Object searchKey) {
        String key = (String) searchKey;
        storage.remove(key);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> copy = new ArrayList<>(List.copyOf(storage.values()));
        copy.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        return copy;
    }

    @Override
    public int size() {
        return storage.size();
    }
}
