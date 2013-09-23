<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<div class="row">
		<div class="col-md-4">
			<h3>Kundenqualifikation</h3>
			<p>${bigPlayItem.customerQualificationAsHtml}</p>
		</div>
		<div class="col-md-4">
			<h3>Typische Herausforderung</h3>
			<p>${bigPlayItem.painPointsAsHtml}</p>
		</div>
		<div class="col-md-4">
			<h3>Zielsetzung</h3>
			<p>${bigPlayItem.visionAsHtml}</p>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<h3>Services</h3>
			<div class="container-fluid">
				<div class="row">
					<c:forEach items="${serviceOfferList}" var="serviceOffer"
						varStatus="status">
						<div class="col-md-4">
							<div class="panel panel-info">
								<div class="panel-heading">
									<div class="panel-title">${serviceOffer.label}</div>
								</div>
								<div class="panel-body">
								<p>${serviceOffer.summary}</p>
									<div align="right">
										<a href="<c:url value='/serviceOffer/${serviceOffer.id}'/>"
											style="horizontal-align: right"
											class="btn btn-xs btn-info"> Details </a>
									</div>
								</div>
							</div>
						</div>

						<c:if test="${status.count mod 3 eq 0 }">
							<div class="row"></div>
						</c:if>

					</c:forEach>
				</div>
			</div>
		</div>
	</div>


</div>
