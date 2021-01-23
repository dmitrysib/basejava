package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class MapStorageUUID extends AbstractStorage {
    private final Map<String, Resume> storage;

    public MapStorageUUID(Map<String, Resume> storage) {
        this.storage = storage;
    }

    @Override
    protected boolean keyIsExist(Object key) {
        return key != null;
    }

    @Override
    protected boolean keyIsNotExist(Object key) {
        return key == null;
    }

    @Override
    protected Object getKey(String key) {
        return storage.get(key) == null ? null : key;
    }

    @Override
    protected Resume executeGet(Object key) {
        return storage.get((String) key);
    }

    @Override
    protected void executeUpdate(Object key, Resume resume) {
        storage.replace((String) key, resume);
    }

    @Override
    protected void executeSave(Object key, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void executeDelete(Object key) {
        storage.remove((String) key);
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
