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
        var results;
        var imageURLs;
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

        var col1 = document.getElementById("column1");
        for(let i = 0; i < results[0].length; i++) {
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
            sec3.innerHTML = results[0][i].driveTimeText;

            let sec4 = document.createElement("div");
            sec4.setAttribute("class", "Res_section4");
            sec4.innerHTML = results[0][i].address;

            let sec5 = document.createElement("div");
            sec5.setAttribute("class", "Res_section5");
            sec5.innerHTML = results[0][i].priceLevel;

            let res = document.createElement("div");
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
            sec3.innerHTML = results[1][i].prepTime + " min";

            let sec4 = document.createElement("div");
            sec4.setAttribute("class", "Rec_section4");
            sec4.innerHTML = results[1][i].cookTime + " min";

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

        var collage = document.getElementById("collage");
        for(let i = 0; i < imageURLs.length; i++) {
            let imgdiv = document.createElement("div");
            imgdiv.setAttribute("class", "imageDiv");
            imgdiv.setAttribute("id", "image"+i);
            let img = document.createElement("img");
            img.setAttribute("src", imageURLs[i]);
            img.setAttribute("class", "image");
            imgdiv.appendChild(img);
            let x = 2*(i%5-1)*20+Math.floor(Math.random()*20);
            let y = 2*(i%2)*50+Math.floor(Math.random()*20)-20;
            /*let x = Math.floor(Math.random()*200)-50;
            let y = Math.floor(Math.random()*200);*/
            let rot = Math.floor(Math.random()*180)-90;
            imgdiv.setAttribute("style", "-webkit-transform: translate("+x+"%, "+y+"%) rotate("+rot+"deg);" +
                "-ms-transform: translate("+x+"%, "+y+"%) rotate("+rot+"deg);" +
                "transform: translate("+x+"%, "+y+"%) rotate("+rot+"deg);");
            collage.appendChild(imgdiv);
        }
    </script>

</body>
</html>