<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<h3>Weiterf&uuml;hrende
	Informationen</h3>

	<c:forEach var="link" items="${serviceOffer.relatedInformation}">
		<ul class="ref">
			<li class="ref"><a target="_blank" href="${link.url}">${link.label}</a>
			</li>
		</ul>
	</c:forEach>

<h3>Vertriebsmaterial</h3>

	<c:forEach var="link" items="${serviceOffer.salesCollateral}">
		<ul class="ref">
			<li class="ref"><a target="_blank" href="${link.url}">${link.label}</a>
			</li>
		</ul>
	</c:forEach>

<h3>Kundenbeispiele</h3>

	<c:forEach var="caseStudy" items="${serviceOffer.caseStudies}">
		<ul class="ref">
			<li class="ref"><a target="_blank" href="${caseStudy.url}">${caseStudy.label}</a></li>
		</ul>
	</c:forEach>
<h3>Preis</h3>

	${serviceOffer.price}
