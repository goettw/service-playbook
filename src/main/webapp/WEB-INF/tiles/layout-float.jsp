<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<div class="row">


	<div class="container well well-sm">

		<div class="col-md-8">

			<tiles:insertAttribute name="header" />
		</div>
		<div class="col-md-4">
			<tiles:insertAttribute name="image" />
		</div>
	</div>



</div>
<div class="row">
	<div class="col-md-8">
		<tiles:insertAttribute name="body" />
	</div>
	<div class="col-md-4 ">
		<tiles:insertAttribute name="floatRight" />
	</div>
</div>