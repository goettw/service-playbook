<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="container">
	<div class="row">
		<div class="col-md-4">
			<h3>
			<spring:message code="username"></spring:message>
			</h3>
			<p>${profile.username}</p>
		</div>
		<div class="col-md-4">
			<h3><spring:message code="authorities"></spring:message></h3>
			<p>${profile.authorityValues}</p>
		</div>
	</div>
	
</div>
