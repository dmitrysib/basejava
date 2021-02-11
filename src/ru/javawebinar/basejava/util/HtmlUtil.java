package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;

public final class HtmlUtil {

    public static String getContact(Resume resume, ContactType contactType) {
        return resume.getContacts().get(ContactType.EMAIL);
    }
}
