<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
            <img src="/resources/home_logo.jpg" alt="МЕНЮ" width="50" height="50" align="left"/>
        </a>
        ПОИСК КОНТРАГЕНТОВ
    </div>
    <%--@elvariable id="contractor" type="ru.bach.bank_api.model.WebContractor"--%>
    <form:form action="/searchByBic" modelAttribute="contractor">
        <table>
            <tr>
                <td><form:label path="bic">БИК банка</form:label></td>
                <td><form:input type="text" id="bic" path="bic" maxlength="9"/></td>
                <td><form:errors path="bic" cssClass="error"/></td
            </tr>
            <tr>
                <td><form:label path="accountNumber">Номер счета</form:label></td>
                <td><form:input type="text" id="accountNumber" path="accountNumber" maxlength="20"/></td>
                <td><form:errors path="accountNumber" cssClass="error"/></td
            </tr>
        </table>
        <button type="submit">Найти</button>
    </form:form>
    <table>

        <tr>
            <th>Наименование</th>
            <th>ИНН</th>
            <th>КПП</th>
            <th>БИК банка</th>
            <th>Номер счета</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${contractorsFromServer}" var="contractor">
            <tr>
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