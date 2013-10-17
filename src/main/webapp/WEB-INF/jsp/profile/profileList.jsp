<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<table class="table">
		<tbody>
			<c:forEach items="${profileList}" var="profile">
				<tr>
					<td><a href="<c:url value='/profile/${profile.username}'/>">${profile.username}</a>
					
					<td><a
						href="<c:url value='/admin/profile/edit/${profile.username}'/>">Edit</a>
					</td>
					
				</tr>

			</c:forEach>
			<tr>
				<td><a href="<c:url value='/admin/profile/new'/>">New</a></td>
			</tr>
		</tbody>
	</table>





</div>
