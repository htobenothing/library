<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
 <c:when test="${loginuser.userId!=null }"><label style="float:right;">Welcome:${loginuser.userName}</label><br><a href="/library/user/logout" style="float:right; ">Log out</a></c:when>
 </c:choose>
   <div style="clear:both; height:10px;"></div>
    <!-- end .header --></div>
     
  <div>
  <div style="margin:10px;">
  
<form class="searchbar" action="/library/items/searchresult" method="post">
    <table class="searchtable">
    <tr  class="str">
    	<td >Item Title</td>
        <td ><input type="text" name="title" style="width:400px"/></td>
    </tr>
     <tr>
    	<td>ItemType</td>
        <td >
        <select name="itemtypeID" style="width:200px">
        	<option value="-1" selected="selected">All</option>
        	<option value="1" >Book</option>
            <option value="2">CD</option>
            <option value="3">Casettes</option>
            <option value="4">Kits</option>
            <option value="5">Manuscripts</option>
            <option value="6">Magazines</option>
            <option value="7">Journals</option>
        </select>
        </td>
    </tr>
     <tr >
    	<td>ItemStatus</td>
        <td> 
        <select name="itemstatus" style="width:200px">
        	<option value="-1" selected="selected">All</option>
        	<option value="1">Available</option>
        </select>
        </td>
    </tr>
    <tr >
    	<td colspan="3" ><button type="submit">Search</button></td>
    </tr>
      </table>
    </form>
    
    <!-- search result table -->
   <form class="searchbar" action="/library/transaction/home" method="post">
   <div style="magin:10px auto; overflow-y: auto; height:740px;">
  	<label>Search Result</label>
  	<table class="stable" style="width:100%;">
    	<tr>
            <th>SN</th>
            <th>Title</th>
            <th>Author</th>
            <th>Publisher</th>
            <th>Status</th>
            <th>Borrow</th> 
        </tr>
        
        <c:forEach items="${itmlist}" var="items" varStatus="i">
			<tr>
				<td>${i.index+1}</td>
				<td>${items.title}</td>
				<td>${items.author}</td>
				<td>${items.publisher}</td>				
				<c:choose>
					<c:when test="${items.itemstatus != 0}">
						<td>available</td>
					</c:when>
					<c:otherwise>
						<td>unavailable</td>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${items.itemstatus == 0}">
						<td>
						<input type="checkbox" name="borrow" disabled="disabled" value=${items.itemNumber}>
						</td>
					</c:when>
					<c:otherwise>
						<td>
						<input type="checkbox" name="borrow" value=${items.itemNumber}>
						</td>
					</c:otherwise>
				</c:choose>
        	</tr>        
        </c:forEach> 
    </table>
    </div>
    <button type="submit" >Borrow</button>
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