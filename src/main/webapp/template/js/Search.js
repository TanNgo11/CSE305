var homeConfig = {
    pageSize: 10,
    currentPage: 1
}





var queryString = window.location.search;


var urlParams = new URLSearchParams(queryString);


var qValue = urlParams.get('q');

function linkToSearchPage(query) {
	
	   window.location.href = "/expense/search?q="+query
}



function loadAllSearchExpense(qValue) {
	
    $.ajax({
        url: "/api/expenses/search?q="+qValue,
        type: "get",
        data: {
            page: homeConfig.currentPage,
            limit: homeConfig.pageSize
        },
        success: function (data) {
        	
        	 var totalPages = data.totalPage;
        	
             var currentPage = data.page;
             
          
             
             const content= document.querySelector("#content");
             let listResult = data.listResult
             var i = 1 * currentPage ;
             var str=""
        		if(currentPage!==1)
        		i+=1;
        		
        		for (let item of listResult) {
        			var date = secondsToDate(item.createdDate)
        		 		str+=`<tr>

						<th scope="row">${i++}</th>
						<td>${date}</td>
						<td>${formatVND(item.amount)}</td>
						<td>${item.description}</td>
						<td>${item.categoryDTO.name}</td>
						<td><button onclick ="editExpenseById(${item.id})" type="button" class="btn btn-light">Edit</button>
        			  		<button onclick ="deleteExpenseById(${item.id})" type="button" class="btn btn-danger">Delete</button></td>
					</tr>`
        	}
        		 paging(totalPages, currentPage, function () {
        			 loadAllSearchExpense(qValue)
                 });
        		
        		content.innerHTML=str;
        	
        }
            
    });
}


function paging(totalPages, currentPage, callback) {

    $('#pagination').twbsPagination({
        totalPages: totalPages,
        visiblePages: 10,
        startPage: currentPage,
        onPageClick: function (event, page) {
            homeConfig.currentPage = page;
            setTimeout(callback, 100);
        }
    });

}
function editExpenseById(id) {
	window.location.href = "/expense/edit/"+id
}

function deleteExpenseById(id){
	$.ajax({
		url : '/api/expense/'+id,
		type : 'DELETE',
		success : function(result) {
			initPage()
			createToast("success_delete")
		},
		error : function(error) {
			initPage()
			createToast("error_delete")
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

function loadCurrentBudget() {
	
		$.ajax({
			url : "/api/budget/",
			type : "get",
			success : function(data) {
				console.log(data)
				$("#budget").html(formatVND(data.targetAmount));
				$("#currentAmount").html(formatVND(data.currentAmount));
				$("#endDate").html(secondsToDate(data.createdDate));
				 
			},
			error : function(error) {
				console.log(error)
			}

		});
	

}

function formatVND(number) {
	 
	  if (typeof Intl.NumberFormat === 'function') {
	    return number.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
	  } 
	    
	    return number.toLocaleString('vi-VN') + ' VND';
	  
}



window.onload = initPage()
function initPage(){
	loadAllSearchExpense(qValue);
	loadCurrentBudget()
}
