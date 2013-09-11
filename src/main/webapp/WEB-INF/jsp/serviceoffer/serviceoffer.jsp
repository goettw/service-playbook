<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="subTitleBlue marginTop15">Mehrwert f&uuml;r den Kunden</div>
${serviceOffer.addedValueAsHTML}
<div class="subTitleBlue marginTop15">Why EMC</div>
${serviceOffer.whyEMCAsHTML}
<div class="subTitleBluemarginTop15">EMC Ansprechpartner</div>
<c:forEach var="contact" items="${serviceOffer.emcContacts}">
	<ul class="disc marginTop10">
		<li class="disc"><a href="${contact.url}">${contact.label}</a>
			(${contact.role})<c:if test="${contact.responsible}">*</c:if></li>
	</ul>
</c:forEach>
<p/>
	<a href="<c:url value='/serviceOfferEdit/${serviceOffer.id}'/>">Edit</a>