function loadCategories(){
	
	  $.ajax({
	        url: "/api/categories",
	        type: "get",
	        success: function (data) {
	        	console.log(data)
	        	const categories= document.querySelector("#categories");
	        	
	        	var str =""
	        		for (let item of data) {
	        		  str+=`<option value="${item.id}">${item.name}</option>`
	        	}
	        	categories.innerHTML=str;
	        	 
	        }
	            
	    });
}




let formData = new FormData();


$('#addExpense').click(function(e) {
    e.preventDefault();

    formData.append("amount", $("#amount").val());
    formData.append("description", $("#description").val());
    formData.append("cateId", $("#categories").val());
    formData.append("date", $("#date").val());
    

    addExpense(formData);
});



function addExpense(formData) {

	$.ajax({
		url : '/api/expense',
		type : 'POST',
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