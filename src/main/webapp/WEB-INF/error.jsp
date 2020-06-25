<%--
  Created by IntelliJ IDEA.
  User: Roman
  Date: 09-Jan-20
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <link href="${pageContext.request.contextPath}/web/css/fitness.css" rel="stylesheet">
    <link rel="icon" href="${pageContext.request.contextPath}/web/images/bodybuilding_icon.ico">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/web/images/bodybuilding_icon.ico">
    <meta charset="UTF-8"/>
    <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'/>
    <title>Fitness Tracker</title>
</head>
<body>
<jsp:include page="templates/header.jsp"/>
<div class="wrapper">
    <div class="pane-wrapper">
        <div class="content-panel pane">
            <div class="pane">
                <fmt:message key="message.error"/>
            </div>
        </div>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>
