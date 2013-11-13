<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script src="<c:url value='/bootstrap/js/bootstrap-typeahead.js'/>"></script>

<script>
	function test(item) {
		$('#hiddenID').val(item.payload.username);
		$("#myModal").modal('hide');
	}
</script>

<input type="text" name="hiddenID" id="hiddenID" />
CONTACTS: ${contacts}
<button class="btn btn-primary" data-toggle="modal" data-target="#myModal">Launch demo modal</button>
<jsp:include page="editContactDialog.jsp"></jsp:include>
