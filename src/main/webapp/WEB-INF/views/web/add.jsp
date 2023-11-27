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
				<label for="exampleInputEmail1">Amount</label> <input type="number"
					id="amount" class="form-control">

			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">Category</label> <select
					id="categories" class="form-control">


				</select>
			</div>
			<div class="form-group">
				<label for="">Description</label> <input type="text"
					id="description" class="form-control">
			</div>


			<button id="addExpense" type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
	<script src="<c:url value='/template/js/ExpenseAndCategoreis.js'/>"></script>
	<script type="text/javascript">
		window.onload = loadCategories();
	</script>


</body>
</html>