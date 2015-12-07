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
   	<div style="clear:both; height:10px;"></div>
    <!-- end .header --></div>
  
  <div style="clear:both; height:10px;"></div>
  <div id="mainview" style="height:1000px; background:white;">
  	<div class="homesearchbar">  	
    <form action="/library/items/add" method="post">      
      <label>New Item</label>            
      <table style="text-align:left">
        <tr >
            <td >Title</td>
            <td >
            	<input type="text" name="title" style="width:100%"/>
            </td>
        </tr>        
        <tr >
            <td >Author</td>
            <td >
            	<input type="text" name="author" style="width:100%"/>
            </td>
        </tr>
        <tr >
            <td >Publisher</td>
            <td>
            	<input type="text" name="publisher" style="width:100%"/>
            </td>
        </tr>
        <tr>
        	<td>Year</td>
			<td><input type="text" name="year"/></td>
     	</tr>
     	<tr>
     	<td>Description</td>
		<td><input type="text" name="description" ></td>
		</tr>
		<tr>
		<td>ISBN</td>
		<td><input type="text" name="isbn"/></td>
		</tr>
		<tr >
            <td>Type</td>
            <td>
			<select name="itemtypeID">
        	<option value="1">Book</option>
        	<option value="2">CD</option>
        	<option value="3">Casettes</option>
        	<option value="4">Kits</option>
        	<option value="5">Manuscripts</option>
        	<option value="6">Journals</option>
     		</select>
     	</td>
        </tr>        
        <tr>
            <td>Status</td>            
			<td>
			<select name="itemstatus">
        	<option value="1" selected="selected">Available</option>
     		</select>
     		</td>		  	
        </tr>
        <tr>
        	<td>
        		<input type="submit" value="Create">
        	</td>        		      	
        </tr>
      </table>      
      <c:if test="${istitlenull == true}"><br>
      	<label class="errorlabe">Title can not be empty</label>
      </c:if>
      <c:if test="${isauthornull == true}"><br>
      	<label class="errorlabe">Author can not be empty</label>
      </c:if>
      <c:if test="${isphonecorrect == false}"><br>
      	<label class="errorlabe">Phone No must be 8 digit.</label>
      </c:if>      
      </form>
      <form action="../jsp/libsearch.jsp" method="post"><button type="submit">Cancel</button></form>
	</div>
  </div>
  <div class="footer">
    <p>People Who Like Books, Like Sun Rise In the World.</p>
    <p>Sun Rise Library</p>
    <!-- end .footer --></div>
  <!-- end .container --></div>
</body>
</html>