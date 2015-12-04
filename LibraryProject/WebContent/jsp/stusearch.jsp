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
  <div class="header"><a href="#"><img src="../img/BigLogo.png" alt="Insert Logo Here" name="Insert_logo" width="180" height="90" id="Insert_logo" style="background-color: lightgrey; display:block;" /></a> 
    <!-- end .header --></div>
  <div class="sidebar1">
    <ul class="nav">
      <li><a href="#">Serach Item</a></li>
      <li><a href="#">Transaction History</a></li>
      <li><a href="#">Return Item</a></li>
    </ul>
    <!-- end .sidebar1 --></div>
  <div class="content">
 <div style="margin:10px;">
 	<form class="searchbar">
    <table class="searchtable">
    <tr  class="str">
    	<td >Item Title</td>
        <td ><input type="text" name="keyword" /></td>
    </tr>
     <tr>
    	<td>ItemType</td>
        <td >
        <select name="itemtype">
        	<option value="-1" selected="selected">All</option>
        	<option value="1" >Book</option>
            <option value="2">CD</option>
            <option value="3">Casettes</option>
            <option value="4">Kits</option>
            <option value="5">Manuscripts</option>
            <option value="6">Magazines</option>
            <option value="7">Journals</option>
        </select>
        </td>
    </tr>
     <tr >
    	<td>ItemStatus</td>
        <td> 
        <select name="itemstatus">
        	<option value="-1" selected="selected">All</option>
        	<option value="0">Avaiable</option>
        </select>
        </td>
    </tr>
    <tr >
    	<td colspan="3" ><button type="submit">Search</button></td>
    </tr>
      </table>
    </form>
   <form>
   <div style="height:650px;">
  	
  	<table class="stable">
    	<caption class="scaptain" >Search Result</caption>
    	<tr class="str">
            <th class="sth">SN</th>
            <th class="sth">Title</th>
            <th class="sth">Author</th>
            <th class="sth">Publisher</th>
            <th class="sth">Status</th>
            <th class="sth">Borrow</th> 
        </tr>
    	<tr class="str">
        	<td class="std">0001</td>
            <td class="std">Java</td>
            <td class="std">2015-05-06</td>
            <td class="std">null</td>
            <td class="std">2015-06-06</td>
            <td class="std"><input type="checkbox" name="borrow" value=${item.id}></td>
        </tr>
    </table>
    </div>
    <button type="submit" >Borrow</button>
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