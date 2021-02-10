package ru.javawebinar.basejava.web;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Storage;

import java.io.IOException;
import java.util.List;

public class ResumeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html; charset=UTF-8");

        Storage storage = Config.getInstance().getSqlStorage();
        String uuid = request.getParameter("uuid");
        List<Resume> resumes = uuid == null ? storage.getAllSorted() : List.of(storage.get(uuid));
        response.getWriter().write("<table>");
        for (Resume resume : resumes) {
            response.getWriter().write("<tr><td>" + resume.getUuid() + "</td><td>" + resume.getFullName() + "</td></tr>");
        }
        response.getWriter().write("</table>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    }
}
