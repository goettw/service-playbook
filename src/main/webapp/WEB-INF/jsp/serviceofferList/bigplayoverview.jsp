<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<c:forEach items="${sessionContext.serviceNavigator.bigDataCatalog}"
		var="level1Item">
		<div class="row">
			<div class="col-md-1" style="padding-right: 0px;">


				<div class="well well-sm">${level1Item.label}</div>

			</div>
			<div class="col-md-11">
				<c:forEach items="${level1Item.entries}" var="level2Item">
					<div class="col-md-4">
						<div class="panel panel-info">
							<div class="panel-heading">
								<div class="panel-title">${level2Item.label}</div>
							</div>
							<div class="panel-body">
								
								<div class="row">
									<ul class="list-group list-info">
										<c:forEach items="${level2Item.entries}" var="level3Item">
											<a class="list-group-item"
												href="<c:url value='/serviceOffer/${level3Item.id}'/>">${level3Item.label}</a>
										</c:forEach>
									</ul>
								</div>

							</div>
							<div align="right">
									<a
										href="<c:url value='/bigPlayItem/${level2Item.localEntity.id}'/>" class="btn btn-xs btn-info">Details</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</c:forEach>
</div>
