package ru.javawebinar.basejava.util;

import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.model.AbstractSection;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.StringSection;

import static ru.javawebinar.basejava.TestData.*;

import static org.junit.jupiter.api.Assertions.*;

class JsonParerTest {

    @Test
    void read() {
        String json = JsonParer.write(RESUME_1, Resume.class);
        Resume resume = JsonParer.read(json, Resume.class);
        assertEquals(RESUME_1, resume);
    }

    @Test
    void write() {
        AbstractSection section = new StringSection("Test String Section");
        String json = JsonParer.write(section, AbstractSection.class);
        System.out.println(json);
        AbstractSection result = JsonParer.read(json, AbstractSection.class);
        assertEquals(section, result);
    }
}