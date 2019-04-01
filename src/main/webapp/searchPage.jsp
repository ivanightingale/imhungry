<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<div id = "header">I'm Hungry </div>
	<div id = "format">
		<form action = "resultPage.jsp" method = "GET" onsubmit="if(localStorage.getItem('loggedIn')===null) {window.location = '/loginPage.jsp'; return false;} else return true;">
			<input type = "text" name = "search" id = "search" placeholder = "Enter Food" required />
			<div id = hover_format>
				<input type = "number" name = "number" id = "number" value = "5" min= "1" />
				<div id = "hover_text">
					Number of items to show in results
				</div>
			</div>

			<select id = "radius_dropdown" name="radius">
				<option value="1">1 mi</option>
				<option value="2">2 mi</option>
				<option value="3">3 mi</option>
				<option value="4">4 mi</option>
				<option value="5">5 mi</option>
			</select>
			<%--<input id = "submit" type="submit" value="Search">--%>
			<br>
			<input type = "image" src="resources/grumpy.png" onmousedown="sadToHappy()" onmouseleave="happyToSad()" name = "submit" id ="submit" value = "Feed Me!" />
			
		</form>
		<button id = "login" onclick="window.location = '/loginPage.jsp';">Log In</button>
		<button id = "signup" onclick="window.location = '/signupPage.jsp';">Sign Up</button>
	</div>
<script>
    //Functions to switch emoji states
    function happyToSad()
    {
        document.getElementById("submit").src = "resources/grumpy.png";
    }
    function sadToHappy()
    {
        document.getElementById("submit").src = "resources/smile.png";
    }
</script>

</body>
</html>