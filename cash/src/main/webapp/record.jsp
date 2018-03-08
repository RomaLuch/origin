<%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 08.03.2018
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>

<html>
<head>
    <title>Record</title>
</head>

<form method="post" action = 'records' name="record">
    <c:if test="${record.getId()!=null}">
    <input type="text" readonly="readonly" name="id" value="${record.getId()}">
    </c:if>
    <br>
<label>Дата</label>
<input type="datetime-local" name="date" value="${record.getId()!=null?f:formatLocalDateTime(record.getDateTime(), "yyyy-MM-dd'T'HH:mm"):""}">
    <br>
<label>Описание</label>
<input type="text" name="description" value=""${record.getId()!=null?record.getDescription():"description"}">
    <br>
<label>Сумма</label>
<input type="text" name="amount" value=""${record.getId()!=null?record.getAmount():0}">
    <br>
    <br>
    <input type="submit" value="${record.getId()!=null?"Изменить":"Создать"}">
</form>
</body>
</html>
