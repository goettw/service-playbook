<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="container">


	<div class="row">
		<div class="col-md-12">${servicePlaybookDescription.description}</div>
	</div>
	<sec:authorize access="hasAnyRole('ROLE_Author')">
		<div class="row">
			<div class="col-md-12">
				<H4>
					<spring:message code="authorsHelp" />
				</H4>

				${servicePlaybookDescription.authorsHelp}
			</div>
		</div>
	</sec:authorize>

</div>
