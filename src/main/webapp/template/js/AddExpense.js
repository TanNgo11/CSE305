function loadCategories(){
	
	  $.ajax({
	        url: "/api/categories",
	        type: "get",
	        success: function (data) {
	        	console.log(data)
	        	const categories= document.querySelector("#categories");
	        	
	        	var str =""
	        		for (let item of data) {
	        			if(item.status!==0)
	        			 
	                          str += `<option value="${item.id}">${item.name}</option>`;
	                      
	        	}
	        	categories.innerHTML=str;
	        	 
	        }
	            
	    });
}

window.onload = loadCategories();


var formData = new FormData();


$('#addExpense').click(function(e) {
    e.preventDefault();
   

   
    formData.append("description", $("#description").val());
    
   
    	 formData.append("amount", $("#amount").val());
    
    	 formData.append("cateId", $("#categories").val());
    
    	
    
    

    addExpense(formData);
    formData = new FormData();
   
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
			createToast("success_add")
		},
		error : function(error) {
			createToast("error_add")
		}
	});
}

// window.addEventListener('load', function() {
//   
// checkTypeOfExpense();
//    
//   
//   
// });

// document.getElementById('typeOfExpense').addEventListener('change',
// function() {
//   
// checkTypeOfExpense();
// });


// function checkTypeOfExpense(){
// var selectedValue = document.getElementById('typeOfExpense').value;
//		
//	   
// var categorySelect = document.getElementById('categories');
//	    
// var categorySLabel = document.getElementById('categoryLabel');
//	   
// if (selectedValue === '13') {
//	   
// categorySelect.style.display = 'none';
// categorySLabel.style.display = 'none';
//	  
// } else {
// categorySLabel.style.display = 'block';
// categorySelect.style.display = 'block';
// }
// }

