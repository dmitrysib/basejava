package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.util.UUID;

public class TestData {
    public static final String STORAGE_DIR = Config.getInstance().getStorageDirectory();
    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String UUID_4 = UUID.randomUUID().toString();
    public static final Resume RESUME_1 = ResumeTestData.generateResume(UUID_1, ResumeTestData.generateRandomString(2));
    public static final Resume RESUME_2 = ResumeTestData.generateResume(UUID_2, ResumeTestData.generateRandomString(2));
    public static final Resume RESUME_3 = ResumeTestData.generateResume(UUID_3, ResumeTestData.generateRandomString(2));
    public static final Resume RESUME_4 = ResumeTestData.generateResume(UUID_4, ResumeTestData.generateRandomString(2));
}
