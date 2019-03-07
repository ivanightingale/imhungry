<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Restaurant Page Print Version</title>
		<link rel="stylesheet" type="text/css" href="detailedPage.css" />
	</head>
	<body>
		<div class = "textinfo">
			<p id="title"></p>
			<div class = "address">
				<span id="address1"></span>
				<span id ="address2"></span>
			</div>
			<div class ="tel">
				<span id="tel1"></span>
				<span id="tel2"></span>
			</div>
			<div class ="website">
				<span id="website1"></span>
				<span id ="website2"></span>
			</div>
		</div>

		<script src="dropdown.js"></script>
		<script>
		/*
		//uncomment this part once Luke has figured it out
		var result = localStorage.getItem('restaurantArray')[i];
		var title = result.title;
		var address = result.address;
		var tel = result.tel;
		var website = result.website; //assume this doesn't have "http://" included
		var websiteHTTP = "http://"+website;
		var img = result.img; //only will be needed in the recipe page
		*/
		
		//comment out this part once what's above is on duty. this part for testing purposes only
		var title =  "USC Italian Restaurant";
		var address = "1616 McClintock Ave.";
		var tel = "(213)110-0110";
		var website = "www.github.com"
		var img = "";
		var temp = address.replace(" ","+");
		
		
		document.getElementById("title").innerHTML = title;
		document.getElementById("address1").innerHTML = "Address: ";
		document.getElementById("address2").innerHTML = address;
		document.getElementById("tel1").innerHTML = "Tel. "
		document.getElementById("tel2").innerHTML = tel;
		document.getElementById("website1").innerHTML = "Website: "
		document.getElementById("website2").innerHTML = website;
		//document.getElementById("img").innerHTML = "<br><img src=\"" +img+ "\">"; //only needed by the 
		</script>
	</body>
</html>