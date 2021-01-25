package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapStorageResume extends AbstractStorage<Resume> {
    private final Map<String, Resume> storage;

    public MapStorageResume(Map<String, Resume> storage) {
        this.storage = storage;
    }

    @Override
    protected boolean isExit(Resume key) {
        return key != null;
    }

    @Override
    protected Resume getKey(String key) {
        return storage.get(key);
    }

    @Override
    protected Resume executeGet(Resume key) {
        return key;
    }

    @Override
    protected void executeUpdate(Resume key, Resume resume) {
        storage.replace(key.getUuid(), resume);
    }

    @Override
    protected void executeSave(Resume key, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void executeDelete(Resume key) {
        storage.remove(key.getUuid());
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
