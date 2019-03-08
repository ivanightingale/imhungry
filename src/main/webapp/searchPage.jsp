<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/searchPage.css" />
<link href="https://afeld.github.io/emoji-css/emoji.css" rel="stylesheet">
<meta charset="UTF-8">
<title>Search Page</title>
</head>
<body>
	<div id = "header">I'm Hungry </div>
	<div id = "format">
		<form action = "resultPage.jsp" method = "GET">
			<input type = "text" name = "search" id = "search" placeholder = "Enter Food" required />
			<div id = hover_format>
				<input type = "number" name = "number" id = "number" value = "5" min= "1" />
				<div id = "hover_text">
					Number of items to show in results
				</div>
			</div>
			
			<br>
			<input type = "image" src="resources/grumpy.png" onmousedown="sadToHappy()" onmouseleave="happyToSad()" name = "submit" id ="submit" value = "Feed Me!" />
			
		</form>
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