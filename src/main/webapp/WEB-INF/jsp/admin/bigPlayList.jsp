<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
				<div class="edit">
					<table>
						<tbody>
							<c:forEach items="${bigPlayList}" var="bigPlayItem">
								<tr>
									<td><a
										href="<c:url value='/admin/bigPlayItem/edit/${bigPlayItem.id}'/>">${bigPlayItem.level1}/${bigPlayItem.level2}</a>
																		<td>
									</td>
									<td><a
										href="<c:url value='/admin/bigPlayItem/delete/${bigPlayItem.id}'/>">Delete</a>
									</td>
								</tr>

							</c:forEach>
							<tr>
								<td><a href="<c:url value='/admin/bigPlayItem/new'/>">New</a></td>
							</tr>
						</tbody>
					</table>





				</div>
