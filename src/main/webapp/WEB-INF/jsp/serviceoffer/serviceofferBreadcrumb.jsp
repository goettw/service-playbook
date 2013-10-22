<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>






<ul class="breadcrumb">

	<c:forEach items="${bigPlays}" var="bigPlay" varStatus="status">

		<c:url var="url" value="/bigPlayItem/${bigPlays[0].id}" />

		<li>${bigPlay.level1}</li>
		<li><a href="${url}">${bigPlay.level2}</a></li>
		
	</c:forEach>
</ul>