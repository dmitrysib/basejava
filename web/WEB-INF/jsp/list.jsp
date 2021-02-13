<%--suppress HtmlUnknownTarget --%>
<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html" charset="utf-8">
    <%--suppress HtmlUnknownTarget --%>
    <link rel="stylesheet" href="css/style.css">
    <title>Список резюме</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <table class="table-list">
        <tr>
            <td colspan="4" style="text-align: right;"><a href="resume?action=new" class="add-link"><img
                    src="img/add.png" alt="Добавить резюме"/>&nbsp;Добавить резюме</a></td>
        </tr>
        <tr>
            <th class="full-name-field">Имя</th>
            <th colspan="3">Email</th>
        </tr>
        <jsp:useBean id="resumes" scope="request" type="java.util.List"/>
        <c:forEach items="${resumes}" var="resume">
            <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume"/>
            <tr>
                <td class="full-name-field"><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>
                <td>${ContactType.EMAIL.toHtml(resume.getContact(ContactType.EMAIL))}</td>
                <td class="td-control"><a
                        href="resume?uuid=${resume.uuid}&action=delete"><img
                        src="img/delete.png" alt="Delete"/></a></td>
                <td class="td-control"><a
                        href="resume?uuid=${resume.uuid}&action=edit"><img
                        src="img/pencil.png" alt="Edit"/></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
