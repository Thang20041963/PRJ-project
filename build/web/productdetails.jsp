<%-- 
    Document   : productdetails
    Created on : Feb 3, 2024, 8:04:14 PM
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
        <c:set var="x" value="${requestScope.product}"/>
        <c:set var="size" value="${sessionScope.size}"/>
        <c:set var="acc" value="${sessionScope.account}"/>
        <c:set var="o" value="${sessionScope.cart}"/>
        <c:set var="notice" value="${requestScope.notice}"/>

        <c:set var="oncart" value="${0}"/>

        <c:if test="${o.getItems().size()>0&&o.getItemById(x.getId())!=null}">
            <c:set var="oncart" value="${o.getQuantityById(x.getId())}"/>
        </c:if>

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
                            <h3 class="breadcrumb-header">${x.getTitle()}</h3>

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
                    <!-- Product main img -->
                    <div class="col-md-5 col-md-push-2">
                        <div id="product-main-img">
                            <c:forEach items="${x.getImg()}" var="img">
                                <div class="product-preview">
                                    <img src="product_images/${img}" alt="">
                                </div>
                            </c:forEach>


                        </div>
                    </div>
                    <!-- /Product main img -->

                    <!-- Product thumb imgs -->
                    <div class="col-md-2  col-md-pull-5">
                        <div id="product-imgs">
                            <c:forEach items="${x.getImg()}" var="img">
                                <div class="product-preview">
                                    <img src="product_images/${img}" alt="">
                                </div>
                            </c:forEach>

                        </div>
                    </div>
                    <!-- /Product thumb imgs -->

                    <!-- Product details -->
                    <div class="col-md-5">
                        <c:if test="${notice!=null}">
                            <p id="demo"></p>
                            <div class="alert">
                                <p>Error:</p>
                                <span class="closebtn" onclick="this.parentElement.style.display = 'none';">&times;</span> 
                                <strong>${notice}</strong> 
                            </div>

                        </c:if>
                        <style>
                            .alert {
                                padding: 20px;
                                background-color: #f44336;
                                color: white;
                            }
                        </style>
                        <div class="product-details">
                            <h2 class="product-name">${x.getTitle()} </h2>

                            <div>
                                <h3 class="product-price"> <fmt:formatNumber type = "number" 
                                                  pattern="#,##0" value = "${x.getOutPrice()-x.getOutPrice()*x.getDiscount()}" /> VND  
                                    <c:if test="${x.getDiscount()>0}">
                                        <del class="product-old-price"> <fmt:formatNumber type = "number" 
                                                          pattern="#,##0" value = "${x.getOutPrice()}" /> VND</del>
                                        </c:if>
                                </h3>
                                <c:if test="${x.getStock()>0}">
                                    <span class="product-available" >In Stock :${x.getStock()}</span>
                                </c:if>
                            </div>

                            <div class="product-options">
                                <label>
                                    Type:${x.getType().getName()}

                                </label>

                                <label>
                                    Brand:${x.getBrand().getName()}

                                </label>
                            </div>
                            <c:if test="${x.getStock()>0}">


                                <form name="f" action="" method="POST" onSubmit="return false;" >
                                    <input type="hidden" name="page" value="1"/> 
                                    <div class="add-to-cart">
                                        <div class="qty-label">
                                            Qty
                                            <div class="input-number">
                                                <input type="number" id="quantity" name="quantity" value="1" min="1" max="${x.getStock()}">   
                                                <span class="qty-up">+</span>
                                                <span class="qty-down">-</span>
                                                <c:if test="">

                                                </c:if>
                                            </div>
                                        </div>
                                        <button class="add-to-cart-btn" type="button" onclick="addtocart(${x.getId()})"><i class="fa fa-shopping-cart"></i> add to cart</button>
                                    </div>
                                </form>
                                <ul class="product-btns">

                                </ul>
                            </c:if>


                            <ul class="product-links">
                                <li>Share:</li>
                                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                <li><a href="#"><i class="fa fa-envelope"></i></a></li>
                            </ul>

                        </div>
                    </div>
                    <!-- /Product details -->

                    <!-- Product tab -->
                    <div class="col-md-12">
                        <div id="product-tab">
                            <!-- product tab nav -->
                            <ul class="tab-nav">

                                <li><a data-toggle="tab" href="#tab2">Details</a></li>
                                <li><a data-toggle="tab" href="#tab3">Reviews (${x.getReviews().size()})</a></li>
                            </ul>
                            <!-- /product tab nav -->

                            <!-- product tab content -->
                            <div class="tab-content">
                                <!-- tab1  -->

                                <!-- /tab1  -->

                                <!-- tab2  -->
                                <div id="tab2" class="tab-pane fade in">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <p> Số lượng keycap: ${x.getNokc()} pcs</p>
                                            <p> Độ dày: ${x.getDesity()} mm</p>
                                            <p> Chất liệu : ${x.getMaterial()}</p>
                                            <p> Tương thích : ${x.getSuitability()}</p>

                                        </div>
                                    </div>
                                </div>
                                <!-- /tab2  -->

                                <!-- tab3  -->
                                <div id="tab3" class="tab-pane fade in">
                                    <div class="row">
                                        <!-- Rating -->
                                        <div class="col-md-3">
                                            <div id="rating">
                                                <div class="rating-avg">
                                                    <span>${x.getProductReviewScore()}</span>
                                                    <div class="rating-stars">
                                                        <c:forEach var = "star" begin = "1" end = "${x.getProductReviewScore()}">
                                                            <i class="fa fa-star"></i>    
                                                        </c:forEach>
                                                        <c:forEach var = "notstar" begin = "1" end = "${5-x.getProductReviewScore()}">
                                                            <i class="fa fa-star-o"></i> 
                                                        </c:forEach>   

                                                    </div>
                                                </div>
                                                <ul class="rating">
                                                    <li>
                                                        <div class="rating-stars">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                        </div>
                                                        <div class="rating-progress">
                                                            <div style="width:${x.getProductReviewScoreDetail2(5)}%;"></div>
                                                        </div>
                                                        <span class="sum">${x.getProductReviewScoreDetail1(5)}</span>
                                                    </li>
                                                    <li>
                                                        <div class="rating-stars">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star-o"></i>
                                                        </div>
                                                        <div class="rating-progress">
                                                            <div style="width:${x.getProductReviewScoreDetail2(4)}%;"></div>
                                                        </div>
                                                        <span class="sum">${x.getProductReviewScoreDetail1(4)}</span>
                                                    </li>
                                                    <li>
                                                        <div class="rating-stars">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                        </div>
                                                        <div class="rating-progress">
                                                            <div style="width:${x.getProductReviewScoreDetail2(3)}%;"></div>
                                                        </div>
                                                        <span class="sum">${x.getProductReviewScoreDetail1(3)}</span>
                                                    </li>
                                                    <li>
                                                        <div class="rating-stars">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                        </div>
                                                        <div class="rating-progress">
                                                            <div style="width:${x.getProductReviewScoreDetail2(2)}%;"></div>
                                                        </div>
                                                        <span class="sum">${x.getProductReviewScoreDetail1(2)}</span>
                                                    </li>
                                                    <li>
                                                        <div class="rating-stars">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                        </div>
                                                        <div class="rating-progress">
                                                            <div style="width:${x.getProductReviewScoreDetail2(1)}%;"></div>
                                                        </div>
                                                        <span class="sum">${x.getProductReviewScoreDetail1(1)}</span>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <!-- /Rating -->

                                        <!-- Reviews -->
                                        <div class="col-md-6">
                                            <div id="reviews">
                                                <ul class="reviews">
                                                    <c:forEach var="review" items="${x.getReviews()}">
                                                        <li>
                                                            <div class="review-heading">
                                                                <h5 class="name">${x.getReviewerName(review.getCustomer_id())}</h5>
                                                                <p class="date">${i.getReviewDate()}</p>
                                                                <div class="review-rating">
                                                                    <c:forEach var = "star" begin = "1" end = "${review.getReviewPoint()}">
                                                                        <i class="fa fa-star"></i>    
                                                                    </c:forEach>
                                                                    <c:forEach var = "notstar" begin = "1" end = "${5-review.getReviewPoint()}">
                                                                        <i class="fa fa-star-o"></i> 
                                                                    </c:forEach>   

                                                                </div>
                                                            </div>
                                                            <div class="review-body">
                                                                <p>${review.getDetail()}</p>
                                                            </div>
                                                        </li>
                                                    </c:forEach>

                                                </ul>

                                            </div>
                                        </div>
                                        <!-- /Reviews -->

                                        <!-- Review Form -->
                                        <div class="col-md-3">
                                            <div id="review-form">
                                                <form class="review-form" action="review" method="post" name="">
                                                    <input type="hidden" name="accid" value="${acc.getId()}"/>
                                                    <input type="hidden" name="proid" value="${x.getId()}"/>
                                                    <textarea class="input" placeholder="Your Review" name="detail"></textarea>
                                                    <div class="input-rating">
                                                        <span>Your Rating: </span>
                                                        <div class="stars">
                                                            <input id="star5" name="rating" value="5" type="radio"><label for="star5"></label>
                                                            <input id="star4" name="rating" value="4" type="radio"><label for="star4"></label>
                                                            <input id="star3" name="rating" value="3" type="radio"><label for="star3"></label>
                                                            <input id="star2" name="rating" value="2" type="radio"><label for="star2"></label>
                                                            <input id="star1" name="rating" value="1" type="radio"><label for="star1"></label>
                                                        </div>
                                                    </div>
                                                    <button class="primary-btn" type="submit" value="submit">Submit</button>
                                                </form>
                                            </div>
                                        </div>
                                        <!-- /Review Form -->
                                    </div>
                                </div>
                                <!-- /tab3  -->
                            </div>
                            <!-- /product tab content  -->
                        </div>
                    </div>
                    <!-- /product tab -->
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /SECTION -->

        <!-- Section -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">

                    <div class="col-md-12">
                        <div class="section-title text-center">
                            <h3 class="title">Related Products</h3>
                        </div>
                    </div>
                    <c:set var="r" value="${requestScope.related}"/>
                    <c:forEach items="${requestScope.related}" var="i">
                        <!-- product -->

                        <div class="col-md-3 col-xs-6">
                            <div class="product">
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

                                    <h3 class="product-name"><a href="#">${i.title}</a></h3>
                                    <h4 class="product-price"><fmt:formatNumber type = "number" 
                                                      pattern="#,##0" value = "${i.outPrice}" /> VND 
                                        <c:if  test="${i.discount}>0">
                                            <del class="product-old-price"><fmt:formatNumber type = "number" 
                                                              pattern="#,##0" value = "${i.outPrice+i.outPrice*i.discount}" /> VND</del></h4>
                                            </c:if>
                                    <div class="product-rating">
                                        <c:forEach var = "star" begin = "1" end = "${i.getProductReviewScore()}">
                                            <i class="fa fa-star"></i>    
                                        </c:forEach>
                                        <c:forEach var = "nonstar" begin = "1" end = "${5-i.getProductReviewScore()}">
                                            <i class="fa fa-star-o"></i> 
                                        </c:forEach>   
                                    </div>
                                    <div class="product-btns">


                                        <button type="button" class="quick-view" onclick="redirectToProductDetails(${i.id})" ><i class="fa fa-eye"></i><span class="tooltipp">view detail</span></button>
                                    </div>
                                </div>  

                                <div class="add-to-cart">
                                    <button type="button" class="add-to-cart-btn" onclick="addtocart(${i.id})"><i class="fa fa-shopping-cart"></i> add to cart</button>
                                </div>
                            </div>
                            <!-- /product -->

                        </div>
                        <!-- /product -->
                    </c:forEach>


                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /Section -->

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
                                <button type="button" class="newsletter-btn"><i class="fa fa-envelope"></i> Subscribe</button>
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



                var quantityInput = document.getElementById('quantity');
                var enteredQuantity = parseInt(quantityInput.value, 10);
                var stock = ${x.getStock()};
                var cartquantity = ${oncart};

                if (enteredQuantity + cartquantity > stock) {

                    alert("Quantity exceeds available stock!");
                    return;
                } else {
                    document.f.action = "cartcheking?id=" + id;
                    document.f.submit();
                }
            }

            function addtocart1() {
                document.a.submit();
            }

        </script>
        <script>
            function logout() {
                document.l.submit();
            }

        </script>
    </body>
</html>
