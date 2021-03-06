package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.serializer.Serializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private final File directory;
    private final Serializer serializer;

    public FileStorage(String dir, Serializer serialization) {
        Objects.requireNonNull(dir, "directory cannot be null");
        directory = new File(dir);
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!(directory.canRead() && directory.canWrite())) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.serializer = serialization;
    }

    @Override
    protected File getKey(String key) {
        return new File(directory, key);
    }

    @Override
    protected boolean isExit(File file) {
        return file.exists();
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return serializer.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Couldn't read from file", e);
        }
    }

    @Override
    protected void doUpdate(File file, Resume resume) {
        try {
            serializer.doWrite(new BufferedOutputStream(new FileOutputStream(file)), resume);
        } catch (IOException e) {
            throw new StorageException("Couldn't write to file", e);
        }
    }

    @Override
    protected void doSave(File file, Resume resume) {
        try {
            if (!(file.createNewFile())) {
                throw new StorageException("Couldn't create new file");
            }
        } catch (IOException e) {
            throw new StorageException("Couldn't create file", e);
        }
        doUpdate(file, resume);
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("Couldn't delete file");
        }
    }

    @Override
    protected List<Resume> getList() {
        List<Resume> resumes = new ArrayList<>();
        for (String path : getFilesList()) {
            File file = new File(directory, path);
            resumes.add(doGet(file));
        }
        return resumes;
    }

    @Override
    public void clear() {
        for (String path : getFilesList()) {
            File file = new File(directory, path);
            if (!file.delete()) {
                throw new StorageException("Couldn't delete file");
            }
        }
    }

    @Override
    public int size() {
        return getFilesList().length;
    }

    String[] getFilesList() {
        String[] list = directory.list();
        if (list == null) {
            throw new StorageException("Couldn't get directory list");
        }
        return list;
    }
}
