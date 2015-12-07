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
    
    
  <div class="sidebar1">
    <ul class="nav">
     <li><a href="http://localhost:8080/library/jsp/libsearch.jsp">Search Item</a></li>
      <li><a href="../jsp/librariantransaction">Transaction History</a></li>
      <li><a href="../jsp/libreturn">Return Item</a></li>
      <li><a href="http://localhost:8080/library/jsp/MaintainItem.jsp">Maintain Item</a>
      <li><a href="/library/user/maintainstudent">Maintain Student</a></li>
    </ul>
    <!-- end .sidebar1 --></div>
  <div class="content">
 <div style="margin:10px;">
 	<form class="searchbar" action="/library/transaction/returnlib" method="post">
    	<label >Student ID</label>
        <input type="text" name="studentid"  />
        <button type="submit">Search</button>
    </form>
   <div style="height:740px;">
  
        <table  class="stable" >
        <caption class="scaptain">Onloan Item</caption>
        	<tr>
                <th>ItemID</th>
                <th>Title</th>
                <th>Borrow</th>
                <th>BorrowDate</th>
                <th>DueDate</th>
                <th>Status</th>
                <th>Return</th> 
            </tr>
           <c:forEach items="${rlist}" var="t" varStatus="i">
           <form action="/library/transaction/returnlib2" method="post">
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
  </div>
  <div class="footer">
    <p>People Who Like Books, Like Sun Rise In the World.</p>
    <p>Sun Rise Library</p>
    <!-- end .footer --></div>
  <!-- end .container --></div>
</body>
</html>