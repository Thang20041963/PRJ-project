<%-- 
    Document   : checkout
    Created on : Feb 13, 2024, 3:52:35 PM
    Author     : asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<title>Kingcap Store</title>
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
                        <style>
.alert {
  padding: 20px;
  background-color: #f44336;
  color: white;
}

.closebtn {
  margin-left: 15px;
  color: white;
  font-weight: bold;
  float: right;
  font-size: 22px;
  line-height: 20px;
  cursor: pointer;
  transition: 0.3s;
}

.closebtn:hover {
  color: black;
}
</style>

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
            <script>
function logout() {
 document.l.submit();
}                                                                                                                 
            </script>
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
                                                                                            <input type="hidden" name="page" value="checkout"/>
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
                                                                              pattern="#,##0" value = "${total_money}" /> VND</h5>
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

		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">

					<div class="col-md-4">
						
						
						
					</div>

					<!-- Order Details -->
					<div class="col-md-5 order-details">
						<div class="section-title text-center">
							<h3 class="title">Your Order</h3>
						</div>
						<div class="order-summary">
							<div class="order-col">
								<div><strong>PRODUCT</strong></div>
								<div><strong>TOTAL</strong></div>
							</div>
							<div class="order-products">
                                                            <c:set var="total" value="${0}"/>
                                                            <c:forEach items="${o.items}" var ="i"> 
								<div class="order-col">
									<div>${i.quantity}x ${i.product.title}</div>
									<div><fmt:formatNumber type = "number" 
                                                                              pattern="#,##0" value = "${i.product.outPrice-(i.product.outPrice*i.product.discount)}" /> VND</div>
								</div>
                                                                <c:set var="total" value="${total + (i.product.outPrice-(i.product.outPrice*i.product.discount))*i.quantity}" />
                                                            </c:forEach>

							</div>
							<div class="order-col">
								<div>Shiping</div>
								<div><strong>FREE SHIP UNTIL DECEMBER</strong></div>
							</div>
                                                            <c:set var="total_money" value="${total}"/>
							<div class="order-col">
								<div><strong>TOTAL</strong></div>
								<div><strong class="order-total"><fmt:formatNumber type = "number" 
                                                                              pattern="#,##0" value = "${total}" /> VND</strong></div>
							</div>
						</div>
						
                                                        <form name="f" action="checkout?action=order" method="post" >
                                                        <div class="form-group">
								<input class="input" type="text" name="address" placeholder="Address">
							
								<input class="input" type="text" name="city" placeholder="City">
							
                                                        
								<input class="input" type="tel" name="province" placeholder="Province">
							
							
								<input class="input" type="text" name="country" placeholder="Country">
							
							
								<input class="input" type="text" name="zip-code" placeholder="ZIP Code">
							</div>
                                                        
						<div class="input-checkbox">
                                                    <input type="checkbox" id="terms" >
							<label for="terms">
								<span></span>
								I've read and accept the <a href="#">terms & conditions</a>
							</label>
						</div>
                                                </form>         

                                                        <button  class="primary-btn order-submit" onclick="addtochekout()">Place order</button>
                                                        <c:set var="mess" value="${requestScope.ms}" />
                                                        <c:if test="${mess=='order successful!'}">
                                                        <div class="alert alert-success">
                                                            <strong>Congratulation!</strong> ${mess}
                                                        </div></c:if>
                                                        <c:if test="${mess!=null&&mess!='order successful!'}">
                                                        <div class="alert alert-danger">
                                                            <strong>Order fail!</strong> ${mess}
                                                        </div></c:if>
                                        </div>
					<!-- /Order Details -->
                                        
                                        
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

		<!-- jQuery Plugins -->
		<script src="electro-master/js/jquery.min.js"></script>
		<script src="electro-master/js/bootstrap.min.js"></script>
		<script src="electro-master/js/slick.min.js"></script>
		<script src="electro-master/js/nouislider.min.js"></script>
		<script src="electro-master/js/jquery.zoom.min.js"></script>
		<script src="electro-master/js/main.js"></script>
                <script>
    function convertToLocation() {
        // Retrieve values from input fields
        var address = document.querySelector('input[name="address"]').value;
        var city = document.querySelector('input[name="city"]').value;
        var province = document.querySelector('input[name="province"]').value;
        var country = document.querySelector('input[name="country"]').value;
        var zipCode = document.querySelector('input[name="zip-code"]').value;

        // Concatenate the values with a "-" separator
        var location = address + "-" + city + "-" + province + "-" + country + "-" + zipCode;

        // Do something with the concatenated location
        return location;
        function useLocation() {
        var finalLocation = convertToLocation();
        // Now you can use the finalLocation variable in another part of your code
        console.log("Final Location: " + finalLocation);
        // For example, you can assign it to a hidden input field in another form
        document.querySelector('input[name="final-location"]').value = finalLocation;
    }
        // You can perform further processing with this concatenated location
    }
                </script>
                <script type="text/javascript">
    function addtochekout() {
   var checkBox = document.getElementById("terms");
    if (checkBox.checked == false) {
        alert("Hãy đồng ý với các điều khỏa!");
    }
else {   document.f.submit();
    }
    }

                </script>
                
	</body>
</html>

