<body>
	<main>
	<div class="head-title">
		<div class="left">
			<h1>Dashboard</h1>
			<ul class="breadcrumb">
				<li><a href="#">Dashboard</a></li>
				<li><i class='bx bx-chevron-right'></i></li>
				<li><a class="active" href="#">Home</a></li>
			</ul>
		</div>
		<a href="#" class="btn-download"> <i class='bx bxs-cloud-download'></i>
			<span class="text">Download PDF</span>
		</a>
	</div>

	<ul class="box-info">
		<li><i class='bx bxs-calendar-check'></i> <span class="text">
				<h3>1020</h3>
				<p>New Order</p>
		</span></li>
		<li><i class='bx bxs-group'></i> <span class="text">
				<h3>2834</h3>
				<p>Visitors</p>
		</span></li>
		<li><i class='bx bxs-dollar-circle'></i> <span class="text">
				<h3>$2543</h3>
				<p>Total Sales</p>
		</span></li>
	</ul>


	<div class="table-data">
		<div class="order">
			<div class="head">
				<h3>User list</h3>
				<i class='bx bx-search'></i> <i class='bx bx-filter'></i>
				<!-- add Product -->
				<a href="#" id="add"> <i class="fa fa-plus-circle fa-2x mr-2"></i>
					<span>Add new Category</span>
				</a>
			</div>





			<table >
				<thead>
					<tr>
						<th>#</th>
						<th>Type</th>
						<th>Created date</th>
					</tr>
				</thead>
				<tbody id="contentTable">






				</tbody>

			</table>
			<ul id="pagination" class="pagination"></ul>






		</div>

	</div>

	</main>


	<script src="/template/js/AdminRenderCate.js"></script>

</body>