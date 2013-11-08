<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>

<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><tiles:getAsString name="title" /> <c:if test="${subtitle} ">/</c:if> ${subtitle}</title>
<script src="<c:url value='/bootstrap/assets/js/jquery.js'/>"></script>

<link rel="icon" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon">
<link href="<c:url value='/bootstrap/dist/css/bootstrap-flatly.css'/>" type="text/css" rel="stylesheet" />
<link href="getbootstrap.com/2.3.2/assets/css/bootstrap.css" rel="stylesheet" />
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
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<%=request.getContextPath()%>/bigPlayOverview">Service Playbook</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">


					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="serviceOffers" /> <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li class="active"><a href="<%=request.getContextPath()%>/bigPlayOverview"><spring:message code="serviceListByBigplay" /></a></li>
							<li><a href="<%=request.getContextPath()%>/auth/serviceList"><spring:message code="serviceList" /></a></li>
							<li><a href="<%=request.getContextPath()%>/auth/serviceListByUpdate"><spring:message code="serviceListByUpdate" /></a></li>




						</ul></li>





					<sec:authorize access="hasAnyRole('ROLE_Author')">

						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="authoring" /> <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="<%=request.getContextPath()%>/admin/bigPlayItemList"><spring:message code="bigPlays" /></a></li>
								<li><a href="<%=request.getContextPath()%>/admin/serviceOfferList"><spring:message code="serviceOffersForAuthors" /></a></li>
								<li class="divider" />
								<c:if test="${not empty editUrl}">

									<li><a href="<%=request.getContextPath()%>${editUrl}"><spring:message code="edit" /></a></li>

								</c:if>
								<li><a href="<%=request.getContextPath()%>/author/serviceOffer/new"><spring:message code="newService" /></a></li>
							</ul></li>

					</sec:authorize>

					<!-- PROFILES -->

					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="profiles" /> <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="<%=request.getContextPath()%>/auth/profileList"><spring:message code="profileList" /></a></li>


							<li class="divider" />
							<li><c:url var="profileEditUrl" value='/auth/profile/edit'>
									<c:param name="id" value="<%=request.getRemoteUser()%>" />
								</c:url> <a href="${profileEditUrl}"><spring:message code="profileEdit" /></a></li>

						</ul></li>


					<li><a href="<%=request.getContextPath()%>/servicePlaybookDescription"><spring:message code="about" /></a></li>




				</ul>




				<ul class="pull-right">
					<li class="navbar-text"><sec:authorize
							access="isAuthenticated()">
							<spring:message code="welcome" />
							<sec:authentication property="principal.firstName" />&nbsp;<sec:authentication property="principal.lastName" />
							<c:url var="logoutUrl" value="/logout" />&nbsp;|&nbsp; <a class="navbar-link" href="${logoutUrl}"><spring:message code="logout" /></a>
						</sec:authorize> <sec:authorize access="isAnonymous()">
							<c:url var="loginUrl" value="/loginForm" />
							<c:url var="registerUrl" value="/register" />
							<a class="navbar-link" href="${registerUrl}"><spring:message code="register" /></a> &nbsp;|&nbsp; <a class="navbar-link" href="${loginUrl}"><spring:message
									code="login" /></a>

						</sec:authorize>
						
						&nbsp;&nbsp; &nbsp; 
						<a class="navbar-link" href="?language=en">EN</a> &nbsp;|&nbsp; <a class="navbar-link" href="?language=de">DE</a> 
						</li>
				</ul>





			</div>
			<!--/.nav-collapse -->

		</div>
	</div>

	<div class="container">
		<tiles:insertAttribute name="breadcrumb" />
	</div>
	<div class="container theme-showcase">

		<div id="content" class="container theme">
			<tiles:insertAttribute name="content" />
		</div>



		<hr />

		<footer>
			<p>Service Playbook is created by Wolfgang G&ouml;tte, based on Pivotal 3rd Generation Platform Technology</p>
		</footer>

	</div>
	<script src="<c:url value='/bootstrap/js/bootstrap.js'/>"></script>

</body>
</html>

