<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>List Management</title>
		<link rel="stylesheet" type="text/css" href="css/listPage.css" />
	</head>
	<body>
		<form action="listPage.jsp" method="GET">
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
	
		<form action="resultPage.jsp">
			<input type="hidden" id="queryStringInput" name="search" value="" />
			<input type="hidden" id="numberResultsInput" name="number" value="cache" />
			<input type="submit" id = "back_result" value="Back to Result" />
		</form>
	
		<form action="searchPage.jsp">
			<input type="submit" id = "back_search" value="Back to Search" />
		</form>
	    
		<div id = "header"></div>
		<div id = "container">
		</div>

		<script src="js/dropdown.js"></script>
		<script src="js/ListClient.js"></script>
		<script src="js/parseQueryString.js"></script>
		<script>
			//Configure the back to results button
            if(document.getElementById("queryStringInput") != null) document.getElementById("queryStringInput").value = localStorage.getItem('search');

            //Identify the list this page is displaying from the query string
			var listName = parseQuery(window.location.search).list;
			listName = listName.replace(/\+/g, ' ');
			document.getElementById("header").innerHTML = listName + " List";
			//Request the list for this page from the servlet
			var list = getList(listName).body;

			//Same process as on the results page to add the items to the page
			var col1 = document.getElementById("container");
			//Check if the list is empty first though
			if(list == null || list.length === 0) col1.innerHTML = "This list is empty. Add something to see it here!" ;
			else {
                for (var i = 0; i < list.length; i++) {
                    let sec1 = null;
                    let sec2 = null;
                    let divider = null;
                    let sec3 = null;
                    let sec4 = null;
                    let sec5 = null;
                    //And also check if this is a restaurant or a recipe, so the correct data is displayed
                    if (list[i].hasOwnProperty("placeID")) {
                        sec1 = document.createElement("div");
                        sec1.setAttribute("class", "item_format1");
                        sec1.innerHTML = list[i].name;

                        sec2 = document.createElement("div");
                        sec2.setAttribute("class", "item_format2");
                        for (let j = 0; j < 5; j++) {
                            if (j < list[i].rating) sec2.innerHTML += '⭐';
                            else sec2.innerHTML += '☆';
                        }

                        divider = document.createElement("div");
                        divider.setAttribute("class", "divider");

                        sec3 = document.createElement("div");
                        sec3.setAttribute("class", "item_format3");
                        sec3.innerHTML = list[i].driveTimeText + " away";

                        sec4 = document.createElement("div");
                        sec4.setAttribute("class", "item_format4");
                        sec4.innerHTML = list[i].address;

                        sec5 = document.createElement("div");
                        sec5.setAttribute("class", "item_format5");
                        sec5.innerHTML = list[i].priceLevel;
                    }
                    else {
                        sec1 = document.createElement("div");
                        sec1.setAttribute("class", "item_format1");
                        sec1.innerHTML = list[i].name;

                        sec2 = document.createElement("div");
                        sec2.setAttribute("class", "item_format2");
                        for (let j = 0; j < 5; j++) {
                            if (j < list[i].rating) sec2.innerHTML += '⭐';
                            else sec2.innerHTML += '☆';
                        }

                        divider = document.createElement("div");
                        divider.setAttribute("class", "divider");

                        sec3 = document.createElement("div");
                        sec3.setAttribute("class", "item_format3");
                        sec3.innerHTML = list[i].prepTime + " min prep time";

                        sec4 = document.createElement("div");
                        sec4.setAttribute("class", "item_format4");
                        sec4.innerHTML = list[i].cookTime + " min cook time";

                        sec5 = document.createElement("div");
                        sec5.setAttribute("class", "item_format5");
                        sec5.innerHTML = "   ";
                    }
                    //Build the change and remove list buttons for this item
                    let changeButton = document.createElement("button");
                    changeButton.setAttribute("id", "changeButton"+i);
                    changeButton.innerHTML = "Change List";
                    //Have to use a closure to get the loop index in there properly
                    (function(ind) {
                        changeButton.onclick= function(event) {
                            //Make sure that clicking this button doesn't also send user to the detailed page for this item
                            event.stopPropagation();
                            event.preventDefault();
                            //Check that a list is specified in the dropdown before doing anything
                            if(document.getElementById("dropdown").value !== "invalid") {
                                //Store this item in localStorage
                                setStoredItem(ind);
                                //Then remove from this list and add to the enw one
                                removeItem(listName, JSON.parse(localStorage.getItem('listItem')));
                                addItem(document.getElementById("dropdown").value, JSON.parse(localStorage.getItem('listItem')));
                                //Remove the item from the page
                                document.getElementById('item' + ind).parentNode.removeChild(document.getElementById('item' + ind));
                                //And change the back to results button so that it forces the results page to make a new search from the server
                                document.getElementById("numberResultsInput").value = JSON.parse(localStorage.getItem("searchResults"))[0].length;
                            }
                        }
                    }(i));
					let removeButton = document.createElement("button");
					removeButton.setAttribute("id", "removeButton"+i);
					removeButton.innerHTML = "Remove from List";
					//Another closure. This gave me a headache when I wrote it.
                    (function(ind) {
                        removeButton.onclick = function(event) {
                            //Make sure that clicking this button doesn't also send user to the detailed page for this item
                            event.stopPropagation();
                            event.preventDefault();
                            //Same as above, but don't check the dropdown because it doesn't matter (we always remove from the list this page is for)
                            setStoredItem(ind);
                            removeItem(listName, JSON.parse(localStorage.getItem('listItem')));
                            document.getElementById('item' + ind).parentNode.removeChild(document.getElementById('item' + ind));
                            document.getElementById("numberResultsInput").value = JSON.parse(localStorage.getItem("searchResults"))[0].length;
                        };
                    })(i);

                    //Assemble the elements onto the page as in the results page
                    let res = document.createElement("div");
                    res.setAttribute("class", "item");
                    res.setAttribute("id", "item" + i);
                    //decided which page to link to
                    if (list[i].hasOwnProperty("placeID"))
                    	res.setAttribute("onclick", "setStoredItem(" + i + ");window.location='restaurantPage.jsp?i=-1';");
                    else
                    	res.setAttribute("onclick", "setStoredItem(" + i + ");window.location='recipePage.jsp?i=-1';");
                    res.setAttribute("style", "cursor:pointer;");
                    res.appendChild(sec1);
                    res.appendChild(sec2);
                    res.appendChild(divider);
                    res.appendChild(sec3);
                    res.appendChild(sec4);
                    if (sec5 != null) res.appendChild(sec5);
                    //Do add an extra div compared to there to get the button positioning working
                    let divider2 = document.createElement("div");
                    divider2.setAttribute("class", "divider");
                    res.appendChild(divider2);
                    res.appendChild(changeButton);
                    res.appendChild(removeButton);

                    col1.appendChild(res);
                }

                //Just adds an item to local storage
                function setStoredItem(i) {
                    localStorage.setItem("listItem", JSON.stringify(list[i]));
                }
            }
		</script>
	</body>
</html>