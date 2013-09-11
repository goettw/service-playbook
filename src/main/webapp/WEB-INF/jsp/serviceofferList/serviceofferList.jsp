<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
				<div class="edit">
					<table>
						<tbody>
							<c:forEach items="${serviceOfferList}" var="serviceOffer">
								<tr>
									<td><a
										href="<c:url value='/serviceOffer/${serviceOffer.id}'/>">${serviceOffer.label}</a></td>
									<td>${serviceOffer.status}</td>
									<td>
									<a
										href="<c:url value='/serviceOfferEdit/${serviceOffer.id}'/>">Edit</a>
									</td>
									<td><a
										href="<c:url value='/serviceOffer/delete/${serviceOffer.id}'/>">Delete</a>
									</td>
								</tr>

							</c:forEach>
							<tr>
								<td><a href="<c:url value='/serviceOffer/new'/>">New</a></td>
							</tr>
						</tbody>
					</table>





				</div>
