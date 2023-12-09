<body>
	<main>
	<div class="head-title">
		<div class="left">
			<h1>Dashboard</h1>
			<ul class="breadcrumb">
				<li><a href="#">Dashboard</a></li>
				<li><i class='bx bx-chevron-right'></i></li>
				<li><a class="active" href="/admin/category">Category</a></li>
			</ul>
		</div>

	</div>




	<div class="table-data">
		<div class="order">
			<div class="head">
				<h3>Add New Category</h3>
				<i class='bx bx-search'></i> <i class='bx bx-filter'></i>
				<!-- add Product -->

			</div>



			<div class="container">
				<center class="mt-5 customeText">

					<div class="col-4 customForm">
						<form action="/admin/category/add" method = "POST">
							<div class="mb-3 ">
								<label for="exampleInputAmount" class="form-label"><b>Category</b></label>
								<input  name = "categoryName" type="text" class="form-control" id="categoryName" required>

							</div>

							<div class="d-grid gap-2">
								<button class="btn btn-primary" id="addCategory" role="button">Submit</button>
							</div>
						</form>
					</div>
				</center>

			</div>












		</div>

	</div>

	</main>




</body>