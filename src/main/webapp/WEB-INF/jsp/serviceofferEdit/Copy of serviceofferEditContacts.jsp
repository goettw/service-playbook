<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="<c:url value='/bootstrap/js/bootstrap-typeahead.js'/>"></script>



<c:url var="thUrl" value="/rest/searchProfileByName" />
<c:url var="profileUrl" value='/auth/profile?id='/>
					<jsp:include page="../include/typeahead.jsp">
						<jsp:param value="'${thUrl}'" name="url" />
						<jsp:param value="test" name="callback" />
						<jsp:param value="'.typeahead'" name="selector" />
					</jsp:include>


<script>
	function test(item,target) {
		var index =  target.id.indexOf(".") ;
		var prefix = target.id.substring(0,index) ;
		$("#"+prefix+"\\.url").val("${profileUrl}"+item.payload.username);
		$("#"+prefix+"\\.role").val(item.payload.emcFunction);
		$("#"+prefix+"\\.username").val(item.payload.username);
		console.log("TEST"+target.id.substring(0,index) );
		
	}
</script>
<div class="container">
				<script>
				
				
				
			
					$(document).ready(function() {

								
									
										
										$("#addContact").on("click",function() {
															var index = $('#contacts tr').length - 1;
															$("#contacts").append(
														 '<tr> \
															<td><input style="width:100%" readonly="true" id="emcContacts' + index +'.username" name="emcContacts[' + index + '].username" type="hidden" value=""  /> \
															<input class="typeahead" style="width:100%" id="emcContacts' + index +'.label" name="emcContacts[' + index + '].label" type="text" value="" data-provide="typeahead" autocomplete="off" /></td> \
															<td><input style="width:100%" id="emcContacts' + index +'.url" name="emcContacts[' + index + '].url" type="text" value=""  /></td> \
															<td><input style="width:100%" id="emcContacts' + index +'.role" name="emcContacts[' + index + '].role" type="text" value=""  /></td> \
															<td><input type="checkbox" id="emcContacts' + index +'.responsible" name="emcContacts[' + index + '].responsible" type="text" value="" /></td> \
															<td><a href="javascript:void(0);" class="remContact btn btn-xs btn-default"><spring:message code="remove"/></a></td>\
														</tr>');
															
															$('.typeahead').trigger('added');	
															
														});

										$("#contacts").on('click','.remContact',function() {
															var index = $(this).closest("tr").index() - 1;
															var length = $(this).closest('table tr').length;
															while (index <= (length)) {
																var thisName = "#emcContacts" + index;
																var otherName = "#emcContacts" + (index + 1);
																$(thisName	+ "\\.username").val($(otherName	+ "\\.username").val());
																$(thisName	+ "\\.url").val($(otherName	+ "\\.url").val());
																$(thisName	+ "\\.label").val($(otherName + "\\.label").val());
																$(thisName	+ "\\.role").val($(otherName + "\\.role").val());
																$(thisName	+ "\\.responsible").val($(otherName	+ "\\.responsible").val());
																index++;
															}
															$(this).closest("table").find("tr:last").remove();
														});
									});
				</script>
				<table id="contacts" class="table">
					<tbody>
						<tr>
							<th ></th>
							<th></th>
							<th><spring:message code="displayName"/></th>
							<th><spring:message code="url"/></th>
							<th><spring:message code="role"/></th>
							<th><spring:message code="responsible"/></th>
							<th><spring:message code="action"/></th>
						</tr>
						<c:forEach var="contact" varStatus="status"
							items="${serviceOffer.emcContacts}">
							<tr>
								
								<td style="width:0%"><form:input path="emcContacts[${status.index}].username" readonly="true" style="width:50%" type="hidden"/></td>
								<td style="width:0%"><c:if test="${contact.username != null}"><span class="glyphicon glyphicon-link"></span></c:if></td>
								<td><form:input class="typeahead" path="emcContacts[${status.index}].label" data-provide="typeahead" autocomplete="off" style="width:100%"/></td>
								<td><form:input path="emcContacts[${status.index}].url"	 style="width:100%"/></td>
								<td><form:input path="emcContacts[${status.index}].role" style="width:100%"/></td>
								<td><form:checkbox path="emcContacts[${status.index}].responsible"/></td>
								<td><a href="javascript:void(0);" class="remContact btn btn-xs btn-default"><spring:message code="remove"/></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div style="text-align: right; width: 99%;">
					<a href="javascript:void(0);" id="addContact" class="btn btn-xs btn-default"><spring:message code="add"/></a>
				</div>
			</div>