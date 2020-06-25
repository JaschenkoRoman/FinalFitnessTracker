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
    <div class="log-in-wrapper pane-wrapper">
        <div class="log-in-pane pane">
            <form method="post">
                <input type="hidden" value="login" name="command"/>
                <p class="service"><fmt:message key="login.label.login"/></p>
                <input class="service" placeholder="<fmt:message key="login.placeholder.login"/>" type="text" name="login"/>
                <p class="service"><fmt:message key="login.label.password"/></p>
                <input class="service" placeholder="<fmt:message key="login.placeholder.password"/>" type="password" name="password"/>
                <c:if test="${not empty requestScope.error}"><%--or param.--%>
                    <div class="service error">
                        <fmt:message key="login.label.error"/>
                    </div>
                </c:if>
                <button class="service"><fmt:message key="login.button.login"/></button>
             </form>
        </div>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>
