<%-- 
    Document   : login
    Created on : Jan 30, 2024, 9:34:27 AM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>KingCapStore</title>
        <link rel="shortcut icon" type="image/x-icon" href="support_images/logo1.png" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="electro-master/css/newcss.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- Include your custom JavaScript file -->
        <script src="path/to/your/newjavascript.js"></script>
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
input:-webkit-autofill,
input:-webkit-autofill:hover,
input:-webkit-autofill:focus,
input:-webkit-autofill:active {
color: black !important;
-webkit-text-fill-color: black !important;
-webkit-box-shadow: 0 0 0 1000px white inset !important;
-webkit-background-clip: text !important;
background-clip: text !important;
}
</style>
    </head>
    <body>
        <c:set var="cookie" value="${pageContext.request.cookies}"/>
        <c:set var="mess" value="${requestScope.ms}"/>

        

        <!-- comment -->

        <div class="materialContainer">


            <div class="box">
                
                <div class="title">LOGIN TO</div>
                <c:if test="${mess!=null}">
                    <div>
                        <img src="support_images/logo.png" width="30px" height="30px" alt="alt" /> <i>Kingcap Store</i> 
                <div class="alert">
                    <p>Error:</p>
                    <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
                    <strong>${mess}</strong> 
                </div>
                </div>
                </c:if>
                <form action="login" method="post">
                <div class="input">
                    <label for="name">Username</label>
                    <input type="text" name="user" id="name" value="${cookie.cuser.value}" >
                    <span class="spin"></span>
                </div>

                <div class="input">
                    <label for="pass">Password</label>
                    <input type="password" name="pass" id="pass" value="${cookie.cpass.value}">
                    <span class="spin"></span>
                </div>
                    <input type="checkbox"
                           ${cookie.crem!=null?'checked':''}
                           name="rem" value="ON"/> Remember me<br/>
                    
                <div class="button login">
                    <button type="submit" name="login" value="login"><span>GO</span> <i class="fa fa-check"></i></button>
                </div>

                <a href="" class="pass-forgot">Forgot your password?</a>
               
                </form>
                                  
            </div>
                                 
                    
            <div class="overbox">
                <div class="material-button alt-2"><span class="shape"></span></div>

                <div class="title">REGISTER</div>
               <form action="login" method="post">
                <div class="input">
                    <label for="regname">Username</label>
                    <input type="text" name="regname" id="regname">
                    <span class="spin"></span>
                </div>

                <div class="input">
                    <label for="regpass">Password</label>
                    <input type="password" name="regpass" id="regpass">
                    <span class="spin"></span>
                </div>

                <div class="input">
                    <label for="reregpass">Repeat Password</label>
                    <input type="password" name="reregpass" id="reregpass">
                    <span class="spin"></span>
                </div>

                <div class="button">
                    <button type="submit" name="signup" value="signup"><span>NEXT</span></button>
                </div>
                    </form>

            </div>
        </div>   
        <script src="electro-master/js/newjavascript.js"></script>
    </body>
</html>
