<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="resultPage.css" />
	<meta charset="UTF-8">
	<title>Result Page</title>
</head>
<body>
	<div id = "header">Results for X </div>
	<div id = "collage"></div>

	<div class="dropDown" style="width:200px;">
        <select id = "dropdown">
            <option value="invalid">&nbsp</option>
            <option value="Favorites">Favorites</option>
            <option value="To Explore">To Explore</option>
            <option value="Do Not Show">Do Not Show</option>
        </select>
	</div>
	
	<form action="listPage.jsp">
   	 	<input type="submit" id = "manage_list" value="Manage List" />
	</form>
	
	<form action="searchPage.jsp">
   	 	<input type="submit" id = "back_search" value="Back to Search" />
	</form>
	
	<div id = "container">
		<div id = "column1">
			<div class = "sub_header">Restaurants</div>
			<div class = "item" id = "Res_item1">
				<div class ="Res_section1">Section 1</div>
				<div class ="Res_section2">Section 2</div>
				<div class = "divider"></div>
				<div class ="Res_section3">Section 3</div>
				<div class ="Res_section4">Section 4</div>
				<div class ="Res_section5">Section 5</div>
			</div>
			<div class = "item" id = "Res_item2">
				<div class ="Res_section1">Section 1</div>
				<div class ="Res_section2">Section 2</div>
				<div class = "divider"></div>
				<div class ="Res_section3">Section 3</div>
				<div class ="Res_section4">Section 4</div>
				<div class ="Res_section5">Section 5</div>
			</div>
				<div class = "item" id = "Res_item3">
				<div class ="Res_section1">Section 1</div>
				<div class ="Res_section2">Section 2</div>
				<div class = "divider"></div>
				<div class ="Res_section3">Section 3</div>
				<div class ="Res_section4">Section 4</div>
				<div class ="Res_section5">Section 5</div>
			</div>
			<div class = "item" id = "Res_item4">
				<div class ="Res_section1">Section 1</div>
				<div class ="Res_section2">Section 2</div>
				<div class = "divider"></div>
				<div class ="Res_section3">Section 3</div>
				<div class ="Res_section4">Section 4</div>
				<div class ="Res_section5">Section 5</div>
			</div>
		</div>
		<div id = "column2">
			<div class = "sub_header">Recipe</div>
			<div class = "item" id = "Rec_item1">
				<div class ="Rec_section1">Section 1</div>
				<div class ="Rec_section2">Section 2</div>
				<div class = "divider"></div>
				<div class ="Rec_section3">Section 3</div>
				<div class ="Rec_section4">Section 4</div>
				<div class ="Rec_section5">Section 5</div>
			</div>

			<div class = "item" id = "Rec_item2">
				<div class ="Rec_section1">Section 1</div>
				<div class ="Rec_section2">Section 2</div>
				<div class = "divider"></div>
				<div class ="Rec_section3">Section 3</div>
				<div class ="Rec_section4">Section 4</div>
				<div class ="Rec_section5">Section 5</div>
			</div>
			<div class = "item" id = "Rec_item3">
				<div class ="Rec_section1">Section 1</div>
				<div class ="Rec_section2">Section 2</div>
				<div class = "divider"></div>
				<div class ="Rec_section3">Section 3</div>
				<div class ="Rec_section4">Section 4</div>
				<div class ="Rec_section5">Section 5</div>
			</div>
			<div class = "item" id = "Rec_item4">
				<div class ="Rec_section1">Section 1</div>
				<div class ="Rec_section2">Section 2</div>
				<div class = "divider"></div>
				<div class ="Rec_section3">Section 3</div>
				<div class ="Rec_section4">Section 4</div>
				<div class ="Rec_section5">Section 5</div>
			</div>
		</div>
	</div>

    <script src="dropdown.js"></script>

</body>
</html>