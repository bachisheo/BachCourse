<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
       СВЕДЕНИЯ О КОНТРАГЕНТЕ
    </div>

    <%--@elvariable id="contractor" type="ru.bach.bank_api.model.WebContractor"--%>
    <form:form  action="show" modelAttribute="contractor">
        <table>
                <tr>
                    <td>Наименование</td>
                    <td>${contractor.getNomination()}</td>
                 </tr>
            <tr>
                <td>ИНН</td>
                <td>${contractor.getInn()}</td>
            </tr>
            <tr>
                <td>КПП</td>
                <td>${contractor.getKpp()}</td>
            </tr>
             <tr>
                 <td>БИК банка</td>
                <td>${contractor.getBic()}</td>
             </tr>
            <tr>
                <td>Номер счета</td>
                <td>${contractor.getAccountNumber()}</td>
            </tr>
                              </table>
        <button type="button" onclick="location.href='/edit/${contractor.getNomination()}'">Изменить</button>
        <button type="button" onclick="location.href='/delete/${contractor.getNomination()}'">Удалить</button>
        <button type="button" onclick="location.href='/home'">Вернуться</button>
    </form:form>
</div>

</body>
</html>