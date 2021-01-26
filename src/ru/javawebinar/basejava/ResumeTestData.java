package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("John Wick");

        resume.setContacts(Map.of(
                ContactType.EMAIL, "gkislin@yandex.ru",
                ContactType.PHONE, "+7(921) 855-0482",
                ContactType.SKYPE, "grigory.kislin"
        ));

        try {
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

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            List<Experience> exList = new ArrayList<>(Arrays.asList(
                    new Experience(
                            new Link("http://javaops.ru/", "Java Online Projects"),
                            "Автор проекта.",
                            LocalDate.parse("01/10/2013", formatter),
                            LocalDate.now(),
                            "Создание, организация и проведение Java онлайн проектов и стажировок."
                    ),
                    new Experience(
                            new Link("https://www.wrike.com/", "Wrike"),
                            "Старший разработчик (backend)",
                            LocalDate.parse("01/10/2014", formatter),
                            LocalDate.parse("01/01/2016", formatter),
                            "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."
                    )
            ));
            Organization o = new Organization(exList);
            resume.setSection(SectionType.EXPERIENCE, o);

            exList = new ArrayList<>(Arrays.asList(
                    new Experience(
                            new Link("https://www.coursera.org/course/progfun", "Coursera"),
                            "\"Functional Programming Principles in Scala\" by Martin Odersky",
                            LocalDate.parse("01/03/2013", formatter),
                            LocalDate.parse("01/05/2013", formatter)
                    ),
                    new Experience(
                            new Link("http://www.luxoft-training.ru/training/catalog/course.html?ID=22366", "Luxoft"),
                            "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"",
                            LocalDate.parse("01/03/2011", formatter),
                            LocalDate.parse("01/04/2011", formatter)
                    )
            ));
            o = new Organization(exList);
            resume.setSection(SectionType.EDUCATION, o);
        } catch (MalformedURLException ignored) {}

        for (Map.Entry<SectionType, AbstractSection<?>> entry : resume.getSections().entrySet()) {
            System.out.println(entry.getKey().getTitle() + ": " + entry.getValue());
        }

        for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
            System.out.println(entry.getKey().getTitle() + ": " + entry.getValue());
        }
    }
}