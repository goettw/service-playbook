<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="edit">
	<c:url var="addUrl" value="/serviceLineEdit/submit" />
	<form:form method="POST" action="${addUrl}" commandName="serviceLine">
		<table class="buttons">
			<tr>
				<td><input type="submit" value="Save" name="action" /></td>
				<td><input type="Submit" value="Cancel" name="action" /></td>
			</tr>
		</table>
		<tr>
			<td class="label"><form:label path="id">_id</form:label></td>
			<td><form:input path="id" readonly="true" /></td>
		</tr>
		<tr>
			<td class="label"><form:label path="label">Label</form:label></td>
			<td><form:input path="label" /></td>
		<tr>
			<td class="label"><form:label path="summary">Zusammenfassung</form:label></td>
			<td><form:textarea path="summary" /></td>
		</tr>
		</tr>

	</form:form>
</div>