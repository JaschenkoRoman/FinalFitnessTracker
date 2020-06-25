<%--
  Created by IntelliJ IDEA.
  User: Roman
  Date: 08-Jan-20
  Time: 15:06
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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'/>
</head>
<body>
<jsp:include page="templates/header.jsp"/>
<div class="wrapper">
    <div class="pane-wrapper">
        <jsp:include page="templates/left_panel.jsp"/>
        <div class="content-panel pane">
            <c:if test="${sessionScope.user.role == 'TRAINER'}">
                <div class="new-program-name">
                    <form action="${pageContext.request.contextPath}/" method="GET">
                        <input type="hidden" name="command" value="new_program_form">
                        <button class="service"><fmt:message key="program.button.createprogram"/></button>
                    </form>
                </div>
            </c:if>
            <div class="pane">
                <c:forEach items="${requestScope.programs}" var="program">
                    <div class="program-name-wrapper">
                        <h2 class="program-name">
                            <fmt:message key="program.label.program"/>: <c:out value="${program.name}"/>
                        </h2>
                    </div>
                    <c:if test="${sessionScope.user.role == 'CLIENT'}">
                        <p>
                            <fmt:message key="program.label.trainer"/>:
                            <c:out value="${program.trainer.name}"/>
                            <c:out value="${program.trainer.surName}"/>
                        </p>
                    </c:if>
                    <c:if test="${sessionScope.user.role == 'TRAINER'}">
                        <p>
                            <fmt:message key="program.label.client"/>:
                            <c:out value="${program.client.name}"/>
                            <c:out value="${program.client.surName}"/>
                        </p>
                    </c:if>
                    <p>
                        <fmt:message key="program.label.calories"/>:
                        <c:out value="${program.calories}"/>  <fmt:message key="program.label.cal"/>
                    </p>
                    <p>
                        <fmt:message key="program.label.duration"/>:
                        <c:out value="${program.duration}"/>  <fmt:message key="program.label.days"/>
                    </p>
                    <p>
                        <fmt:message key="program.label.cost"/>:
                        <c:out value="${program.cost}"/> $
                    </p>
                    <p>
                        <fmt:message key="program.label.programcreated"/>:
                        <%--<c:out value="${program.creationTime}"/>--%>
                        <ttg:datetimestamp date="${program.creationTime}"/>
                    </p>
                    <p><fmt:message key="program.label.paymentinfo"/>:
                        <c:choose>
                            <c:when test="${program.paymentTime eq null}">
                                <fmt:message key="program.label.paymentinfo.unpaid"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="program.label.paymentinfo.paid"/>
                                <ttg:datetimestamp date="${program.paymentTime}"/>
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <div class="program-panel">
                        <c:if test="${sessionScope.user.role != 'CLIENT' && program.paymentTime eq null}">
                            <form action="${pageContext.request.contextPath}/" method="GET">
                                <input type="hidden" name="command" value="delete_program">
                                <input type="hidden" name="id" value="${program.id}">
                                <button class="service delete"><fmt:message key="program.button.delete"/></button>
                            </form>
                        </c:if>
                        <form action="${pageContext.request.contextPath}/" method="GET">
                            <input type="hidden" name="command" value="edit_program_form">
                            <input type="hidden" name="id" value="${program.id}">
                            <button class="service"><fmt:message key="program.button.edit"/></button>
                        </form>
                        <c:if test="${sessionScope.user.role == 'CLIENT'}">
                            <form action="${pageContext.request.contextPath}/" method="GET">
                                <input type="hidden" name="command" value="pay_program">
                                <input type="hidden" name="id" value="${program.id}">
                                <c:if test="${program.paymentTime eq null}">
                                    <button class="service"><fmt:message key="program.button.pay"/></button>
                                </c:if>
                            </form>
                        </c:if>
                    </div>
                    <table class="user-table">
                        <tr>
                            <th><fmt:message key="program.exercise.label.description"/></th>
                            <th><fmt:message key="program.exercise.label.approaches"/></th>
                            <th><fmt:message key="program.exercise.label.repetitions"/></th>
                            <th><fmt:message key="program.exercise.label.day"/></th>
                        </tr>
                        <c:forEach items="${program.exercises}" var="exercise">
                            <tr>
                                <td>${exercise.description}</td>
                                <td>${exercise.approaches}</td>
                                <td>${exercise.repetitions}</td>
                                <td>${exercise.day}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:forEach>
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
