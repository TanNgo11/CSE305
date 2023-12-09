

$('#addBudget').click(function(e) {

	
	e.preventDefault();
	
	setBudget($("#amount").val(),$("#endDate").val());
});

function setBudget(amount, endDate) {
	var form = {
			"targetAmount":amount,
			"endDate":endDate
		}
	$.ajax({
		url : '/api/budget',
		type : 'POST',
		 dataType: 'json',
		data: JSON.stringify(form),
		contentType: 'application/json',
		success : function(result) {
			createToast("success_add")
		},
		error : function(error) {
			createToast("error_add")
			
		}
	});
}


