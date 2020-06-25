<%--
  Created by IntelliJ IDEA.
  User: Roman
  Date: 10-Jan-20
  Time: 18:03
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
            <form action="${pageContext.request.contextPath}/" method="post" id="edit-form" class="validate">
                <input type="hidden" name="id" value="${param.id}">
                <input type="hidden" name="command" value="update_program">
                <div>
                    <div class="program-name-wrapper">
                        <h2 class="program-name">
                            <fmt:message key="program.label.program"/>: <c:out value="${requestScope.program.name}"/>
                        </h2>
                    </div>
                    <c:if test="${sessionScope.user.role == 'TRAINER'}">
                        <p class="field-description service"><fmt:message key="program.label.calories"/></p>
                        <input type="number" name="calories" class="service calories input-field"
                               value="${empty requestScope.program.calories ? '' : requestScope.program.calories}">
                        <c:if test="${requestScope.program.paymentTime eq null}">
                            <p class="field-description service"><fmt:message key="program.label.duration"/></p>
                            <input type="number" name="duration" class="service duration input-field"
                                   value="${empty requestScope.program.duration ? '' : requestScope.program.duration}">
                            <p class="field-description service"><fmt:message key="program.label.cost"/></p>
                            <input type="number" name="cost" class="service cost input-field"
                                   value="${empty requestScope.program.cost ? '' : requestScope.program.cost}">
                        </c:if>
                    </c:if>
                </div>
                <div id="exercises">
                    <h2 class="exercise-list-name">
                        <fmt:message key="program.label.exeriselist"/>:
                    </h2>
                    <c:forEach items="${requestScope.program.exercises}" var="exercise" varStatus="loop">
                        <div id="exercise[${loop.count}]" class="exercise-input">
                            <input type="hidden" name="exerciseId[${loop.count}]" value="${exercise.id}">
                            <div class="exercise-separator"></div>
                            <p class="field-description service"><fmt:message key="program.exercise.label.description"/></p>
                            <input type="text" name="description[${loop.count}]" class="service description input-field"
                                   value="${exercise.description ? '' : exercise.description}">
                            <p class="field-description service"><fmt:message key="program.exercise.label.approaches"/></p>
                            <input type="number" name="approaches[${loop.count}]" class="service approaches input-field"
                                   value="${empty exercise.approaches ? '' : exercise.approaches}">
                            <p class="field-description service"><fmt:message key="program.exercise.label.repetitions"/></p>
                            <input type="number" name="repetitions[${loop.count}]" class="service repetitions input-field"
                                   value="${empty exercise.repetitions ? '' : exercise.repetitions}">
                            <p class="field-description service"><fmt:message key="program.exercise.label.day"/></p>
                            <input type="number" name="day[${loop.count}]" class="service day input-field"
                                   value="${empty exercise.day ? '' : exercise.day}">
                        </div>
                    </c:forEach>
                </div>
                <div>
                    <button type="button" id="add" class="service"><fmt:message key="program.exercise.button.newexercise"/></button>
                    <button type="button" id="delete" class="service"><fmt:message key="program.exercise.button.deleteexercise"/></button>
                </div>
                <div>
                    <button class="service"><fmt:message key="menu.button.save"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/addExerciseForm.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $.validator.addClassRules({
            calories: {
                sRequired: true,
                sDigits: true,
                sRange: [1, 20000]
            },
            duration: {
                sRequired: true,
                sDigits: true,
                sRange: [1, 500]
            },
            cost: {
                sRequired: true,
                sDigits: true,
                sRange: [1, 10000]
            },
            description: {
                sRequired: true,
                sRangeLength: [2, 255]
            },
            approaches: {
                sRequired: true,
                sDigits: true,
                sRange: [1, 20]
            },
            repetitions: {
                sRequired: true,
                sDigits: true,
                sRange: [1, 500]
            },
            day: {
                sRequired: true,
                sDigits: true,
                sRange: [1, 7]
            }
        });
        $(".validate").validate({
            errorElement:'div'
        });
        $.validator.addMethod("sRangeLength", $.validator.methods.rangelength, "<fmt:message key="validator.message.invalidlength"/>");
        $.validator.addMethod("sRange", $.validator.methods.range, "<fmt:message key="validator.message.invalidrange"/>");
        $.validator.addMethod('sRequired', $.validator.methods.required, "<fmt:message key="validator.message.required"/>");
        $.validator.addMethod("sDigits", $.validator.methods.digits, "<fmt:message key="validator.message.digits"/>");
    });
</script>
</body>
</html>