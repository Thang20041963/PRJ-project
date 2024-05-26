<%-- 
    Document   : index
    Created on : Oct 19, 2021, 11:22:48 PM
    Author     : Khuong Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Quản trị Admin | KingcapStore</title>
       
       <link rel="shortcut icon" type="image/x-icon" href="support_images/logo1.png" />
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
        <form name="l" action="login" method="get">
            <input type="hidden"  name="status" value="0"/>               
        </form>
        <!-- Navbar-->

        <header class="app-header">
            <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar"
                                            aria-label="Hide Sidebar"></a>
            <!-- Navbar Right Menu-->
            <ul class="app-nav">


                <!-- User Menu-->
                <li><a class="app-nav__item" onclick="logout()"><i class='bx bx-log-out bx-rotate-180'></i> </a>

                </li>
            </ul>
        </header>
        <!-- Sidebar menu-->
        <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
        <aside class="app-sidebar">
            <div class="app-sidebar__user">
                <div>
                    <p class="app-sidebar__user-name"><b>Admin : ${sessionScope.account.getFullname()}</b></p>
                  
                </div>
            </div>
            <hr>
            <ul class="app-menu">
                <li><a class="app-menu__item" href="dashboard"><i class='app-menu__icon bx bx-tachometer'></i><span
                            class="app-menu__label">Bảng điều khiển</span></a></li>
                <li><a class="app-menu__item" href="customermanager"><i class='app-menu__icon bx bx-user-voice'></i><span
                            class="app-menu__label">Quản lý khách hàng</span></a></li>
                <li><a class="app-menu__item" href="productmanager"><i
                            class='app-menu__icon bx bx-purchase-tag-alt'></i><span class="app-menu__label">Quản lý sản phẩm</span></a>
                </li>
                <li><a class="app-menu__item" href="ordermanager"><i class='app-menu__icon bx bx-task'></i><span
                            class="app-menu__label">Quản lý đơn hàng</span></a></li>
                <li><a class="app-menu__item" href="moneyrequestmanager" ><i class='app-menu__icon bx bx-task'></i><span
                            class="app-menu__label">Quản lý yêu cầu nạp tiền </span></a></li>
            </ul>
        </aside>
        <main class="app-content">
            <div class="row">
                <div class="col-md-12">
                    <div class="app-title">
                        <ul class="app-breadcrumb breadcrumb">
                            <li class="breadcrumb-item"><a href="#"><b>Bảng điều khiển</b></a></li>
                        </ul>
                        <div id="clock"></div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 col-lg-12">
                    <div class="row">
                        <!-- col-6 -->
                        <div class="col-md-6">
                            <div class="widget-small primary coloured-icon"><i class='icon bx bxs-user-account fa-3x'></i>
                                <div class="info">
                                    <h4>Tổng khách hàng</h4>
                                    <p><b>${requestScope.user} khách hàng</b></p>
                                    <p class="info-tong">Tổng số khách hàng được quản lý.</p>
                                </div>
                            </div>
                        </div>
                        <!-- col-6 -->
                        <div class="col-md-6">
                            <div class="widget-small info coloured-icon"><i class='icon bx bxs-data fa-3x'></i>
                                <div class="info">
                                    <h4>Tổng sản phẩm</h4>
                                    <p><b>${requestScope.product} sản phẩm</b></p>
                                    <p class="info-tong">Tổng số sản phẩm được quản lý.</p>
                                </div>
                            </div>
                        </div>
                        <!-- col-6 -->
                        <div class="col-md-6">
                            <div class="widget-small warning coloured-icon"><i class='icon bx bxs-shopping-bags fa-3x'></i>
                                <div class="info">
                                    <h4>Tổng đơn hàng</h4>
                                    <p><b>${requestScope.order} đơn hàng</b></p>
                                    <p class="info-tong">Tổng số hóa đơn bán hàng trong tháng.</p>
                                </div>
                            </div>
                        </div>
                        <!-- col-6 -->
                        <div class="col-md-6">
                            <div class="widget-small danger coloured-icon"><i class='icon bx bxs-error-alt fa-3x'></i>
                                <div class="info">
                                    <h4>Sắp hết hàng</h4>
                                    <p><b>${requestScope.low} sản phẩm</b></p>
                                    <p class="info-tong">Số sản phẩm cảnh báo hết cần nhập thêm.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="tile">
                                <style>


                                    /* Button used to open the contact form - fixed at the bottom of the page */
                                    .open-button {
                                        background-color: #555;
                                        color: white;

                                        border: none;
                                        cursor: pointer;
                                        opacity: 0.8;


                                    }

                                    /* The popup form - hidden by default */
                                    .form-popup {
                                        display: none;

                                        right: 15px;
                                        border: 3px solid #f1f1f1;
                                        z-index: 9;
                                    }

                                    /* Add styles to the form container */
                                    .form-container {
                                        max-width: 300px;
                                        padding: 10px;
                                        background-color: white;
                                    }

                                    /* Full-width input fields */
                                    .form-container input[type=text], .form-container input[type=password] {
                                        width: 100%;
                                        padding: 15px;
                                        margin: 5px 0 22px 0;
                                        border: none;
                                        background: #f1f1f1;
                                    }

                                    /* When the inputs get focus, do something */
                                    .form-container input[type=text]:focus, .form-container input[type=password]:focus {
                                        background-color: #ddd;
                                        outline: none;
                                    }

                                    /* Set a style for the submit/login button */
                                    .form-container .btn {
                                        background-color: #04AA6D;
                                        color: white;
                                        padding: 16px 20px;
                                        border: none;
                                        cursor: pointer;
                                        width: 100%;
                                        margin-bottom:10px;
                                        opacity: 0.8;
                                    }

                                    /* Add a red background color to the cancel button */
                                    .form-container .cancel {
                                        background-color: red;
                                    }

                                    /* Add some hover effects to buttons */
                                    .form-container .btn:hover, .open-button:hover {
                                        opacity: 1;
                                    }
                                </style>
                                <h3 class="tile-title">Yêu cầu nạp tiền</h3>
                                <div>
                                    <table class="table table-bordered">
                                        <thead>
                                            <tr>

                                                <th>Khách hàng</th>
                                                <th>Số điện thoại</th>
                                                <th>Số tiền yêu cầu</th>
                                                <th>Trạng thái</th>
                                                <th>Thời gian tạo yêu cầu</th>
                                                <th>Thời gian phản hồi</th>                  
                                                <th>Xử Lý</th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${requestScope.moneyrequest}" var="money">
                                                <tr>
                                                    <td>${money.getAccInfor(money.getCustomerId()).getFullname()}</td>
                                                    <td>(+84) ${money.getAccInfor(money.getCustomerId()).getPhonenumber()}</td>
                                                    <td><fmt:formatNumber type = "number" 
                                                                              pattern="#,##0" value = "${money.getMoney()}" /></td>
                                                    <td style="color: red">${money.getStatus()}</td>
                                                    <td >${money.getRequestDate()}</td>
                                                    <td style="color: red">
                                                        <c:if test="${money.getResponseDate()==null}">
                                                            Chưa xử lý
                                                        </c:if>
                                                    </td>                           
                                                    <td>
                                                        <button class="open-button" onclick="openForm()">Accept</button>
                                                        <div class="form-popup" id="myForm">
                                                            <form action="customermanager?action=accept" method="post" class="form-container">
                                                                <h1>Accept</h1>

                                                                <label for="money"><b>Số tiền nạp vào</b></label>
                                                                <input type="text" value="${money.getMoney()}" name="money" required>
                                                                <input type="hidden" value="${money.getId()}" name="request_id" > 
                                                                <input type="hidden" value="${money.getCustomerId()}" name="customerid" > 
                                                                <b>Note</b>    </br>                                                             

                                                                <textarea id="id" name="note" >
                                                                   
                                                                </textarea>

                                                                <button type="submit" class="btn">Enter</button>
                                                                <button type="button" class="btn cancel" onclick="closeForm()">Close</button>
                                                            </form>
                                                        </div>
                                                        <button class="open-button" onclick="openForm1()">Deny</button>
                                                        <div class="form-popup" id="myForm1">
                                                            <form action="customermanager?action=deny" method="post" class="form-container">
                                                                <h1>Deny</h1>
                                                                <input type="hidden" value="${money.getId()}" name="request_id" > 

                                                                <b>Note</b>
                                                                </br>
                                                                <textarea id="id" name="note" >
                                                                   
                                                                </textarea>

                                                                <button type="submit" class="btn">Enter</button>
                                                                <button type="button" class="btn cancel" onclick="closeForm1()">Close</button>
                                                            </form>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>
                                </div>
                                <!-- / div trống-->
                            </div>
                        </div>
                        <!-- col-12 -->
                        <div class="col-md-12">
                            <div class="tile">
                                <h3 class="tile-title">Đơn hàng hôm nay</h3>
                                <div>
                                    <table class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th>ID đơn hàng</th>
                                                <th>Khách hàng</th>
                                                <th>Số điện thoại</th>
                                                <th>Địa chỉ</th>
                                                <th>Ngày mua</th>
                                                <th>Tổng tiền</th>                  
                                                <th>Chức năng</th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${orderbyday}" var="o">
                                                <tr>
                                                    <td>${o.getId()}</td>
                                                    <td>${o.getAccInfor1(o.getCustomer_id()).getFullname()}</td>
                                                    <td>(+84)${o.getAccInfor1(o.getCustomer_id()).getPhonenumber()}</td>
                                                    <td>${o.getReceiveLocation()}</td>
                                                    <td>${o.getOrderDate()}</td>
                                                    <td><fmt:formatNumber type = "number" 
                                                                              pattern="#,##0" value = "${o.getTotalPrice()}" /></td>                             
                                                    <td><a style=" color: rgb(245 157 57);background-color: rgb(251 226 197); padding: 5px;border-radius: 5px;" href="ordermanager?action=showdetail&order_id=${o.getId()}"><i class="fa"></i>Chi tiết đơn hàng</a></td>
                                                </tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>
                                </div>
                                <!-- / div trống-->
                            </div>
                        </div>
                        <!-- / col-12 -->
                    </div>
                </div>
            </div>


            <div class="text-center" style="font-size: 13px">
                <p><b>Copyright
                        <script type="text/javascript">
                            document.write(new Date().getFullYear());
                        </script> Phần mềm quản lý Website
                    </b></p>
            </div>
        </main>
        <script src="admin/js/jquery-3.2.1.min.js"></script>
        <!--===============================================================================================-->
        <script src="admin/js/popper.min.js"></script>
        <script src="https://unpkg.com/boxicons@latest/dist/boxicons.js"></script>
        <!--===============================================================================================-->
        <script src="admin/js/bootstrap.min.js"></script>
        <!--===============================================================================================-->
        <script src="admin/js/main.js"></script>
        <!--===============================================================================================-->
        <script src="admin/js/plugins/pace.min.js"></script>
        <!--===============================================================================================-->
        <!--===============================================================================================-->
        <script type="text/javascript">
                            var data = {
                                labels: ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6"],
                                datasets: [{
                                        label: "Dữ liệu đầu tiên",
                                        fillColor: "rgba(255, 213, 59, 0.767), 212, 59)",
                                        strokeColor: "rgb(255, 212, 59)",
                                        pointColor: "rgb(255, 212, 59)",
                                        pointStrokeColor: "rgb(255, 212, 59)",
                                        pointHighlightFill: "rgb(255, 212, 59)",
                                        pointHighlightStroke: "rgb(255, 212, 59)",
                                        data: [20, 59, 90, 51, 56, 100]
                                    },
                                    {
                                        label: "Dữ liệu kế tiếp",
                                        fillColor: "rgba(9, 109, 239, 0.651)  ",
                                        pointColor: "rgb(9, 109, 239)",
                                        strokeColor: "rgb(9, 109, 239)",
                                        pointStrokeColor: "rgb(9, 109, 239)",
                                        pointHighlightFill: "rgb(9, 109, 239)",
                                        pointHighlightStroke: "rgb(9, 109, 239)",
                                        data: [48, 48, 49, 39, 86, 10]
                                    }
                                ]
                            };
                            var ctxl = $("#lineChartDemo").get(0).getContext("2d");
                            var lineChart = new Chart(ctxl).Line(data);

                            var ctxb = $("#barChartDemo").get(0).getContext("2d");
                            var barChart = new Chart(ctxb).Bar(data);
        </script>
        <script type="text/javascript">
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
                m = checkTime(m);
                nowTime = h + ":" + m + "";
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
            function logout() {
                document.l.submit();
            }
            function openForm() {
                document.getElementById("myForm").style.display = "block";
            }

            function closeForm() {
                document.getElementById("myForm").style.display = "none";
            }
            function openForm1() {
                document.getElementById("myForm1").style.display = "block";
            }

            function closeForm1() {
                document.getElementById("myForm1").style.display = "none";
            }
        </script>
    </body>

</html>