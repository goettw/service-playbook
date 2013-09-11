<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="subTitleBlue marginTop15">Weiterf&uuml;hrende
	Informationen</div>
<div class="marginTop5blueLink">
	<c:forEach var="link" items="${serviceOffer.relatedInformation}">
		<ul class="ref">
			<li class="ref"><a target="_blank" href="${link.url}">${link.label}</a>
			</li>
		</ul>
	</c:forEach>
</div>
<div class="subTitleBlue marginTop15">Vertriebsmaterial</div>
<div class="marginTop5blueLink">
	<c:forEach var="link" items="${serviceOffer.salesCollateral}">
		<ul class="ref">
			<li class="ref"><a target="_blank" href="${link.url}">${link.label}</a>
			</li>
		</ul>
	</c:forEach>
</div>
<div class="subTitleBlue marginTop15">Kundenbeispiele</div>
<div class="marginTop5blueLink">
	<c:forEach var="caseStudy" items="${serviceOffer.caseStudies}">
		<ul class="ref">
			<li class="ref"><a target="_blank" href="${caseStudy.url}">${caseStudy.label}</a></li>
		</ul>
	</c:forEach>
</div>
<p>
	<b>Preis:</b>${serviceOffer.price}
</p>
