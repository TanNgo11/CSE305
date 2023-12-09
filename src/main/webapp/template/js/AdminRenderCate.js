var homeConfig = {
    pageSize: 10,
    currentPage: 1
}


function loadAllCategories() {

    var str = "";
    $.ajax({
        url: "/admin/api/categories",
        type: "get",
        data: {
            page: homeConfig.currentPage,
            limit: homeConfig.pageSize
        },

        success: function (data) {

            var totalPages = data.totalPage;
            var currentPage = data.currentPage;
            var listResult = data.listResult;

            const content = document.querySelector("#contentTable");
            var i = 1;
            for (let item of listResult) {


                if (item.status === 1) {
                    var status = "active"
                } else {
                    var status = "inactive"
                }
                str += `<tr>
                    <th scope="row">${item.id}</th>
                    <td>${item.name}</td>
                    <td>${status}</td>
                    <td><button onclick ="activeAccountByID(${item.id})" type="button" class="btn btn-secondary">Active</button>
                          <button onclick ="disableAccountByID(${item.id})" type="button" class="btn btn-secondary">Inactive</button>
                          <button style="width:80px" onclick ="editCategory(${item.id})" type="button" class="btn btn-secondary">Edit</button>
                          </td>
                </tr>`
            }
            paging(totalPages, currentPage, function () {
                loadAllCategories()
            });
            content.innerHTML = str;

        }

    });
}

function editCategory(id){
	window.location.href = "/admin/category/edit/"+id;
}

function disableAccountByID(id) {
    $.ajax({
        url: "/admin/api/category/" + id,
        type: "delete",
        success: function (data) {
         
            window.onload = loadAllCategories();

        }

    });
}
function activeAccountByID(id) {
    $.ajax({
        url: "/admin/api/category/" + id,
        type: "put",
        success: function (data) {
          
            window.onload = loadAllCategories();



        }

    });
}
window.onload = loadAllCategories();


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











