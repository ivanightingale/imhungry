<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Recipe Page</title>
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="css/detailedPage.css" />
        <link rel="stylesheet" type="text/css" href="css/common.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/bootstrap-responsive.css">
    </head>
	<body>

    <script src="js/loginChecker.js"></script>
    <script>checkLoggedIn();</script>
    <div id="common_header">
        <h4 id = "header_text">I'm Hungry </h4>
    </div>

    <div class = "textinfo">
			<p id="title" style="margin-bottom: 1rem"></p>
			<p id="img"></p>
			<div class = "prepT">
                <span id ="prept1" style="font-size:2rem"><strong>Prep Time:</strong></span>
				<span id ="prept2"></span>
			</div>
			<div class ="cookT">
                <span id="cookt1" style="font-size:2rem"><strong>Cook Time:</strong></span>
				<span id="cookt2"></span>
			</div>
			<div class ="ingre">
				<span id="ingre1" style="font-size:2rem">Ingredients:</span>
				<div id="ingre2"></div>
			</div>

            <br>
            <hr>

			<div class ="instr">
				<span id="instr1" style="font-size:2rem">Instructions:</span>
				<div id="instr2"></div>
			</div>
        </div>

    <div class="container">
        <%--<div class = "row">--%>
            <div class="col">
            <form action = "resultPage.jsp">
                    <div class = "backToResults">
                        <input type="hidden" id="queryStringInput" name="search" value="" />
                        <input type="hidden" id="numberResultsInput" name="number" value="cache" />
                        <button type="submit" id = "backtoresults">Back to Results</button>
                    </div>
            </form>
            </div>

            <div class="col">
                <form action = "recipePagePrint.jsp">
                    <div class = "printableVersion">
                        <input type="hidden" id="indexInput" name="i" value="">
                        <button type="submit" id = "printableversion">Printable Version</button>
                    </div>
                </form>
            </div>

        <!-- Fairly complicated onclick to add the item, because it not only calls the ListClient function, it updates the back to results button to force the results page to search on the Servlet again -->
            <div class="col">
                <form onsubmit = "addItem(document.getElementById('dropdown').value, result); document.getElementById('numberResultsInput').value = JSON.parse(localStorage.getItem('searchResults'))[0].length; return false;">
                <div class = "addToList">
                    <button type="submit" id = "addtolist">Add to List</button>
                </div>
            </form>
            </div>

            <div class="col">
                <div class="dropDown">
                    <select id = "dropdown">
                        <option value="invalid">&nbsp</option>
                        <option value="Favorites">Favorites</option>
                        <option value="To Explore">To Explore</option>
                        <option value="Do Not Show">Do Not Show</option>
                        <option id="Grocery" value="Grocery">Grocery</option>
                    </select>
                </div>
            </div>
    </div>
        </form>

        <script src="js/ListClient.js"></script>
        <script src="js/parseQueryString.js"></script>
		<script src="js/recipePage.js"></script>
	</body>
</html>