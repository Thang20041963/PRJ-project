<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Danh sách sản phẩm | Quản trị Admin</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="admin/css/main.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <!-- or -->
    <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">

    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">

</head>

<body onload="time()" class="app sidebar-mini rtl">
<!-- Navbar-->
<header class="app-header">
    <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar"
                                    aria-label="Hide Sidebar"></a>
    <!-- Navbar Right Menu-->
    <ul class="app-nav">


        <!-- User Menu-->
        <li><a class="app-nav__item" href="index.jsp"><i class='bx bx-log-out bx-rotate-180'></i> Home </a>
        <li><a class="app-nav__item" href="login"><i class='bx bx-log-out bx-rotate-180'></i> Logout </a>

        </li>
    </ul>
</header>
<!-- Sidebar menu-->
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<aside class="app-sidebar">
   
        <div>
            <p class="app-sidebar__user-name"><b>${sessionScope.account}</b></p>
            <p class="app-sidebar__user-designation">Chào mừng bạn trở lại</p>
        </div>
    </div>
    <hr>
    <ul class="app-menu">
        <li><a class="app-menu__item" href="admin"><i class='app-menu__icon bx bx-tachometer'></i><span
                class="app-menu__label">Bảng điều khiển</span></a></li>
        <li><a class="app-menu__item" href="customermanager"><i class='app-menu__icon bx bx-user-voice'></i><span
                class="app-menu__label">Quản lý khách hàng</span></a></li>
        <li><a class="app-menu__item" href="productmanager"><i
                class='app-menu__icon bx bx-purchase-tag-alt'></i><span class="app-menu__label">Quản lý sản phẩm</span></a>
        </li>
        <li><a class="app-menu__item" href="ordermanager"><i class='app-menu__icon bx bx-task'></i><span
                class="app-menu__label">Quản lý đơn hàng</span></a></li>
        <li><a class="app-menu__item"
               href="https://docs.google.com/spreadsheets/d/1fOvH-dHByIxizCOyuNf8Jkk0aNm1e-jIQw3ObxfjrWw"
               target="_blank"><i class='app-menu__icon bx bx-task'></i><span
                class="app-menu__label">Kiểm tra phản hồi</span></a></li>
    </ul>
</aside>
<main class="app-content">
    <div class="app-title">
        <ul class="app-breadcrumb breadcrumb side">
            <li class="breadcrumb-item active"><a href="#"><b>Danh sách sản phẩm</b></a></li>
        </ul>
        <div id="clock"></div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="tile">
                <div class="tile-body">
                    <div class="row element-button">
                        <div class="col-sm-2">
                            <a class="btn btn-add btn-sm" href="productmanager?action=insert" title="Thêm"><i
                                    class="fas fa-plus"></i>
                                Tạo mới sản phẩm</a>
                        </div>
                        <div class="col-sm-2">
                            <a class="btn btn-delete btn-sm print-file" type="button" title="In"
                               onclick="myApp.printTable()"><i
                                    class="fas fa-print"></i> In dữ liệu</a>
                        </div>
                    </div>
                    <table class="table table-hover table-bordered" id="sampleTable">
                        <thead>
                        <tr>
                           <th>Mã sản phẩm</th>                                          
                                            <th>Tên sản phẩm</th>
                                            <th>Giá </th>
                                            <th>Giá </th>
                                            <th>Kiểu Loại</th>
                                            <th>Hãng</th>
                                            <th>Số lượng phím</th>
                                            <th>Số lượng trong kho</th>
                                            <th>Chất liệu</th>
                                            <th>Tương thích</th>
                                            <th>Độ dày phím</th>
                                            <th>Discount</th>
                                            <th>Ảnh</th>
                                            <th>Chức năng</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.ProductData}" var="p">
                            <tr>
                                 <td>${p.getId()}</td>
                                                <td>${p.getTitle()}</td>
                                                <td>${p.getInPrice()}</td>
                                                <td>${p.getOutPrice()}</td>
                                                <td>
                                                    ${p.getType().getName()}
                                                </td>

                                                <td>
                                                    ${p.getBrand().getName()}
                                                </td>
                                                <td>${p.getNokc()}</td>
                                                <td>${p.getStock()}</td>
                                                <td>${p.getMaterial()}</td>
                                                <td>${p.getSuitability()}</td>
                                                <td>${p.getDesity()}</td>
                                                <td>${p.getDiscount()}</td>
                                                <td>
                                                    <c:forEach var="i" items="${p.getImg()}">
                                                 Url : ${i}    <img src="product_images/${i}" alt="" width="100px;"></br>
                                                    </c:forEach>
                                                </td>
                                <td>
                                    <button class="btn btn-primary btn-sm trash" type="button" title="Xóa"
                                            value="${p.id}"><i
                                            class="fas fa-trash-alt"></i>
                                    </button>
                                    <button class="btn btn-primary btn-sm edit" type="button" title="Sửa"
                                            id="show-emp"
                                            data-toggle="modal" data-target="#ModalUP${p.id}"><i
                                            class="fas fa-edit"></i>
                                    </button>
                                </td>
                            </tr>

                            <!--
                            MODAL
                            -->

                            <div class="modal fade" id="ModalUP${p.id}" tabindex="-1" role="dialog"
                                 aria-hidden="true" data-backdrop="static"
                                 data-keyboard="false">
                                <form action="productmanager?action=updateproduct" method="POST">
                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                        <div class="modal-content">
                                            <div class="modal-body">
                                                <div class="row">
                                                    <div class="form-group  col-md-12">
                                                                <span class="thong-tin-thanh-toan">
                                                                    <h5>Chỉnh sửa thông tin sản phẩm</h5>
                                                                </span>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group col-md-6">
                                                                <label class="control-label">Mã sản phẩm </label>
                                                                <input class="form-control" type="text" readonly name="product_id" value="${p.getId()}">
                                                            </div>
                                                            
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Tên sản phẩm</label>
                                                                <input class="form-control" type="text" name="product_name" required value="${p.getTitle()}">
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Giá nhập</label>
                                                                <input class="form-control" type="text" name="in_price" required value="${p.getInPrice()}">
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Giá Bán</label>
                                                                <input class="form-control" type="text" name="out_price" required value="${p.getOutPrice()}">
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Kiểu loại</label>
                                                                <input class="form-control" name="type" type="text" value="${p.getType().getName()}">
                                                            </div>

                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Hãng</label>
                                                                <input class="form-control" name="brand" type="text" value="${p.getBrand().getName()}">
                                                            </div>
                                                        <div class="form-group col-md-6">
                                                            <label class="control-label">Số lượng phím</label>
                                                            <input class="form-control" type="text" name="nokc" value="${p.getNokc()}">
                                                        </div>
                                                        <div class="form-group col-md-6">
                                                            <label class="control-label">Số lượng trong kho</label>
                                                            <input class="form-control" type="text" name="stock" value="${p.getStock()}">
                                                        </div>
                                                        <div class="form-group col-md-6">
                                                            <label class="control-label">Chất liệu</label>
                                                            <input class="form-control" type="text" name="material" value="${p.getMaterial()}">
                                                        </div>
                                                        <div class="form-group col-md-6">
                                                            <label class="control-label">Tương thích</label>
                                                            <input class="form-control" type="text" name="suitability" value="${p.getSuitability()}">
                                                        </div>
                                                        <div class="form-group col-md-6">
                                                            <label class="control-label">Độ dày phím</label>
                                                            <input class="form-control" type="text" name="density" value="${p.getDesity()}">
                                                        </div>
                                                        <div class="form-group col-md-6">
                                                            <label class="control-label">discount</label>
                                                            <input class="form-control" type="text" name="discount" value="${p.getDiscount()}">
                                                        </div>
                                                    <!--anh san pham-->
                                                  <div class="form-group col-md-6">
                                                            <label class="control-label">Chọn ảnh để thay đổi</label>
                                                            <select name="img" >
                                                                <c:forEach var="i" items="${p.getImg()}">
                                                                    <option value="${i}">${i}</option>
                                                                </c:forEach> 
                                                            </select>
                                                        </div>
                                                        <div class="form-group col-md-12">
                                                            <label class="control-label">Ảnh sản phẩm</label>
                                                            <div id="myfileupload">
                                                                <input type="file" id="uploadfile" name="product_img"  onchange="readURL(this);" />
                                                            </div>
                                                            <div id="thumbbox">
                                                                <img height="450" width="400" alt="Thumb image" id="thumbimage" style="display: none" />
                                                                <a class="removeimg" href="javascript:"></a>
                                                            </div>
                                                            <div id="boxchoice">
                                                                <a href="javascript:" class="Choicefile"><i class="fas fa-cloud-upload-alt"></i> Chọn ảnh</a>
                                                                <p style="clear:both"></p>
                                                            </div>
                                                    </div>
                                                </div>
                                                <BR>
                                                <button class="btn btn-save" type="submit">Lưu lại</button>
                                                <a class="btn btn-cancel" data-dismiss="modal" href="#">Hủy bỏ</a>
                                                <BR>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <!--
                            MODAL
                            -->
                        </c:forEach>
                        </tbody>
                    </table>
                    <%--                    </form>--%>
                </div>
            </div>
        </div>
    </div>
</main>


<!-- Essential javascripts for application to work-->
<script src="admin/js/jquery-3.2.1.min.js"></script>
<script src="admin/js/popper.min.js"></script>
<script src="admin/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="admin/js/main.js"></script>
<!-- The javascript plugin to display page loading on top-->
<script src="admin/js/plugins/pace.min.js"></script>
<!-- Page specific javascripts-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
<!-- Data table plugin-->
<script type="text/javascript" src="admin/js/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="admin/js/plugins/dataTables.bootstrap.min.js"></script>
<script type="text/javascript">
    $('#sampleTable').DataTable();

    //Thời Gian
    function time() {
        var today = new Date();
        var weekday = new Array(7);
        weekday[0] = "Chủ Nhật";
        weekday[1] = "Thứ Hai";
        weekday[2] = "Thứ Ba";
        weekday[3] = "Thứ Tư";
        weekday[4] = "Thứ Năm";
        weekday[5] = "Thứ Sáu";
        weekday[6] = "Thứ Bảy";
        var day = weekday[today.getDay()];
        var dd = today.getDate();
        var mm = today.getMonth() + 1;
        var yyyy = today.getFullYear();
        var h = today.getHours();
        var m = today.getMinutes();
        var s = today.getSeconds();
        m = checkTime(m);
        s = checkTime(s);
        nowTime = h + " giờ " + m + " phút " + s + " giây";
        if (dd < 10) {
            dd = '0' + dd
        }
        if (mm < 10) {
            mm = '0' + mm
        }
        today = day + ', ' + dd + '/' + mm + '/' + yyyy;
        tmp = '<span class="date"> ' + today + ' - ' + nowTime +
            '</span>';
        document.getElementById("clock").innerHTML = tmp;
        clocktime = setTimeout("time()", "1000", "Javascript");

        function checkTime(i) {
            if (i < 10) {
                i = "0" + i;
            }
            return i;
        }
    }
</script>
<script>
    $(document).ready(function () {
        $(document).on('click', '.trash', function () {
            swal({
                title: "Cảnh báo",
                text: "Bạn có chắc chắn là muốn xóa sản phẩm này?",
                buttons: ["Hủy bỏ", "Đồng ý"],
            }).then((willDelete) => {
                if (willDelete) {
                    window.location = "productmanager?action=deleteproduct&productId=" + $(this).attr("value");
                    swal("Đã xóa thành công.!", {});
                }
            });
        });
    });
</script>
<script>
    var myApp = new function () {
        this.printTable = function () {
            var tab = document.getElementById('sampleTable');
            var win = window.open('', '', 'height=700,width=700');
            win.document.write(tab.outerHTML);
            win.document.close();
            win.print();
        }
    }
</script>
</body>

</html>