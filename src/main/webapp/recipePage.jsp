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
			<p id="title"></p>
			<p id="img"></p>
			<div class = "prepT">
				<span id ="prept1">Prep Time:</span>
				<span id ="prept2"></span>
			</div>
			<div class ="cookT">
				<span id="cookt1">Cook Time:</span>
				<span id="cookt2"></span>
			</div>
			<div class ="ingre">
				<span id="ingre1">Ingredients:</span>
				<div id="ingre2"></div>
			</div>
			<div class ="instr">
				<span id="instr1">Instructions:</span>
				<div id="instr2"></div>
			</div>
        </div>

        <form action = "resultPage.jsp">
            <div class = "backToResults">
                <input type="hidden" id="queryStringInput" name="search" value="" />
                <input type="hidden" id="numberResultsInput" name="number" value="" />
                <input type="image" id = "backtoresults" name="profile" value= "OK" src= "resources/backToResults.png" />
            </div>
        </form>
    
		<form action = "recipePagePrint.jsp">
            <div class = "printableVersion">
                <input type="hidden" id="indexInput" name="i" value="">
                <input type="image" id = "printableversion" name="profile" value= "OK" src= "resources/printableVersion.png" />
            </div>
		</form>

        <form onsubmit = "return recipeAddItem()">
            <div class = "addToList">
                <input type="image" id = "addtolist" name="profile" value= "OK" src= "resources/addToList.png" />
            </div>
        </form>

    
        <div class="dropDown" style="width:200px;">
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