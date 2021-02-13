<%--suppress HtmlUnknownTarget --%>
<%@ page import="ru.javawebinar.basejava.model.StringSection" %>
<%@ page import="ru.javawebinar.basejava.model.ListSection" %>
<%@ page import="ru.javawebinar.basejava.model.Organization" %>
<%@ page import="ru.javawebinar.basejava.util.HtmlUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html" charset="utf-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2 class="h2-operation">Просмотр резюме</h2>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png" alt="Edit"/></a>
    </h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<ru.javawebinar.basejava.model.ContactType, java.lang.String>"/>
            ${contactEntry.key.toHtml(contactEntry.value)}<br/>
        </c:forEach>
    </p>
    <hr/>
    <c:forEach var="sectionEntry" items="${resume.sections}">
        <jsp:useBean id="sectionEntry"
                     type="java.util.Map.Entry<ru.javawebinar.basejava.model.SectionType, ru.javawebinar.basejava.model.AbstractSection>"/>
        <h2>${sectionEntry.key.title}</h2>
        <c:choose>
            <c:when test="${sectionEntry.key == 'PERSONAL' || sectionEntry.key == 'OBJECTIVE'}">
                <%--suppress HtmlUnknownAttribute --%>
                <span class="section_${sectionEntry.key.name()}"><%=((StringSection) sectionEntry.getValue()).getValue()%></span>
            </c:when>
            <c:when test="${sectionEntry.key == 'ACHIEVEMENT' || sectionEntry.key == 'QUALIFICATIONS'}">
                <ul>
                    <c:forEach var="item" items="<%=((ListSection) sectionEntry.getValue()).getElements()%>">
                        <li>${item}</li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:when test="${sectionEntry.key == 'EXPERIENCE' || sectionEntry.key == 'EDUCATION'}">
                <c:forEach var="item" items="<%=((Organization) sectionEntry.getValue()).getElements()%>">
                    <jsp:useBean id="item" type="ru.javawebinar.basejava.model.Experience"/>
                    <div class="organization">
                        <h3>${HtmlUtil.buildHtmlLink(item.homePage)}</h3>
                        <c:forEach var="position" items="${item.positions}">
                            <jsp:useBean id="position" type="ru.javawebinar.basejava.model.Experience.Position"/>
                            <table>
                                <tr>
                                    <td class="date-period">${position.datePeriod}</td>
                                    <td>
                                        <b>${position.title}</b>
                                        <c:if test="${sectionEntry.key == 'EXPERIENCE'}">
                                            <br/>${position.description}
                                        </c:if>
                                    </td>
                                </tr>
                            </table>
                        </c:forEach>
                    </div>
                </c:forEach>
            </c:when>
        </c:choose>
    </c:forEach>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
