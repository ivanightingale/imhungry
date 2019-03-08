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
	<div id = "header">Results</div>
	<div id = "collage"></div>
	
	<form action="listPage.jsp">
        <div class="dropDown" style="width:200px;">
            <select id = "dropdown" name="list">
                <option value="invalid">&nbsp</option>
                <option value="Favorites">Favorites</option>
                <option value="To Explore">To Explore</option>
                <option value="Do Not Show">Do Not Show</option>
            </select>
        </div>
   	 	<input type="submit" id = "manage_list" value="Manage List" />
	</form>
	
	<form action="searchPage.jsp">
   	 	<input type="submit" id = "back_search" value="Back to Search" />
	</form>
	
	<div id = "container">
		<div id = "column1">
			<div class = "sub_header">Restaurants</div>
		</div>
		<div id = "column2">
			<div class = "sub_header">Recipe</div>
		</div>
	</div>

    <script src="js/dropdown.js"></script>
    <script src="js/parseQueryString.js"></script>
    <script>
        var query = parseQuery(window.location.search);
        document.getElementById("header").innerHTML = 'Results for "' + query.search + '"';
        var xhttp = new XMLHttpRequest();
        xhttp.open("GET", "/Search?search="+query.search+"&number="+query.number, false);
        xhttp.send();
        console.log(xhttp.response);
        /* ****** TODO: Uncomment when SearchServlet is ready
        var response = JSON.parse(xhttp.response);
        var results = response.body;
        */
        // ******* TODO: Testing only
        results = [[{
            name: "Test Restaurant - Starbucks",
            rating: 3,
            placeID: "2PC9+M4 Los Angeles, California",
            address: "642 W 34th St, Los Angeles, CA 90007",
            priceLevel: "$",
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
        // ******* TODO: End test section
        //Store results in local storage
        window.localStorage.setItem("search", query.search);
        window.localStorage.setItem("searchResults", JSON.stringify(results));

        var col1 = document.getElementById("column1");
        for(var i = 0; i < results[0].length; i++) {
            var sec1 = document.createElement("div");
            sec1.setAttribute("class", "Res_section1");
            sec1.innerHTML = results[0][i].name;

            var sec2 = document.createElement("div");
            sec2.setAttribute("class", "Res_section2");
            for(var j = 0; j < 5; j++) {
                if(j < results[0][i].rating) sec2.innerHTML += '⭐';
                else sec2.innerHTML += '☆';
            }

            var divider = document.createElement("div");
            divider.setAttribute("class", "divider");

            var sec3 = document.createElement("div");
            sec3.setAttribute("class", "Res_section3");
            sec3.innerHTML = results[0][i].driveTimeText;

            var sec4 = document.createElement("div");
            sec4.setAttribute("class", "Res_section4");
            sec4.innerHTML = results[0][i].address;

            var sec5 = document.createElement("div");
            sec5.setAttribute("class", "Res_section5");
            sec5.innerHTML = results[0][i].priceLevel;

            var res = document.createElement("div");
            res.setAttribute("class","item");
            res.setAttribute("id","Res_item" + i);
            res.setAttribute("onclick","window.location='restaurantPage.jsp?i="+i+"'");
            res.setAttribute("style","cursor:pointer;");
            res.appendChild(sec1);
            res.appendChild(sec2);
            res.appendChild(divider);
            res.appendChild(sec3);
            res.appendChild(sec4);
            res.appendChild(sec5);

            col1.appendChild(res);
        }

        var col2 = document.getElementById("column2");
        for(var i = 0; i < results[0].length; i++) {
            var sec1 = document.createElement("div");
            sec1.setAttribute("class", "Rec_section1");
            sec1.innerHTML = results[1][i].name;

            var sec2 = document.createElement("div");
            sec2.setAttribute("class", "Rec_section2");
            for(var j = 0; j < 5; j++) {
                if(j < results[1][i].rating) sec2.innerHTML += '⭐';
                else sec2.innerHTML += '☆';
            }

            var divider = document.createElement("div");
            divider.setAttribute("class", "divider");

            var sec3 = document.createElement("div");
            sec3.setAttribute("class", "Rec_section3");
            sec3.innerHTML = results[1][i].prepTime;

            var sec4 = document.createElement("div");
            sec4.setAttribute("class", "Rec_section4");
            sec4.innerHTML = results[1][i].cookTime;

            var res = document.createElement("div");
            res.setAttribute("class","item");
            res.setAttribute("id","Rec_item" + i);
            res.setAttribute("onclick","window.location='recipePage.jsp?i="+i+"'");
            res.setAttribute("style","cursor:pointer;");
            res.appendChild(sec1);
            res.appendChild(sec2);
            res.appendChild(divider);
            res.appendChild(sec3);
            res.appendChild(sec4);

            col2.appendChild(res);
        }
    </script>

</body>
</html>