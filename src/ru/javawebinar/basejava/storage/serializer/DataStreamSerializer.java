package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataStreamSerializer implements Serializer {
    @Override
    public void doWrite(OutputStream os, Resume resume) throws IOException {
        try (var dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            doForEachOut(dos, resume.getContacts().entrySet(), contact -> {
                dos.writeUTF(contact.getKey().name());
                dos.writeUTF(contact.getValue());
            });

            doForEachOut(dos, resume.getSections().entrySet(), section -> {
                dos.writeUTF(section.getKey().name());
                switch (section.getKey()) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((StringSection) section.getValue()).getValue());
                    case ACHIEVEMENT, QUALIFICATIONS -> doForEachOut(dos, ((ListSection) section.getValue()).getElements(), dos::writeUTF);
                    case EDUCATION, EXPERIENCE -> doForEachOut(dos, ((Organization) section.getValue()).getElements(), experience -> {
                        dos.writeUTF(experience.getHomePage().getTitle());
                        dos.writeUTF(experience.getHomePage().getUrl());

                        doForEachOut(dos, experience.getPositions(), position -> {
                            dos.writeUTF(position.getTitle());
                            dos.writeUTF(position.getStartDate());
                            dos.writeUTF(position.getEndDate());
                            dos.writeUTF(position.getDescription());
                        });
                    });
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (var dis = new DataInputStream(is)) {
            Resume resume = new Resume(dis.readUTF(), dis.readUTF());

            doForEachIn(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));

            doForEachIn(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case OBJECTIVE, PERSONAL -> resume.addSection(sectionType, new StringSection(dis.readUTF()));
                    case ACHIEVEMENT, QUALIFICATIONS -> resume.addSection(sectionType, new ListSection(
                            readList(dis, dis::readUTF)
                    ));
                    case EDUCATION, EXPERIENCE -> resume.addSection(sectionType, new Organization(
                            readList(dis, () -> new Experience(
                                    new Experience.Link(dis.readUTF(), dis.readUTF()),
                                    readList(dis, () ->
                                            new Experience.Position(dis.readUTF(), dis.readUTF(), dis.readUTF(), dis.readUTF()))
                            ))
                    ));
                }
            });
            return resume;
        }
    }

    private <T> void doForEachOut(DataOutputStream dos, Collection<T> collection, ActionOut<T> actionOut) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            actionOut.action(item);
        }
    }

    private void doForEachIn(DataInputStream dis, ActionIn actionIn) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            actionIn.action();
        }
    }

    private <T> List<T> readList(DataInputStream dis, ActionInType<T> action) throws IOException {
        int size = dis.readInt();
        List<T> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(action.action());
        }
        return result;
    }

    private interface ActionOut<T> {
        void action(T t) throws IOException;
    }

    private interface ActionIn {
        void action() throws IOException;
    }

    private interface ActionInType<T> {
        T action() throws IOException;
    }
}
