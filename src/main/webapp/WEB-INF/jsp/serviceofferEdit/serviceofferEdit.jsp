<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<script src="<c:url value='/js/jquery/jquery-1.9.1.js' />"></script>
<script src="<c:url value="/js/jquery/ui/jquery.ui.core.js"/>"></script>
<script src="<c:url value="/js/jquery/ui/jquery.ui.widget.js"/>"></script>
<script src="<c:url value="/js/jquery/ui/jquery.ui.tabs.js"/>"></script>
<script src="<c:url value='/js/jquery/jquery.fileupload.js' />"></script>
<link href="<c:url value='/css/dropzone.css'/>" type="text/css" rel="stylesheet" />

<script src="<c:url value='/js/fileupload.js'/>"></script>

<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>

<div class="edit">


	<c:url var="addUrl" value="/serviceOfferEdit/submit" />
	<form:form method="POST" action="${addUrl}" commandName="serviceOffer">
		<table class="buttons">
			<tr>
				<td><input type="submit" value="Save" name="action" /></td>
				<td><input type="Submit" value="Cancel" name="action" /></td>
			</tr>
		</table>
		<div id="tabs">
			<ul>
				<li><a href="#tabs-1">Header</a></li>
				<li><a href="#tabs-7">Image</a></li>
				<li><a href="#tabs-2">Contacts</a></li>
				<li><a href="#tabs-3">Case Studies</a></li>
				<li><a href="#tabs-4">Vertriebsmaterial</a></li>
				<li><a href="#tabs-5">Info</a></li>
				<li><a href="#tabs-6">Structure</a></li>
			</ul>
			<div id="tabs-7">
			<div id="imgcontainer">
			</div>
				<input id="fileupload" type="file" name="files[]" data-url="<c:url value='/serviceOffer/uploadImage'/>" multiple >
				<div id="dropzone" class="fade well">Drop files here</div>
				<div id="progress" class="progress">
    				<div class="bar" style="width: 0%;"></div>
				</div>
			</div>
			<div id="tabs-6">
				<table>
					<tr>
						<td class="label"><form:label path="status">Status</form:label></td>
						<td><form:select id="status" path="status"
								items="${statusList}" /></td>
					</tr>
					<tr>
						<td class="label"><form:label path="serviceCategory">Service Category</form:label></td>
						<td><form:select id="serviceCategories"
								path="serviceCategory">
								<form:options items="${serviceCategoryList}" itemValue="id"
									itemLabel="label" />
							</form:select></td>
					</tr>


					<tr>
						<td class="label"><form:label path="bigPlay">Big Plays</form:label></td>
						<td><form:select id="bigPlay" path="bigPlay" multiple="true" style="height:200px">
								<form:options items="${bigPlayList}" itemValue="id"
									itemLabel="display" />
							</form:select></td>
					</tr>
				</table>
			</div>
			<div id="tabs-1">
				<table>

					<tr>
						<td class="label"><form:label path="id">_id</form:label></td>
						<td><form:input path="id" readonly="true" /></td>
					</tr>

					<tr>
						<td class="label"><form:label path="label">Label</form:label></td>
						<td><form:input path="label" /></td>
					</tr>
					<tr>
						<td class="label"><form:label path="summary">Zusammenfassung</form:label></td>
						<td><form:textarea path="summary" /></td>
					</tr>
					<tr>
						<td class="label"><form:label path="addedValue">Mehrwert f&uuml;r den Kunden</form:label></td>
						<td><form:textarea path="addedValue" /></td>
					</tr>
					<tr>
						<td class="label"><form:label path="whyEMC">Why EMC</form:label></td>
						<td><form:textarea path="whyEMC" /></td>
					</tr>
					<tr>
						<td class="label"><form:label path="price">Price</form:label></td>
						<td><form:input path="price" /></td>
					</tr>
				</table>
			</div>

			<div id="tabs-2">
				<jsp:include page="serviceofferEditContacts.jsp"></jsp:include>
			</div>

			<div id="tabs-3">
				<jsp:include page="serviceofferEditCaseStudies.jsp" />
			</div>

			<div id="tabs-4">
				<jsp:include page="serviceofferEditCollateral.jsp" />
			</div>


			<div id="tabs-5">
				<jsp:include page="serviceofferEditAdditionalInformation.jsp" />
			</div>
		</div>
	</form:form>
</div>


