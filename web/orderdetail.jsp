<%-- 
    Document   : myaccount
    Created on : Feb 14, 2024, 12:08:30 PM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<title>KingcapStore</title>
                <link rel="shortcut icon" type="image/x-icon" href="support_images/logo1.png" />
		<!-- Google font -->
		<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

		<!-- Bootstrap -->
		<link type="text/css" rel="stylesheet" href="electro-master/css/bootstrap.min.css"/>

		<!-- Slick -->
		<link type="text/css" rel="stylesheet" href="electro-master/css/slick.css"/>
		<link type="text/css" rel="stylesheet" href="electro-master/css/slick-theme.css"/>

		<!-- nouislider -->
		<link type="text/css" rel="stylesheet" href="electro-master/css/nouislider.min.css"/>

		<!-- Font Awesome Icon -->
		<link rel="stylesheet" href="electro-master/css/font-awesome.min.css">

		<!-- Custom stlylesheet -->
                <link type="text/css" rel="stylesheet" href="electro-master/css/style.css"/>

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

    </head>
	<body>
             <c:set var="size" value="${sessionScope.size}"/>
            <c:set var="o" value="${sessionScope.cart}"/>
            <c:set var="acc" value="${sessionScope.account}"/>
		<!-- HEADER -->
		<header>
                    <form name="l" action="login" method="get">
                <input type="hidden"  name="status" value="0"/>               
            </form>
			<!-- TOP HEADER -->
			<div id="top-header">
				<div class="container">
					<ul class="header-links pull-left">
						<li><a href="#"><i class="fa fa-phone"></i> +021-95-51-84</a></li>
						<li><a href="#"><i class="fa fa-envelope-o"></i> thangndhe186090@fpt.edu.vn</a></li>
						<li><a href="#"><i class="fa fa-map-marker"></i> 1734 Stonecoal Road</a></li>
					</ul>
					<ul class="header-links pull-right">
						<li><a href="balance"><i class="fa fa-dollar"></i> VND(<fmt:formatNumber type = "number" 
                                                                              pattern="#,##0" value = "${acc.balance}" />)</a></li>
						<li><a href="account"><i class="fa fa-user-o"></i> My Account(${acc.username})</a></li>
                                                <c:if test="${acc==null}">
                                                <li><a href="login.jsp"><i class="fa fa-sign-in" aria-hidden="true"></i>Login/Signup</a></li>
                                                </c:if>
                                                <c:if test="${acc!=null}">
                                                <li><a onclick="logout()"<i class="fa fa-sign-out" aria-hidden="true"></i>Log out</a></li>
                                                </c:if>
					</ul>
				</div>
			</div>
                                                
			<!-- /TOP HEADER -->

			<!-- MAIN HEADER -->
			<div id="header">
				<!-- container -->
				<div class="container">
					<!-- row -->
					<div class="row">
						<!-- LOGO -->
						<div class="col-md-3">
							<div class="header-logo">
								<a href="homepage" class="logo">
                                                                    <img src="support_images/logo1.png" width="80px" height="80px" alt="">
								</a>
							</div>
						</div>
						<!-- /LOGO -->

						<!-- SEARCH BAR -->
						<div class="col-md-6">
							<div class="header-search">
                                                            <form action="search?action=search" method="post">
                                                                    <select class="input-select" name="search_type">
										<option value="0">Tên sản phẩm</option>
										<option value="1">Brand</option>
										<option value="2">Type</option>
									</select>
									<input class="input" placeholder="Search here" name="search_name">
                                                                        <button class="search-btn" type="submit">Search</button>
								</form>
							</div>
						</div>
						<!-- /SEARCH BAR -->

						<!-- ACCOUNT -->
						<div class="col-md-3 clearfix">
							<div class="header-ctn">
								<!-- Wishlist -->
								
								<!-- /Wishlist -->

								<!-- Cart -->
								
                                                                <div class="dropdown">
                                                                    <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                                                        <i class="fa fa-shopping-cart"></i>
                                                                        <span>Your Cart</span>
                                                                        <div class="qty">${size}</div>
                                                                    </a>                                       
                                                                    
                                                                    <c:set var="total" value="${0}"/>
                                                                        <div class="cart-dropdown">
                                                                            
                                                                            <div class="cart-list">
                                                                                <c:forEach items="${o.items}" var ="i">    
                                                                                    <div class="product-widget">
                                                                                        <div class="product-img">
                                                                                            <img src="./product_images/${i.product.img.get(0)}" alt="">
                                                                                        </div>
                                                                                        <div class="product-body">
                                                                                            <h3 class="product-name"><a href="productdetails?productId=${i.product.id}">${i.product.title}</a></h3>
                                                                                            <h4 class="product-price"><span class="qty">${i.quantity}x</span> <fmt:formatNumber type = "number" 
                                                                             pattern="#,##0" value = "${i.product.outPrice-(i.product.outPrice*i.product.discount)}" /> VND</h4>
                                                                                        </div>
                                                                                        <form name="a" action="process" method="post">
                                                                                            <input type="hidden" name="id" value="${i.product.id}"/>  
                                                                                            <input type="hidden" name="page" value="cart.jsp"/>
                                                                                            <input type="submit" class="delete" value="x"/>
                                                                                        </form>
                                                                                            
                                                                                    </div>
                                                                                        <c:set var="total" value="${total + (i.product.outPrice-(i.product.outPrice*i.product.discount))*i.quantity}" />
                                                                                </c:forEach>
                                                                            </div>
                                                                            <c:set var="total_money" value="${total}"/>
                                                                            <div class="cart-summary">
                                                                                <small>${size} Item(s) selected</small>
                                                                                <h5>SUBTOTAL: <fmt:formatNumber type = "number" 
                                                                              pattern="#,##0"  value = "${total_money}" /> VND</h5>
                                                                            </div>
                                                                            <div class="cart-btns">
                                                                                <a href="cart.jsp">View Cart</a>
                                                                                <a href="checkout">Checkout  <i class="fa fa-arrow-circle-right"></i></a>
                                                                            </div>
                                                                        </div>
                                                                    </div>
								<!-- /Cart -->

								<!-- Menu Toogle -->
								<div class="menu-toggle">
									<a href="#">
										<i class="fa fa-bars"></i>
										<span>Menu</span>
									</a>
								</div>
								<!-- /Menu Toogle -->
							</div>
						</div>
						<!-- /ACCOUNT -->
					</div>
					<!-- row -->
				</div>
				<!-- container -->
			</div>
			<!-- /MAIN HEADER -->
		</header>
		<!-- /HEADER -->

		<!-- NAVIGATION -->
		<nav id="navigation">
			<!-- container -->
			<div class="container">
				<!-- responsive-nav -->
				<div id="responsive-nav">
					<!-- NAV -->
					<ul class="main-nav nav navbar-nav">
						<li class="active"><a href="homepage"><i class="fa fa-home" aria-hidden="true"></i>Home</a></li>
						<li><a href="search"><i class="fa fa-search" aria-hidden="true"></i>Search</a></li>
                                                <li><a href="cart.jsp"><i class="fa fa-shopping-cart" aria-hidden="true"></i>Cart</a></li>
					</ul>
					<!-- /NAV -->
				</div>
				<!-- /responsive-nav -->
			</div>
			<!-- /container -->
		</nav>
		<!-- /NAVIGATION -->

		<!-- BREADCRUMB -->
		<div id="breadcrumb" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<h3 class="breadcrumb-header">Order Detail</h3>
						
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /BREADCRUMB -->

		<!-- SECTION -->
                <div class="section">
                    <!-- container -->
                    <div class="container">
                        <!-- row -->
                        <div class="row">
                            <div class="col-md-3">

                            </div>
                            <div class="col-md-5">
                                <style>
                                    html,
body {
	height: 100%;
}

body {
	margin: 0;
	background: linear-gradient(45deg, #49a09d, #5f2c82);
	font-family: sans-serif;
	font-weight: 100;
}



table {
	width: 800px;
	border-collapse: collapse;
	overflow: auto;
	box-shadow: 0 0 20px rgba(0,0,0,0.1);
}

th,
td {
	padding: 15px;
	background-color: rgba(255,255,255,0.2);
	color: #fff;
}

th {
	text-align: left;
}

thead {
	th {
		background-color: #55608f;
	}
}

tbody {
	tr {
		&:hover {
			background-color: rgba(255,255,255,0.3);
		}
	}
	td {
		position: relative;
		&:hover {
			&:before {
				content: "";
				position: absolute;
				left: 0;
				right: 0;
				top: -9999px;
				bottom: -9999px;
				background-color: rgba(255,255,255,0.2);
				z-index: -1;
			}
		}
	}
}
button{
    background-color: #55608f;
    color: whitesmoke;
    border: #fff;
    border-radius: 5px 5px 5px 5px; 
}
                                    </style>
                                <c:set var="orderdetail" value="${requestScope.detail}"/>
                                <table>
                                    <thead>
                                        <tr>
                                            <th>Ảnh</th>
                                            <th>Mã sản phẩm</th>
                                            <th>Tên sản phẩm</th>
                                            <th>Kiểu Loại</th>
                                            <th>Hãng</th>
                                            <th>Số lượng</th>
                                            <th>Đơn giá</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="d" items="${orderdetail}">
                                        <tr>
                                            <td><img src="product_images//${d.getProductById(d.getProductId()).getImg().get(0)}" alt="" width="100px;"></td>
                                            <td>${d.getProductById(d.getProductId()).getId()}</td>
                                            <td>${d.getProductById(d.getProductId()).getTitle()}</td>
                                            <td>${d.getProductById(d.getProductId()).getType().getName()}</td>
                                            <td>${d.getProductById(d.getProductId()).getBrand().getName()}</td>
                                            <td>${d.getAmount()}</td>
                                            <td>${d.getPrice()}</td>
                                            <td><button type="button" onclick="rebuy(${d.getProductId()})">Mua lại</button></td> 
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <script >
                                       function rebuy(id){
                                           window.location.href = "productdetails?productId=" + id;
                                       } 
                                </script>
                            </div>                                      

                            <div class="col-md-3">

                            </div>       
                            </div>
                            <!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->

		<!-- NEWSLETTER -->
		<div id="newsletter" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<div class="newsletter">
							<p>Sign Up for the <strong>NEWSLETTER</strong></p>
							<form>
								<input class="input" type="email" placeholder="Enter Your Email">
								<button class="newsletter-btn"><i class="fa fa-envelope"></i> Subscribe</button>
							</form>
							<ul class="newsletter-follow">
								<li>
									<a href="#"><i class="fa fa-facebook"></i></a>
								</li>
								<li>
									<a href="#"><i class="fa fa-twitter"></i></a>
								</li>
								<li>
									<a href="#"><i class="fa fa-instagram"></i></a>
								</li>
								<li>
									<a href="#"><i class="fa fa-pinterest"></i></a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /NEWSLETTER -->

		<!-- FOOTER -->
		<footer id="footer">
			<!-- top footer -->
			<div class="section">
				<!-- container -->
				<div class="container">
					<!-- row -->
					<div class="row">
						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">About Us</h3>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut.</p>
								<ul class="footer-links">
									<li><a href="#"><i class="fa fa-map-marker"></i>1734 Stonecoal Road</a></li>
									<li><a href="#"><i class="fa fa-phone"></i>+021-95-51-84</a></li>
									<li><a href="#"><i class="fa fa-envelope-o"></i>email@email.com</a></li>
								</ul>
							</div>
						</div>

						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Categories</h3>
								<ul class="footer-links">
									<li><a href="#">Hot deals</a></li>
									<li><a href="#">Laptops</a></li>
									<li><a href="#">Smartphones</a></li>
									<li><a href="#">Cameras</a></li>
									<li><a href="#">Accessories</a></li>
								</ul>
							</div>
						</div>

						<div class="clearfix visible-xs"></div>

						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Information</h3>
								<ul class="footer-links">
									<li><a href="#">About Us</a></li>
									<li><a href="#">Contact Us</a></li>
									<li><a href="#">Privacy Policy</a></li>
									<li><a href="#">Orders and Returns</a></li>
									<li><a href="#">Terms & Conditions</a></li>
								</ul>
							</div>
						</div>

						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Service</h3>
								<ul class="footer-links">
									<li><a href="#">My Account</a></li>
									<li><a href="#">View Cart</a></li>
									<li><a href="#">Wishlist</a></li>
									<li><a href="#">Track My Order</a></li>
									<li><a href="#">Help</a></li>
								</ul>
							</div>
						</div>
					</div>
					<!-- /row -->
				</div>
				<!-- /container -->
			</div>
			<!-- /top footer -->

			<!-- bottom footer -->
			<div id="bottom-footer" class="section">
				<div class="container">
					<!-- row -->
					<div class="row">
						<div class="col-md-12 text-center">
							<ul class="footer-payments">
								<li><a href="#"><i class="fa fa-cc-visa"></i></a></li>
								<li><a href="#"><i class="fa fa-credit-card"></i></a></li>
								<li><a href="#"><i class="fa fa-cc-paypal"></i></a></li>
								<li><a href="#"><i class="fa fa-cc-mastercard"></i></a></li>
								<li><a href="#"><i class="fa fa-cc-discover"></i></a></li>
								<li><a href="#"><i class="fa fa-cc-amex"></i></a></li>
							</ul>
							<span class="copyright">
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
								Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							</span>


						</div>
					</div>
						<!-- /row -->
				</div>
				<!-- /container -->
			</div>
			<!-- /bottom footer -->
		</footer>
		<!-- /FOOTER -->
<script>
                                                                                                        // JavaScript Function to Redirect to Product Details Page
                                                                                                        function redirectToProductDetails(productId) {
                                                                                                          // Assuming x is your result set containing product details
                                                                                                          // Replace this line with the correct way to retrieve product ID from your data
                                                                                                          
                                                                                                          // Redirect to the product details page with the product ID
                                                                                                          window.location.href = "productdetails?productId=" + productId;
                                                                                                                         }
                                                                                                        </script> 
                                                                                                        <script type="text/javascript">
                                                                                                                function addtocart(id) {
                                                                                                        
                                                                                                                document.f.action = "buy?id="+id;
                                                                                                                document.f.submit();
                                                                                                            
                                                                                                                }
                                                                                                              function addtocart1() {
                                                                                                                document.a.submit();
                                                                                                                }  
                                                                                                                function logout() {
                                                                                                                document.l.submit();
                                                                                                                }  
                </script>
		<!-- jQuery Plugins -->
		<script src="electro-master/js/jquery.min.js"></script>
		<script src="electro-master/js/bootstrap.min.js"></script>
		<script src="electro-master/js/slick.min.js"></script>
		<script src="electro-master/js/nouislider.min.js"></script>
		<script src="electro-master/js/jquery.zoom.min.js"></script>
		<script src="electro-master/js/main.js"></script>

	</body>
</html>
