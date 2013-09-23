<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty imageUrl}">
<a href="${imageUrl}" target="_blank">


<img   src ="${imageUrl}" class="img-rounded"/>

</a>
</c:if>
<h3>Mehrwert f&uuml;r den Kunden</h3>
${serviceOffer.addedValueAsHTML}
<h3>Why EMC</h3>
${serviceOffer.whyEMCAsHTML}

