<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<html xmlns:sts="http://www.emc.com/strategic-services">
<head>

<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><tiles:getAsString name="title" /> <c:if
		test="${subtitle} ">/</c:if> ${subtitle}</title>
<script src="<c:url value='/bootstrap/assets/js/jquery.js'/>"></script>


<link href="<c:url value='/bootstrap/css/bootstrap.css'/>"
	type="text/css" rel="stylesheet" />
<link href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>"
	type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="<c:url value="/css/theme.css"/>">
<!--[if lt IE 9]>
      <script src="<c:url value='/bootstrap/assets/js/html5shiv.js'/>"></script>
      <script src="<c:url value='/bootstrap/assets/js/respond.min.js'/>"></script>
    <![endif]-->
</head>
<body>


	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Service Playbook</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="<%=request.getContextPath()%>">Home</a></li>
					<li><a href="#about">About</a></li>
					<li><a href="#contact">Contact</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
	<div class="container theme-showcase">

		<div id="content" class="container theme">
			<tiles:insertAttribute name="content" />
		</div>
		
	

<hr/>
		
			<footer><p>Service Playbook is based on Pivotal 3rd Generation
				Platform Technology, created by Wolfgang Goette</p></footer>
		
</div>	
	<script src="<c:url value='/bootstrap/js/bootstrap.js'/>"></script>

</body>
</html>

