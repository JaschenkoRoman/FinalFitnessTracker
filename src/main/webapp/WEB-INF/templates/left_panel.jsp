<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>

<div class="left-menu pane">
    <span><fmt:message key="menu.label.hello"/>, ${sessionScope.user.login}!</span>
    <ul type="none">
        <li><a href="${pageContext.request.contextPath}"><fmt:message key="menu.label.myprofile"/></a></li>
        <c:if test="${sessionScope.user.role == 'TRAINER'}">
            <li><a href="${pageContext.request.contextPath}?command=show_all_programs_with_exercises">
                <fmt:message key="menu.label.programs"/></a></li>
        </c:if>
        <c:if test="${sessionScope.user.role == 'CLIENT'}">
            <li><a href="${pageContext.request.contextPath}?command=show_all_programs_with_exercises">
                <fmt:message key="menu.label.training"/></a></li>
        </c:if>
        <c:if test="${sessionScope.user.role == 'ADMIN'}">
            <li><a href="${pageContext.request.contextPath}?command=show_users">
                <fmt:message key="menu.label.users"/>
            </a></li>
        </c:if>
        <li><a href="${pageContext.request.contextPath}?command=show_payments">
            <fmt:message key="menu.label.payments"/>
        </a></li>
    </ul>
    <form action="${pageContext.request.contextPath}/" method="post">
        <input type="hidden" name="command" value="logout">
        <button class="service log-out"><fmt:message key="menu.label.logout"/></button>
    </form>
</div>