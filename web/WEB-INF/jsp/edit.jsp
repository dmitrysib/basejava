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
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd>
                <%--suppress HtmlFormInputWithoutLabel --%>
                <input type="text" name="fullName" size="50" value="${resume.fullName}"></dd>
        </dl>
        <hr/>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd>
                        <%--suppress HtmlFormInputWithoutLabel --%>
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
<%--suppress HtmlFormInputWithoutLabel --%>
                        <input type="text" name="${section.name()}" size="100" value="<%=((StringSection) resume.getSections().get(section)).getValue()%>">
                    </c:when>
                    <c:when test="${section.equals(SectionType.ACHIEVEMENT) || section.equals(SectionType.QUALIFICATIONS)}">
                        <%--suppress HtmlFormInputWithoutLabel --%>
                        <textarea cols="5" class="list-block" name="<%=section.name()%>"><%=String.join("\n", ((ListSection) resume.getSections().get(section)).getElements())%></textarea>
                    </c:when>
                </c:choose>
            </dl>

        </c:forEach>
        <hr/>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
