<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:url var="addUrl" value="/admin/profile/submit" />
<div class="container">
	<div class="row">
		<div class=" col-md-12">

			<form:form method="POST" action="${addUrl}" commandName="profile"
				class="form-horizontal">
				
				
				<div class="control-group">
					<input type="submit" value="Save" name="action"
						class="btn btn-default btn-xs" /> <input type="Submit"
						value="Cancel" name="action" class="btn btn-default btn-xs" />
				</div>
				<p>
				<div class="control-group">
					<form:label path="username" class="control-label">
						<spring:message code="username" />
					</form:label>
					<div class="controls">
						<form:input path="username" />
					</div>
				</div>
				<div class="control-group">
					<form:label path="password" class="control-label">
						<spring:message code="password" />
					</form:label>
					<div class="controls">
						<form:password path="password" />
						<form:errors path="password" class="error"></form:errors>
					</div>
				</div>
				<div class="control-group">
					<form:label path="title" class="control-label">
						<spring:message code="title" />
					</form:label>
					<div class="controls">
						<form:input path="title" />
					</div>
				</div>

				<div class="control-group">
					<form:label path="firstName" class="control-label">
						<spring:message code="firstName" />
					</form:label>
					<div class="controls">
						<form:input path="firstName" />
					</div>
				</div>
				<div class="control-group">
					<form:label path="lastName" class="control-label">
						<spring:message code="lastName" />
					</form:label>
					<div class="controls">
						<form:input path="lastName" />
					</div>
				</div>
				<div class="control-group">
					<form:label path="emailAddress" class="control-label">
						<spring:message code="emailAddress" />
					</form:label>
					<div class="controls">
						<form:input path="emailAddress" />
					</div>
				</div>
				<div class="control-group">
					<form:label path="emcFunction" class="control-label">
						<spring:message code="emcFunction" />
					</form:label>
					<div class="controls">
						<form:input path="emcFunction" />
					</div>
				</div>
				<div class="control-group">
					<form:label path="emcProfileUrl" class="control-label">
						<spring:message code="emcProfileUrl" />
					</form:label>
					<div class="controls">
						<form:input path="emcProfileUrl" />
					</div>
				</div>
				<div class="control-group">
					<form:label path="aboutMe" class="control-label">
						<spring:message code="aboutMe" />
					</form:label>
					<div class="controls">
						<form:textarea path="aboutMe" />
					</div>
				</div>
				<div class="control-group">
					<form:label path="accountNonExpired" class="checkbox">
						<spring:message code="non_expired" />
						<form:checkbox path="accountNonExpired" />
					</form:label>
					<form:label path="accountNonLocked" class="checkbox">
						<spring:message code="non_locked" />
						<form:checkbox path="accountNonLocked" />
					</form:label>
					<form:label path="credentialsNonExpired" class="checkbox">
						<spring:message code="credentials_non_expired" />
						<form:checkbox path="credentialsNonExpired" />
					</form:label>
					<form:label path="enabled" class="checkbox">
						<spring:message code="enabled" />
						<form:checkbox path="enabled" />
					</form:label>
				</div>
				<div class="control-group">
					<form:label path="authorityValues" for="authorities">
						<spring:message code="authorities" />
					</form:label>
					<form:select class="form-control" id="authorities"
						path="authorityValues" multiple="true" style="height:250px">
						<form:options items="${authorityList}" />
					</form:select>
				</div>

			</form:form>
		</div>
	</div>
</div>