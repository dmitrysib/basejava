package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("John Wick");

        AbstractSection<String> section;
        section = new StringSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        resume.setSections(SectionType.PERSONAL, section);

        section = new StringSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        resume.setSections(SectionType.OBJECTIVE, section);

        section = new ListSection();
        section.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        section.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django). ");
        section.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга. ");
        section.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера. ");
        section.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk. ");
        section.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        resume.setSections(SectionType.ACHIEVEMENT, section);

        section = new ListSection();
        section.add("Родной русский, английский \"upper intermediate\"");
        section.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования ");
        section.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer. ");
        section.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix, ");
        section.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT. ");
        resume.setSections(SectionType.QUALIFICATIONS, section);

        ComplexSection cmplxSection = new ComplexSection();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        ComplexElement ce = new ComplexElement("Java Online Projects",
                "http://javaops.ru/",
                "Автор проекта.",
                LocalDate.parse("01/10/2013", formatter),
                LocalDate.parse("01/01/2021", formatter),
                "Создание, организация и проведение Java онлайн проектов и стажировок."
        );
        cmplxSection.add(ce);

        ce = new ComplexElement("Wrike",
                "https://www.wrike.com/",
                "Старший разработчик (backend)",
                LocalDate.parse("01/10/2014", formatter),
                LocalDate.parse("01/01/2016", formatter),
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."
        );
        cmplxSection.add(ce);
        resume.setSections(SectionType.EXPERIENCE, cmplxSection);

    }
}