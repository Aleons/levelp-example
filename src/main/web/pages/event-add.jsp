<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="formBean" scope="request" type="ru.levelp.example.web.EventAddFormBean" />
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        .error {
            color: red;
        }
    </style>
</head>
<body>
    <h1>Добавление события</h1>

    <form:form action="/events/add"
               method="post"
               enctype="application/x-www-form-urlencoded"
               modelAttribute="formBean">
        <p>
            Название: <form:input type="text" path="title" />
            <form:errors path="title" cssClass="error" />
        </p>
        <p>
            Описание: <form:input type="text" path="description" />
            <form:errors path="description" cssClass="error" />
        </p>
        <p>
            Дата начала: <form:input type="datetime-local" path="start" />
            <form:errors path="start" cssClass="error" />
        </p>
        <p>
            Дата окночания: <form:input type="datetime-local" path="end" />
            <form:errors path="end" cssClass="error" />
        </p>
        <p>
            <input type="submit" />
        </p>
    </form:form>
</body>
</html>
