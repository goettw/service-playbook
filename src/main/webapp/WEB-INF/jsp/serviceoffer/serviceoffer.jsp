<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:if test="${not empty imageUrl}">
	<a href="${imageUrl}" target="_blank"> <img src="${imageUrl}" class="img-rounded" />

	</a>
</c:if>

<h4>
	<spring:message code="addedValue" />
</h4>
${serviceOffer.addedValueAsHTML}
<h4>
	<spring:message code="whyEMC" />
</h4>
${serviceOffer.whyEMCAsHTML}

<p>

	<sec:authorize access="hasAnyRole('ROLE_Author')">
		<c:url value="/author/serviceOffer/edit/${serviceOffer.id}" var="editUrl"></c:url>
		<a href="${editUrl}"><spring:message code="edit" /></a>
	</sec:authorize>
</p>
