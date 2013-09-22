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
</head>
<body class="text">
	<tiles:insertAttribute name="banner" />

	<div id="newWrap" style="height: 77px;">
		<div id="contentLeftMenu">
			<tiles:insertAttribute name="navigation" />
		</div>
		<div id="content">
			<div id="midBodyHeader"></div>
			<div id="contentMid561">
				<div class="bannerContainer.marginTop5">
					<img width="550"
						src="<c:url value="/images/ic-offering-greenit.jpg"/>">
					<div class="mid550Banner marginTop20">
						<div class="mid550BannerText servicesOfferingText">
							<tiles:insertAttribute name="header" />
						</div>
					</div>
				</div>
				<div class="solutionSubTopicMid550BannerRight">
					<br>
				</div>
				<div class="clearBoth"></div>
				<div class="contentHalfMidNoTabNoMargin">
					<div class="contentHalfMidNoTabTop"></div>
					<div class="contentHalfMidNoTabBodyInner">
						<div>
							<div>
								<tiles:insertAttribute name="image" />
							</div>

							<tiles:insertAttribute name="floatRight" />
							<div class="marginBottom15">
								<tiles:insertAttribute name="body" />
							</div>
						</div>
					</div>
					<div class="contentHalfMidNoTabBottom">
						<img width="1" height="1" alt="" src="images/common/spacer.gif">
					</div>

				</div>
				<div class="footnote">Service Playbook is based on Pivotal 3rd
					Generation Platform Technology, created by Wolfgang Goette</div>
			</div>


		</div>
	</div>


</body>
</html>

