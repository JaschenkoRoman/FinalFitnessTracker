<%--
  Created by IntelliJ IDEA.
  User: Roman
  Date: 29-Jan-20
  Time: 13:20
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
                <h2 class="user-login">
                    <fmt:message key="program.label.unpaidprograms"/>
                </h2>
                <table class="user-table">
                    <tr>
                        <th><fmt:message key="program.label.name"/></th>
                        <th><fmt:message key="program.label.creationtime"/></th>
                        <c:if test="${sessionScope.user.role != 'TRAINER'}">
                            <th><fmt:message key="program.label.trainername"/></th>
                        </c:if>
                        <c:if test="${sessionScope.user.role != 'CLIENT'}">
                            <th><fmt:message key="program.label.clientname"/></th>
                        </c:if>
                        <th><fmt:message key="program.label.duration"/></th>
                        <th><fmt:message key="program.label.cost"/></th>
                    </tr>
                    <c:forEach items="${requestScope.programs}" var="program">
                        <c:if test="${program.paymentTime eq null}">
                            <tr>
                                <td>${program.name}</td>
                                <td><ttg:datetimestamp date="${program.creationTime}"/></td>
                                <c:if test="${sessionScope.user.role != 'TRAINER'}">
                                    <td>${program.trainer.name} ${program.trainer.surName}</td>
                                </c:if>
                                <c:if test="${sessionScope.user.role != 'CLIENT'}">
                                    <td>${program.client.name} ${program.client.surName}</td>
                                </c:if>
                                <td>${program.duration}</td>
                                <td>${program.cost}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
                <h2 class="user-login">
                    <fmt:message key="program.label.paidprograms"/>
                </h2>
                <table class="user-table">
                    <tr>
                        <th><fmt:message key="program.label.name"/></th>
                        <th><fmt:message key="program.label.creationtime"/></th>
                        <c:if test="${sessionScope.user.role != 'TRAINER'}">
                            <th><fmt:message key="program.label.trainername"/></th>
                        </c:if>
                        <c:if test="${sessionScope.user.role != 'CLIENT'}">
                            <th><fmt:message key="program.label.clientname"/></th>
                        </c:if>
                        <th><fmt:message key="program.label.duration"/></th>
                        <th><fmt:message key="program.label.cost"/></th>
                        <th><fmt:message key="program.label.paymenttime"/></th>
                    </tr>
                    <c:forEach items="${requestScope.programs}" var="program">
                        <c:if test="${program.paymentTime ne null}">
                            <tr>
                                <td>${program.name}</td>
                                <td><ttg:datetimestamp date="${program.creationTime}"/></td>
                                <c:if test="${sessionScope.user.role != 'TRAINER'}">
                                    <td>${program.trainer.name} ${program.trainer.surName}</td>
                                </c:if>
                                <c:if test="${sessionScope.user.role != 'CLIENT'}">
                                    <td>${program.client.name} ${program.client.surName}</td>
                                </c:if>
                                <td>${program.duration}</td>
                                <td>${program.cost}</td>
                                <td><ttg:datetimestamp date="${program.paymentTime}"/></td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>
