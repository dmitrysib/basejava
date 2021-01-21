package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage;

    public MapStorage(Map<String, Resume> storage) {
        this.storage = storage;
    }

    @Override
    protected int getIndex(String uuid) {
        return storage.containsKey(uuid) ? 1 : -1;
    }

    @Override
    protected Resume getElement(int index, String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void updateElement(int index, Resume resume) {
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    protected void saveElement(int index, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteElement(int index, String uuid) {
        storage.remove(uuid);
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

    @Override
    protected boolean checkStorageLimit() {
        return false;
    }
}
