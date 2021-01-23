package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapStorageResume extends AbstractStorage {
    private final Map<String, Resume> storage;

    public MapStorageResume(Map<String, Resume> storage) {
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
        return storage.get(key);
    }

    @Override
    protected Resume executeGet(Object key) {
        return (Resume) key;
    }

    @Override
    protected void executeUpdate(Object key, Resume resume) {
        storage.replace(((Resume) key).getUuid(), resume);
    }

    @Override
    protected void executeSave(Object key, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void executeDelete(Object key) {
        storage.remove(((Resume) key).getUuid());
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
