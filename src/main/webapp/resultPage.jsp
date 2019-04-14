<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/resultPage.css" />
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

	<meta charset="UTF-8">
	<title>Result Page</title>
</head>

<body>

<script src="js/loginChecker.js"></script>
<script>checkLoggedIn();</script>
<div id="common_header">
	<h4 id = "header_text">I'm Hungry </h4>
</div>
	<div id = "header">Results</div>
	<div id = "collage"></div>
	
	<form action="listPage.jsp">
        <div class="dropDown">
            <select id = "dropdown" name="list">
                <option value="invalid">&nbsp</option>
                <option value="Favorites">Favorites</option>
                <option value="To Explore">To Explore</option>
                <option value="Do Not Show">Do Not Show</option>
				<option value="Grocery">Grocery</option>
            </select>
        </div>
   	 	<input type="submit" id = "manage_list" value="Manage List" />
	</form>
	
	<form action="searchPage.jsp">
   	 	<input type="submit" id = "back_search" value="Back to Search" />
	</form>
	
	<div id = "container">
		<div id = "column1">
			<div id = "restaurantColumn" class = "sub_header">Restaurants</div>

		</div>
		<div id = "column2">
			<div id = "recipeColumn" class = "sub_header">Recipes</div>
		</div>

		<ul id=”pages”></ul>

	</div>

    <script src="js/parseQueryString.js"></script>
	<script src="js/resultPage.js"></script>

</body>
</html>