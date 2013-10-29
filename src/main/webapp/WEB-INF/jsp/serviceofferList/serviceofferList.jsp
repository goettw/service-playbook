<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="container">
	<table class="table">
		<tbody>
		<tr>
		<th><spring:message code="label"/></th><th><spring:message code="responsible"/></th>
		
		<th><spring:message code="bigPlay"/></th>
		<th><spring:message code="status"/></th>
		<th><spring:message code="lastChange"/></th>
		</tr>
			<c:forEach items="${serviceOfferList}" var="serviceOffer">
				<tr>
					<td><a href="<c:url value='/serviceOffer/${serviceOffer.id}'/>">${serviceOffer.label}</a></td>
					<td><c:forEach var="contact" items="${serviceOffer.emcContacts}" varStatus="status">
							<c:if test="${contact.responsible}">
					${contact.label}
						(${contact.role})*

							<c:if test="${not status.last}">
								<br />
							</c:if>
</c:if>
						</c:forEach></td>
						
						
					<td>
					
					<c:forEach items="${serviceOffer.bigPlay}" var="play" varStatus="status">
					
										
					${play}
					
					<c:if test="${not status.last}">
								<br />
					</c:if>
					
					</c:forEach>
					</td>	
					<td>${serviceOffer.status}</td>
					<td>
					<c:if test="${!empty serviceOffer.actionLog}">

					
					<c:set var="actionLogItem" value="${serviceOffer.actionLog[0]}"/>
					${actionLogItem.personName}, <fmt:formatDate type="both" value="${actionLogItem.dateTime}" timeZone="CET"/>  (<spring:message code="${actionLogItem.actionType}" />)
					</c:if>
					
					
					
					
					</td>
					<td><a href="<c:url value='/author/serviceOffer/edit/${serviceOffer.id}'/>">Edit</a></td>
					<sec:authorize access="hasAnyRole('ROLE_Administrator')">
						<td><a href="<c:url value='/author/serviceOffer/delete/${serviceOffer.id}'/>">Delete</a></td>
					</sec:authorize>
				</tr>

			</c:forEach>
			<tr>
				<td><a href="<c:url value='/author/serviceOffer/new'/>">New</a></td>
			</tr>
		</tbody>
	</table>





</div>
