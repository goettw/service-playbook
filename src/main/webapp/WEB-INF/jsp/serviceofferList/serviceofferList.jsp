<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="container">
	<table class="table table-hover">
		<tbody>
			<tr>

				<th width="20%"><a href="<%=request.getContextPath()%>/auth/serviceList"><spring:message code="label" /></a></th>

				<th width="30%"><spring:message code="summary" /></th>
				<th width="30%"><a href="<%=request.getContextPath()%>/bigPlayOverview"><spring:message code="bigPlay" /></a></th>
				<th width="20%"><a href="<%=request.getContextPath()%>/auth/serviceListByUpdate"><spring:message code="lastChange" /></a></th>
			</tr>
			
			<c:forEach items="${serviceOfferList}" var="serviceOffer">
				<c:if test="${serviceOffer.status == 'released'}">
					<tr>
						<td><a href="<c:url value='/serviceOffer/${serviceOffer.id}'/>">${serviceOffer.label}</a></td>

						<td>${serviceOffer.summary}</td>
						<td><c:forEach items="${serviceOffer.bigPlay}" var="play" varStatus="status">
					
										
					${play}
					
					<c:if test="${not status.last}">
									<br />
								</c:if>

							</c:forEach></td>


						<td><c:if test="${!empty serviceOffer.actionLog}">


								<c:set var="actionLogItem" value="${serviceOffer.actionLog[0]}" />
								<fmt:formatDate type="both" value="${actionLogItem.dateTime}" timeZone="CET" />
							</c:if></td>
					</tr>
				</c:if>
			</c:forEach>
			
		</tbody>
	</table>





</div>
