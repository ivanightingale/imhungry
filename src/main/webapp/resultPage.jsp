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
        <div class="dropDown">
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
			<div class = "sub_header">Recipes</div>
		</div>
	</div>

    <script src="js/dropdown.js"></script>
    <script src="js/parseQueryString.js"></script>
    <script>
        var query = parseQuery(window.location.search);
        //Have to replace '+'s with ' 's before displaying name to user
        document.getElementById("header").innerHTML = 'Results for ' + query.search.replace(/\+/g, ' ');
        var results;
        var imageURLs;
        //To reduce server overhead and improve performance, the page will only search from the server if it was arrived at from the search page
        //or if a list was modified on the last page. Otherwise, it'll load the results from localStorage (much faster).
        if(query.number == "cache") {
            results = JSON.parse(localStorage.getItem("searchResults"));
            imageURLs = JSON.parse(localStorage.getItem("imageURLs"));
        }
        else {
            var xhttp = new XMLHttpRequest();
            xhttp.open("GET", "/Search?search=" + query.search + "&number=" + query.number, false);
            xhttp.send();
            var response = JSON.parse(xhttp.response);
            results = response.body.results;
            imageURLs = response.body.imageURLs;
        }
        //Store results in local storage
        window.localStorage.setItem("search", query.search);
        window.localStorage.setItem("searchResults", JSON.stringify(results));
        window.localStorage.setItem("imageURLs", JSON.stringify(imageURLs));

        //First, populate restaurant results
        var col1 = document.getElementById("column1");
        for(let i = 0; i < results[0].length; i++) {
            //Create each sub section for the entry and populate it with data and attributes
            let sec1 = document.createElement("div");
            sec1.setAttribute("class", "Res_section1");
            sec1.innerHTML = results[0][i].name;

            let sec2 = document.createElement("div");
            sec2.setAttribute("class", "Res_section2");
            for(let j = 0; j < 5; j++) {
                if(j < results[0][i].rating) sec2.innerHTML += '⭐';
                else sec2.innerHTML += '☆';
            }

            let divider = document.createElement("div");
            divider.setAttribute("class", "divider");

            let sec3 = document.createElement("div");
            sec3.setAttribute("class", "Res_section3");
            sec3.innerHTML = results[0][i].driveTimeText + " away";

            let sec4 = document.createElement("div");
            sec4.setAttribute("class", "Res_section4");
            sec4.innerHTML = results[0][i].address;

            let sec5 = document.createElement("div");
            sec5.setAttribute("class", "Res_section5");
            sec5.innerHTML = results[0][i].priceLevel;

            //Create the actual entry element and set the previous subsections to be its children
            let res = document.createElement("div");
            res.setAttribute("class","item");
            res.setAttribute("id","Res_item" + i);
            //Sets the onclick so that you can navigate to the proper detailed page.
            res.setAttribute("onclick","window.location='restaurantPage.jsp?i="+i+"'");
            res.setAttribute("style","cursor:pointer;");
            res.appendChild(sec1);
            res.appendChild(sec2);
            res.appendChild(divider);
            res.appendChild(sec3);
            res.appendChild(sec4);
            res.appendChild(sec5);

            //Add the entry to the proper place on the page
            col1.appendChild(res);
        }

        //Same process as above, but for recipe results
        var col2 = document.getElementById("column2");
        for(let i = 0; i < results[1].length; i++) {
            let sec1 = document.createElement("div");
            sec1.setAttribute("class", "Rec_section1");
            sec1.innerHTML = results[1][i].name;

            let sec2 = document.createElement("div");
            sec2.setAttribute("class", "Rec_section2");
            for(let j = 0; j < 5; j++) {
                if(j < results[1][i].rating) sec2.innerHTML += '⭐';
                else sec2.innerHTML += '☆';
            }

            let divider = document.createElement("div");
            divider.setAttribute("class", "divider");

            let sec3 = document.createElement("div");
            sec3.setAttribute("class", "Rec_section3");
            sec3.innerHTML = results[1][i].prepTime + " min prep time";

            let sec4 = document.createElement("div");
            sec4.setAttribute("class", "Rec_section4");
            sec4.innerHTML = results[1][i].cookTime + " min cook time";

            let res = document.createElement("div");
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

        //Assemble the collage
        var collage = document.getElementById("collage");
        for(let i = 0; i < imageURLs.length; i++) {
            //Create a div to hold this image
            let imgdiv = document.createElement("div");
            imgdiv.setAttribute("class", "imageDiv");
            imgdiv.setAttribute("id", "image"+i);
            //Create the img element
            let img = document.createElement("img");
            img.setAttribute("src", imageURLs[i]);
            img.setAttribute("class", "image");
            //Add the img to the div
            imgdiv.appendChild(img);
            //Generate a set of randomized position, rotation angle, scaling factor, and z index
            let x = 2*(i%5-1)*20+Math.floor(Math.random()*30);
            let y = 2*(i%2)*50+Math.floor(Math.random()*30)-20;
            let rot = Math.floor(Math.random()*90)-45;
            let scale = Math.random()*0.2+0.9;
            let z = Math.floor(Math.random()*50);
            //Apply a style to the element that applies the above transformations to it
            imgdiv.setAttribute("style", "-webkit-transform: translate("+x+"%, "+y+"%) rotate("+rot+"deg) scale("+scale+");" +
                "-ms-transform: translate("+x+"%, "+y+"%) rotate("+rot+"deg) scale("+scale+");" +
                "transform: translate("+x+"%, "+y+"%) rotate("+rot+"deg) scale("+scale+");" +
                "z-index:"+z+";");
            //Add the element to the collage
            collage.appendChild(imgdiv);
        }
    </script>

</body>
</html>