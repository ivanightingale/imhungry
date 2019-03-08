<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Restaurant Page</title>
		<link rel="stylesheet" type="text/css" href="css/detailedPage.css" />
	</head>
	<body>
		<div class = "textinfo">
			<p id="title"></p>
			<div class = "address">
				<span id="address1" style="font-size: 2rem">Address:</span>
				<a href = "" id ="address2"></a>
			</div>
			<div class ="tel">
				<span id="tel1" style="font-size: 2rem">Phone number:</span>
				<span id="tel2"></span>
			</div>
			<div class ="website">
				<span id="website1" style="font-size: 2rem">Website:</span>
				<a href = "" id ="website2"></a>
			</div>
		</div>

        <form action = "resultPage.jsp">
            <div class = "backToResults">
                <input type="hidden" id="queryStringInput" name="search" value="" />
                <input type="hidden" id="numberResultsInput" name="number" value="cache" />
                <button type="submit" id = "backtoresults">Back to Results</button>
            </div>
        </form>

        <form action = "restaurantPagePrint.jsp">
            <div class = "printableVersion">
                <input type="hidden" id="indexInput" name="i" value="">
                <button type="submit" id = "printableversion">Printable Version</button>
            </div>
        </form>

        <!-- Fairly complicated onclick to add the item, because it not only calls the ListClient function, it updates the back to results button to force the results page to search on the Servlet again -->
        <form onsubmit = "addItem(document.getElementById('dropdown').value, result); document.getElementById('numberResultsInput').value = JSON.parse(localStorage.getItem('searchResults'))[0].length; return false;">
            <div class = "addToList">
                <button type="submit" id = "addtolist">Add to List</button>
            </div>
        </form>

        <div class="dropDown">
            <select id = "dropdown">
                <option value="invalid">&nbsp</option>
                <option value="Favorites">Favorites</option>
                <option value="To Explore">To Explore</option>
                <option value="Do Not Show">Do Not Show</option>
            </select>
        </div>

        <script src="js/dropdown.js"></script>
        <script src="js/ListClient.js"></script>
        <script src="js/parseQueryString.js"></script>
		<script src="js/restaurantPage.js"></script>
	</body>
</html>