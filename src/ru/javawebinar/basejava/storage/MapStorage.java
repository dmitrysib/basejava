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
        int index = 0;
        for (Map.Entry<String, Resume> entry: storage.entrySet()) {
            if (uuid.equals(entry.getKey())) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    protected Resume eGet(int index) {
        int i = 0;
        for (Map.Entry<String, Resume> entry: storage.entrySet()) {
            if (i == index) {
                return entry.getValue();
            }
            i++;
        }
        return null;
    }

    @Override
    protected void eUpdate(int index, Resume resume) {
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    protected void eSave(int index, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void eDelete(int index) {
        Resume resume = eGet(index);
        storage.remove(resume.getUuid());
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
