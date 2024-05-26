<%-- 
    Document   : search
    Created on : Feb 14, 2024, 12:10:41 PM
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

		<title>KingCapStore</title>
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
		<!-- HEADER -->
           
            <c:set var="y" value="${requestScope.Llist}"/>
            
            <c:set var="size" value="${sessionScope.size}"/>
            <c:set var="o" value="${sessionScope.cart}"/>
            <c:set var="acc" value="${sessionScope.account}"/>
            <jsp:useBean id="type" class="model.type.TypeDAO"/>
            <jsp:useBean id="brand" class="model.brand.BrandDAO"/>
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
                                                                              pattern="#,##0" value = "${i.product.outPrice-i.product.outPrice*i.product.discount}" /> VND</h4>
                                                                                        </div>
                                                                                        <form name="a" action="process" method="post">
                                                                                            <input type="hidden" name="id" value="${i.product.id}"/>  
                                                                                            <input type="hidden" name="page" value="search"/>
                                                                                            <input type="submit" class="delete" value="x"/>
                                                                                        </form>
                                                                                            
                                                                                    </div>
                                                                                        <c:set var="total" value="${total + (i.product.outPrice-i.product.outPrice*i.product.discount)*i.quantity}" />
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

		<!-- BREADCRUMB -->
		<div id="breadcrumb" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
                                <div class="row">
                                    <div class="col-md-12">
                                        <ul class="breadcrumb-tree">
                                            <h3 class="breadcrumb-header">Search</h3>

                                        </ul>
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
                            <!-- ASIDE -->
                            <form action="search?action=find" method="post" id="s">
                                <div id="aside" class="col-md-3">
                                    <!-- aside Widget -->
                                    <div class="aside">
                                        <h3 class="aside-title">Type</h3>
                                        <div class="checkbox-filter">
                                            <select style="background-color: #f4f4f4; color: #333; padding: 5px; border: 1px solid #ccc; border-radius: 5px;" id="id" name="type">
                                                <option value="0">...</option>
                                                <c:forEach items="${requestScope.tlist}" var="i" >    
                                                    <option value="${i.getId()}">${i.getName()}</option>
                                                </c:forEach>

                                            </select>

                                        </div>
                                    </div>
                                    <!-- /aside Widget -->

                                    <!-- aside Widget -->
                                    <div class="aside">
                                        <h3 class="aside-title">Price</h3>
                                        <div class="price-filter">
                                            <div id="price-slider"></div>
                                            <div class="input-number price-min">
                                                <input id="price-min" type="number" name="min_price">
                                                <span class="qty-up">+</span>
                                                <span class="qty-down">-</span>
                                            </div>
                                            <span>-</span>
                                            <div class="input-number price-max">
                                                <input id="price-max" type="number" name="max_price">
                                                <span class="qty-up">+</span>
                                                <span class="qty-down">-</span>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /aside Widget -->

                                    <!-- aside Widget -->
                                    <div class="aside">
                                        <h3 class="aside-title">Brand</h3>
                                        <div class="checkbox-filter">

                                            <select style="background-color: #f4f4f4; color: #333; padding: 5px; border: 1px solid #ccc; border-radius: 5px;" id="id" name="brand">
                                                <option value="0">...</option>
                                                <c:forEach items="${requestScope.blist}" var="i">    
                                                    <option value="${i.getId()}">${i.getName()}</option>
                                                </c:forEach>

                                            </select>

                                        </div>
                                    </div>
                                    <!-- /aside Widget -->

                                    <!-- aside Widget -->
                                    <div class="aside">
                                        <button type="submit" form="s" value="Submit" style="background-color: #204d74;color: white">Find</button>
                                    </div>
                                    <!-- /aside Widget -->
                                </div>

                                <!-- /ASIDE -->

                                <!-- STORE -->
                                <div id="store" class="col-md-9">
                                    <!-- store top filter -->
                                    <div class="store-filter clearfix">
                                        <div class="store-sort">
                                            <label>
                                                Sort By:
                                                <select class="input-select" name="sort">
                                                    <option value="1">Latest</option>
                                                    <option value="2">Oldest</option>
                                                    <option value="3">Price increasing</option>
                                                    <option value="4">Price decreasing</option>

                                                </select>
                                            </label>
                                            <c:set var="lsize" value="${requestScope.lsize}"/>
                                            <div class="store-filter clearfix">
                                                <span class="store-qty">Showing ${lsize} product(s)</span>

                                            </div>

                                        </div>

                                    </div>
                                    <!-- /store top filter -->
                            </form> 
						<!-- store products -->
						<div class="row">
                                                    <c:set var="mess" value="${requestScope.ms}"/>
                                                    <h3>${mess}</h3>
                                                    <c:forEach var="i" items="${requestScope.Llist}">
										<!-- product -->
                                                                                <div class="col-md-4 col-xs-6">
										<div class="product" >
											<div class="product-img">
                                                                                             <img src="product_images/${i.img.get(0)}" width="345px" height="345px" alt="">
												<div class="product-label">
                                                                                                    <c:if test="${i.id>49}">
                                                                                                        <span class="new">NEW</span>
                                                                                                    </c:if>
                                                                                                    
                                                                                                    <c:if  test="${i.discount>0}">
                                                                                                        <span class="sale"><fmt:formatNumber type = "number" 
                                                                              pattern="#,##0" value = "${i.discount*100}" /> %</span>
                                                                                                    </c:if>    
                                                                                                    
												</div>
											</div>
											<div class="product-body">
												
												<h3 class="product-name"><a href="#">${i.title} </a></h3>
												<h4 class="product-price"><fmt:formatNumber type = "number" 
                                                                              pattern="#,##0" value = "${i.outPrice-i.outPrice*i.discount}" /> VND 
                                                                                                    <c:if  test="${i.discount}>0">
                                                                                                    <del class="product-old-price"><fmt:formatNumber type = "number" 
                                                                              pattern="#,##0" value = "${i.outPrice}" /> VND</del>
                                                                                                    </c:if>
                                                                                                    </h4>
												<div class="product-rating">
													<c:forEach var = "o" begin = "1" end = "${i.getProductReviewScore()}">
                                                                                                            <i class="fa fa-star"></i>    
                                                                                                                </c:forEach>
                                                                                                            <c:forEach var = "x" begin = "1" end = "${5-i.getProductReviewScore()}">
                                                                                                             <i class="fa fa-star-o"></i> 
                                                                                                                </c:forEach>
												</div>
												<div class="product-btns">
													
                                                                                                     
                                                                                                    <button type="button" class="quick-view" onclick="redirectToProductDetails(${i.id})" ><i class="fa fa-eye"></i><span class="tooltipp">VIEW DETAIL </span></button>
												</div>
											</div>  
                                                                                                              
											
										</div>
                                                                                </div>
										<!-- /product -->
                                                                                </c:forEach>
							
							
						</div>
						<!-- /store products -->

						<!-- store bottom filter -->
						
						<!-- /store bottom filter -->
					</div>
					<!-- /STORE -->
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
                                                                                                        
                                                                                                        function redirectToProductDetails(productId) {
                                                                                                        
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
