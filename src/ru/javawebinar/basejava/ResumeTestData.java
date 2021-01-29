package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.DateUtil;

import java.time.Month;
import java.util.*;

public class ResumeTestData {

    public static String generateRandomWord() {
        int leftLimit = 97;
        int rightLimit = 122;
        Random random = new Random();
        int targetStringLength = random.nextInt(5) + 10;
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public static String generateRandomString(int words) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words; i++) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(generateRandomWord());
        }
        return sb.toString();
    }

    public static Resume generateResume(String uuid, String fullName) {

        Random random = new Random();

        Resume resume = new Resume(uuid, fullName);

        HashMap<ContactType, String> contacts = new HashMap<>();
        for (ContactType contactType : ContactType.values()) {
            contacts.put(contactType, generateRandomWord());
        }
        resume.setContacts(contacts);

        for (SectionType sectionType : SectionType.values()) {
            switch (sectionType.name()) {
                case "PERSONAL", "OBJECTIVE" -> {
                    AbstractSection<String> sc = new StringSection(generateRandomString(10));
                    resume.setSection(sectionType, sc);
                }
                case "ACHIEVEMENT", "QUALIFICATIONS" -> {
                    String[] list = new String[random.nextInt(2) + 3];
                    for (int i = 0; i < list.length; i++) {
                        list[i] = generateRandomString(10);
                    }
                    AbstractSection<String> sc = new ListSection(List.of(list));
                    resume.setSection(sectionType, sc);
                }
                case "EXPERIENCE" -> {
                    Experience[] eList = new Experience[random.nextInt(2) + 3];
                    for (int i = 0; i < eList.length; i++) {
                        eList[i] = new Experience(
                                generateRandomString(2),
                                "https://www." + generateRandomWord() + ".com/",
                                new Experience.Position(
                                        generateRandomString(4),
                                        DateUtil.NOW,
                                        DateUtil.NOW,
                                        generateRandomString(10)
                                )
                        );
                    }
                    AbstractSection<Experience> exp = new Organization(List.of(eList));
                    resume.setSection(sectionType, exp);
                }
                case "EDUCATION" -> {
                    Experience[] eList = new Experience[random.nextInt(2) + 3];
                    for (int i = 0; i < eList.length; i++) {
                        eList[i] = new Experience(
                                generateRandomString(2),
                                "https://www." + generateRandomWord() + ".com/",
                                new Experience.Position(
                                        generateRandomString(4),
                                        DateUtil.NOW,
                                        DateUtil.NOW
                                ),
                                new Experience.Position(
                                        generateRandomString(4),
                                        DateUtil.of(2000, Month.DECEMBER),
                                        DateUtil.NOW
                                )
                        );
                    }
                    AbstractSection<Experience> exp = new Organization(List.of(eList));
                    resume.setSection(sectionType, exp);
                }
            }
        }

        return resume;
    }

    public static void main(String[] args) {
        Resume resume = generateResume(UUID.randomUUID().toString(), generateRandomString(2));

        System.out.println(resume);
        for (Map.Entry<ContactType, String> entry: resume.getContacts().entrySet()) {
            System.out.println(entry.getKey().getTitle() + ": " + entry.getValue());
        }
        for (Map.Entry<SectionType, AbstractSection<?>> entry: resume.getSections().entrySet()) {
            System.out.println(entry.getKey().getTitle() + ": " + entry.getValue());
        }
    }
}