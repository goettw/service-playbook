<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="container">

	<div class="row">
		<div class="col-md-4">
			<b> <spring:message code="title"></spring:message>
			</b>
			<p>${profile.title}</p>
		</div>
		<div class="col-md-4">
			<b> <spring:message code="firstName"></spring:message>
			</b>
			<p>${profile.firstName}</p>
		</div>
		<div class="col-md-4">
			<b> <spring:message code="lastName"></spring:message>
			</b>
			<p>${profile.lastName}</p>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<b> <spring:message code="emailAddress"></spring:message>
			</b>
			<p>${profile.emailAddress}</p>
		</div>
		<div class="col-md-4">
			<b> <spring:message code="emcFunction"></spring:message>
			</b>
			<p>${profile.emcFunction}</p>
		</div>
		<div class="col-md-4">
			<b> <spring:message code="emcProfileUrl"></spring:message>
			</b>
			<p>${profile.emcProfileUrl}</p>
		</div>
	</div>
	<hr>
	<div class="row">
		<div class="col-md-12">
			<b> <spring:message code="aboutMe"></spring:message>
			</b>
			<p>${profile.aboutMe}</p>
		</div>

	</div>
	<hr>
	<div class="row">
		<div class="col-md-4">
			<b> <spring:message code="username"></spring:message>
			</b>
			<p>${profile.username}</p>
		</div>
		<div class="col-md-4">
			<b><spring:message code="authorities"></spring:message></b>
			<p>${profile.authorityValues}</p>
		</div>
	</div>

</div>
