<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="bean" type="ru.levelp.example.web.EventsListBean" scope="request"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Привет, ${bean.userAgent}</h1>

<c:choose>
    <c:when test="${not empty bean.events}">
        <table>
            <tbody>
            <c:forEach items="${bean.events}" var="event">
                <tr>
                    <td><c:out value="${event.title}" escapeXml="true"/></td>
                    <td><c:out value="${event.description}" escapeXml="false"/></td>
                    <td>${event.start}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <p>Нет событий.</p>
    </c:otherwise>
</c:choose>

<p>
    <a href="/events/add">Добавить событие</a>
</p>

</body>
</html>
