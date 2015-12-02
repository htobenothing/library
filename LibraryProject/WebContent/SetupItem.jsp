<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:url var="show" value="/items/list" />
  <h1> 
   <a href="${show}" > Items List </a>
  </h1>
	<form action="/library/items/add" method="post">
     	Title
		<input type="text" name="title"/>
     <br/>
     	Author
		<input type="text" name="author"/>
     <br/>
     	Publisher
		<input type="text" name="publisher"/>
     <br/>
     	Year
		<input type="text" name="year"/>
     <br/>
     	Description
		<textarea name="description" rows="10" cols="30"></textarea>
     <br/>
     	ISBN
		<input type="text" name="isbn"/>
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
        <option value="1" selected="selected">Available</option>
        <option value="0">Unavailable</option>
     	</select>
     	<input type="submit" value="SUBMIT"/>
     	<input type="reset" value="RESET"/>
     	
	</form>
</body>
</html>