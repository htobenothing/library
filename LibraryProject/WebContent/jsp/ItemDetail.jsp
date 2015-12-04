<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dto.Items" %>
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
    <%
		Items itm = (Items)session.getAttribute("itmobj");   	
    %>
    <%!
    	public String itemType(int i){
    		switch (i){
    		case 1:
    			return "Book";
    		case 2:
    			return "CD";
    		case 3:
    			return "Casettes";
    		case 4:
    			return "Kits";
    		case 5:
    			return "Manuscripts";
    		case 6:
    			return "Magazines";
    		case 7:
    			return "Journals";
    		default:
    			return "Not Defined Yet!";
    		}    	
    }
    %>
    
      <label>Item Detail</label>
      <table class="stable">
        <tr >
            <td >ItemID</td>
            <td align="left">
            	<%=itm.getItemNumber()%>
            </td>
        </tr>
        <tr >
            <td >Title</td>
            <td >
            	<input type="text" name="title" value="<%=itm.getTitle()%>" style="width: 400px; "/>
            </td>
        </tr>        
        <tr >
            <td >Author</td>
            <td >
            	<input type="text" name="author" value="<%=itm.getAuthor()%>" style="width: 400px; "/>
            </td>
        </tr>
        <tr >
            <td >Publisher</td>
            <td>
            	<input type="text" name="publisher" value="<%=itm.getPublisher()%>" style="width: 400px; "/>
            </td>
        </tr>
		<tr >
            <td>Type</td>
            <td>
				<%= itemType(itm.getItemtypeID()) %>          
      			<!-- <input type="text" name="itemtypeID" value="<%=itm.getItemtypeID()%>" style="width: 400px; "/> -->
            </td>
        </tr>        
        <tr>
            <td >Status</td>
     <%!
    	public String itemstatus(String i){
    		if(i.equals("1"))
    			return "available";
    		else
    			return "unavailable";    	
    }
    %>
            <td>
            <%= itemstatus(itm.getItemstatus())%>
			<c:choose>
				<c:when test="${itm.getItemstatus() == '1'}">
					<select>
            		<option selected="selected" value="1">available</option>
            		<option value="0">unavailable</option>          
            		</select>
				</c:when>
				<c:otherwise>
					<select>
            		<option value="1">available</option>
            		<option selected="selected" value="0">unavailable</option>          
            		</select>
				</c:otherwise>
			</c:choose>		  	
			</td>
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