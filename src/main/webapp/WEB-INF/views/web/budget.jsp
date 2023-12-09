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





	<center class="mt-5 customeText">

		<div class="col-4 customForm">
			<form>

				<div class="mb-3 ">
					<label for="exampleInputAmount" class="form-label"><b>Amount</b></label>
					<input type="number" class="form-control" id="amount" required>

				</div>

				<div class="mb-3 ">
					<label for="exampleInputAmount" class="form-label"><b>End
							Date</b></label> <input type="date" class="form-control" id="endDate"
						required>

				</div>

				<div class="d-grid gap-2">
					<button class="btn btn-primary" id="addBudget" role="button">Submit</button>
				</div>
			</form>
		</div>
	</center>




	<script src="<c:url value='/template/js/budget.js'/>"></script>




</body>
</html>