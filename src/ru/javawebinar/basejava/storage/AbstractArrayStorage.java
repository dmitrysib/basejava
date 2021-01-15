package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exists");
            return null;
        }
        return storage[index];
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (getIndex(resume.getUuid()) < 0) {
            System.out.println("Update ERROR: Resume with UUID " + resume.getUuid() + " not found");
        } else {
            storage[index] = resume;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Delete ERROR: Resume with UUID " + uuid + " not found");
        } else {
            size--;
            System.arraycopy(storage, index + 1, storage, index, size);
        }
    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            System.out.println("Save ERROR: Resume  with UUID " + resume.getUuid() + " exists in database");
        } else if (size == storage.length) {
            System.out.println("Save ERROR: storage is full");
        } else {
            size++;
            index = -index - 1;
            System.arraycopy(storage, index, storage, index + 1, size);
            storage[index] = resume;
        }
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int getIndex(String uuid);
}
