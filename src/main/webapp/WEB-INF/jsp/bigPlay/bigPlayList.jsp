<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="container">
	<table class="table">
		<tbody>
			<c:forEach items="${bigPlayList}" var="bigPlayItem">
				<tr>
					<td><a href="<c:url value='/bigPlayItem/${bigPlayItem.id}'/>">${bigPlayItem.level1}/${bigPlayItem.level2}</a>
					<td>${bigPlayItem.sortOrderNo}111</td>
					<td><a
						href="<c:url value='/admin/bigPlayItem/edit/${bigPlayItem.id}'/>">Edit</a>
					</td>
					
					<sec:authorize access="hasAnyRole('ROLE_Administrator')">
					<td><a
						href="<c:url value='/admin/bigPlayItem/delete/${bigPlayItem.id}'/>">Delete</a>
					</td>
					</sec:authorize>
				</tr>

			</c:forEach>
			<tr>
				<td><a href="<c:url value='/admin/bigPlayItem/new'/>">New</a></td>
			</tr>
		</tbody>
	</table>





</div>
