var homeConfig = {
    pageSize: 10,
    currentPage: 1
}


function loadAllUsers() {

    
    $.ajax({
        url: "/admin/api/accounts",
        type: "get",
        data: {
            page: homeConfig.currentPage,
            limit: homeConfig.pageSize
        },

        success: function (data) {

            var totalPages = data.totalPage;
            var currentPage = data.page;
            var listResult = data.listResult;




            const content = document.querySelector("#contentTable");
            var i = 1 * currentPage ;
            var str=""
       		if(currentPage!==1)
       		i+=1;
            for (let item of listResult) {


                if (item.status === 1) {
                    var status = "active"
                } else {
                    var status = "inactive"
                }
                str += `<tr>
                    <th scope="row">${item.id}</th>
                    <td>${item.username}</td>
                    <td>${item.fullName}</td>
                    <td>${item.email}</td>
                    <td>${item.address}</td>
                    <td>${item.phoneNumber}</td>
                    <td>${status}</td>
                    <td><button onclick ="activeAccountByID(${item.id})" type="button" class="btn btn-secondary">Active</button>
                          <button onclick ="disableAccountByID(${item.id})" type="button" class="btn btn-secondary">Inactive</button></td>
                </tr>`
            }
            paging(totalPages, currentPage, function () {
                loadAllUsers()
            });
            content.innerHTML = str;

        }

    });
}

function disableAccountByID(id) {
    $.ajax({
        url: "/admin/api/account/" + id,
        type: "delete",
        success: function (data) {
            console.log(data);
            window.onload = loadAllUsers();



        }

    });
}
function activeAccountByID(id) {
    $.ajax({
        url: "/admin/api/account/" + id,
        type: "put",
        success: function (data) {
            console.log(data);
            window.onload = loadAllUsers();



        }

    });
}
window.onload = loadAllUsers();


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











