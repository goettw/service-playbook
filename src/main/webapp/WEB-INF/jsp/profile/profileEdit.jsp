<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<form:form method="POST" action="${addUrl}" commandName="profile"
	class="form-horizontal">
	<div class="container">
		<div class="row">
			<div class=" col-md-12">
				<div class="control-group">
					<input type="submit" value="Save" name="action"
						class="btn btn-default btn-xs" /> <input type="Submit"
						value="Cancel" name="action" class="btn btn-default btn-xs" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class=" col-md-4">
				<div class="control-group">
					<form:label path="username" class="control-label">
						<spring:message code="username" />
					</form:label>
					<div class="controls">
						<form:input path="username" style="width:100%" readonly="true"/>
						<form:errors path="username" class="error"></form:errors>
					</div>
				</div>
			</div>
			<div class=" col-md-4">
				<div class="control-group">
					<form:label path="password" class="control-label">
						<spring:message code="password" />
					</form:label>
					<div class="controls">
						<form:password path="password" style="width:100%" />
						<form:errors path="password" class="error"></form:errors>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class=" col-md-4">
				<div class="control-group">
					<form:label path="title" class="control-label">
						<spring:message code="title" />
					</form:label>
					<div class="controls">
						<form:input path="title" style="width:100%" />
					</div>
				</div>
			</div>
			<div class=" col-md-4">
				<div class="control-group">
					<form:label path="firstName" class="control-label">
						<spring:message code="firstName" />
					</form:label>
					<div class="controls">
						<form:input path="firstName" style="width:100%" />
						<form:errors path="firstName" class="error"></form:errors>
					</div>
				</div>
			</div>
			<div class=" col-md-4">
				<div class="control-group">
					<form:label path="lastName" class="control-label">
						<spring:message code="lastName" />
					</form:label>
					<div class="controls">
						<form:input path="lastName" style="width:100%" />
						<form:errors path="lastName" class="error"></form:errors>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class=" col-md-4">
				<div class="control-group">
					<form:label path="emailAddress" class="control-label">
						<spring:message code="emailAddress" />
					</form:label>
					<div class="controls">
						<form:input path="emailAddress" style="width:100%" />
					</div>
				</div>
			</div>
			<div class=" col-md-4">
				<div class="control-group">
					<form:label path="emcFunction" class="control-label">
						<spring:message code="emcFunction" />
					</form:label>
					<div class="controls">
						<form:input path="emcFunction" style="width:100%" />
					</div>
				</div>
			</div>
			<div class=" col-md-4">
				<div class="control-group">
					<form:label path="emcProfileUrl" class="control-label">
						<spring:message code="emcProfileUrl" />
					</form:label>
					<div class="controls">
						<form:input path="emcProfileUrl" style="width:100%" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class=" col-md-12">
				<div class="control-group">
					<form:label path="aboutMe" class="control-label">
						<spring:message code="aboutMe" />
					</form:label>
					<div class="controls">
						<form:textarea path="aboutMe" style="width:100%;height:200px" />
					</div>
				</div>
			</div>
		</div>
		<sec:authorize access="hasAnyRole('ROLE_Administrator')">
			<div class="row">

				<div class=" col-md-4">
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
				</div>
				<div class=" col-md-4">
					<div class="control-group">
						<form:label path="authorityValues" for="authorities">
							<spring:message code="authorities" />
						</form:label>
						<form:select class="form-control" id="authorities"
							path="authorityValues" multiple="true" style="height:250px">
							<form:options items="${authorityList}" />
						</form:select>
					</div>
				</div>
			</div>
		</sec:authorize>
	</div>

</form:form>