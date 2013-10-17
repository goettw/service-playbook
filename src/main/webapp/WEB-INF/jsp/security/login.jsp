<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url var="addUrl" value="/login" />
<div class="container">
<div class="row">
	<form:form method="POST" action="${addUrl}" commandName="profile">
		<p>
		<div class="control-group">
			<form:label path="j_username" class="control-label">
				<spring:message code="username" />
			</form:label>
			<div class="controls">
				<form:input path="j_username" />
			</div>
		</div>
		<div class="control-group">
			<form:label path="j_password" class="control-label">
				<spring:message code="password" />
			</form:label>
			<div class="controls">
				<form:password  path="j_password" />
			</div>
		</div>
		<div class="control-group">
			<input type="submit" value="Login" name="action"
				class="btn btn-default btn-xs" /> 
		</div>
	</form:form>
	</div>
	<div class="row">
	<c:if test="${param.login_error != null}">
		<spring:message code="loginFailure" /><p>
		<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
		<b><spring:message code="reason" />:</b>
		<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"></c:out>
		</c:if>
	</c:if>
</div>
</div>
