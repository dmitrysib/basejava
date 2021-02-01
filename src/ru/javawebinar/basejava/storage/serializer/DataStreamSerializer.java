package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements Serializer {
    @Override
    public void doWrite(OutputStream os, Resume resume) throws IOException {
        try (var dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            contacts.forEach((contactType, contact) -> {
                writeUTF(contactType.name(), dos);
                writeUTF(contact, dos);
            });

            Map<SectionType, AbstractSection> sections = resume.getSections();
            dos.writeInt(sections.size());
            sections.forEach((sectionType, section) -> {
                writeUTF(sectionType.name(), dos);
                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> writeUTF(((StringSection) section).getValue(), dos);
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        List<String> list = ((ListSection) section).getElements();
                        writeInt(list.size(), dos);
                        list.forEach(s -> writeUTF(s, dos));
                    }
                    case EDUCATION, EXPERIENCE -> {
                        List<Experience> list = ((Organization) section).getElements();
                        writeInt(list.size(), dos);
                        list.forEach(e -> {
                            writeUTF(e.getHomePage().getTitle(), dos);
                            writeUTF(e.getHomePage().getUrl(), dos);

                            List<Experience.Position> positions = e.getPositions();
                            writeInt(positions.size(), dos);
                            positions.forEach(p -> {
                                writeUTF(p.getTitle(), dos);
                                writeUTF(p.getStartDate(), dos);
                                writeUTF(p.getEndDate(), dos);
                                writeUTF(p.getDescription(), dos);
                            });
                        });
                    }
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (var dis = new DataInputStream(is)) {
            Resume resume = new Resume(dis.readUTF(), dis.readUTF());

            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int sectionsCount = dis.readInt();
            for (int i = 0; i < sectionsCount; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case OBJECTIVE, PERSONAL -> resume.addSection(sectionType, new StringSection(dis.readUTF()));
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        int listSize = dis.readInt();
                        List<String> stringList = new ArrayList<>();
                        for (int k = 0; k < listSize; k++) {
                            stringList.add(dis.readUTF());
                        }
                        resume.addSection(sectionType, new ListSection(stringList));
                    }
                    case EDUCATION,EXPERIENCE -> {
                        int orgSize = dis.readInt();
                        List<Experience> experiences = new ArrayList<>();
                        for (int k = 0; k < orgSize; k++) {
                            Experience e = new Experience(dis.readUTF(), dis.readUTF());

                            int posSize = dis.readInt();
                            List<Experience.Position> positions = new ArrayList<>();
                            for (int m = 0; m < posSize; m++) {
                                positions.add(new Experience.Position(dis.readUTF(), dis.readUTF(), dis.readUTF(), dis.readUTF()));
                            }
                            e.setPositions(positions);
                            experiences.add(e);
                        }
                        resume.addSection(sectionType, new Organization(experiences));
                    }
                }
            }

            return resume;
        }
    }

    private void writeUTF(String str, DataOutputStream dos) {
        try {
            dos.writeUTF(str);
        } catch (IOException e) {
            throw new StorageException("Can't write to file", e);
        }
    }

    private void writeInt(int number, DataOutputStream dos) {
        try {
            dos.writeInt(number);
        } catch (IOException e) {
            throw new StorageException("Can't write to file", e);
        }
    }
}
