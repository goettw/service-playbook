<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="container">
	
					<script>
						$(document)
								.ready(
										function() {
											$("#addCaseStudy")
													.click(
															function() {
																var index = $('#caseStudies tr').length - 1;
																$(
																		"#caseStudies")
																		.append(
																				'<tr> \
															<td><input class="form-control" id="caseStudies' + index +'.label" name="caseStudies[' + index + '].label" type="text" value=""/></td> \
															<td><input class="form-control" id="caseStudies' + index +'.url" name="caseStudies[' + index + '].url" type="text" value=""/></td> \
															<td><a href="javascript:void(0);" class="remCaseStudy btn btn-default">Remove</a></td>\
														</tr>');
															});

											$("#caseStudies").on(
													'click',
													'.remCaseStudy',
													function() {
														var index = $(this).closest("tr").index()-1;
														var length = $(this).closest('table tr').length;
														while(index <= (length)) {
															var thisName = "#caseStudies" + index;
															var otherName ="#caseStudies" + (index+1);
															$(thisName+"\\.url").val($(otherName+"\\.url").val());
															$(thisName+"\\.label").val($(otherName+"\\.label").val());
															
															index++;
														}
														$(this).closest("table").find("tr:last").remove();
														//$(this)
														//$(this).parent()
														
															//	.parent().css("border","9px solid red");
																//find("value").val(	"$(this).parent().parent().input.val()");
													});
										});
					</script>
					<table class="table" id="caseStudies">
						<tbody>
							<tr>
								<th>Label</th>
								<th>URL</th>
								<th>Action</th>
							</tr>
							<c:forEach var="caseStudy" varStatus="status"
								items="${serviceOffer.caseStudies}">
								<tr>
									<td><form:input class="form-control" path="caseStudies[${status.index}].label" /></td>
									<td><form:input class="form-control" path="caseStudies[${status.index}].url" /></td>
									<td><a href="javascript:void(0);" class="remCaseStudy btn btn-default">Remove</a>

									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div style="text-align: right; width: 99%;">
						<a href="javascript:void(0);" id="addCaseStudy" class="btn btn-default">Add</a>
					</div>

				</div>
	

