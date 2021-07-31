<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить контрагента</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-2" align="center" >
    <div class="form-style-2-heading">
        <a href="home">
            <img src="/resources/home_logo.jpg" alt="МЕНЮ" width="50" height="50" align="left" />
        </a>
        ДОБАВИТЬ КОНТРАГЕНТА

    </div>
<%--@elvariable id="webContractor" type="ru.bach.bank_api.model.WebContractor"--%>
<form:form action="add" modelAttribute="webContractor">
    <table>
        <tr>
            <td><form:label path="nomination">Наименование</form:label></td>
            <td><form:input path="nomination" maxlength="20" minlength="1"/></td>
        </tr>
        <tr>
            <td><form:label path="inn">ИНН</form:label></td>
            <td><form:input type="text" id="inn" path="inn" maxlength="12"/></td>
        </tr>
        <tr>
            <td><form:label path="kpp">КПП</form:label></td>
            <td><form:input type="text" id="kpp" path="kpp" maxlength="12"/></td>
        </tr>
        <tr>
            <td><form:label path="kpp">БИК банка</form:label></td>
            <td><form:input type="text" id="bic" path="bic" maxlength="12"/></td>
        </tr>
        <tr>
            <td><form:label path="accountNumber">Номер счета</form:label></td>
            <td><form:input type="text" id="accountNumber" path="accountNumber" maxlength="12"/></td>
        </tr>
    </table>
            <button type="submit">Сохранить</button>
            <button type="button" onclick="location.href='/contractors'">Отмена</button>
</form:form>
</div>
</body>
</html>