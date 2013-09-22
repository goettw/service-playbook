<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h3>Mehrwert f&uuml;r den Kunden</h3>
${serviceOffer.addedValueAsHTML}
<h3>Why EMC</h3>
${serviceOffer.whyEMCAsHTML}
<h3>EMC Ansprechpartner</h3>
<c:forEach var="contact" items="${serviceOffer.emcContacts}">
	<ul class="disc marginTop10">
		<li class="disc"><a href="${contact.url}">${contact.label}</a>
			(${contact.role})<c:if test="${contact.responsible}">*</c:if></li>
	</ul>
</c:forEach>
<p/>
	<a href="<c:url value='/serviceOfferEdit/${serviceOffer.id}'/>">Edit</a>