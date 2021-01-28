package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;

public class ObjectStreamPathStorage extends AbstractPathStorage {

    ObjectStreamPathStorage(String dir) {
        super(dir);
    }

    @Override
    protected Resume doRead(InputStream is) throws IOException {
        Resume resume;
        try (var ois = new ObjectInputStream(is)) {
            resume = (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Couldn't read from file", e);
        }
        return resume;
    }

    @Override
    protected void doWrite(OutputStream os, Resume resume) throws IOException {
        try (var oos = new ObjectOutputStream(os)) {
            oos.writeObject(resume);
        }
    }
}
