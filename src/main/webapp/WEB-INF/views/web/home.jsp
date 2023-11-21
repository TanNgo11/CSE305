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
	<header>
		<h1 class="title">Expense tracker</h1>
		<h3 class="title">Home</h3>
		<h3 class="title">About</h3>
		<h3 class="title">Contact</h3>


	</header>
	<div class="container">
		<h1>Personal Expense Manager</h1>
		<button type="button" class="btn btn-outline-primary">Add
			expense</button>
		<button type="button" class="btn btn-outline-secondary">Expense
			report</button>

		<table class="table">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Date</th>
					<th scope="col">Amount</th>
					<th scope="col">Description</th>
					<th scope="col">Category</th>
					<th scope="col">Function</th>
				</tr>
			</thead>
			<tbody id="content">
				
				


			</tbody>
		</table>
</body>
</html>