<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body >
	<div class="container">
		<form>

			 <input type="hidden" id="id" class="form-control" value="${id}">

		
			<div class="form-group">
				<label for="exampleInputEmail1">Amount</label> <input type="number"
					id="amount" class="form-control" value="${amount}">

			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">Category</label> <select
					id="categories" class="form-control" value="${category}">


				</select>
			</div>
			<div class="form-group">
				<label for="">Description</label> <input type="text"
					id="description" class="form-control" value="${description}">
			</div>


			<button id="editExpense" type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
	<script type="text/javascript">
	console.log("hah")
	let formData = new FormData();


	$('#editExpense').click(function(e) {
	    e.preventDefault();
	    formData.append("id", $("#id").val());
	    formData.append("amount", $("#amount").val());
	    formData.append("description", $("#description").val());
	    formData.append("cateId", $("#categories").val());
	    

	    updateExpense(formData);
	});
	function updateExpense(formData) {

		$.ajax({
			url : '/api/expense',
			type : 'PUT',
			data : formData,
			cache : false,
			contentType : false,
			processData : false,
			success : function(result) {
				window.location.href = "/home?msg=add_success";
			},
			error : function(error) {
				window.location.href = "/home?msg=add_error";
			}
		});
	}


	
	</script>

</body>
</html>