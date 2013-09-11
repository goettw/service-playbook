<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="edit">
	<table>
		<tbody>
			<c:forEach items="${serviceLineList}" var="serviceLine">
				<tr>
					<td><a
						href="<c:url value='/serviceLine/${serviceLine.id}'/>">${serviceLine.label}</a></td>
					<td><a
						href="<c:url value='/serviceLineEdit/${serviceLine.id}'/>">Edit</a>
					</td>
					<td><a
						href="<c:url value='/serviceLine/delete/${serviceLine.id}'/>">Delete</a>
					</td>
				</tr>

			</c:forEach>
			<tr>
				<td><a href="<c:url value='/serviceLine/new'/>">New</a></td>
			</tr>
		</tbody>
	</table>





</div>
