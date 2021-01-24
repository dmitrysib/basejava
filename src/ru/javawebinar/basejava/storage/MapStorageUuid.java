package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapStorageUuid extends AbstractStorage {
    private final Map<String, Resume> storage;

    public MapStorageUuid(Map<String, Resume> storage) {
        this.storage = storage;
    }

    @Override
    protected boolean isExit(Object key) {
        return key != null;
    }

    @Override
    protected Object getKey(String key) {
        return storage.get(key) == null ? null : key;
    }

    @Override
    protected Resume executeGet(Object key) {
        // Insert string variable to suppress IDE warning cast Object -> String
        String strKey = (String) key;
        return storage.get(strKey);
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
        // Insert string variable to suppress IDE warning cast Object -> String
        String strKey = (String) key;
        storage.remove(strKey);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected List<Resume> getArrayList() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
