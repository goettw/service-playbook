<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<script src="<c:url value='/js/jquery/jquery-1.9.1.js' />"></script>
<script src="<c:url value="/js/jquery/ui/jquery.ui.core.js"/>"></script>
<script src="<c:url value="/js/jquery/ui/jquery.ui.widget.js"/>"></script>
<script src="<c:url value="/js/jquery/ui/jquery.ui.tabs.js"/>"></script>
<script src="<c:url value='/js/jquery/jquery.fileupload.js' />"></script>
<link rel="stylesheet"
	href="<c:url value="/js/jquery/themes/base/jquery.ui.all.css"/>" />

<link href="<c:url value='/css/dropzone.css'/>" type="text/css"
	rel="stylesheet" />

<script src="<c:url value='/js/fileupload.js'/>"></script>

<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>

<div class="edit">


	<c:url var="addUrl" value="/serviceOfferEdit/submit" />
	<form:form method="POST" action="${addUrl}" commandName="serviceOffer"
		role="form">
		<table class="buttons">
			<tr>
				<td><input type="submit" value="Save" name="action"
					type="button" class="btn btn-primary" /></td>
				<td><input type="Submit" value="Cancel" name="action"
					type="button" class="btn btn-default" /></td>
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
			<!--[if lt IE 9]>
			<div class="alert alert-warning">
			Upload of images is not possible with IE 8 and below. Please install a browser that supports HTML5 like Internet Explorer 10, <a href="https://www.google.com/intl/de/chrome/browser/" target="_blank">Chrome</a> or <a href="https://www.mozilla.org/firefox">Firefox</a>.
			</div>
			   <![endif]--> 
				<div class="container">
					<div class="row">
					<div class="col-md-8">
							<input id="fileupload" type="file" name="files[]"
								data-url="<c:url value='/serviceOffer/uploadImage/${serviceOffer.id} '/>">
							<div id="dropzone" class="panel panel-default" style="width:100%;">Drop
								files here</div>
							<div id="progress" class="progress">
								<div class="bar progress-bar" style="width: 0%;"></div>
							</div>
						</div>
						<div id="imgcontainer" class="col-md-4">
							<a href="${imageUrl}" target="_blank"><img
								style="width: 100%;" src="${imageUrl}" /></a>
						</div>

						
					</div>
				</div>

			</div>
			<div id="tabs-6">
				<div class="form-group">
					<form:label path="status">Status</form:label>

					<form:select id="status" path="status" items="${statusList}"
						class="form-control" />
				</div>
				<!-- >div class="form-group">
					<form:label path="serviceCategory">Service Category</form:label>
					<form:select id="serviceCategories" path="serviceCategory"
						class="form-control">
						<form:options items="${serviceCategoryList}" itemValue="id"
							itemLabel="label" />
					</form:select>
				</div-->
				<div class="form-group">
					<form:label path="bigPlay" for="bigPlay">Big Plays</form:label>
					<form:select class="form-control" id="bigPlay" path="bigPlay"
						multiple="true" style="height:250px">
						<form:options items="${bigPlayList}" itemValue="id"
							itemLabel="display" />
					</form:select>
				</div>
			</div>
			<div id="tabs-1" class="form-group">
				<div class="form-group">
					<form:label path="id">_id</form:label>
					<form:input class="form-control" path="id" readonly="true" />
				</div>
				<div class="form-group">
					<form:label path="label">Label</form:label>
					<form:input class="form-control" path="label" />

				</div>
				<div class="form-group">
					<form:label path="summary">Zusammenfassung</form:label>
					<form:textarea class="form-control" path="summary" />

				</div>
				<div class="form-group">
					<form:label path="addedValue">Mehrwert f&uuml;r den Kunden</form:label>

					<form:textarea class="form-control" path="addedValue" style="height:250px"/>
				</div>
				<div class="form-group">
					<form:label path="whyEMC">Why EMC</form:label>
					<form:textarea class="form-control" path="whyEMC" style="height:250px"/>
				</div>
				<div class="form-group">
					<form:label path="price">Price</form:label>
					<form:input class="form-control" path="price" />

				</div>
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


