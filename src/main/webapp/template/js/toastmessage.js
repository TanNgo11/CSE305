const toasts = {

	success_add : {
		icon : '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
		msg : 'Add Expense successful!',
		alert : [ '#4dff4d', '#00b300' ]
	},
	

	success_delete : {
		icon : '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
		msg : 'Delete Expense successful!',
		alert : [ '#4dff4d', '#00b300' ]
	},
	
	success_update : {
		icon : '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
		msg : 'Update Expense successful!',
		alert : [ '#4dff4d', '#00b300' ]
	},
	
	error_add : {
		icon : '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
		msg : 'Add Expense unsuccessful!',
		alert : [ '#ff0000', '#803300' ]
	},
	
	error_delete : {
		icon : '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
		msg : 'Delete Expense unsuccessful!',
		alert : [ '#ff0000', '#803300' ]
	},
	
	error_update : {
		icon : '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
		msg : 'Update Expense unsuccessful!',
		alert : [ '#ff0000', '#803300' ]
	},

	error_notFound : {
		icon : '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
		msg : 'Not Found Resource!',
		alert : [ '#ff0000', '#803300' ]
	}

}

function createToast(status) {
	let toast = toasts[status]

	Toastify(
			{
				text : toast.msg,
				duration : 3000,

				close : true,
				gravity : "top",
				position : "right",

				stopOnFocus : true,

				style : {
					background : `linear-gradient(to right, ${toast.alert[0]}, ${toast.alert[1]})`,
				},

			}).showToast();

}


