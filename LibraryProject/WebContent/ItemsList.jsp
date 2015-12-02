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
<table border="1">
		<tr>
		    <td>ItemNumber </td>
			<td>Title</td>
			<td>Author</td>
			<td>Publisher</td>
			<td>Year</td>
			<td>Description</td>
			<td>ISBN</td>
			<td>Item Type </td>
			<td>Status </td>
			<td>Edit</td>
		</tr>
		<c:forEach items="${itmlist}" var="items">
			<tr> 
				<td>${items.itemNumber}</td>
				<td>${items.title}</td>
				<td>${items.author}</td>
				<td>${items.publisher}</td>
				<td>${items.year}</td>
				<td>${items.description}</td>
				<td>${items.isbn}</td>
				<td>${items.itemtypeID}</td>
				<td>${items.itemstatus}</td>
				<td> 
				   <c:url var="edt" value="/items/edit">
				      <c:param name="itemNumber" value="${items.itemNumber}"/>
				   </c:url>
				   <a href="${edt}">Update</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>