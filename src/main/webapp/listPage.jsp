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
	
		<form action="resultPage.jsp">
			<input type="hidden" id="queryStringInput" name="search" value="" />
			<input type="hidden" id="numberResultsInput" name="number" value="" />
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
            if(document.getElementById("queryStringInput") != null) document.getElementById("queryStringInput").value = localStorage.getItem('search');
            if(document.getElementById("numberResultsInput") != null) document.getElementById("numberResultsInput").value = JSON.parse(localStorage.getItem('searchResults'))[0].length;

			var listName = parseQuery(window.location.search).list;
			document.getElementById("header").innerHTML = listName + " List";
			var list = getList(listName).body;

			var col1 = document.getElementById("container");
			if(list == null || list.length === 0) col1.innerHTML = "This list is empty. Add something to see it here!" ;
			else {
                for (var i = 0; i < list.length; i++) {
                    var sec1 = null;
                    var sec2 = null;
                    var divider = null;
                    var sec3 = null;
                    var sec4 = null;
                    var sec5 = null;
                    if (list[i].hasOwnProperty("placeID")) {
                        sec1 = document.createElement("div");
                        sec1.setAttribute("class", "item_format1");
                        sec1.innerHTML = list[i].name;

                        sec2 = document.createElement("div");
                        sec2.setAttribute("class", "item_format2");
                        for (var j = 0; j < 5; j++) {
                            if (j < list[i].rating) sec2.innerHTML += '⭐';
                            else sec2.innerHTML += '☆';
                        }

                        divider = document.createElement("div");
                        divider.setAttribute("class", "divider");

                        sec3 = document.createElement("div");
                        sec3.setAttribute("class", "item_format3");
                        sec3.innerHTML = list[i].driveTimeText;

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
                        for (var j = 0; j < 5; j++) {
                            if (j < list[i].rating) sec2.innerHTML += '⭐';
                            else sec2.innerHTML += '☆';
                        }

                        divider = document.createElement("div");
                        divider.setAttribute("class", "divider");

                        sec3 = document.createElement("div");
                        sec3.setAttribute("class", "item_format3");
                        sec3.innerHTML = list[i].prepTime;

                        sec4 = document.createElement("div");
                        sec4.setAttribute("class", "item_format4");
                        sec4.innerHTML = list[i].cookTime;
                    }
                    var changeButton = document.createElement("button");
                    changeButton.setAttribute("id", "changeButton"+i);
                    changeButton.innerHTML = "Change List";
                    (function(ind) {
                        changeButton.onclick= function(event) {
                            event.stopPropagation();
                            event.preventDefault();
                            setStoredItem(ind);
                            removeItem(listName,JSON.parse(localStorage.getItem('listItem')));
                            addItem(document.getElementById("dropdown").value, JSON.parse(localStorage.getItem('listItem')));
                            document.getElementById('item' + ind).parentNode.removeChild(document.getElementById('item' + ind));
                        }
                    }(i));
					var removeButton = document.createElement("button");
					removeButton.setAttribute("id", "removeButton"+i);
					removeButton.innerHTML = "Remove from List";
                    (function(ind) {
                        removeButton.onclick = function(event) {
                            event.stopPropagation();
                            event.preventDefault();
                            setStoredItem(ind);
                            removeItem(listName, JSON.parse(localStorage.getItem('listItem')));
                            document.getElementById('item' + ind).parentNode.removeChild(document.getElementById('item' + ind));
                        };
                    })(i);

                    var res = document.createElement("div");
                    res.setAttribute("class", "item");
                    res.setAttribute("id", "item" + i);
                    res.setAttribute("onclick", "setStoredItem(" + i + ");window.location='restaurantPage.jsp?i=-1';");
                    res.setAttribute("style", "cursor:pointer;");
                    res.appendChild(sec1);
                    res.appendChild(sec2);
                    res.appendChild(divider);
                    res.appendChild(sec3);
                    res.appendChild(sec4);
                    if (sec5 != null) res.appendChild(sec5);
                    res.appendChild(changeButton);
                    res.appendChild(removeButton);

                    col1.appendChild(res);
                }

                function setStoredItem(i) {
                    localStorage.setItem("listItem", JSON.stringify(list[i]));
                }
            }
		</script>
	</body>
</html>