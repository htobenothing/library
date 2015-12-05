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
 <c:when test="${loginuser.userId!=null }"><label style="float:right;">Welcome:${loginuser.userName}</label><br><a href="/library/user/logout" style="float:right; ">Log out</a></c:when>
 </c:choose>
 <div style="clear:both; height:10px;"></div>
 <!-- end .header --></div>
 
 
  <div class="sidebar1">
    <ul class="nav">
      <li><a href="#">Serach Item</a></li>
      <li><a href="#">Transaction History</a></li>
      <li><a href="#">Return Item</a></li>
    </ul>
  </div>
  <div class="content">
   <label>Current Onloan</label>
  <div style="height:300px; ">
  <form action="/library/transaction/viewonloantransactionstu" method="post"> 
  <button type="submit">view on loan</button>
  </form>
 
  <table class="stable">
    	<tr >
            <th>ItemID</th>
            <th>Title</th>
            <th>BorrowDate</th>
            <th>DueDate</th>
            <th>Status</th>
            <th>Renew</th> 
        </tr>
		<c:forEach items="${ruslist}" var="t" varStatus="i">
    	<form action="/library/transaction/renew" method="post">
    	<input type="hidden" name="transactionid" value=${t.tranasctionID }>
        <tr>
        	<td>${t.itemID}</td>
            <td>${t.title}</td>
            <td>${t.borrowDate}</td>
            <td>${t.dueDate }</td>
            <td>${t.status}</td>
            <c:choose>
            	<c:when test="${t.statusNumber == 1 }">
            	<td><button type="submit">Renew</button></td>            	
            	</c:when>
            	<c:otherwise>
            	<td></td>
            	</c:otherwise>
            	
            </c:choose>
        </tr>
    	</form>        
      </c:forEach>
    </table>
 </div> 
  
  
  <div>
  	<form action="/library/transaction/viewtransactionstu" method="post">
  	<label>Transaction History</label>
    <br>
    <label>From:</label>
    <input type="date" name="startdate" >
    <label> To:</label>
    <input type="date" name="enddate" >
    <button type="submit">search</button>
    </form>
  </div>
  <div style="margin:10px; overflow:auto; height:450px;">
  	
  	<table class="stable">
    	<tr>
            <th>ItemID</th>
            <th>Title</th>
            <th>BorrowDate</th>
            <th>ReturnDate</th>
            <th>DueDate</th>
			<th>Status</th>
        </tr>
        <c:forEach items="${uslist}" var="t" varStatus="i">
    	<tr >
        	<td>${t.itemID}</td>
            <td>${t.title}</td>
            <td >${t.borrowDate}</td>
            <td>${t.returnDate}</td>
            <td>${t.dueDate}</td>
           	<td>${t.status}</td>
        </tr>
        </c:forEach>
    </table>
  </div>
  	
  </div>
  <div class="footer">
    <div class="footer">
      <p>People Who Like Books, Like Sun Rise In the World.</p>
      <p>Sun Rise Library</p>
      <!-- end .footer -->
    </div>
    <p>&nbsp;</p>
  </div>
  <!-- end .container --></div>
</body>
</html>