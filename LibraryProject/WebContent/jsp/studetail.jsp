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
  
  <div style="clear:both; height:10px;"></div>
  <div id="mainview" style="height:1000px; background:white;">
  	<div class="homesearchbar">
   <form action="/library/user/updatestudent" method="post">
      <label>Student Detail</label>
      <input  type="hidden" value="${stu.userId}" name="studentid">
     <table class="stable" style="text-align:left">
        <tr>
            <td>Student ID</td>
            <td>${stu.userId}</td>
            
        </tr>
        <tr>
            <td>Student name</td>
            <td><input type="text" name="studentname" value="${stu.userName}"></td>
        </tr>
         <tr>
            <td>password</td>
            <td><input type="password" name="password" value="${stu.password}"></td>
        </tr>
         <tr>
            <td>password</td>
            <td><input type="password" name="confirmpassword" value="${stu.password}"></td>
        </tr>
        
         
        <tr>
            <td>role type</td>
            
            <td>
            <c:choose>
            	<c:when test='${stu.role=="student"}'>
            	<select name="roletype" >
            	<option value="librarian" >librarian</option>
            	<option value="student" selected>student</option>
            	</select>
            	</c:when>
            	<c:when test='${stu.role=="librarian"}'>
            	<select name="roletype" >
            	<option value="librarian" selected>librarian</option>
            	<option value="student" >student</option>
            	</select>
            	</c:when>
            </c:choose>	
            </td>
        </tr>
         <tr>
            <td>Date of Birth</td>
            <td><input type="Date" name="dateofbirth" value="${stu.dateOfBirth }"></td>
        </tr>
         
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" value="${stu.email }"></td>
        </tr>
         <tr>
            <td>Contact Number</td>
            <td><input type="text" name="phone" value="${stu.phoneNumber}"></td>
        </tr>
        
        <tr>
            <td>Address</td>
            <td><textarea rows="3" name="address" value="${stu.address}"></textarea></td>
        </tr>
        <tr>
            <td>status</td>
            <c:choose>
            <c:when test='${stu.userStatus ==1 }'>
            	<td><select name="status">
           	 	<option value="1" selected>active</option>
            	<option value="0">unactive</option></select></td>
            </c:when>
             <c:when test='${stu.userStatus ==0 }'>
            	<td><select name="status">
           	 	<option value="1" >active</option>
            	<option value="0" selected>unactive</option></select></td>
            </c:when>
            </c:choose>
        </tr> 
     
      </table>
      <button type="submit" >Confirm</button>
      		<c:if test="${ispasswordsame==false}"><br><label class="errorlabe">password not same</label></c:if>
      		<c:if test="${ispasswordnull==true}"><br><label class="errorlabe">password can not be empty</label></c:if>
      		<c:if test="${isusernamenull==true}"><br><label class="errorlabe">User name can not be empty</label></c:if>
      		<c:if test="${isphonecorrect==false}"><br><label class="errorlabe">Phone not 8 digits</label></c:if>
      </form>
      
      <form action="/library/user/maintainstudent" method="post"><button type="submit">Cancel</button></form>
      
	</div>
  </div>
  <div class="footer">
    <p>People Who Like Books, Like Sun Rise In the World.</p>
    <p>Sun Rise Library</p>
    <!-- end .footer --></div>
  <!-- end .container --></div>

</body>
</html>