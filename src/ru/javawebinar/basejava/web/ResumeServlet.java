package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

public class ResumeServlet extends HttpServlet {
    private final Storage storage = Config.getInstance().getSqlStorage();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");

        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume resume;
        switch (action) {
            case "delete" -> {
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            }
            case "new" -> {
                uuid = "";
                resume = new Resume(uuid, "");
                action = "edit";
            }
            case "view", "edit" -> resume = storage.get(uuid);
            default -> throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        if (fullName == null || fullName.trim().length() == 0) {
            response.sendRedirect("resume");
        }

        Resume resume;
        try {
            resume = storage.get(uuid);
            resume.setFullName(fullName);
            resume.getContacts().clear();
            resume.getSections().clear();
        } catch (NotExistStorageException e) {
            uuid = "";
            resume = new Resume(UUID.randomUUID().toString(), fullName);
        }

        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                resume.addContact(type, value.trim());
            }
        }

        for (SectionType sectionType : SectionType.values()) {
            String value = request.getParameter(sectionType.name());
            if (value != null && value.trim().length() != 0) {
                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> resume.addSection(sectionType, new StringSection(value.trim()));
                    case QUALIFICATIONS, ACHIEVEMENT -> {
                        var values = Arrays.stream(
                                value.split("[\r\n]"))
                                .filter(s -> !s.isEmpty())
                                .collect(Collectors.toList());
                        resume.addSection(sectionType, new ListSection(values));
                    }
                }
            }
        }
        if (uuid.length() == 0) {
            storage.save(resume);
        } else {
            storage.update(resume);
        }
        response.sendRedirect("resume?action=view&uuid=" + resume.getUuid());
    }
}