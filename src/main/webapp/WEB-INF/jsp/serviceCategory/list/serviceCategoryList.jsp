<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="edit">
	<table>
		<tbody>
			<c:forEach items="${serviceCategoryList}" var="serviceCategory">
				<tr>
					<td><a
						href="<c:url value='/serviceCategory/${serviceCategory.id}'/>">${serviceCategory.label}</a></td>
					<td><a
						href="<c:url value='/serviceCategoryEdit/${serviceCategory.id}'/>">Edit</a>
					</td>
					<td><a
						href="<c:url value='/serviceCategory/delete/${serviceCategory.id}'/>">Delete</a>
					</td>
				</tr>

			</c:forEach>
			<tr>
				<td><a href="<c:url value='/serviceCategory/new'/>">New</a></td>
			</tr>
		</tbody>
	</table>





</div>
