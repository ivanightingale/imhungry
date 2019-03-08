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
				<span id="address1">Address:</span>
				<a href = "" id ="address2"></a>
			</div>
			<div class ="tel">
				<span id="tel1">Phone number:</span>
				<span id="tel2"></span>
			</div>
			<div class ="website">
				<span id="website1">Website:</span>
				<a href = "" id ="website2"></a>
			</div>
		</div>

        <form action = "resultPage.jsp">
            <div class = "backToResults">
                <input type="hidden" id="queryStringInput" name="search" value="" />
                <input type="hidden" id="numberResultsInput" name="number" value="" />
                <input type="image" id = "backtoresults" name="profile" src= "resources/backToResults.png" />
            </div>
        </form>

        <form action = "restaurantPagePrint.jsp">
            <div class = "printableVersion">
                <input type="hidden" id="indexInput" name="i" value="">
                <input type="image" id = "printableversion" name="profile" src= "resources/printableVersion.png" />
            </div>
        </form>

        <form onsubmit = "addItem(document.getElementById('dropdown').value, result); return false;">
            <div class = "addToList">
                <input type="image" id = "addtolist" name="profile" src= "resources/addToList.png" />
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
		<script src="js/restaurantPage.js"></script>
	</body>
</html>