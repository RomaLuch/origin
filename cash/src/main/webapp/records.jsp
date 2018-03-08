<%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 08.03.2018
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <td>${record.getDateTime()}</td>
            <td>${record.getDescription()}</td>
            <td>${record.getAmount()}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
