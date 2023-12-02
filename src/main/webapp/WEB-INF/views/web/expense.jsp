<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div class="container">
		<br></br>

		<center>
			<h1 style="color: yellow;">Expense Tracking</h1>
		</center>
		<br></br>
		<nav class="navbar bg-body-tertiary">
			<div class="container-fluid">
				<a> <input class="btn btn-primary btn-lg" type="button"
					value="Add Expense"
					onclick="window.location.href='AddExpense.html'"> <input
					class="btn btn-success btn-lg" type="button" value="Expense Report"
					onclick="window.location.href='ExpenseReport.html'"></a>
				<form class="d-flex" role="search">
					<input class="form-control me-2" type="search" placeholder="Search"
						aria-label="Search"> <input class="btn btn-info"
						type="button" value="Filter"></a>
				</form>
			</div>
		</nav>

		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Created Date</th>
					<th scope="col">Amount</th>
					<th scope="col">Category</th>
					<th scope="col">Description</th>
					<th scope="col">Action Item</th>
				</tr>
			</thead>
			<tbody id="content">
				
				
			</tbody>
		</table>
	</div>

	<script src="<c:url value='/template/js/main.js'/>"></script>

</body>
</html>