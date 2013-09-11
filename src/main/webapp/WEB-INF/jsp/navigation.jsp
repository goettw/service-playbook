<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="leftMenuTitle">
	<a href="<c:url value='/bigPlayOverview'/>"> Consulting and
		IT-Services </a>
</div>
	
<c:forEach items="${serviceLineList}" var="serviceLineItem">
	<div class="leftMenuTitle2">
		<a href="<c:url value='/serviceLine/${serviceLineItem.id}'/>">${serviceLineItem.label}</a>
	</div>
	<c:if test="${serviceLineId == serviceLineItem.id}">
		<ul class="leftMenuOuter">
			<c:forEach items="${serviceCategoryList}" var="serviceCategoryItem">
				<li class="leftMenuItemOuter"><c:choose>
						<c:when
							test="${serviceOffer == null && serviceCategory != null && serviceCategoryItem.id == serviceCategory.id}">
							<span class="leftMenuBlue">${serviceCategoryItem.label} </span>
							<ul class="leftMenuItem">
								<c:forEach items="${serviceOfferList}" var="serviceOfferItem">
									<li class="leftMenuItem"><c:choose>
											<c:when test="${serviceOfferItem.id == serviceOffer.id}">
												<span class="leftMenuBlue">${serviceOfferItem.label}</span>
											</c:when>
											<c:otherwise>
												<a
													href="<c:url value='/serviceOffer/${serviceOfferItem.id}'/>">${serviceOfferItem.label}</a>
											</c:otherwise>
										</c:choose></li>
								</c:forEach>
							</ul>
						</c:when>
						<c:otherwise>
							<a
								href="<c:url value='/serviceCategory/${serviceCategoryItem.id}'/>">${serviceCategoryItem.label}</a>
							<c:if test="${serviceCategoryId == serviceCategoryItem.id}">
								<ul class="leftMenuItem">
									<c:forEach items="${serviceOfferList}" var="serviceOfferItem">
										<li class="leftMenuItem"><c:choose>
												<c:when test="${serviceOfferItem.id == serviceOffer.id}">
													<span class="leftMenuBlue">${serviceOfferItem.label}</span>
												</c:when>
												<c:otherwise>
													<a
														href="<c:url value='/serviceOffer/${serviceOfferItem.id}'/>">${serviceOfferItem.label}</a>
												</c:otherwise>
											</c:choose></li>
									</c:forEach>
								</ul>
							</c:if>
						</c:otherwise>
					</c:choose>
			</c:forEach>
		</ul>
	</c:if>
</c:forEach>

