package ru.javawebinar.basejava.web;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ResumeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");

        response.setHeader("Content-type", "text/html; charset=UTF-8");
        response.getWriter().write(name == null ? "Hello Resumes!" : "Hello " + name + '!');
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
