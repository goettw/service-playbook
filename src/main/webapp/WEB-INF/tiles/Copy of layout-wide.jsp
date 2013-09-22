<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<html xmlns:sts="http://www.emc.com/strategic-services">
<head>

<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" /> <c:if
		test="${subtitle} ">/</c:if> ${subtitle}</title>
<link rel="stylesheet"
	href="<c:url value="/js/jquery/themes/base/jquery.ui.all.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/service.css"/>">
<script src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
<link href="<c:url value='/bootstrap/css/bootstrap.css'/>" type="text/css" rel="stylesheet" />
</head>
<body class="text">
	<tiles:insertAttribute name="banner" />

	<div style="height: 77px;">
	
		<tiles:insertAttribute name="header" />
		<div id="content">
			
						
			
			<div >
				
				
				<div style="border: 1;">

					<table>
					<tr><td>
					<div style="border: 1;">
						<div
							style="border: solid; padding: 10px; border-radius: 6px; border-width: 1px; border-color: rgb(170, 170, 170);">
							<div>
								<a target="_blank" href="resources/bp.jpg"> <!-- img class="body"
									src="resources/bp.jpg"-->
								</a>
							</div>


							<div>
								<tiles:insertAttribute name="body" />
							</div>
						</div>
					</div>


				
				</td><td>
				<tiles:insertAttribute name="floatRight" /></td></tr>
				</table>
				</div>
				<div class="footnote">Service Playbook is based on Pivotal 3rd
					Generation Platform Technology, created by Wolfgang Goette</div>
			</div>


		</div>
	</div>


</body>
</html>

