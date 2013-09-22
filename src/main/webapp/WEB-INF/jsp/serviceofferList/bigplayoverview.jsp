<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<c:forEach items="${sessionContext.serviceNavigator.bigDataCatalog}"
		var="level1Item">
		<div class="row">
			<div class="col-md-2">


				<div class="well well-sm">
					${level1Item.label}</div>
					
			</div>
			<div class="col-md-10">
				<c:forEach items="${level1Item.entries}" var="level2Item">
					<div class="col-md-4">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="panel-title">${level2Item.label}</div>
							</div>
							<div class="panel-body">
								<ul class="ref">
									<c:forEach items="${level2Item.entries}" var="level3Item">


										<li class="ref"><a
											href="<c:url value='/serviceOffer/${level3Item.id}'/>">${level3Item.label}</a></li>



									</c:forEach>
								</ul>
							</div>
						</div>

					</div>
				</c:forEach>
			</div>
		</div>
	</c:forEach>
</div>
