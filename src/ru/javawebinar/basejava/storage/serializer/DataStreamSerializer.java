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

            execute(dos, resume.getContacts().entrySet(), contact -> {
                dos.writeUTF(contact.getKey().name());
                dos.writeUTF(contact.getValue());
            });

            execute(dos, resume.getSections().entrySet(), section -> {
                dos.writeUTF(section.getKey().name());
                switch (section.getKey()) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((StringSection) section.getValue()).getValue());
                    case ACHIEVEMENT, QUALIFICATIONS -> execute(dos, ((ListSection) section.getValue()).getElements(), dos::writeUTF);
                    case EDUCATION, EXPERIENCE -> execute(dos, ((Organization) section.getValue()).getElements(), experience -> {
                        dos.writeUTF(experience.getHomePage().getTitle());
                        dos.writeUTF(experience.getHomePage().getUrl());

                        execute(dos, experience.getPositions(), position -> {
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
                    case EDUCATION, EXPERIENCE -> {
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

    /*
    Определяется функциональный интерфейс, в котром описан метод принимающий один аргумент
    любого типа
     */
    private interface FunctionalBlock<T> {
        void lambda(T t) throws IOException;
    }

    /*
    на вход обобщеного метода подается выходной поток, коллеция и лямбда
    краткое описание - перебрать колекцию по элементам и что-то сделать с каждым элементом. Что сделать - описано в
    функциональном блоке
    Это прикольно но это работает типа
    execute(dos, Arrays.asList("a", "b", "c"), System.out::println);
     */
    private <T> void execute(DataOutputStream dos, Collection<T> collection, FunctionalBlock<T> functionalBlock) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            functionalBlock.lambda(item);
        }
    }
}
