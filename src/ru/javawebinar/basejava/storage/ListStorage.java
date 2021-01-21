package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storage;

    public ListStorage(List<Resume> storage) {
        this.storage = storage;
    }

    @Override
    protected int eIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return storage.indexOf(resume);
    }

    @Override
    protected Resume eGet(String searchKey) {
        return storage.get(Integer.parseInt(searchKey));
    }

    @Override
    protected void eUpdate(String searchKey, Resume resume) {
        storage.set(Integer.parseInt(searchKey), resume);
    }

    @Override
    protected void eSave(String searchKey, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void eDelete(String searchKey) {
        storage.remove(Integer.parseInt(searchKey));
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(Resume[]::new);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
