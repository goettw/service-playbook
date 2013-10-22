<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container">
				<script>
					$(document)
							.ready(
									function() {
										$("#addContact")
												.click(
														function() {
															var index = $('#contacts tr').length - 1;
															$("#contacts")
																	.append(
																			'<tr> \
															<td><input style="width:100%" id="emcContacts' + index +'.label" name="emcContacts[' + index + '].label" type="text" value="" /></td> \
															<td><input style="width:100%" id="emcContacts' + index +'.url" name="emcContacts[' + index + '].url" type="text" value=""  /></td> \
															<td><input style="width:100%" id="emcContacts' + index +'.role" name="emcContacts[' + index + '].role" type="text" value=""  /></td> \
															<td><input type="checkbox" id="emcContacts' + index +'.responsible" name="emcContacts[' + index + '].responsible" type="text" value="" /></td> \
															<td><a href="javascript:void(0);" class="remContact btn btn-xs btn-default"><spring:message code="remove"/></a></td>\
														</tr>');
														});

										$("#contacts")
												.on(
														'click',
														'.remContact',
														function() {
															var index = $(this)
																	.closest(
																			"tr")
																	.index() - 1;
															var length = $(this)
																	.closest(
																			'table tr').length;
															while (index <= (length)) {
																var thisName = "#emcContacts"
																		+ index;
																var otherName = "#emcContacts"
																		+ (index + 1);
																$(
																		thisName
																				+ "\\.url")
																		.val(
																				$(
																						otherName
																								+ "\\.url")
																						.val());
																$(
																		thisName
																				+ "\\.label")
																		.val(
																				$(
																						otherName
																								+ "\\.label")
																						.val());
																$(
																		thisName
																				+ "\\.role")
																		.val(
																				$(
																						otherName
																								+ "\\.role")
																						.val());
																$(
																		thisName
																				+ "\\.responsible")
																		.val(
																				$(
																						otherName
																								+ "\\.responsible")
																						.val());

																index++;
															}
															$(this)
																	.closest(
																			"table")
																	.find(
																			"tr:last")
																	.remove();
														});
									});
				</script>
				<table id="contacts" class="table">
					<tbody>
						<tr>
							<th><spring:message code="label"/></th>
							<th><spring:message code="url"/></th>
							<th><spring:message code="role"/></th>
							<th><spring:message code="responsible"/></th>
							<th><spring:message code="action"/></th>
						</tr>
						<c:forEach var="contact" varStatus="status"
							items="${serviceOffer.emcContacts}">
							<tr>
								<td><form:input path="emcContacts[${status.index}].label"
										 style="width:100%"/></td>
								<td><form:input path="emcContacts[${status.index}].url"
										 style="width:100%"/></td>
								<td><form:input path="emcContacts[${status.index}].role"
									 style="width:100%"/></td>
								<td><form:checkbox
										path="emcContacts[${status.index}].responsible"
									 /></td>
								<td><a href="javascript:void(0);" class="remContact btn btn-xs btn-default"
									><spring:message code="remove"/></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div style="text-align: right; width: 99%;">
					<a href="javascript:void(0);" id="addContact"
						class="btn btn-xs btn-default"><spring:message code="add"/></a>
				</div>

			</div>

