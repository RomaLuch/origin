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
<hr>
<form method="get" action = 'records' name="category_filter">
<dl>
    <dd>Категория</dd>
    <dt><select name="category_id">
        <%--<option selected value="${record.category.id}">${record.category.name}</option>--%>
        <c:forEach items="${categories}" var="category">
            <jsp:useBean id="category" scope="page" type="ru.mycash.cash.model.Category"/>
                <option value="${category.id}">${category.name}</option>
        </c:forEach>
    </select></dt>
</dl>
    <input type="submit">
</form>
<hr>
<table border="_1">
    <caption>Все записи</caption>
    <th>Дата</th>
    <th>Описание</th>
    <th>Категория</th>
    <th>Сумма</th>
    <th colspan="2">Редактирование</th>

    <c:forEach items="${records}" var="record">
        <jsp:useBean id="record" scope="page" type="ru.mycash.cash.model.Record"/>
        <tr>
            <td>${f:toString(record.dateTime)}</td>
            <td>${record.description}</td>
            <td hidden>${record.category.id}</td>
            <td>${record.category.name}</td>
            <td>${record.amount}</td>
            <td><a href="records?action=update&id=${record.id}">update</a></td>
            <td><a href="records?action=delete&id=${record.id}">delete</a></td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="3" align="center"><b>Сумма:</b></td>
        <td><b>${total}</b></td>
        <td colspan="2"><b></b></td>
    </tr>
</table>

<a href="records?action=create">
    <button>Добавить запись</button>
</a>
<a href="records?action=createCategory">
    <button>Добавить категорию</button>
</a>
</body>
</html>
