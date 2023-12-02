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
 <center>
        <h1>Add Expense</h1>

        <div class="col-4">
            <form>
                <div class="mb-3">
                    <label for="exampleInputItemName" class="form-label"><b>Item Name</b></label>
                    <input type="text" class="form-control" id="exampleInputItemName">
                </div>
                <div class="mb-3">
                    <label for="exampleInputCategory" class="form-label"><b>Category</b></label>
                    <select class="form-select" aria-label="Default select example" id="exampleInputCategory">
                        <option selected>Food</option>
                        <option value="1">Shopping</option>
                        <option value="2">Travel</option>
                        <option value="3">Health</option>
                    </select>

                </div>
                <div class="mb-3 ">
                    <label for="exampleInputAmount" class="form-label"><b>Amount</b></label>
                    <input type="text" class="form-control" id="exampleInputAmount">

                </div>
                <div class="mb-3 ">
                    <label for="exampleInputDate" class="form-label"><b>Expense Date</b></label>
                    <input type="date" class="form-control" id="exampleInputAmount">

                </div>
                <div class="d-grid gap-2">
                    <a class="btn btn-primary" href="Expense.html" role="button">Submit</a>
                </div>
            </form>
        </div>
    </center>

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