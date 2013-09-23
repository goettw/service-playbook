<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:if test="${not empty serviceOffer.emcContacts}">
	<div class="panel panel-success">
		<div class="panel-heading">

			<div class="panel-title">EMC Ansprechpartner</div>
		</div>
		<div class="panel-body">
			<ul class="list-group">
				<c:forEach var="contact" items="${serviceOffer.emcContacts}">

					<a class="list-group-item" href="${contact.url}">${contact.label}
						(${contact.role})<c:if test="${contact.responsible}">*</c:if>
					</a>


				</c:forEach>
			</ul>
		</div>
	</div>
</c:if>





<c:if test="${not empty serviceOffer.relatedInformation}">
	<div class="panel panel-default">
		<div class="panel-heading">

			<div class="panel-title">Weiterf&uuml;hrende Informationen</div>
		</div>
		<div class="panel-body">
			<ul class="list-group">
				<c:forEach var="link" items="${serviceOffer.relatedInformation}">

					<a class="list-group-item" target="_blank" href="${link.url}">${link.label}</a>

				</c:forEach>
			</ul>
		</div>
	</div>
</c:if>
<c:if test="${not empty serviceOffer.salesCollateral}">
	<div class="panel panel-default">
		<div class="panel-heading">

			<div class="panel-title">Vertriebsmaterial</div>
		</div>
		<div class="panel-body">
			<ul class="list-group">
				<c:forEach var="link" items="${serviceOffer.salesCollateral}">

					<a class="list-group-item" target="_blank" href="${link.url}">${link.label}</a>

				</c:forEach>
			</ul>
		</div>
	</div>

</c:if>
<c:if test="${not empty serviceOffer.caseStudies}">

	<div class="panel panel-default">
		<div class="panel-heading">

			<div class="panel-title">Kundenbeispiele</div>
		</div>
		<div class="panel-body">
			<ul class="list-group">
				<c:forEach var="caseStudy" items="${serviceOffer.caseStudies}">

					<a class="list-group-item" target="_blank" href="${caseStudy.url}">${caseStudy.label}</a>

				</c:forEach>
			</ul>
		</div>
	</div>
</c:if>
<c:if test="${not empty serviceOffer.price}">
	<div class="panel panel-default">
		<div class="panel-heading">

			<div class="panel-title">Preis</div>
		</div>
		<div class="panel-body">${serviceOffer.price}</div>
	</div>
</c:if>


