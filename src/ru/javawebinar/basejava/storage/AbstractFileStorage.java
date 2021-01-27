package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory cannot be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!(directory.canRead() && directory.canWrite())) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    protected abstract Resume executeRead(File file) throws IOException;
    protected abstract void executeWrite(File file, Resume resume) throws IOException;

    @Override
    protected File getKey(String key) {
        return new File(directory, key);
    }

    @Override
    protected boolean isExit(File file) {
        return file.exists();
    }

    @Override
    protected Resume executeGet(File file) {
        Resume resume;
        try {
            resume = executeRead(file);
        } catch (IOException e) {
            throw new StorageException("IO error", e);
        }
        return resume;
    }

    @Override
    protected void executeUpdate(File file, Resume resume) {
        try {
            executeWrite(file, resume);
        } catch (IOException e) {
            throw new StorageException("IO error", e);
        }
    }

    @Override
    @SuppressWarnings("ResultOfMethodCallIgnored ")
    protected void executeSave(File file, Resume resume) {
        try {
            file.createNewFile();
            executeWrite(file, resume);
        } catch (IOException e) {
            throw new StorageException("IO error", e);
        }
    }

    @Override
    @SuppressWarnings("ResultOfMethodCallIgnored ")
    protected void executeDelete(File file) {
        file.delete();
    }

    @Override
    protected List<Resume> getArrayList() {
        List<Resume> resumes = new ArrayList<>();
        for (String path: Objects.requireNonNull(directory.list())) {
            File file = new File(directory, path);
            resumes.add(executeGet(file));
        }
        return resumes;
    }

    @Override
    @SuppressWarnings("ResultOfMethodCallIgnored ")
    public void clear() {
        for (String path: Objects.requireNonNull(directory.list())) {
            File file = new File(directory, path);
            file.delete();
        }
    }

    @Override
    public int size() {
        return Objects.requireNonNull(directory.list()).length;
    }
}
