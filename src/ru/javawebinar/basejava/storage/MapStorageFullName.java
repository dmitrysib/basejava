package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MapStorageFullName extends AbstractStorage {
    private final Map<String, Resume> storage;

    public MapStorageFullName(Map<String, Resume> storage) {
        this.storage = storage;
    }

    private Resume getResumeByFullName(String fullName) {
        for (Map.Entry<String, Resume> entry: storage.entrySet()) {
            if (entry.getValue().getFullName().equals(fullName)) {
                return entry.getValue();
            }
        }
        return null;
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
        return getResumeByFullName(key) == null ? null : key;
    }

    @Override
    protected Resume executeGet(Object key) {
        return getResumeByFullName((String) key);
    }

    @Override
    protected void executeUpdate(Object key, Resume resume) {
        Resume r = getResumeByFullName((String) key);

        // Insert Objects.requireNonNull to suppress IDE warning
        storage.replace(Objects.requireNonNull(r).getUuid(), resume);
    }

    @Override
    protected void executeSave(Object key, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void executeDelete(Object key) {
        Resume resume = getResumeByFullName((String) key);

        // Insert Objects.requireNonNull to suppress IDE warning
        storage.remove(Objects.requireNonNull(resume).getUuid());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected List<Resume> getArrayCopy() {
        return new ArrayList<>(List.copyOf(storage.values()));
    }

    @Override
    public int size() {
        return storage.size();
    }
}
