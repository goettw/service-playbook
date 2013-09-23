<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="edit">
	<c:url var="addUrl" value="/admin/bigPlayItem/submit" />
	<form:form method="POST" action="${addUrl}" commandName="bigPlayItem">
		<div class="container">
			<div class="row">
				<input type="submit" value="Save" name="action"
					class="btn btn-default btn-xs" /> <input type="Submit"
					value="Cancel" name="action" class="btn btn-default btn-xs" />
			</div>
			<div class="row">
				<div class="row">
					<div class=" col-md-4">
						<div class="form-group">
							<form:label path="id">_id</form:label>
							<form:input path="id" readonly="true" class="form-control" />
						</div>


						<div class="form-group">
							<form:label path="sortOrderNo">sortOrderNo</form:label>
							<form:input path="sortOrderNo" class="form-control" />
						</div>
					</div>
					<div class=" col-md-4">


						<div class="form-group">
							<form:label path="level1">Level 1</form:label>
							<form:input path="level1" class="form-control" />
						</div>


						<div class="form-group">
							<form:label path="level2">Level 2</form:label>
							<form:input path="level2" class="form-control" />
						</div>


					</div>
					<div class="form-group col-md-4">
						<form:label path="label">Label</form:label>
						<form:input path="label" class="form-control" readonly="true" />

					</div>

				</div>
				<div class="row">

					<div class="form-group col-md-12">
						<form:label path="summary">Summary</form:label>
						<form:textarea path="summary" class="form-control"
							style="height:100px;" />
					</div>
				</div>

				<div class="row">

					<div class="form-group col-md-4">
						<form:label path="customerQualification">Kundenqualifikation</form:label>
						<form:textarea path="customerQualification" class="form-control"
							style="height:250px;" />
					</div>
					<div class="form-group col-md-4">
						<form:label path="painPoints">Pain Points</form:label>
						<form:textarea path="painPoints" class="form-control"
							style="height:250px;" />
					</div>
					<div class="form-group col-md-4">
						<form:label path="vision">Vision</form:label>
						<form:textarea path="vision" class="form-control"
							style="height:250px;" />
					</div>
				</div>
			</div>
		</div>
	</form:form>
</div>