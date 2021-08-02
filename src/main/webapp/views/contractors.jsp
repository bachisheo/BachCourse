<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Title</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="form-style-2" align="center">
    <div class="form-style-2-heading">
        <a href="/home">
            <img src="/resources/home_logo.jpg" alt="МЕНЮ" width="50" height="50" align="left" />
        </a>
           СПИСОК КОНТРАГЕНТОВ

    </div>
    <table>
        <tr>
            <th>ID</th>
            <th>Наименование</th>
            <th>ИНН</th>
            <th>КПП</th>
            <th>БИК банка</th>
            <th>Номер счета</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${contractorsFromServer}" var="contractor">
            <tr>
                <td>${contractor.getId()}</td>
                <td><a href='/show/${contractor.getNomination()}'>${contractor.getNomination()}</a></td>
                <td>${contractor.getInn()}</td>
                <td>${contractor.getKpp()}</td>
                <td>${contractor.getBic()}</td>
                <td>${contractor.getAccountNumber()}</td>
                <td>
                    <a href='/update/${contractor.getNomination()}'>Изменить</a>
                    <a href='/delete/${contractor.getNomination()}'>Удалить</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>