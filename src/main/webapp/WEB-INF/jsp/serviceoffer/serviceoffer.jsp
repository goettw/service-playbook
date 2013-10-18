<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:if test="${not empty imageUrl}">
<a href="${imageUrl}" target="_blank">


<img   src ="${imageUrl}" class="img-rounded"/>

</a>
</c:if>
<h3><spring:message code="addedValue"/></h3>
${serviceOffer.addedValueAsHTML}
<h3><spring:message code="whyEMC"/></h3>
${serviceOffer.whyEMCAsHTML}

