
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<div class="container well well-sm">

	<div class="col-md-8">

		<tiles:insertAttribute name="header" />
	</div>
	<div class="col-md-4">
		<tiles:insertAttribute name="image" />
	</div>
</div>

<div class="row">
	
	<tiles:insertAttribute name="body" />

</div>



