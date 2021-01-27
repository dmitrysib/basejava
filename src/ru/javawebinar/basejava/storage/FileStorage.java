package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;

public class FileStorage extends AbstractFileStorage {

    public FileStorage(String pathname) {
        super(new File(pathname));
    }

    @Override
    protected Resume executeRead(File file) throws IOException {
        return null;
    }

    @Override
    protected void executeWrite(File file, Resume resume) throws IOException {

    }
}
