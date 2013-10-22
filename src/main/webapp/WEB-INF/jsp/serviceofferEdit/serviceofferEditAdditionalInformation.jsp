<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	<div class="container">
					<script>
						$(document)
								.ready(
										function() {
											$("#addAddInfo")
													.click(
															function() {
																var index = $('#relatedInformation tr').length - 1;
																$("#relatedInformation")
																		.append(
																				'<tr> \
															<td><input style="width:100%" id="relatedInformation' + index +'.label" name="relatedInformation[' + index + '].label" type="text" value=""/></td> \
															<td><input style="width:100%" id="relatedInformation' + index +'.url" name="relatedInformation[' + index + '].url" type="text" value=""/></td> \
															<td><a href="javascript:void(0);" class="remAddInfo btn btn-xs btn-default"><spring:message code="remove"/></a></td>\
														</tr>'); 
															});

											$("#relatedInformation").on(
													'click',
													'.remAddInfo',
													function() {
														var index = $(this).closest("tr").index()-1;
														var length = $(this).closest('table tr').length;
														while(index <= (length)) {
															var thisName = "#relatedInformation" + index;
															var otherName ="#relatedInformation" + (index+1);
															$(thisName+"\\.url").val($(otherName+"\\.url").val());
															$(thisName+"\\.label").val($(otherName+"\\.label").val());
															
															index++;
														}
														$(this).closest("table").find("tr:last").remove();
													});
										});
					</script>
					<table id="relatedInformation" class="table">
						<tbody>
																	<tr>
											<th><spring:message code="label"/></th>
											<th><spring:message code="url"/></th>
											<th><spring:message code="action"/></th>
										</tr>
										<c:forEach var="link" varStatus="status"
											items="${serviceOffer.relatedInformation}">
											<tr>
												<td><form:input
														path="relatedInformation[${status.index}].label" style="width:100%" /></td>
												<td><form:input
														path="relatedInformation[${status.index}].url" style="width:100%" /></td>
											
									<td><a href="javascript:void(0);" class="remAddInfo"><spring:message code="remove"/></a>

									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div style="text-align: right; width: 99%;">
						<a href="javascript:void(0);" id="addAddInfo"  class="btn btn-xs btn-default"><spring:message code="add"/></a>
					</div>

				</div>


		