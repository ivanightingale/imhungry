<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/resultPage.css" />
	<meta charset="UTF-8">
	<title>Result Page</title>
</head>
<body>
	<div id = "header">Results for X</div>
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
		</div>
	</div>

    <script src="js/dropdown.js"></script>
    <script src="js/parseQueryString.js"></script>
    <script>
        var query = parseQuery(window.location.search);
        /* ****** Uncomment when SearchServlet is ready
        var xhttp = new XMLHttpRequest();
        xhttp.open("GET", "/Search?search="+query.search+"&number="+query.number, false);
        xhttp.send();
        var response = JSON.parse(xhttp.response);
        var results = response.body;
        */
        // ******* Testing only
        results = [[{
            name: "Test Restaurant - Starbucks",
            rating: 3,
            placeID: "2PC9+M4 Los Angeles, California",
            address: "642 W 34th St, Los Angeles, CA 90007",
            price: 5,
            driveTimeText: "2 min",
            driveTimeValue: 120,
            phone: "(213) 740-6285",
            url: "starbucks.com"
        }], [{
            name: "Test Recipe",
            rating: 5,
            recipeID: 12345,
            prepTime: 5,
            cookTime: 10,
            ingredients: ["A", "BC", "DEF"],
            instructions: ["GHIJ", "KLMNO", "PQRSTU"],
            imageURL: "https://foodrevolution.org/wp-content/uploads/2018/04/blog-featured-diabetes-20180406-1330.jpg"
        }]];
        // ******* End test section
        //Store results in local storage
        window.localStorage.setItem("search", query.search);
        window.localStorage.setItem("searchResults", JSON.stringify(results));
        //Use results object to populate page after this (need SearchServlet implemented)
    </script>

</body>
</html>