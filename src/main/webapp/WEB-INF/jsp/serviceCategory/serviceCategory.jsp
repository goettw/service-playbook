<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<div class="subTitleBlue marginTop15">Merkmale zur Kunden - Qualifizierung</div>
${serviceCategory.qualificationAsHTML}
<div class="subTitleBlue marginTop15">Service Angebote</div>
<ul class="ref">
	<c:forEach items="${serviceOfferList}" var="serviceOffer">
		<li class="ref"><a
			href="<c:url value='/serviceOffer/${serviceOffer.id}'/>">${serviceOffer.label}</a>
			<p class="marginTop5" style="color: rgb(73, 72, 72)">
				${serviceOffer.summary}</p></li>
	</c:forEach>
</ul>