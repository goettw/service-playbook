<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<table>
	<tr>
		<td><div>
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
															<td><input id="emcContacts' + index +'.label" name="emcContacts[' + index + '].label" type="text" value="" class="form-control"/></td> \
															<td><input id="emcContacts' + index +'.url" name="emcContacts[' + index + '].url" type="text" value=""  class="form-control"/></td> \
															<td><input id="emcContacts' + index +'.role" name="emcContacts[' + index + '].role" type="text" value=""  class="form-control"/></td> \
															<td><input id="emcContacts' + index +'.responsible" name="emcContacts[' + index + '].responsible" type="text" value=""  class="form-control"/></td> \
															<td><a href="javascript:void(0);" class="remContact btn btn-default">Remove</a></td>\
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
				<table id="contacts">
					<tbody>
						<tr>
							<th>Label</th>
							<th>URL</th>
							<th>Role</th>
							<th>Responsible</th>
						</tr>
						<c:forEach var="contact" varStatus="status"
							items="${serviceOffer.emcContacts}">
							<tr>
								<td><form:input path="emcContacts[${status.index}].label"
										class="form-control" /></td>
								<td><form:input path="emcContacts[${status.index}].url"
										class="form-control" /></td>
								<td><form:input path="emcContacts[${status.index}].role"
										class="form-control" /></td>
								<td><form:input
										path="emcContacts[${status.index}].responsible"
										class="form-control" /></td>
								<td><a href="javascript:void(0);" class="remContact btn btn-default"
									>Remove</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div style="text-align: right; width: 99%;">
					<a href="javascript:void(0);" id="addContact"
						class="btn btn-default">Add</a>
				</div>

			</div>
	</tr>
</table>

