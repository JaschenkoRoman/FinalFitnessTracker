<%--
  Created by IntelliJ IDEA.
  User: Roman
  Date: 10-Feb-20
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <div class="edit-panel content-panel pane">
            <div class="exercise-list-name"><fmt:message key="program.label.programupdatedsuccess"/></div>
            <form action="${pageContext.request.contextPath}/" method="post">
                <input type="hidden" name="command" value="show_all_programs_with_exercises">
                <button class="service"><fmt:message key="menu.button.backtoprograms"/></button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>
