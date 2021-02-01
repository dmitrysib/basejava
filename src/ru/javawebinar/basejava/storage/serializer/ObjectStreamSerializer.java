package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;

public class ObjectStreamSerializer implements Serializer {
    @Override
    public void doWrite(File file, Resume resume) throws IOException {
        var os = new BufferedOutputStream(new FileOutputStream(file));
        try (var oos = new ObjectOutputStream(os)) {
            oos.writeObject(resume);
        }
    }

    @Override
    public Resume doRead(File file) throws IOException {
        Resume resume;
        var is = new BufferedInputStream(new FileInputStream(file));
        try (var ois = new ObjectInputStream(is)) {
            resume = (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Couldn't read from file", e);
        }
        return resume;
    }
}
