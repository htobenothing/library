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
    
    
  <div class="sidebar1">
  <ul class="nav">
     <li><a href="http://localhost:8080/library/jsp/libsearch.jsp">Search Item</a></li>
      <li><a href="../jsp/librariantransaction.jsp">Transaction History</a></li>
      <li><a href="../jsp/libreturn.jsp">Return Item</a></li>
      <li><a href="http://localhost:8080/library/jsp/MaintainItem.jsp">Maintain Item</a>
      <li><a href="/library/user/maintainstudent">Maintain Student</a></li>
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
                <option value="4">Kits</option>
                <option value="5">Manuscripts</option>
                <option value="6">Magazines</option>
                <option value="7">Journals</option>
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
                <option value="3">Renew</option>
               
                </select>
            </td>
        </tr>
   
       </table>
    <button type="submit">Search</button>
    </form>

  </div>
  <div style="margin:10px; height:660px; overflow-y: auto;">
  	
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