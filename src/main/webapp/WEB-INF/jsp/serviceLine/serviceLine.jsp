<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<div class="subTitleBlue marginTop15">Themenfelder</div>

<ul class="ref">
	<c:forEach items="${serviceCategoryList}" var="serviceCategory">
		<li class="ref"><a
			href="<c:url value='/serviceCategory/${serviceCategory.id}'/>">${serviceCategory.label}</a>
			<p class="marginTop5" style="color: rgb(73, 72, 72)">
				${serviceCategory.summary}</p></li>
	</c:forEach>
</ul>

