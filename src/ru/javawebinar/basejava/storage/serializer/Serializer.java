package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;

public interface Serializer {

    void doWrite(File file, Resume resume) throws IOException;

    Resume doRead(File file) throws IOException;
}
