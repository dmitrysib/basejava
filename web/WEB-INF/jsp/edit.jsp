<%--suppress HtmlFormInputWithoutLabel --%>
<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page import="ru.javawebinar.basejava.model.StringSection" %>
<%@ page import="ru.javawebinar.basejava.model.ListSection" %>
<%@ page import="ru.javawebinar.basejava.model.Organization" %>
<%@ page import="ru.javawebinar.basejava.model.Experience" %>
<%@ page import="ru.javawebinar.basejava.util.DateUtil" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html" charset="utf-8">
    <%--suppress HtmlUnknownTarget --%>
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <c:set var="resumeType" value="${resume.uuid == 'new' ? 'Новое' : 'Редактирование'} резюме"/>
    <title>${resumeType} ${resume.fullName}</title>
    <%--suppress JSUnresolvedLibraryURL --%>
    <script src="//code.jquery.com/jquery.js" type="text/javascript"></script>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2 class="h2-operation">${resumeType}</h2>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd>
                <input type="text" name="fullName" size="50" value="${resume.fullName}" required></dd>
        </dl>
        <hr/>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="${ContactType.values()}">
            <dl>
                <dt>${type.title}</dt>
                <dd>
                    <input type="text" name="${type.name()}" size="30" value="${resume.getContact(type)}">
                </dd>
            </dl>
        </c:forEach>
        <hr/>
        <h3>Секции:</h3>
        <c:forEach var="section" items="${SectionType.values()}">
            <jsp:useBean id="section" type="ru.javawebinar.basejava.model.SectionType"/>
            <dl>
                <dt style="font-weight: bold;">${section.title}</dt>
                <c:choose>
                    <c:when test="${section.equals(SectionType.OBJECTIVE) || section.equals(SectionType.PERSONAL)}">
                        <% StringSection stringSection = (StringSection) resume.getSection(section); %>
                        <input type="text" name="${section.name()}" size="100"
                               value="<% out.print(stringSection == null ? "" : stringSection.getValue()); %>">
                    </c:when>
                    <c:when test="${section.equals(SectionType.ACHIEVEMENT) || section.equals(SectionType.QUALIFICATIONS)}">
                        <% ListSection listSection = (ListSection) resume.getSection(section); %>
                        <textarea cols="10" class="list-block"
                                  name="${section.name()}"><% out.print(listSection == null ? "" : String.join("\n", listSection.getElements())); %></textarea>
                    </c:when>
                    <c:when test="${section.equals(SectionType.EXPERIENCE) || section.equals(SectionType.EDUCATION)}">
                        <%
                            Organization organization = (Organization) resume.getSection(section);
                            if (organization == null) {
                                organization = new Organization(Collections.singletonList(Experience.EMPTY));
                            } else {
                                organization.getElements().add(Experience.EMPTY);
                            }
                        %>
                        <c:forEach items="<%=organization.getElements()%>" var="item" varStatus="loop">
                            <jsp:useBean id="item" type="ru.javawebinar.basejava.model.Experience"/>
                            <div class="o-section" id="experience-${section.name()}-${loop.index}">
                                <div style="text-align: right"><a href="javascript:void(0);" onclick="deleteSection('experience-${section.name()}-${loop.index}')"><%--suppress HtmlUnknownTarget --%><img src="img/delete.png" alt="Удалить блок"/></a></div>
                                <dl>
                                    <dt>Название учреждения</dt>
                                    <dd><input type="text" value="${item.homePage.title}" name="${section.name()}-title" size="100"/></dd>
                                </dl>
                                <dl>
                                    <dt>Урл учреждения</dt>
                                    <dd><input type="text" value="${item.homePage.url}" name="${section.name()}-url" size="100"/></dd>
                                </dl>
                                <c:forEach items="${item.positions}" var="position">
                                    <div class="o-position">
                                        <dl>
                                            <dt>Начальная дата</dt>
                                            <dd><input type="text" value="${DateUtil.format(position.startDateDt)}" placeholder="MM/yyyy" name="${section.name()}-startDate"/></dd>
                                        </dl>
                                        <dl>
                                            <dt>Конечная дата</dt>
                                            <dd><input type="text" value="${DateUtil.format(position.endDateDt)}" placeholder="MM/yyyy" name="${section.name()}-endDate"/></dd>
                                        </dl>
                                        <dl>
                                            <dt>Должность/Позиция</dt>
                                            <dd><input type="text" value="${position.title}" size="80" name="${section.name()}-position"/></dd>
                                        </dl>
                                        <c:if test="${section.equals(SectionType.EXPERIENCE)}">
                                        <dl>
                                            <dt>Описание</dt>
                                            <dd><textarea cols="5" style="width: 500px;" name="${section.name()}-description">${position.description}</textarea></dd>
                                        </dl>
                                        </c:if>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </dl>
        </c:forEach>
        <hr/>
        <button type="submit">${resume.uuid == "new" ? "Добавить" : "Сохранить"}</button>
        <button type="reset" onclick="window.history.back()">Отменить</button>
    </form>
</section>
<script>
    function deleteSection(sectionId) {
        $("div#" + sectionId).empty().hide();
    }
</script>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
