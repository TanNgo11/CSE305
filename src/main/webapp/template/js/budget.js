

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
			window.location.href = "/home?msg=add_success";
		},
		error : function(error) {
			window.location.href = "/home?msg=add_error";
			
		}
	});
}


