<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:url var="addUrl" value="/registration/submit" />
<form:form method="POST" action="${addUrl}"
	commandName="registrationBean">
	
	
	<c:if test="${success == null}">
	<div class="control-group">
			<form:label path="username" class="control-label">
				<spring:message code="username" />
			</form:label>
			<div class="controls">
				<form:input path="username" />
				<form:errors path="username" class="error"></form:errors>
			</div>
		</div>
		<div class="control-group">
			<form:label path="emailAddress" class="control-label">
				<spring:message code="emailAddress" />
			</form:label>
			<div class="controls">
				<form:input  path="emailAddress" />
				<form:errors path="emailAddress" class="error"></form:errors>
			</div>
		</div>
	
			<div class="control-group">
			<input type="submit" value="register" name="action"
				class="btn btn-default btn-xs" /> 
		</div>
		</c:if>
		<p class="text-success">
		<c:if test="${success == 'true'}">
		
		<spring:message code="registrationSuccessfull"/>
		</c:if>
		</p>
		
</form:form>