<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="<c:url value='/js/jquery/jquery-1.9.1.js' />"></script>
<script src="<c:url value="/js/jquery/ui/jquery.ui.core.js"/>"></script>
<c:url var="findServiceLinesURL" value="/serviceLinesJSON" />

<!-- script type="text/javascript">
	$(document).ready(
			function() {
				$.getJSON('${findServiceLinesURL}', {
					ajax : 'true'
				}, function(data) {
					var html = '';
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].label + '">'
								+ data[i].label + '</option>';
					}
					html += '</option>';

					$('#serviceLines').html(html);
				});
			});
</script-->
<div class="edit">
	<c:url var="addUrl" value="/serviceCategoryEdit/submit" />
	<form:form method="POST" action="${addUrl}"
		commandName="serviceCategory">
		<table class="buttons">
			<tr>
				<td><input type="submit" value="Save" name="action" /></td>
				<td><input type="Submit" value="Cancel" name="action" /></td>
			</tr>
		</table>
		<table>
			<tr>
				<td><form:select id="serviceLines" path="serviceLine">
				  <form:options items="${serviceLineList}" itemValue="id" itemLabel="label"/>
		</form:select></td>
			</tr>
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

			<tr>
				<td class="label"><form:label path="qualification">Merkmale zur Kunden - Qualifizierung</form:label></td>
				<td><form:textarea path="qualification" /></td>
			</tr>

			<tr>
				<td class="label"><form:label path="challenges">Typische Herausforderungen</form:label></td>
				<td><form:textarea path="challenges" /></td>
			</tr>
			<tr>
				<td class="label"><form:label path="goal">Ergebnis / Zielbild des Kunden</form:label></td>
				<td><form:textarea path="goal" /></td>
			</tr>
		</table>
	</form:form>
</div>