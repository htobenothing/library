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
 <a href="#" style="float:right; ">Log In</a>
   <div style="clear:both; height:10px;"></div>
    <!-- end .header --></div>
  
  <div style="clear:both; height:10px;"></div>
  <div id="mainview" style="height:1000px; background:white;">
  	<div class="confirmborrow">
      	<form style="margin:20px;" action="/library/transaction/finalborrow" method="post">
			<label>StudentID</label>
            <input type="text" disabled value=${user.userId }>
            <br>
            <label>StudentName</label>
            <input type="text" disabled value=${user.userName }>
            
             <table class="stable">
             	<caption >Book List</caption>
                <tr class="str">
                    <th >SN</th>
                    <th >Title</th>
                    <th >Author</th>
                    <th >Publisher</th>
                    <th>confirm </th>
                </tr>
                <c:forEach items="${sblist}" var="it" varStatus="i">
                <tr >
                    <td >${i.index+1}</td>
                    <td >${it.title}</td>
                    <td >${it.author}</td>
                    <td >${it.publisher }</td>
                    <td>
                    <input type="checkbox" name="confirm" value=${it.itemNumber } checked>
                    </td>
                </tr>
                </c:forEach>
         </table>
         <button type="submit">Confirm</button>
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