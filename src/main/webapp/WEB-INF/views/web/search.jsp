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

		<h2>Search</h2>




		<nav class="navbar bg-body-tertiary">
			<div class="container-fluid">
				<a> <input class="btn btn-primary btn-lg" type="button"
					value="Add Expense" onclick="window.location.href='/expense/add'">
					<input class="btn btn-success btn-lg" type="button"
					value="Expense Report"
					onclick="window.location.href='chart/piechart?type=week'">
					<input class="btn btn-success btn-lg" type="button"
					value="Set Budget" onclick="window.location.href='/budget'"></a>




				<form class="d-flex" role="search">
					<input id="query" class="form-control me-2" type="search"
						placeholder="Search" aria-label="Search"> <input
						value="Filter" onclick='linkToSearchPage($("#query").val())'
						id="filter" class="btn btn-info" type="button"></a>
				</form>
			</div>
		</nav>

		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Created Date</th>
					<th scope="col">Amount</th>
					<th scope="col">Description</th>
					<th scope="col">Category</th>
					<th scope="col">Action Item</th>
				</tr>
			</thead>
			<tbody id="content">


			</tbody>
		</table>
		<ul id="pagination" class="pagination"></ul>
	</div>

	<script src="<c:url value='/template/js/Search.js'/>"></script>

</body>
</html>