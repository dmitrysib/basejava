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
    protected Resume eGet(int index) {
        return storage.get(index);
    }

    @Override
    protected void eUpdate(int index, Resume resume) {
        storage.set(index, resume);
    }

    @Override
    protected void eSave(int index, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void eDelete(int index) {
        storage.remove(index);
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
