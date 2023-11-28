<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<form>


			<div class="form-group">
				<label for="exampleInputEmail1">Target Amount</label> <input
					type="number" id="amount" class="form-control">

			</div>

			<div class="form-group">
				<label for="exampleInputEmail1">End Date</label> <input id="endDate"
					type="date" class="form-control">

			</div>
			<button id="addBudget" type="button" class="btn btn-primary">Submit</button>
		</form>
	</div>

	<script src="<c:url value='/template/js/budget.js'/>"></script>




</body>
</html>