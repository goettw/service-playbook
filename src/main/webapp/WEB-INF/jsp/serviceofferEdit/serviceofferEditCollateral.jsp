<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container">
				<script>
					$(document)
							.ready(
									function() {
										$("#addColl")
												.click(
														function() {
															var index = $('#salesCollateral tr').length - 1;
															$(
																	"#salesCollateral")
																	.append(
																			'<tr> \
															<td><input style="width:100%" id="salesCollateral' + index +'.label" name="salesCollateral[' + index + '].label" type="text" value=""/></td> \
															<td><input style="width:100%" id="salesCollateral' + index +'.url" name="salesCollateral[' + index + '].url" type="text" value=""/></td> \
															<td><a href="javascript:void(0);" class="remColl btn btn-xs btn-default">Remove</a></td>\
														</tr>');
														});

										$("#salesCollateral").on(
												'click',
												'.remColl',
												function() {
													var index = $(this).closest("tr").index()-1;
													var length = $(this).closest('table tr').length;
													while(index <= (length)) {
														var thisName = "#salesCollateral" + index;
														var otherName ="#salesCollateral" + (index+1);
														$(thisName+"\\.url").val($(otherName+"\\.url").val());
														$(thisName+"\\.label").val($(otherName+"\\.label").val());
														
														index++;
													}
													$(this).closest("table").find("tr:last").remove();
												});
									});
				</script>
				<table id="salesCollateral" class="table">
					<tbody>
						<tr>
							<th><spring:message code="label"/></th>
							<th><spring:message code="url"/></th>
							<th><spring:message code="action"/></th>
						</tr>
						<c:forEach var="salesColItem" varStatus="status"
							items="${serviceOffer.salesCollateral}">
							<tr>
								<td><form:input
										path="salesCollateral[${status.index}].label" style="width:100%"/></td>
								<td><form:input path="salesCollateral[${status.index}].url" style="width:100%"/></td>
								<td><a href="javascript:void(0);" class="remColl btn-xs btn btn-default"><spring:message code="remove"/></a>

								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div style="text-align: right; width: 99%;">
					<a href="javascript:void(0);" id="addColl" class=" btn btn-xs btn-default"><spring:message code="add"/></a>
				</div>

			</div>
	

