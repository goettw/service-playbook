<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>

<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><tiles:getAsString name="title" /> <c:if
		test="${subtitle} ">/</c:if> ${subtitle}</title>
<script src="<c:url value='/bootstrap/assets/js/jquery.js'/>"></script>


<link href="<c:url value='/bootstrap/dist/css/bootstrap-flatly.css'/>"
	type="text/css" rel="stylesheet" />
<link href="getbootstrap.com/2.3.2/assets/css/bootstrap.css"
	rel="stylesheet" />
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
				<a class="navbar-brand" href="<%=request.getContextPath()%>">Service
					Playbook</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="<%=request.getContextPath()%>">Home</a></li>


					<sec:authorize access="hasAnyRole('ROLE_Author')">

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">Authoring <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a
									href="<%=request.getContextPath()%>/admin/bigPlayItemList">BigPlays</a></li>
								<li><a
									href="<%=request.getContextPath()%>/admin/serviceOfferList">Service
										Offers</a></li>
								<c:if test="${not empty editUrl}">
									<li class="divider" />
									<li><a href="<%=request.getContextPath()%>${editUrl}">Edit</a></li>
								</c:if>
							</ul></li>

					</sec:authorize>
					
					<sec:authorize access="hasAnyRole('ROLE_Administrator')">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">Admin <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a
									href="<%=request.getContextPath()%>/admin/profileList">User
										Profiles</a></li>
								<li><a
									href="<%=request.getContextPath()%>/admin/profile/new">New
										User Profile</a></li>
							</ul>
						<li>
					</sec:authorize>

					<li><a
						href="<%=request.getContextPath()%>/admin/servicePlaybookDescription">About</a></li>
				</ul>
				
				
				<div class="navbar-text pull-right">
					<sec:authorize access="isAuthenticated()">
						<spring:message code="welcome" />
						<sec:authentication property="principal.firstName" />&nbsp;<sec:authentication
							property="principal.lastName" />
						<c:url var="logoutUrl" value="/logout" />&nbsp;|&nbsp;
							<a class="navbar-link" href="${logoutUrl}"><spring:message
								code="logout" /></a>

					</sec:authorize>
					<sec:authorize access="isAnonymous()">
						<c:url var="loginUrl" value="/loginForm" />
						<a class="navbar-link" href="${loginUrl}"><spring:message
								code="login" /></a>
					</sec:authorize>
				</div>
			</div>
			<!--/.nav-collapse -->

		</div>
	</div>
	<div class="container theme-showcase">

		<div id="content" class="container theme">
			<tiles:insertAttribute name="content" />
		</div>



		<hr />

		<footer>
			<p>Service Playbook is created by Wolfgang G&ouml;tte, based on
				Pivotal 3rd Generation Platform Technology</p>
		</footer>

	</div>
	<script src="<c:url value='/bootstrap/js/bootstrap.js'/>"></script>

</body>
</html>

