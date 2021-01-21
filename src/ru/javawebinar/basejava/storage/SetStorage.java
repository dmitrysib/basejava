package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Set;

public class SetStorage extends AbstractStorage {
    private final Set<Resume> storage;

    public SetStorage(Set<Resume> storage) {
        this.storage = storage;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return storage.contains(resume) ? 1 : -1;
    }

    @Override
    protected Resume getElement(int index, String uuid) {
        for (Resume resume : storage) {
            if (resume.getUuid().equals(uuid)) {
                return resume;
            }
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    protected void updateElement(int index, Resume resume) {
        storage.remove(resume);
        storage.add(resume);
    }

    @Override
    protected void saveElement(int index, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void deleteElement(int index, String uuid) {
        Resume resume = get(uuid);
        storage.remove(resume);
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

    @Override
    protected boolean checkStorageLimit() {
        return false;
    }
}
