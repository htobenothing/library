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
  <div class="header"><a href="#"><img src="../img/BigLogo.png" alt="Insert Logo Here" name="Insert_logo" width="180" height="90" id="Insert_logo" style="background-color: lightgrey; display:block;" /></a> 
    <!-- end .header --></div>
  <div class="sidebar1">
    <ul class="nav">
      <li><a href="#">Serach Item</a></li>
      <li><a href="#">Transaction History</a></li>
      <li><a href="#">Return Item</a></li>
    </ul>
  </div>
  <div class="content">
 <label>Current Onloan Item</label>
 <div style="margin:10px;">
 
  	<form action="/library/transaction/returnstu" method="post">
  	<button type="submit">Show on loan</button>
  	</form>
  	<table class="stable">
    	<tr >
            <th>ItemID</th>
            <th>Title</th>
            <th>BorrowDate</th>
            <th>ReturnDate</th>
            <th>DueDate</th>
            <th>Status</th>
            <th>Renew</th> 
        </tr>
    	<c:forEach items="${srlist}" var="t" varStatus="i">
           <form action="/library/transaction/returnstu2" method="post">
           <input type="hidden"  name="tansactionid" value=${t.tranasctionID }>
           <tr>
                <td >${t.itemID}</td>
                <td >${t.title}</td>
                <td >${t.username}</td>
                <td>${t.borrowDate}</td>
                <td >${t.dueDate}</td>
                <td >${t.status}</td>
                <td ><button type="submit">Return</button></td>
           </tr>
            </form>
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
  </div>
  <!-- end .container --></div>
</body>
</html>