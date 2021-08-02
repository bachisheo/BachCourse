<%--
  Created by IntelliJ IDEA.
  User: kesa
  Date: 30.07.2021
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link href="/css/styles.css" rel="stylesheet" type="text/css">
    <title>Справочник контрагентов</title>
</head>
<body>

<div class="form-style-2" align="center">


    <div class="form-style-2-heading">
        <a href="/home">
            <img src="/resources/home_logo.jpg" alt="МЕНЮ" width="50" height="50" align="left" />
        </a>
        Информационная система </br> "Справочник контрагентов"
        ${statusMessage}
    </div>
<div id="nav" class="nav">
    <a href="/contractors">Список контрагентов</a>
    <a href="/searchByName">Поиск контрагентов по наименованию</a>
    <a href="/searchByBic">Поиск контрагентов по БИК и номеру счета</a>
    <a href="/add">Добавить контрагента</a>
</div>
</div>


</body>
</html>
