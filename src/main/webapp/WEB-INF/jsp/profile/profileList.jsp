<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="container">
	<table class="table">
		<tbody>
			<c:forEach items="${profileList}" var="profile">
				<tr>
					<td>${profile.lastName}</td>
					<td>${profile.firstName}</td>
					<td>${profile.emailAddress}</td>
					<sec:authorize access="hasAnyRole('ROLE_Administrator')">
						<td><a
							href="<c:url value='/admin/profile/edit/${profile.username}'/>"><spring:message
									code="edit" /></a></td>
					</sec:authorize>
					<td><a href="<c:url value='/profile/${profile.username}'/>"><spring:message
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
