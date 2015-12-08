<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/loginstyle.css" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
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
    <form action="/library/user/createstudent" method="post">
      <label>Student Detail</label>
     	
      <table class="stable" style="text-align:left">
        <tr>
            <td>Student ID</td>
            <td>S<input type="text" name="studentid">
            </td>
        </tr>
        <tr>
            <td>Student name</td>
            <td><input type="text" name="studentname"></td>
        </tr>
         <tr>
            <td>password</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>confirm password</td>
            <td><input type="password" name="confirmpassword"></td>
        </tr>
         <tr>
            <td>Date of Birth</td>
            <td><input type="Date" name="dateofbirth"></td>
        </tr>
         
        <tr>
            <td>Email</td>
            <td><input type="text" name="email"></td>
        </tr>
         <tr>
            <td>Contact Number</td>
            <td><input type="text" name="phone"></td>
        </tr>
        
        <tr>
            <td>Address</td>
            <td><input type="text" name="address"></td>
        </tr>
        
     <!--    <tr>
            <td>status</td>
            <td><select name="status" disabled="disabled">
            <option value="1" selected>active</option>
            <option value="0">unactive</option>
            </select></td>
        </tr> -->
     
      </table>
      <button type="submit" >Confirm</button>
      		<c:if test="${iscurrentuser ==true}"><br><label class="errorlabe">Student with the same id already exist. </label></c:if>
      		<c:if test="${isidright==false}"><br><label class="errorlabe">Did not enter 8 digits ID  </label></c:if>
      		<c:if test="${ispasswordsame==false}"><br><label class="errorlabe">password not same</label></c:if>
      		<c:if test="${ispasswordnull==true}"><br><label class="errorlabe">password can not be empty</label></c:if>
      		<c:if test="${isusernamenull==true}"><br><label class="errorlabe">User name can not be empty</label></c:if>
      		<c:if test="${isphonecorrect==false}"><br><label class="errorlabe">Phone not 8 digits</label></c:if>

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