<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="panel panel-default">
	<div class="panel-heading">
		<spring:message code="comments" />
	</div>
	<div class="panel-body">

		<c:forEach items="${comments}" var="commentItem">

			
				<div class="pull-right"><b>${commentItem.personName}</b>:
				<fmt:formatDate type="both" value="${commentItem.dateTime}" timeZone="CET" /></div>
				<br/>
			<B>${commentItem.headline}</B>
<br/>
			
	${commentItem.comment}
	<hr/> 
	</c:forEach>
		<c:url var="addUrl" value="/auth/serviceOffer/submitComment" />
		<form:form method="POST" action="${addUrl}" commandName="comment" role="form">

			<form:input type="hidden" path="localEntity.collectionName" />
			<form:input type="hidden" path="localEntity.id" />
			<form:input type="hidden" path="localEntity.folder" />


			<div class="control-group">
				<form:label path="headline">
					<spring:message code="headline" />
				</form:label>
				<div class="controls">
					<form:input path="headline" readonly="false" style="width:100%" />
					<form:errors path="headline" class="error"></form:errors>
				</div>
			</div>
			<div class="control-group">
				<form:label path="comment">
					<spring:message code="comment" />
				</form:label>
				<div class="controls">
					<form:textarea path="comment" readonly="false" style="width:100%" />
					<form:errors path="comment" class="error"></form:errors>
				</div>
			</div>
			<button value="addComment" name="action" type="submit" class="btn btn-primary btn-xs">
				<spring:message code="saveComment" />
			</button>
		</form:form>
	</div>
</div>