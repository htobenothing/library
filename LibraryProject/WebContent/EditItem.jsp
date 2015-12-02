<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dto.Items" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="/library/items/update" method="post">
	<%
		Items itm = (Items)session.getAttribute("itmobj");
	%>
		<%=itm.getItemNumber()%>
     	Title     	
		<input type="text" name="title" value="<%=itm.getTitle()%>"/>
     <br/>
     	Author
		<input type="text" name="author" value="<%=itm.getAuthor()%>"/>
     <br/>
     	Publisher
		<input type="text" name="publisher" value="<%=itm.getPublisher()%>"/>
     <br/>
     	Year
		<input type="text" name="year" value="<%=itm.getYear()%>"/>
     <br/>
     	Description
		<textarea name="description" rows="10" cols="30">
		<%=itm.getDescription()%>
		</textarea>
     <br/>
     	ISBN
		<input type="text" name="isbn" value="<%=itm.getIsbn()%>"/>
     <br/>
     	Item Type
		<select name="itemtypeID">
        <option value="1">Book</option>
        <option value="2">CD</option>
        <option value="3">Casettes</option>
        <option value="4">Kits</option>
        <option value="5">Manuscripts</option>
        <option value="6">Journals</option>
     	</select>
     <br/>
     	Item Status
     	<select name="itemstatus">
        <option value="1">Available</option>
        <option value="0">Unavailable</option>
     	</select>
     	<input type="submit" value="SUBMIT"/>
     	<input type="reset" value="RESET"/>
     	
	</form>
</body>
</html>