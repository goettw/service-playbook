<!-- Modal -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url var="addUrl" value="/rest/searchProfileByName" />

<script>
 $(function() {
//twitter bootstrap script
    $("button#submit").click(function(){
    	$("#myModal").modal('hide'); 
    });
});
</script>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Modal title</h4>
			</div>
			<div class="modal-body">
				<div class="container">
					<jsp:include page="../include/typeahead.jsp">
						<jsp:param value="'${addUrl}'" name="url" />
						<jsp:param value="test" name="callback" />
						<jsp:param value="'input.typeahead'" name="selector" />
					</jsp:include>
			
					<input class="typeahead" type="text" data-provide="typeahead" autocomplete="off"> 
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" id="submit">Save changes</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->