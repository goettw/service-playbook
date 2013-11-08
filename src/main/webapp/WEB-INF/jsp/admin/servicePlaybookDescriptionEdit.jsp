<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="edit">
	<c:url var="addUrl" value="/admin/servicePlaybookDescription/submit" />
	<form:form method="POST" action="${addUrl}"
		commandName="servicePlaybookDescription">
		<div class="container">
			<div class="row">
				<input type="submit" value="Save" name="action"
					class="btn btn-default btn-xs" /> <input type="Submit"
					value="Cancel" name="action" class="btn btn-default btn-xs" />
			</div>
			<div class="row">

				<div class="form-group">
					<form:label path="id">_id</form:label>
					<form:input path="id" readonly="true" class="form-control" />
				</div>


				<div class="form-group">
					<form:label path="label">Label</form:label>
					<form:input path="label" class="form-control" />
				</div>
				<div class="form-group">
					<form:label path="summary">Summary</form:label>
					<form:textarea path="summary" class="form-control" />
				</div>
				<div class="form-group">
					<form:label path="description">Description</form:label>
					<form:textarea path="description" class="form-control" style="height:200px"/>
				</div>
				
				<div class="form-group">
					<form:label path="authorsHelp"><spring:message code="authorsHelp"/></form:label>
					<form:textarea path="authorsHelp" class="form-control" style="height:200px"/>
				</div>

			</div>
		</div>

	</form:form>
</div>