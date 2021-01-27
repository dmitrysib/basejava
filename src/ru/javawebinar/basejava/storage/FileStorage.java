package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;

public class FileStorage extends AbstractFileStorage {

    public FileStorage(String pathname) {
        super(new File(pathname));
    }

    @Override
    protected Resume executeRead(File file) throws IOException {
        Resume resume;
        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream oIn = new ObjectInputStream(fis)) {
            resume = (Resume) oIn.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("IO Error", e);
        }
        return resume;
    }

    @Override
    protected void executeWrite(File file, Resume resume) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oOut = new ObjectOutputStream(fos);
        oOut.writeObject(resume);
        oOut.close();
    }
}
