package ru.javawebinar.basejava.storage.serialization;

import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;

public interface Serialization {
    void write(File file, Resume resume) throws IOException;
    Resume read(File file) throws IOException;
}
