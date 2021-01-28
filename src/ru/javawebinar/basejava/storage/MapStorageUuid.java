package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapStorageUuid extends AbstractStorage<String> {
    private final Map<String, Resume> storage;

    public MapStorageUuid(Map<String, Resume> storage) {
        this.storage = storage;
    }

    @Override
    protected boolean isExit(String key) {
        return key != null;
    }

    @Override
    protected String getKey(String key) {
        return storage.get(key) == null ? null : key;
    }

    @Override
    protected Resume doGet(String key) {
        return storage.get(key);
    }

    @Override
    protected void doUpdate(String key, Resume resume) {
        storage.replace(key, resume);
    }

    @Override
    protected void doSave(String key, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(String key) {
        storage.remove(key);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected List<Resume> getList() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
