<%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 08.03.2018
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<html>
<head>
    <title>Cash Records</title>
</head>
<body>
<p>Cash Records</p>

<table border="_1">
    <caption>Все записи</caption>
    <th>Дата</th>
    <th>Описание</th>
    <th>Сумма</th>
    <%--<th colspan="2">Редактирование</th>--%>


    <c:forEach items="${records}" var="record">
        <tr>
            <td>${f:formatLocalDateTime(record.getDateTime(), 'dd.MM.yyyy HH.mm')}</td>
            <td>${record.getDescription()}</td>
            <td>${record.getAmount()}</td>
            <td><a href="records?action=update&id=${record.getId()}">update</a></td>
            <td><a href="records?action=delete&id=${record.getId()}">delete</a></td>
        </tr>
    </c:forEach>
</table>

<a href="records?action=create">
    <button>Добавить еду</button>
</a>
</body>
</html>
