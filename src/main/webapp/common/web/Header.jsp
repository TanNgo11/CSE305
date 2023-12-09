<%@ page import="com.moneymanagement.utils.SecurityUtils"%>
<header class="header">

	<div class="container">
		<div class="row">
			<div class="col-md-4 pt-2 myCustom">
				<nav class="navbar  navbar-expand-lg bg-body-tertiary">

					<a class="navbar-brand" href="#"><img src="/inc/icon.png"
						alt=""></a>
					<button class="navbar-toggler" type="button"
						data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav me-auto mb-2 mb-lg-0">

							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" role="button"
								data-bs-toggle="dropdown" aria-expanded="false"> Expense </a>
								<ul class="dropdown-menu">
									<li><a class="dropdown-item" href="/expense">Expense
											Table</a></li>
									<li><a class="dropdown-item" href="/expense/add">Add
											Expense</a></li>

								</ul></li>

							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" role="button"
								data-bs-toggle="dropdown" aria-expanded="false"> Report </a>
								<ul class="dropdown-menu">
									<li><a class="dropdown-item" href="/chart/piechart?type=week">Pie Chart
											</a></li>
									<li><a class="dropdown-item" href="/chart/columnchart/2023">Column Chart</a></li>

								</ul></li>


							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" role="button"
								data-bs-toggle="dropdown" aria-expanded="false"> <security:authorize
										access="isAuthenticated()">
										<%=SecurityUtils.getPrincipal().getFullName()%>
									</security:authorize>
							</a>
								<ul class="dropdown-menu">
									<!--  <li><a class="dropdown-item" href="#">Login</a></li> -->
									<li><a class="dropdown-item" href="/logout">Logout</a></li>
								</ul></li>
						</ul>



					</div>

				</nav>
			</div>
			<div class="col-md-4 pl-5 pt-2">

				<a style="text-decoration: none" href="/expense"><h1 style="color: #333333; margin-top: 2rem; text-align: center">Expense
					Tracking</h1></a>

			</div>
			<div class="col-md-4 pt-3">
				<form style="margin-top: 2rem;" class="d-flex" role="search">
					<input id="queryHeader" class="form-control me-2" type="search" placeholder="Search"
						aria-label="Search">
					<button onclick='linkToSearchPage($("#queryHeader").val())' class="btn btn-outline-success" type="submit">Search</button>
				</form>
			</div>
		</div>

	</div>

</header>