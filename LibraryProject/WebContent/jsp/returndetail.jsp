<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../css/loginstyle.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div class="container" >
 <div class="header">
 <img src="../img/BigLogo.png" alt="Insert Logo Here" name="Insert_logo" width="180" height="90" id="Insert_logo" style="background-color: lightgrey; float:left" />
 <c:choose>
 <c:when test="${loginuser.userId==null}"><a href="../jsp/login.jsp" style="float:right; ">Log In</a></c:when>
 <c:when test="${loginuser.userId!=null }">
 	<a href="/library/user/logout" style="float:right; ">Log out</a>
 	<label style="float:right;">Welcome:${loginuser.userName}|</label>
 	</c:when>
 </c:choose>
 <div style="clear:both; height:10px;"></div>
 <!-- end .header --></div>
  
  <div style="clear:both; height:10px;"></div>
  <div id="mainview" style="height:1000px; background:white;">
  <div class="homesearchbar">
  <form action="/library/transaction/returnfinal" method="post">
      <label>Return Item Detail</label>
      <input type="hidden" name="transactionid"  value=${returnID.tranasctionID}>
      <table class="stable">
        <tr>
            <td>Item ID</td>
            <td>${returnID.itemID}</td>
        </tr>
        <tr>
            <td>Item Title</td>
            <td>${returnID.title }</td>
        </tr>
        
        <tr>
            <td>BorrowDate</td>
            <td>${returnID.borrowDate}</td>
        </tr>
        
		<tr>
            <td>DueDate</td>
            <td>${returnID.dueDate}</td>
        </tr>      
          
        <tr>
            <td>ReturnDate</td>
            <td>${returnID.returnDate}</td>
        </tr>
        
        <tr>
            <td>status</td>
            <td>${returnID.status}</td>
        </tr>
        <tr>
            <td>Overdue Fund</td>
            <td>${duefee}</td>
        </tr>
     
      </table>
      <button type="submit" >Confirm</button>
      </form>
  </div>
  </div>
  <div class="footer">
    <p>People Who Like Books, Like Sun Rise In the World.</p>
    <p>Sun Rise Library</p>
    <!-- end .footer --></div>
  <!-- end .container --></div>
</body>
</html>