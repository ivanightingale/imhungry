<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>List Management</title>
		<link rel="stylesheet" type="text/css" href="listPage.css" />
	</head>
	<body>
		<form action="listPage.jsp" method="GET">
			<input type="submit" id = "manage_list" value="Manage List" />
		</form>
	
		<form action="resultPage.jsp">
			<input type="submit" id = "back_result" value="Back to Result" />
		</form>
	
		<form action="searchPage.jsp">
			<input type="submit" id = "back_search" value="Back to Search" />
		</form>
	    
		<div id = "header">X List</div>
		<div id = "container">
			<div class = "item" id = "item1">
				<div class = "item_format1" >Name</div>
				<div class = "item_format2" >Stars</div>
				<div class = "divider"></div>
				<div class = "item_format3" >Mins</div>
				<div class = "item_format4" >Address</div>
				<div class = "item_format5" >Price</div>
			</div>
			</div>
		</div>

		<div class="dropDown" style="width:200px;">
			<select id = "dropdown">
				<option value="invalid">&nbsp</option>
				<option value="Favorites">Favorites</option>
				<option value="To Explore">To Explore</option>
				<option value="Do Not Show">Do Not Show</option>
			</select>
		</div>

		<script src="dropdown.js"></script>
		<script src="ListClient.js"></script>
		<script>

		</script>
	</body>
</html>