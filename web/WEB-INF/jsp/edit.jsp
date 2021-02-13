<%--suppress HtmlFormInputWithoutLabel --%>
<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page import="ru.javawebinar.basejava.model.StringSection" %>
<%@ page import="ru.javawebinar.basejava.model.ListSection" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html" charset="utf-8">
    <%--suppress HtmlUnknownTarget --%>
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>${resume.uuid.length() == 0? "Новое" : "Редактирование"} Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2 class="h2-operation">${resume.uuid.length() == 0 ? "Новое" : "Редактирование"}
        резюме</h2>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd>
                <input type="text" name="fullName" size="50" value="${resume.fullName}" required></dd>
        </dl>
        <hr/>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd>
                    <input type="text" name="${type.name()}" size="30" value="${resume.getContact(type)}">
                </dd>
            </dl>
        </c:forEach>
        <hr/>
        <h3>Секции:</h3>
        <c:forEach var="section" items="<%=SectionType.values()%>">
            <jsp:useBean id="section" type="ru.javawebinar.basejava.model.SectionType"/>
            <dl>
                <dt>${section.title}</dt>
                <c:choose>
                    <c:when test="${section.equals(SectionType.OBJECTIVE) || section.equals(SectionType.PERSONAL)}">
                        <%
                            StringSection stringSection = (StringSection) resume.getSection(section);
                        %>
                        <input type="text" name="${section.name()}" size="100"
                               value="<% out.print(stringSection == null? "" :stringSection.getValue()); %>">
                    </c:when>
                    <c:when test="${section.equals(SectionType.ACHIEVEMENT) || section.equals(SectionType.QUALIFICATIONS)}">
                        <%
                            ListSection listSection = (ListSection) resume.getSection(section);
                            String textArea = listSection == null ? "" : String.join("\n", listSection.getElements());
                        %>
                        <textarea cols="10" class="list-block" name="<%=section.name()%>"><%=textArea%></textarea>
                    </c:when>
                </c:choose>
            </dl>

        </c:forEach>
        <hr/>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()" type="reset">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
