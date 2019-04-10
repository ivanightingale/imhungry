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

	<input id = "login" type="button" onclick="location.href='loginPage.jsp';" value = "Log In"/>
	<input id = "signup" type="button" onclick="location.href='signupPage.jsp';" value = "Sign Up"/>

	<div id = "header">I'm Hungry </div>
	<div id = "format">
		<form action = "resultPage.jsp" method = "GET" onsubmit="if(localStorage.getItem('loggedIn')===null) {window.location = '/loginPage.jsp'; return false;} else return true;">
			<input type = "text" name = "search" id = "search" placeholder = "Enter Food" required />
			<div id = "hover_format" class = "hover_format">
				<input type = "number" name = "number" id = "number" class = "number" value = "5" min= "1" />
				<div id = "hover_text" class = "hover_text">
					Number of items to show in results
				</div>
			</div>

            <div class = hover_format>
                <input type = "number" name = "radius" class = "number" value = "1" min= "1" />
                <div class = "hover_text">
                    Radius Selected (in miles)
                </div>
            </div>

            <div class = hover_format>
                <input id = "submit" class = "search" type="submit" value="Search">
            </div>

			<br>
			<%--<input type = "image" src="resources/grumpy.png" onmousedown="sadToHappy()" onmouseleave="happyToSad()" name = "submit" id ="submit" value = "Feed Me!" />--%>
			
		</form>
	</div>

</body>
</html>