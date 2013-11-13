<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



<script src="<c:url value="/js/jquery/ui/jquery.ui.core.js"/>"></script>
<script src="<c:url value="/js/jquery/ui/jquery.ui.widget.js"/>"></script>
<script src="<c:url value="/js/jquery/ui/jquery.ui.tabs.js"/>"></script>
<script src="<c:url value='/js/jquery/jquery.fileupload.js' />"></script>

<link href="<c:url value='/css/dropzone.css'/>" type="text/css" rel="stylesheet" />

<script src="<c:url value='/js/fileupload.js'/>"></script>



<div class="edit">


	<c:url var="addUrl" value="/author/serviceOffer/submit" />
	
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<form:form method="POST" action="${addUrl}" commandName="serviceOffer" role="form">
					<div class="row">
						<div class="col-md-12">
							<button value="Save" name="action" type="submit" class="btn btn-primary btn-xs">
								<spring:message code="save" />
							</button>


							<button value="Cancel" name="action" type="submit" class="btn btn-default btn-xs">
								<spring:message code="cancel" />
							</button>
						</div>
					</div>


					<div class="row">
						<div class="col-md-12">

							<ul class="nav nav-tabs">
								<li><a href="#tabs-1" data-toggle="tab"><spring:message code="serviceOfferHeader" /></a></li>
								<li><a href="#tabs-7" data-toggle="tab"><spring:message code="image" /></a></li>
								<li><a href="#tabs-2" data-toggle="tab"><spring:message code="contacts" /></a></li>
								<li><a href="#tabs-3" data-toggle="tab"><spring:message code="caseStudies" /></a></li>
								<li><a href="#tabs-4" data-toggle="tab"><spring:message code="salesCollateral" /></a></li>
								<li><a href="#tabs-5" data-toggle="tab"><spring:message code="info" /></a></li>
								<li><a href="#tabs-6" data-toggle="tab"><spring:message code="serviceOfferStructure" /></a></li>
							</ul>
						</div>
					</div>

					<div class="tab-content">
						<div id="tabs-7" class="tab-pane">
							<!--[if lt IE 9]>
											<div class="alert alert-warning">
											Upload of images is not possible with IE 8 and below. Please install a browser that supports HTML5 like Internet Explorer 10, <a href="https://www.google.com/intl/de/chrome/browser/" target="_blank">Chrome</a> or <a href="https://www.mozilla.org/firefox">Firefox</a>.
											</div>
											   <![endif]-->
							<div class="row">
								<div class="col-md-8">
									<input id="fileupload" type="file" name="files[]" data-url="<c:url value='/author/serviceOffer/uploadImage/${serviceOffer.id} '/>">
									<div id="dropzone" class="panel panel-default" style="width: 100%;">Drop files here</div>
									<div id="progress" class="progress">
										<div class="bar progress-bar" style="width: 0%;"></div>
									</div>
								</div>
								<div id="imgcontainer" class="col-md-4">
									<a href="${imageUrl}" target="_blank"><img style="width: 100%;" src="${imageUrl}" /></a>
								</div>
							</div>
						</div>
						<div id="tabs-6" class="tab-pane">
							<div class="row">
								<div class="col-md-8">
									<div class="form-group">
										<form:label path="status">Status</form:label>

										<form:select id="status" path="status" items="${statusList}" class="form-control" />
									</div>

									<div class="form-group">
										<form:label path="bigPlay" for="bigPlay">
											<spring:message code="bigPlays" />
										</form:label>
										<form:select class="form-control" id="bigPlay" path="bigPlay" multiple="true" style="height:250px">
											<form:options items="${bigPlayList}" itemValue="id" itemLabel="display" />
										</form:select>
									</div>
								</div>
							</div>
						</div>
						<div id="tabs-1" class="tab-pane active">
							<div class="row">
								<div class="col-md-12">
									<div class="control-group">
										<form:label path="id">_id</form:label>
										<div class="controls">
											<form:input path="id" readonly="true" style="width:100%"/>
										</div>
									</div>
									<div class="control-group">
										<form:label path="label">
											<spring:message code="label" />
										</form:label>
										<div class="controls">
											<form:input path="label" style="width:100%"/>
											<form:errors path="label" class="error"></form:errors>
										</div>
									</div>
									<div class="control-group">
										<form:label path="summary">
											<spring:message code="summary" />
										</form:label>
										<div class="controls">
											<form:textarea path="summary" />
											<form:errors path="summary" class="error"></form:errors>
										</div>
									</div>
									<div class="control-group">
										<form:label path="addedValue">
											<spring:message code="addedValue" />
										</form:label>
										<div class="controls">
											<form:textarea path="addedValue" style="height:250px" />
											<form:errors path="addedValue" class="error"></form:errors>
										</div>
									</div>
									<div class="control-group">
										<form:label path="whyEMC">
											<spring:message code="whyEMC" />
										</form:label>
										<div class="controls">
											<form:textarea path="whyEMC" style="height:250px" />
											<form:errors path="whyEMC" class="error"></form:errors>
										</div>
									</div>
									<div class="control-group">
										<form:label path="price">
											<spring:message code="price" />
										</form:label>
										<div class="controls">
											<form:input path="price" />
										</div>
									</div>
								</div>
							</div>
						</div>
						<div id="tabs-2" class="tab-pane">
							<jsp:include page="serviceofferEditContacts.jsp"></jsp:include>
						</div>

						<div id="tabs-3" class="tab-pane">
							<jsp:include page="serviceofferEditCaseStudies.jsp" />
						</div>

						<div id="tabs-4" class="tab-pane">
							<jsp:include page="serviceofferEditCollateral.jsp" />
						</div>


						<div id="tabs-5" class="tab-pane">
							<jsp:include page="serviceofferEditAdditionalInformation.jsp" />
						</div>
					</div>

					


				</form:form>
			</div>
		</div>
	</div>
</div>