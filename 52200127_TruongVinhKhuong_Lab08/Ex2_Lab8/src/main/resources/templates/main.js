$(document).ready(function () {
    // Kích hoạt tooltip
    $('[data-toggle="tooltip"]').tooltip();

    // Chọn/Bỏ chọn tất cả các checkbox
    let checkbox = $('table tbody input[type="checkbox"]');
    $("#selectAll").click(function () {
        checkbox.prop("checked", this.checked);
    });
    checkbox.click(function () {
        if (!this.checked) {
            $("#selectAll").prop("checked", false);
        }
    });

    // Thêm nhân viên mới
    $('#addButton').click(function (event) {
        event.preventDefault();

        const name = $('#name').val();
        const email = $('#email').val();
        const address = $('#address').val();
        const phone = $('#phone').val();

        const employeeData = { name, email, address, phone };

        $.ajax({
            url: '/employees',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(employeeData),
            success: function (response) {
                alert("Nhân viên được thêm thành công!");

                // Thêm dòng mới vào bảng mà không tải lại trang
                const row = `<tr>
                    <td>
                        <span class="custom-checkbox">
                            <input type="checkbox" id="checkbox${response.id}" name="options[]" value="${response.id}">
                            <label for="checkbox${response.id}"></label>
                        </span>
                    </td>
                    <td>${response.name}</td>
                    <td>${response.email}</td>
                    <td>${response.address}</td>
                    <td>${response.phone}</td>
                    <td>
                        <a href="#" class="edit" data-id="${response.id}"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                        <a href="#" class="delete" data-id="${response.id}" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                    </td>
                </tr>`;

                $('table tbody').append(row);
                $('[data-toggle="tooltip"]').tooltip(); // Kích hoạt tooltip cho dòng mới
            },
            error: function (error) {
                console.error("Lỗi khi thêm nhân viên:", error);
                alert("Thêm nhân viên thất bại.");
            }
        });
    });

    // Xoá nhân viên
    $(document).on('click', '.delete', function () {
        const employeeId = $(this).data('id');

        if (confirm("Bạn có chắc chắn muốn xoá nhân viên này không?")) {
            $.ajax({
                url: `/employees/${employeeId}`,
                method: 'DELETE',
                success: function () {
                    alert("Nhân viên đã được xoá thành công!");

                    // Xoá dòng trong bảng mà không cần tải lại trang
                    $(`table tbody tr:has(input[value="${employeeId}"])`).remove();
                },
                error: function (error) {
                    console.error("Lỗi khi xoá nhân viên:", error);
                    alert("Xoá nhân viên thất bại.");
                }
            });
        }
    });
});
