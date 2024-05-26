<%-- 
    Document   : list
    Created on : Jan 30, 2024, 11:27:47 AM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>


<html>
  <head>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <title>List of students</title>
  </head>
  <body>
      <c:set var="size" value="${sessionScope.size}"/>
      <c:set var="x" value="${requestScope.lst}"/>
    <h2> List of products </h2>
    <form name="f"action="" method="get">
        <input type="number" name="num" value="1">
    <table border="1">
      <tr>
        <td> Id </td>
        <td> Name </td>
        <td> stock </td>
        <td> image</td>
         <td> type</td>
      </tr>
      <c:forEach var = "i" begin = "0" end = "54">
      <tr>
        <td>${x.get(i).getId()}</td>
        <td>${x.get(i).getTitle()}</td>
        <td>${x.get(i).getStock()}</td>
        <td><img src="product_images/${x.get(i).getImg().get(0)}" width="300px" height="300px"/> </td>
        <td>${x.get(i).getType().getName()}</td>
        <td>
            <input type="submit" onclick="buy(${x.get(i).getId()})" value="buy">
        </td>
       
     </c:forEach> 
        </form>
      </tr> 
    </table>     
    <p><button onclick='window.history.go(-1);'>Back to previous page</button>
    <p><a href="index.html">Back to homepage</a>
    <script type="text/jacascript">
            function buy(id){
            document.f.action="buy?id="+id;
            document.f.submit();
         }
            </script>    
  </body>
  
</html>