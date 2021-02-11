package ru.javawebinar.basejava.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.javawebinar.basejava.model.AbstractSection;

import java.io.Reader;
import java.io.Writer;

public class JsonParer {
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(AbstractSection.class, new JsonSectionAdapter<>())
            .create();

    public static <T> T read(Reader reader, Class<T> tClass) {
        return GSON.fromJson(reader, tClass);
    }

    public static <T> void write(T object, Writer writer) {
        GSON.toJson(object, writer);
    }

    public static <T> T read(String content, Class<T> tClass) {
        return GSON.fromJson(content, tClass);
    }

    public static <T> String write(T section, Class<T> tClass) {
        return GSON.toJson(section, tClass);
    }
}
