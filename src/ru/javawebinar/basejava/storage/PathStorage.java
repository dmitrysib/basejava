package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.serializer.Serializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final Serializer serializer;

    PathStorage(String dir, Serializer serializer) {
        Objects.requireNonNull(dir, "directory cannot be null");
        directory = Paths.get(dir);
        if (!(Files.isDirectory(directory) && Files.isWritable(directory))) {
            throw new IllegalArgumentException(dir + " is not readable/writable");
        }
        this.serializer = serializer;
    }

    @Override
    protected Path getKey(String key) {
        return directory.resolve(key);
    }

    @Override
    protected boolean isExit(Path path) {
        return Files.exists(path);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return serializer.doRead(path.toFile());
        } catch (IOException e) {
            throw new StorageException("Couldn't read from Path", e);
        }
    }

    @Override
    protected void doUpdate(Path path, Resume resume) {
        try {
            serializer.doWrite(path.toFile(), resume);
        } catch (IOException e) {
            throw new StorageException("Couldn't write to Path", e);
        }
    }

    @Override
    protected void doSave(Path path, Resume resume) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create Path", e);
        }
        doUpdate(path, resume);
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't delete path", e);
        }
    }

    @Override
    protected List<Resume> getList() {
        return getDirectoryList().map(this::doGet).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        getDirectoryList().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) getDirectoryList().count();
    }

    private Stream<Path> getDirectoryList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Couldn't read path", e);
        }
    }
}
