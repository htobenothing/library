<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/loginstyle.css" type="text/css">
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
      <li><a href="#">Maintain Item</a>
      <li><a href="#">Maintain Student</a></li>
    </ul>
  </div>
  <div class="content">
  <div>
  	<form action="/library/transaction/viewtransactionlib" method="post">
  	<label>Transaction History</label>
    <br>
    <table class="searchtransaction">
    	<tr>
        	<td>Date:</td>
            <td>
            <label>From:</label>
            <input type="date" name="startdate" >
            <label> To:</label>
            <input type="date" name="enddate">
            </td>
        </tr>
        <tr>
        	<td>Item Type:</td>
            <td>
                <select name="Item Type">
                <option value="-1">ALL</option>
                <option value="1">Book</option>
                <option value="2">CD</option>
                <option value="3">Casseette</option>
                </select>
            </td>
        </tr>
 		<tr>
        	<td>Status:</td>
            <td>
                <select name="Status">
                <option value="-1">All</option>
                <option value="1">Onloan</option>
                <option value="2">OverDue</option>
               
                </select>
            </td>
        </tr>
   
       </table>
    <button type="submit">Search</button>
    </form>

  </div>
  <div style="margin:10px;  ">
  	
  	<table class="stable">
    	<tr >
            <th>ItemID</th>
            <th>Title</th>
            <th>Borrower</th>
            <th>BorrowDate</th>
            <th>ReturnDate</th>
			<th>Status</th>
        </tr>
        <c:forEach items="${slist}" var="t" varStatus="i">
    	<tr >
        	<td>${t.itemID}</td>
            <td>${t.title}</td>
            <td>${t.username}</td>
            <td >${t.borrowDate}</td>
            <td>${t.returnDate}</td>
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
   
  </div>
  <!-- end .container --></div>


</body>
</html>