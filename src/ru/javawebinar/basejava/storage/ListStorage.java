package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storage;

    public ListStorage(List<Resume> storage) {
        this.storage = storage;
    }

    @Override
    protected boolean keyIsExist(Object key) {
        return (int) key > -1;
    }

    @Override
    protected boolean keyIsNotExist(Object key) {
        return (int) key < 0;
    }

    @Override
    protected Integer getKey(String key) {
        for (int i = 0; i < size(); i++) {
            if (storage.get(i).getUuid().equals(key)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected Resume executeGet(Object key) {
        return storage.get((int) key);
    }

    @Override
    protected void executeUpdate(Object key, Resume resume) {
        storage.set((int) key, resume);
    }

    @Override
    protected void executeSave(Object key, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void executeDelete(Object key) {
        storage.remove((int) key);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected List<Resume> getArrayList() {
        return new ArrayList<>(storage);
    }

    @Override
    public int size() {
        return storage.size();
    }
}