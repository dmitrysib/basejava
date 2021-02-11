package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Storage;
import ru.javawebinar.basejava.util.HtmlUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {
    private final Storage storage = Config.getInstance().getSqlStorage();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html; charset=UTF-8");

        response.getWriter().write("""
                <html>
                    <head>
                        <meta http-equiv="Content-type" content="text/html" charset="utf-8">
                        <link rel="stylesheet" href="css/style.css">
                        <title>Список резюме</title>
                    </head>
                    <body>
                        <section>
                            <table border="1" cellpadding="1" cellspacing="0">
                                <tr>
                                    <td>Имя</td>
                                    <td>Email</td>
                                </tr>
                                """);

        for (Resume resume : storage.getAllSorted()) {
            response.getWriter().write(
                    "<tr>\n" +
                            "<td><a href=\"resume?uuid=" + resume.getUuid() + "\">" + resume.getFullName() + "</td>\n" +
                            "<td>" + HtmlUtil.getContact(resume, ContactType.EMAIL) + "</td>\n" +
                            "</tr>\n");
        }

        response.getWriter().write("""
                            </table>
                        </section>
                    </body>
                </html>
                                """);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}