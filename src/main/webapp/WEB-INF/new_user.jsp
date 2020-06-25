<%--
  Created by IntelliJ IDEA.
  User: Roman
  Date: 24-Feb-20
  Time: 15:44
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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'/>
</head>
<body>
<jsp:include page="templates/header.jsp"/>
<div class="wrapper">
    <div class="pane-wrapper">
        <jsp:include page="templates/left_panel.jsp"/>
        <div class="edit-panel content-panel pane">
            <form action="${pageContext.request.contextPath}/" method="post" id="create-user-form" class="validate">
                <input type="hidden" name="command" value="create_user">
                <input type="hidden" name="id" value="${param.id}">
                <div>
                    <div class="new-program-name-wrapper">
                        <h2 class="program-name field-description service">
                            <fmt:message key="user.label.login"/>
                        </h2>
                        <input type="text" name="login" class="service login input-field"
                               value="${empty requestScope.user.login ? '' : requestScope.user.login}">
                    </div>
                    <c:if test="${not empty requestScope.error}"><%--or param.--%>
                        <div class="error">
                            <fmt:message key="user.label.login.error"/>
                        </div>
                    </c:if>
                    <div class="common-program-info">
                        <p class="field-description service"><fmt:message key="user.label.password"/></p>
                        <input type="password" id="password" name="password" class="service password input-field"
                               value="${empty requestScope.user.password ? '' : requestScope.user.password}">
                        <p class="field-description service"><fmt:message key="user.label.confirmpasword"/></p>
                        <input type="password" name="confirm_password" class="service confirm_password input-field"
                               value="${empty requestScope.user.password ? '' : requestScope.user.password}">
                        <p class="field-description service"><fmt:message key="user.label.name"/></p>
                        <input type="text" name="name" class="service name input-field"
                               value="${empty requestScope.user.name ? '' : requestScope.user.name}">
                        <p class="field-description service"><fmt:message key="user.label.surname"/></p>
                        <input type="text" name="sur_name" class="service sur_name input-field"
                               value="${empty requestScope.user.surName ? '' : requestScope.user.surName}">
                        <p class="field-description service"><fmt:message key="user.label.birthdate"/></p>
                        <input type="date" name="birth_date" class="service birth_date input-field"
                               value="${empty requestScope.user.birthDate ? '' : requestScope.user.birthDate}">
                        <p class="field-description service"><fmt:message key="user.label.role"/></p>
                        <select name="role" class="service role input-field">
                            <option selected="selected" disabled="disabled"> -- <fmt:message key="menu.label.choose"/> --</option>
                            <c:forEach items="${requestScope.roles}" var="role">
                                <option class="service" value="${role}"><fmt:message key="user.label.role.${fn:toLowerCase(role)}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div>
                    <button class="service"><fmt:message key="menu.button.create"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $.validator.addClassRules({

            login: {
                sRequired: true,
                sRangeLength: [2, 20]
            },
            password: {
                sRequired: true,
                sRangeLength: [2, 64]
            },
            confirm_password: {
                sRequired: true,
                sRangeLength: [2, 64],
                sEqualTo: "#password"
            },
            name: {
                sRequired: true,
                sRangeLength: [2, 30]
            },
            sur_name: {
                sRequired: true,
                sRangeLength: [2, 30]
            },
            birth_date: {
                sRequired: true
            },
            role: {
                sRequired: true
            }
        });

        $(".validate").validate({
            errorElement:'div'
        });
        $.validator.addMethod("sEqualTo", $.validator.methods.equalTo, "<fmt:message key="validator.message.unequalpasswords"/>");
        $.validator.addMethod("sRangeLength", $.validator.methods.rangelength, "<fmt:message key="validator.message.invalidlength"/>");
        $.validator.addMethod('sRequired', $.validator.methods.required, "<fmt:message key="validator.message.required"/>");
    });
</script>
</body>
</html>
