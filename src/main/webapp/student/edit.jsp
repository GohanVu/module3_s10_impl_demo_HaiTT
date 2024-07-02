<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/1/2024
  Time: 8:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Student</title>
</head>
<body>
<h1>Edit Student</h1>
<p>
    <c:if test='${requestScope["message"]!= null}'>
    <span class="message">${requestScope["message"]}</span>
    </c:if>
</p>
<p>
    <a href="/student">Back to student list</a>
</p>
<form method="post">
    <fieldset>
        <legend>Student information</legend>
        <table>
            <tr>
                <td>Name: </td>
                <td>
                    <input type="text" name="name" id="name" value="${student.name}">
                    <input type="hidden" name="id" id="id" value="${student.id}">
                </td>
            </tr>
            <tr>
                <td>Address: </td>
                <td>
                    <input type="text" name="address" id="address" value="${student.address}">
                </td>
            </tr>
            <tr>
                <td>Mark :</td>
                <td>
                    <input type="text" name="point" id="point" value="${student.point}">
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <input type="submit" value="Edit student">
                </td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
