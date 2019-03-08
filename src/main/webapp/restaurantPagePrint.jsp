<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Restaurant Page Print Version</title>
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

		<script src="js/dropdown.js"></script>
		<script src="js/parseQueryString.js"></script>
		<script src="js/restaurantPage.js"></script>
	</body>
</html>