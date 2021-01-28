package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private final Path directory;

    protected AbstractPathStorage(String dir) {
        Objects.requireNonNull(dir, "directory cannot be null");
        directory = Paths.get(dir);
        if (!(Files.isDirectory(directory) && Files.isWritable(directory))) {
            throw new IllegalArgumentException(dir + " is not readable/writable");
        }
    }

    protected abstract Resume executeRead(BufferedInputStream path) throws IOException;

    protected abstract void executeWrite(BufferedOutputStream path, Resume resume) throws IOException;

    @Override
    protected Path getKey(String key) {
        return Paths.get(directory.toString(), key);
    }

    @Override
    protected boolean isExit(Path path) {
        return Files.exists(path);
    }

    @Override
    protected Resume executeGet(Path path) {
        Resume resume;
        try {
            resume = executeRead(new BufferedInputStream(new FileInputStream(path.toFile())));
        } catch (IOException e) {
            throw new StorageException("Couldn't read from Path", e);
        }
        return resume;
    }

    @Override
    protected void executeUpdate(Path path, Resume resume) {
        try {
            executeWrite(new BufferedOutputStream(new FileOutputStream(path.toFile())), resume);
        } catch (IOException e) {
            throw new StorageException("Couldn't write to Path", e);
        }
    }

    @Override
    protected void executeSave(Path path, Resume resume) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create Path", e);
        }
        executeUpdate(path, resume);
    }

    @Override
    protected void executeDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't delete path", e);
        }
    }

    @Override
    protected List<Resume> getArrayList() {
        List<Resume> resumes = new ArrayList<>();
        try {
            Files.list(directory).forEach(path -> resumes.add(executeGet(path)));
        } catch (IOException e) {
            throw new StorageException("Couldn't read path", e);
        }
        return resumes;
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::executeDelete);
        } catch (IOException e) {
            throw new StorageException("Couldn't delete path", e);
        }
    }

    @Override
    public int size() {
        int size;
        try {
            size = (int) Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("Couldn't read path", e);
        }
        return size;
    }
}
