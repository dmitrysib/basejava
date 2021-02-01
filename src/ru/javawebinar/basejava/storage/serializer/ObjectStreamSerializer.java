package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;

public class ObjectStreamSerializer implements Serializer {
    @Override
    public void doWrite(OutputStream os, Resume resume) throws IOException {
        try (var oos = new ObjectOutputStream(os)) {
            oos.writeObject(resume);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        Resume resume;
        try (var ois = new ObjectInputStream(is)) {
            resume = (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Couldn't read from file", e);
        }
        return resume;
    }
}