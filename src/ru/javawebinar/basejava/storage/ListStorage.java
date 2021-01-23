package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storage;

    public ListStorage(List<Resume> storage) {
        this.storage = storage;
    }

    @Override
    protected int getIndex(String searchKey) {
        Resume resume = new Resume(searchKey);
        return storage.indexOf(resume);
    }

    @Override
    protected Resume executeGet(Object searchKey) {
        return storage.get((int) searchKey);
    }

    @Override
    protected void executeUpdate(Object searchKey, Resume resume) {
        storage.set((int) searchKey, resume);
    }

    @Override
    protected void executeSave(Object searchKey, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void executeDelete(Object searchKey) {
        storage.remove((int) searchKey);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> copy = new ArrayList<>(storage);
        copy.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        return copy;
    }

    @Override
    public int size() {
        return storage.size();
    }
}