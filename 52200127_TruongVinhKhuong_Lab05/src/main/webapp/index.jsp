<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Danh sách sản phẩm</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        function validateForm() {
            var name = document.getElementById("product-name").value;
            var price = document.getElementById("price").value;

            if (name === "" || price === "") {
                alert("Vui lòng nhập tên sản phẩm và giá.");
                return false;
            }

            return true;
        }

        function populateEditForm(id, name, price) {
            document.getElementById("product-name").value = name;
            document.getElementById("price").value = price;

            // Thêm input lưu id sản phẩm
            var actionInput = document.createElement("input");
            actionInput.type = "hidden";
            actionInput.name = "action";
            actionInput.value = "edit";
            var idInput = document.createElement("input");
            idInput.type = "hidden";
            idInput.name = "id";
            idInput.value = id;

            // Xóa các input ẩn cũ nếu có
            var oldActionInput = document.querySelector('input[name="action"]');
            var oldIdInput = document.querySelector('input[name="id"]');
            if (oldActionInput) oldActionInput.remove();
            if (oldIdInput) oldIdInput.remove();

            document.forms[0].appendChild(actionInput);
            document.forms[0].appendChild(idInput);
        }
    </script>
</head>
<body style="background-color: #f8f8f8">

<div class="container-fluid p-5">
    <div class="row mb-5">
        <div class="col-md-6">
            <h3>Product Management</h3>
        </div>
        <div class="col-md-6 text-right">
            <c:if test="${not empty sessionScope.username}">
                Xin chào <span class="text-danger">${sessionScope.username}</span> | <a href="logout">Logout</a>
            </c:if>
        </div>
    </div>
    <div class="row rounded border p-3">
        <div class="col-md-4">
            <h4 class="text-success">Thêm sản phẩm mới</h4>
            <form class="mt-3" method="post" onsubmit="return validateForm();">
                <div class="form-group">
                    <label for="product-name">Tên sản phẩm</label>
                    <input class="form-control" type="text" value="" placeholder="Nhập tên sản phẩm" id="product-name" name="name" required>
                </div>
                <div class="form-group">
                    <label for="price">Giá sản phẩm</label>
                    <input class="form-control" type="number" value="add" placeholder="Nhập giá bán" id="price" name="price" required>
                </div>
                <div class="form-group">
                    <form class="mt-3" method="post" onsubmit="return validateForm();">
                        <input type="hidden" name="action" value="add">
                    <button type="submit" class="btn btn-success mr-2">Thêm sản phẩm</button>
                    </form>
                </div>
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger">${errorMessage}</div>
                </c:if>
            </form>
        </div>
        <div class="col-md-8">
            <h4 class="text-success">Danh sách sản phẩm</h4>
            <p>Chọn một sản phẩm cụ thể để xem chi tiết</p>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Tên sản phẩm</th>
                    <th>Giá</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${not empty products}">
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td>${product.id}</td>
                            <td><a href="#" onclick="populateEditForm(${product.id}, '${product.name}', ${product.price})">${product.name}</a></td>
                            <td>${product.price}</td>
                            <td>
                                <form method="post" style="display:inline;">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="id" value="${product.id}">
                                    <button type="submit" onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm này?');" class="btn btn-danger btn-sm">Xóa</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${empty products}">
                    <tr>
                        <td colspan="4">Không có sản phẩm nào để hiển thị.</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
