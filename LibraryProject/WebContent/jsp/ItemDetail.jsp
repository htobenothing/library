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
 <a href="#" style="float:right; ">Log In</a>
   <div style="clear:both; height:10px;"></div>
    <!-- end .header --></div>
  
  <div style="clear:both; height:10px;"></div>
  <div id="mainview" style="height:1000px; background:white;">
  	<div class="homesearchbar">
    <form action="/library/items/update" method="post">
      <label>Item Detail</label>
      <table class="stable">
        <tr >
            <td >ItemID</td>
            <td align="left">
            	<%-- <%=itm.getItemNumber()%> --%>
            	
            	${itmobj.itemNumber}
            </td>
        </tr>
        <tr >
            <td >Title</td>
            <td >
            	<input type="text" name="title" value="${itmobj.title}" style="width: 400px; "/>
            </td>
        </tr>        
        <tr >
            <td >Author</td>
            <td >
            	<input type="text" name="author" value="${itmobj.author}" style="width: 400px; "/>
            </td>
        </tr>
        <tr >
            <td >Publisher</td>
            <td>
            	<input type="text" name="publisher" value="${itmobj.publisher}" style="width: 400px; "/>
            </td>
        </tr>
		<tr >
            <td>Type</td>
			<c:choose>
				<c:when test='${itmobj.itemtypeID == 1}'>
				<td>Book</td>					
				</c:when>
				<c:when test='${itmobj.itemtypeID == 2}'>
				<td>CD</td>					
				</c:when>
				<c:when test='${itmobj.itemtypeID == 3}'>
				<td>Casettes</td>					
				</c:when>
				<c:when test='${itmobj.itemtypeID == 4}'>
				<td>Kits</td>					
				</c:when>
				<c:when test='${itmobj.itemtypeID == 5}'>
				<td>Manuscripts</td>					
				</c:when>
				<c:when test='${itmobj.itemtypeID == 6}'>
				<td>Magazines</td>					
				</c:when>
				<c:when test='${itmobj.itemtypeID == 7}'>
				<td>Journals</td>					
				</c:when>
			</c:choose>
        </tr>        
        <tr>
            <td >Status</td>            
			<c:choose>
				<c:when test='${itmobj.itemstatus == 1}'>
				<td>
					<select>
            		<option selected="selected" value="1">available</option>
            		<option value="0">unavailable</option>          
            		</select>
            	</td>
				</c:when>				
				<c:when test='${itmobj.itemstatus == 0}'>
				<td>
					<select>
            		<option value="1">available</option>
            		<option selected="selected" value="0">unavailable</option>          
            		</select>
            	</td>
				</c:when>
			</c:choose>		  	
		
        </tr>
     
      </table>
      <button type="submit" >Confirm</button>
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