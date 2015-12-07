<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
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
     <ul class="nav">
     <li><a href="http://localhost:8080/library/jsp/libsearch.jsp">Search Item</a></li>
      <li><a href="../jsp/librariantransaction.jsp">Transaction History</a></li>
      <li><a href="../jsp/libreturn.jsp">Return Item</a></li>
      <li><a href="http://localhost:8080/library/jsp/MaintainItem.jsp">Maintain Item</a>
      <li><a href="/library/user/maintainstudent">Maintain Student</a></li>
    </ul>
    <!-- end .sidebar1 --></div>
  <div class="content">
 <div style="margin:10px;">

 	<form class="searchbar" action="/library/user/showStuById" method="post">
    	<label >Student ID</label>
        <input type="text" name="userid" />
        <button type="submit">Search</button>
    </form>
   	<a href ="../jsp/newstudent.jsp">Create New Student</a>
	<br>
   <form>
   <div style="height:740px;">
        <table class="stable">
       	
       	<c:if test="${isnorecord== true }"><label class="errorlabe">No Record Find</label></c:if>
            <tr >
            	<th>SN</th>
                <th>StudentID</th>
                <th>StudentName</th>
                <th>ContactNumber</th>
                <th>Status</th>
                <th>Edit</th>
            </tr>
           
            <c:forEach items="${stulist}" var="stu" varStatus="i">
            
            <tr>
            	<td>${i.index+1}</td>
                <td>${stu.userId}</td>
                <td>${stu.userName}</td>
                <td>${stu.phoneNumber}</td>
                <c:choose> 
                <c:when test='${stu.userStatus =="1"}'><td>Active</td></c:when>
                <c:when test='${stu.userStatus =="0"}'><td>Unactive</td></c:when>
                </c:choose>
				<td>
					<%-- <c:url var="edit" value="/lbrary/user/studetail">
						<c:param name="userid" value="${stu.userId}"/>
					</c:url> --%>
					<a href="studetail?userid=${stu.userId}">Edit</a>
				</td>
            </tr>
            </c:forEach>

        </table>
    </div>
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