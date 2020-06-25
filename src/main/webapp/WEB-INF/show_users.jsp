<%--
  Created by IntelliJ IDEA.
  User: Roman
  Date: 14-Feb-20
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ttg" uri="datetimetags" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Fitness Tracker</title>
    <link href="${pageContext.request.contextPath}/web/css/fitness.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="${pageContext.request.contextPath}/web/images/bodybuilding_icon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/web/images/bodybuilding_icon.ico" type="image/x-icon">
    <meta charset="UTF-8"/>
    <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'/>
</head>
<body>
<jsp:include page="templates/header.jsp"/>
<div class="wrapper">
    <div class="pane-wrapper">
        <jsp:include page="templates/left_panel.jsp"/>
        <div class="content-panel pane">
            <div class="new-program-name">
                <form action="${pageContext.request.contextPath}/" method="GET">
                    <input type="hidden" name="command" value="new_user_form">
                    <button class="service"><fmt:message key="user.button.createuser"/></button>
                </form>
            </div>
            <div class="pane">
                <table class="user-table">
                    <tr>
                        <th><fmt:message key="user.label.name"/></th>
                        <th><fmt:message key="user.label.surname"/></th>
                        <th><fmt:message key="user.label.login"/></th>
                        <th><fmt:message key="user.label.birthdate"/></th>
                        <th><fmt:message key="user.label.role"/></th>
                        <th><fmt:message key="user.label.edit"/></th>
                        <th><fmt:message key="user.label.delete"/></th>
                    </tr>
                    <c:forEach items="${requestScope.users}" var="user">
                        <tr>
                            <td>${user.name}</td>
                            <td>${user.surName}</td>
                            <td>${user.login}</td>
                            <td><ttg:datestamp date="${user.birthDate}"/></td>
                            <td><fmt:message key="user.label.role.${fn:toLowerCase(user.role)}"/></td>
                            <td>
                                <form action="${pageContext.request.contextPath}/" method="GET">
                                    <input type="hidden" name="command" value="edit_user_form">
                                    <input type="hidden" name="id" value="${user.id}">
                                    <button class="service"><fmt:message key="user.button.edit"/></button>
                                </form>
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/" method="GET">
                                    <input type="hidden" name="command" value="delete_user">
                                    <input type="hidden" name="id" value="${user.id}">
                                    <button class="service delete"><fmt:message key="user.button.delete"/></button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $(function() {
            $('.delete').click(function() {
                return window.confirm("<fmt:message key="message.confirm"/>");
            });
        });
    });
</script>
</body>
</html>
