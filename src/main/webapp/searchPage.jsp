<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/searchPage.css" />
<link href="https://afeld.github.io/emoji-css/emoji.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet">
<meta charset="UTF-8">
<title>Search Page</title>
</head>
<body>

	<input id = "login" type="button" onclick="location.href='loginPage.jsp';" value = "Log In"/>
	<input id = "signup" type="button" onclick="location.href='signupPage.jsp';" value = "Sign Up"/>

	<div id = "header">I'm Hungry </div>
	<div id = "format">
		<form action = "resultPage.jsp" method = "GET" onsubmit="if(localStorage.getItem('loggedIn')===null) {window.location = '/loginPage.jsp'; return false;} else return true;">
			<select id= "prev_search">
				<option value="prev_search1">Previous Searches</option>

			</select>

			<input type = "text" name = "search" id = "search" placeholder = "Enter Food" required />
			<div id = "hover_format" class = "hover_format">
				<input type = "number" name = "number" id = "number" class = "number" value = "5" min= "1" />
				<div id = "hover_text" class = "hover_text">
					Number of items to show in results
				</div>
			</div>

            <div class = hover_format>
                <input type = "number" id="radius" name = "radius" class = "number" value = "1" min= "1" />
                <div class = "hover_text">
                    Radius Selected (in miles)
                </div>
            </div>

            <div class = hover_format>
                <input id = "submit" class = "search" type="submit" value="Search">
            </div>

			<br>

			<%--<input type = "image" src="resources/grumpy.png" onmousedown="sadToHappy()" onmouseleave="happyToSad()" name = "submit" id ="submit" value = "Feed Me!" />--%>
			<script>
                var xhttp = new XMLHttpRequest();
                xhttp.open("GET", "/PrevSearch", false);
                xhttp.send();
                console.log(xhttp.response);
                var response = JSON.parse(xhttp.response);
                var prevSearches = response.body;
                var prevDrop = document.getElementById("prev_search");
                for(i=0; i<prevSearches.length; i++) {
                    var search = document.createElement("option");
                    search.setAttribute("value", JSON.stringify(prevSearches[i]));
                    search.setAttribute("id", "prev_search"+i);
                    search.innerHTML = prevSearches[i].searchTerm;
                    prevDrop.appendChild(search);
                }
                prevDrop.setAttribute("onchange", "populateSearch()");

                function populateSearch() {
                    search = document.getElementById("prev_search").options[document.getElementById("prev_search").selectedIndex].value;
                    if(search == "prev_search1") return;
                    var searchObj = JSON.parse(search);
                    document.getElementById("search").value = searchObj.searchTerm;
                    document.getElementById("number").value = searchObj.expectedResults-0;
                    document.getElementById("radius").value = searchObj.specifiedRadius-0;
				}
			</script>
		</form>
	</div>

</body>
</html>