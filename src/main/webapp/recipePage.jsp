<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Recipe Page</title>
		<link rel="stylesheet" type="text/css" href="css/detailedPage.css" />
	</head>
	<body>
		<div class = "textinfo">
			<p id="title" style="margin-bottom: 1rem"></p>
			<p id="img"></p>
			<div class = "prepT">
				<span id ="prept1" style="font-size:2rem">Prep Time:</span>
				<span id ="prept2"></span>
			</div>
			<div class ="cookT">
				<span id="cookt1" style="font-size:2rem">Cook Time:</span>
				<span id="cookt2"></span>
			</div>
			<div class ="ingre">
				<span id="ingre1" style="font-size:2rem">Ingredients:</span>
				<div id="ingre2"></div>
			</div>
			<div class ="instr">
				<span id="instr1" style="font-size:2rem">Instructions:</span>
				<div id="instr2"></div>
			</div>
        </div>

        <form action = "resultPage.jsp">
            <div class = "backToResults">
                <input type="hidden" id="queryStringInput" name="search" value="" />
                <input type="hidden" id="numberResultsInput" name="number" value="cache" />
                <button type="submit" id = "backtoresults">Back to Results</button>
            </div>
        </form>

        <form action = "recipePagePrint.jsp">
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
		<script src="js/recipePage.js"></script>
	</body>
</html>