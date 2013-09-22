<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table style="font-size: 11px;">
	<tbody>
		<c:forEach items="${sessionContext.serviceNavigator.bigDataCatalog}"
			var="level1Item">
			<tr>
				<td
					style="background-color: rgb(44, 148, 220); width: 60px; color: rgb(255, 255, 255); text-align: center;">${level1Item.label}
				</td>
				<c:forEach items="${level1Item.entries}" var="level2Item">
					<td style="vertical-align: top;">
						<table style="font-size: 11px;">
							<tbody>
								<tr height="30px"
									style="vertical-align: top; color: rgb(255, 255, 255)">
									<td
										style="background-color: rgb(44, 148, 220); vertical-align: middle; text-align: center; width:300px;">${level2Item.label}</td>
								</tr>
								<c:forEach items="${level2Item.entries}" var="level3Item">
									<tr>
										<td style="">
											<ul class="ref">
												<li class="ref"><a
													href="<c:url value='/serviceOffer/${level3Item.id}'/>"
													style="">${level3Item.label}</a></li>

											</ul>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</tbody>
</table>
