

function loadAllExpense() {
	
    $.ajax({
        url: "/api/expenses",
        type: "get",
        success: function (data) {
        	const content= document.querySelector("#content");
        	
        	var str=""
        		var i =1;
        		for (let item of data) {
        			var date = secondsToDate(item.createdDate)
        		  str+=`<tr>

						<th scope="row">${i++}</th>
						<td>${date}</td>
						<td>${item.amount}</td>
						<td>${item.description}</td>
						<td>${item.categoryDTO.name}</td>
						<td><button type="button" class="btn btn-light">Edit</button>
        			  		<button onclick ="deleteExpenseById(${item.id})" type="button" class="btn btn-danger">Delete</button></td>
					</tr>`
        	}
        		
        		content.innerHTML=str;
        	
        }
            
    });
}

window.onload = loadAllExpense();
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

     
 


