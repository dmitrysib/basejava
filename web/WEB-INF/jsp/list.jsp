<%@ page import="ru.javawebinar.basejava.model.Resume" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html" charset="utf-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Список резюме</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <table>
        <tr>
            <th>Имя</th>
            <th>Email</th>
            <th></th>
            <th></th>
        </tr>
        <%
            for (Object resume : (List<?>) request.getAttribute("resumes")) {
        %>
        <tr>
            <td><a href="resume?uuid=<%=((Resume) resume).getUuid()%>"><%=((Resume) resume).getFullName()%>
            </a></td>
            <td><%=((Resume) resume).getContacts().get(ContactType.EMAIL)%>
            </td>
            <td></td>
            <td></td>
        </tr>
        <%
            }
        %>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
