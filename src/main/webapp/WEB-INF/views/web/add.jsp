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
	   <center>
        <h1>Add Expense</h1>

        <div class="col-4">
            <form>
                <div class="mb-3">
                    <label for="exampleInputItemName" class="form-label"><b>Description</b></label>
                    <input type="text" class="form-control" id="description">
                </div>
                <div class="mb-3">
                    <label for="exampleInputCategory" class="form-label"><b>Category</b></label>
                    <select class="form-select" aria-label="Default select example" id="categories">
                      
                    </select>

                </div>
                <div class="mb-3 ">
                    <label for="exampleInputAmount" class="form-label"><b>Amount</b></label>
                    <input type="text" class="form-control" id="amount">

                </div>
               
                <div class="d-grid gap-2">
                    <button class="btn btn-primary" id="addExpense">Submit</button>
                </div>
            </form>
        </div>
    </center>
	
	
	<script src="<c:url value='/template/js/ExpenseAndCategories.js'/>"></script>
	<script type="text/javascript">
		window.onload = loadCategories();
	</script>


</body>
</html>