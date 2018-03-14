<%--
  Created by IntelliJ IDEA.
  User: RLuchinsky
  Date: 14.03.2018
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
</body>
<form action = 'records' name="category">

<dl>
<dd><input type="hidden" name="category_id">кат:</dd>
<dt><input type="text" name="category"></dt>
<dt><input type="submit" value="+"></dt>
</dl>

</form>
</body>
</html>
