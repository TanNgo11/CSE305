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


let formData = new FormData();


$('#addExpense').click(function(e) {
    e.preventDefault();

    formData.append("amount", $("#amount").val());
    formData.append("description", $("#description").val());
    formData.append("cateId", $("#categories").val());
    

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

function updateProduct(formData) {

	$.ajax({
				url : '/admin/api/v1/products',
				type : 'PUT',
				data : formData,
				enctype : 'multipart/form-data',
				cache : false,
				contentType : false,
				processData : false,
				success : function(result) {
					window.location.href = "/admin/product/edit?id="
							+ result.id + "&msg=update_success";
				},
				error : function(result) {
					window.location.href = "/admin/product/edit?msg=update_error";
				}
			});
}
function loadAllExpense() {

    $.ajax({
        url: "/api/expenses",
        type: "get",
        success: function (data) {
        	const content= document.querySelector("#content");
        	console.log(data)
        	var str =""
        		var i =1;
        		for (let item of data) {
        			var date = secondsToDate(item.createdDate)
        		  str+=`<tr>

						<th scope="row">${i++}</th>
						<td>${date}</td>
						<td>${item.amount}</td>
						<td>${item.description}</td>
						<td>${item.categoryDTO.name}</td>
						<td><button type="button" class="btn btn-primary">Edit</button>
        			  		<button onclick ="deleteExpenseById(${item.id})" type="button" class="btn btn-secondary">Delete</button></td>
					</tr>`
        	}
        		content.innerHTML=str;
        	 
        }
            
    });
}
function deleteExpenseById(id){
	$.ajax({
		url : '/api/expense/'+id,
		type : 'DELETE',
		success : function(result) {
			window.location.href = "/home?msg=add_success";
		},
		error : function(error) {
			window.location.href = "/home?msg=add_error";
		}
	});
	
}

function secondsToDate(seconds) {
	  
	  const date = new Date(seconds);

	  // Format options for date and time
	  const options = {
	    year: 'numeric',
	    month: '2-digit',
	    day: '2-digit',
	    hour: '2-digit',
	    minute: '2-digit',
	    second: '2-digit',
	    hour12: false // Use 24-hour format
	  };

	  // Get date and time components separately
	  const formattedDate = date.toLocaleString('en-US', options);

	  // Extract date and time parts
	  const [datePart, timePart] = formattedDate.split(', ');

	  return `${datePart} ${timePart}`;
	}
