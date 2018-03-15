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

    <style>
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }

        dt {
            display: inline-block;
            width: 170px;
        }

        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>

</head>
<h2>${param.action == 'create' ? 'Create record' : 'Edit record'}</h2>
<hr>
<form method="post" action = 'records' name="record">
    <jsp:useBean id="record" scope="request" type="ru.mycash.cash.model.Record"/>
    <%--<jsp:useBean id="category" scope="request" type="ru.mycash.cash.model.Category"/>--%>

    <input type="hidden" name="id" value="${record.id}">
    <dl>
<dd>Дата</dd>
<dt><input type="datetime-local" name="date" value="${record.dateTime}"></dt>
    </dl>
    <dl>
<dd>Описание</dd>
<dt><input type="text" name="description" value="${record.description}"></dt>
    </dl>
    <dl>
        <dl>
            <dd>Категория</dd>
            <dt><select name="category_id">
                <option selected value="${record.category.id}">${record.category.name}</option>
                <c:forEach items="${categorys}" var="category">
                    <jsp:useBean id="category" scope="page" type="ru.mycash.cash.model.Category"/>
                   <c:if test="${category.id!=record.category.id}">
                       <option value="${category.id}">${category.name}</option>
                   </c:if>
                </c:forEach>
            </select></dt>
        </dl>

<dd>Сумма</dd>
<dt><input type="text" name="amount" value="${record.amount}"></dt>
    </dl>
        <hr>
    <input type="submit" value="${record.getId()!=null?"Изменить":"Создать"}">
</form>
</body>
</html>
