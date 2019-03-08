<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Recipe Page Print Version</title>
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

        <script src="js/dropdown.js"></script>
        <script src="js/parseQueryString.js"></script>
        <script src="js/recipePage.js"></script>
	</body>
</html>