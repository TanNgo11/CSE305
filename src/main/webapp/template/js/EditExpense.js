
var currentExpense = {};

$('#editExpense').click(function(e) {
    e.preventDefault();
    let cateId = $("#categories").val();
    
    loadCategoryById(cateId)
        .then(function() {
            updateExpense(currentExpense);
        })
        .catch(function(error) {
            console.error("Error loading category: ", error);
        });
})
function loadCategoryById(id){
	return new Promise(function (resolve, reject) {
        $.ajax({
            url: "/api/category/" + id,
            type: "get",
            success: function (data) {
                currentCategory = data;
                setValueForUpdatedExpense(data,$("#amount").val(),$("#description").val())
                resolve();
                
            },
            error: function (error) {
                console.error("Error loading category: ", error);
                reject(error);
            }
        });
    });
}

function setValueForUpdatedExpense(category, amount,description){
	currentExpense.amount =  amount
	currentExpense.description=description
	currentExpense.categoryDTO = category;
	
}

function updateExpense(currentExpense) {
	let id = currentExpense.id
	
	$.ajax({
		url : '/api/expense/'+id,
		type : 'PUT',
		data: JSON.stringify(currentExpense),
		contentType: 'application/json', 
	
		success : function(result) {
			
			createToast("success_update")
			
		},
		error : function(error) {
			createToast("error_update")
		}
	});
}
function loadExpenseById() {
	var id = etractNumberFromURL()
	
	if (id != null) {
		$.ajax({
			url : "/api/expense/" + id,
			type : "get",
			success : function(data) {
				currentExpense = data;
				
				 $("#id").val(data.id);
				 $("#amount").val(data.amount)
				 $("#description").val(data.description)
				 $("#categories").val(data.categoryDTO.id)
				 
			},
			error : function(error) {

				window.location.href = "/expense"
				

			}

		});
	}

}

function loadCategories(){
	
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



window.onload = function() {
    loadCategories();
    loadExpenseById();
};

function etractNumberFromURL() {
	var currentUrl = window.location.href;

	var currentUrl = window.location.href;

	var number = currentUrl.match(/\/(\d+)\/?$/);

	if (number) {

		var extractedNumber = number[1];

		return extractedNumber;
	} else {
		return null;
	}
}
