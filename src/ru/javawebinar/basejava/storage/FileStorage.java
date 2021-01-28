package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.serialization.Serialization;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private final File directory;
    private final Serialization serialization;

    public FileStorage(String dir, Serialization serialization) {
        Objects.requireNonNull(dir, "directory cannot be null");
        directory = new File(dir);
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!(directory.canRead() && directory.canWrite())) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }

        this.serialization = serialization;
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
    protected Resume executeGet(File file) {
        Resume resume;
        try {
            resume = serialization.read(file);
        } catch (IOException e) {
            throw new StorageException("Couldn't read from file", e);
        }
        return resume;
    }

    @Override
    protected void executeUpdate(File file, Resume resume) {
        try {
            serialization.write(file, resume);
        } catch (IOException e) {
            throw new StorageException("Couldn't write to file", e);
        }
    }

    @Override
    @SuppressWarnings("ResultOfMethodCallIgnored")
    protected void executeSave(File file, Resume resume) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file", e);
        }
        executeUpdate(file, resume);
    }

    @Override
    @SuppressWarnings("ResultOfMethodCallIgnored")
    protected void executeDelete(File file) {
        file.delete();
    }

    @Override
    protected List<Resume> getArrayList() {
        List<Resume> resumes = new ArrayList<>();
        for (String path : Objects.requireNonNull(directory.list())) {
            File file = new File(directory, path);
            resumes.add(executeGet(file));
        }
        return resumes;
    }

    @Override
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void clear() {
        for (String path : Objects.requireNonNull(directory.list())) {
            File file = new File(directory, path);
            file.delete();
        }
    }

    @Override
    public int size() {
        return Objects.requireNonNull(directory.list()).length;
    }
}
