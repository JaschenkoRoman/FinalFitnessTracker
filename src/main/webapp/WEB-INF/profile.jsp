<%--
  Created by IntelliJ IDEA.
  User: Roman
  Date: 05-Apr-19
  Time: 15:54
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
            <div class="pane">
                <div class="login-wrapper">
                    <h2 class="user-login">
                        <c:out value="${sessionScope.user.login}" />
                    </h2>
                </div>
                <p>
                    <fmt:message key="user.label.name"/>: <c:out value="${sessionScope.user.name}"/>
                </p>
                <p>
                    <fmt:message key="user.label.surname"/>: <c:out value="${sessionScope.user.surName}"/>
                </p>
                <p>
                    <fmt:message key="user.label.birthdate"/>: <ttg:datestamp date="${sessionScope.user.birthDate}"/>
                </p>
                <p>
                    <fmt:message key="user.label.role"/>: <fmt:message key="user.label.role.${fn:toLowerCase(sessionScope.user.role)}"/>
                </p>
            </div>
        </div>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>

