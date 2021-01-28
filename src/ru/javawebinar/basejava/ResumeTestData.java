package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.DateUtil;

import java.time.LocalDate;
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
                    String[] list = new String[new Random().nextInt(2) + 3];
                    for (int i = 0; i < list.length; i++) {
                        list[i] = generateRandomString(10);
                    }
                    AbstractSection<String> sc = new ListSection(new ArrayList<>(Arrays.asList(list)));
                    resume.setSection(sectionType, sc);
                }
                case "EXPERIENCE" -> {
                    Experience[] eList = new Experience[new Random().nextInt(2) + 3];
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
                    AbstractSection<Experience> exp = new Organization(new ArrayList<>(Arrays.asList(eList)));
                    resume.setSection(sectionType, exp);
                }
                case "EDUCATION" -> {
                    Experience[] eList = new Experience[new Random().nextInt(2) + 3];
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
                                        DateUtil.NOW,
                                        DateUtil.NOW
                                )
                        );
                    }
                    AbstractSection<Experience> exp = new Organization(new ArrayList<>(Arrays.asList(eList)));
                    resume.setSection(sectionType, exp);
                }
            }
        }

        return resume;
    }

    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

        resume.setContacts(Map.of(
                ContactType.EMAIL, "gkislin@yandex.ru",
                ContactType.PHONE, "+7(921) 855-0482",
                ContactType.SKYPE, "grigory.kislin"
        ));

        AbstractSection<String> section;
        section = new StringSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        resume.setSection(SectionType.PERSONAL, section);

        section = new StringSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        resume.setSection(SectionType.OBJECTIVE, section);

        List<String> list = new ArrayList<>(Arrays.asList(
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.",
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django). ",
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга. ",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера. ",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk. ",
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников."));
        section = new ListSection(list);
        resume.setSection(SectionType.ACHIEVEMENT, section);

        list = new ArrayList<>(Arrays.asList(
                "Родной русский, английский \"upper intermediate\"",
                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования ",
                "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer. ",
                "Инструменты: Maven + plugin development, Gradle, настройка Ngnix, ",
                "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT. "));
        section = new ListSection(list);
        resume.setSection(SectionType.QUALIFICATIONS, section);

        List<Experience> exList = new ArrayList<>(Arrays.asList(
                new Experience(
                        "Java Online Projects",
                        "http://javaops.ru/",
                        new Experience.Position(
                                "Автор проекта.",
                                DateUtil.of(2013, Month.OCTOBER),
                                LocalDate.now(),
                                "Создание, организация и проведение Java онлайн проектов и стажировок."
                        )
                ),
                new Experience(
                        "Wrike",
                        "https://www.wrike.com/",
                        new Experience.Position(
                                "Старший разработчик (backend)",
                                DateUtil.of(2014, Month.OCTOBER),
                                DateUtil.of(2016, Month.JANUARY),
                                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."
                        )
                )
        ));
        Organization o = new Organization(exList);
        resume.setSection(SectionType.EXPERIENCE, o);

        exList = new ArrayList<>(Arrays.asList(
                new Experience(
                        "Coursera",
                        "https://www.coursera.org/course/progfun",
                        new Experience.Position(
                                "\"Functional Programming Principles in Scala\" by Martin Odersky",
                                DateUtil.of(2013, Month.MARCH),
                                DateUtil.of(2013, Month.MAY)
                        )
                ),
                new Experience(
                        "Luxoft",
                        "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                        new Experience.Position(
                                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"",
                                DateUtil.of(2011, Month.MARCH),
                                DateUtil.of(2011, Month.APRIL)
                        )
                )
        ));
        o = new Organization(exList);
        resume.setSection(SectionType.EDUCATION, o);

        for (Map.Entry<SectionType, AbstractSection<?>> entry : resume.getSections().entrySet()) {
            System.out.println(entry.getKey().getTitle() + ": " + entry.getValue());
        }

        for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
            System.out.println(entry.getKey().getTitle() + ": " + entry.getValue());
        }
    }
}