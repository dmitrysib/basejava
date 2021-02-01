package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.util.JsonParer;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JsonStreamSerializer implements Serializer {
    @Override
    public void doWrite(OutputStream os, Resume resume) throws IOException {
        try (Writer writer = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            JsonParer.write(resume, writer);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return JsonParer.read(reader, Resume.class);
        }
    }
}
