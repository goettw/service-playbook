<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="edit">
	<c:url var="addUrl" value="/admin/bigPlayItem/submit" />
	<form:form method="POST" action="${addUrl}" commandName="bigPlayItem">
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
			<td class="label"><form:label path="level1">Level 1</form:label></td>
			<td><form:input path="level1" /></td>
		<tr>
			<td class="label"><form:label path="level2">Level 2</form:label></td>
			<td><form:textarea path="level2" /></td>
		</tr>
		

	</form:form>
</div>