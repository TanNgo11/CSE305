<%-- 
    Document   : index
    Created on : Jul 18, 2023, 3:51:07 PM
    Author     : TAN
--%>
<%@ include file="/common/taglib.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.moneymanagement.utils.SecurityUtils"%>



<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" />


<link rel="stylesheet"
	href="<c:url value='/template/css/toastmsg.css'/>">



<script src="<c:url value='/template/js/main.js'/>"></script>




<c:if
	test="${pageContext.request.localName =='http://localhost:8080/expense'}">
	<link rel="stylesheet" href="<c:url value='/template/css/text.css'/>">
</c:if>





<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/d6d3d20d54.js"
	crossorigin="anonymous"></script>

</head>
<body>

	<%@ include file="/common/web/Header.jsp"%>

	<div class="container">
		<dec:body />
	</div>



	<div id="toasts">
		<input id="message" type="hidden" value="${msg}">
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
	<script>
		$(document).ready(function() {
			$('.goToTopBtn').click(function() {
				$('html , body').animate({
					scrollTop : 0
				}, 500)
				return false;
			})

			$(window).scroll(function() {
				if ($(this).scrollTop() > 100) {
					$('.goTop_btn').fadeIn();
				} else
					$('.goTop_btn').fadeOut();
			})

		});
	</script>


	<script src="<c:url value='/template/js/toastmessage.js'/>"></script>




</body>
</html>
