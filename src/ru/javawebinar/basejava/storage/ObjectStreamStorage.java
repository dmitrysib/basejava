package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;

public class ObjectStreamStorage extends AbstractFileStorage {

    protected ObjectStreamStorage(File directory) {
        super(directory);
    }

    @Override
    protected Resume executeRead(InputStream is) throws IOException {
        Resume resume;
        try (var ois = new ObjectInputStream(is)) {
            resume = (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Couldn't read from file", e);
        }
        return resume;
    }

    @Override
    protected void executeWrite(OutputStream os, Resume resume) throws IOException {
        try (var oos = new ObjectOutputStream(os)) {
            oos.writeObject(resume);
        }
    }
}
