<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="container">
	<table class="table table-hover">
		<tbody>
			<c:forEach items="${profileList}" var="profile">
			<c:url var="viewUrl" value='/auth/profile'>
								<c:param name="id" value="${profile.username}" />
							</c:url>
				<tr onclick="javascript:document.location=${$url}">
					<td>${profile.lastName}</td>
					<td>${profile.firstName}</td>
					<td>${profile.emcFunction}</td>
					<sec:authorize access="hasAnyRole('ROLE_Administrator')">
						<td><c:url var="editUrl" value='/admin/profile/edit'>
								<c:param name="id" value="${profile.username}" />
							</c:url> <a href="${editUrl}"><spring:message code="edit" /></a></td>
						<td>
						<c:url var="deleteUrl" value='/admin/profile/delete'>
								<c:param name="id" value="${profile.username}" />
							</c:url>
						
						<a
							href="${deleteUrl}"><spring:message
									code="delete" /></a></td>
					</sec:authorize>
					<td>
					
					<a
						href="${viewUrl}"><spring:message
								code="view" /></a></td>

				</tr>

			</c:forEach>
			<sec:authorize access="hasAnyRole('ROLE_Administrator')">
				<tr>
					<td><a href="<c:url value='/admin/profile/new'/>">New</a></td>
				</tr>
			</sec:authorize>
		</tbody>
	</table>





</div>
