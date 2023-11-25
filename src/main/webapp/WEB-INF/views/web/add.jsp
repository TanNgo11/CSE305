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
					onload="loadCategories()" id="categories" class="form-control">


				</select>
			</div>
			<div class="form-group">
				<label for="">Description</label> <input type="text"
					id="description" class="form-control">
			</div>


			<button id="addExpense" type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
	<script type="text/javascript">
	function loadCategories() {

	    $.ajax({
	        url: "/api/categories",
	        type: "get",
	        success: function (data) {
	        	const categories= document.querySelector("#categories");
	        	var str =""
	        		for (let item of data) {
	        		  str+=`<option value="${item.id}">${item.name}</option>`
	        	}
	        	categories.innerHTML=str;
	        	 
	        }
	            
	    });
}
		
	</script>

</body>
</html>