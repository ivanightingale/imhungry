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
			<p id="title"></p>
			<p id="img"></p> <!-- only will be needed in the recipe page -->
			<div class = "prepT">
				<span id ="prept1"></span>
				<span id ="prept2"></span>
			</div>
			<div class ="cookT">
				<span id="cookt1"></span>
				<span id="cookt2"></span>
			</div>
			<div class ="ingre">
				<span id="ingre1"></span>
				<div id="ingre2"></div>
			</div>
			<div class ="instr">
				<span id="instr1"></span>
				<div id="instr2"></div>
			</div>
		</div>

        <script src="js/dropdown.js"></script>
		<script>
		/*
		//uncomment this part once Luke has figured it out
		var result = localStorage.getItem('restaurantArray')[i];
		//logic is same as res page, except that for ingredients and instructions, they should really be arrays
		//I will not implement the iterative methods here, as I have no way to test agaisnt it
		*/
		//comment out this part once what's above is on duty. this part for testing purposes only
		var title =  "Best spaghetti";
		var pt = "10 min";
		var ct = "10 min";
		var ingre = ["- Spaghetti", "- Volvo", "- Justin Timberlake"]; // should really be an array using += method
		var instr = ["1. Add spaghetti", "2. Add Volvo", "3. Add Justin Timberlake"]; //should really be an array using += method
		var img = "invalid.gif";
		
		document.getElementById("title").innerHTML = title;
		document.getElementById("prept1").innerHTML = "Prep Time: ";
		document.getElementById("prept2").innerHTML = pt;
		document.getElementById("cookt1").innerHTML = "Cook Time: ";
		document.getElementById("cookt2").innerHTML = ct;
		document.getElementById("ingre1").innerHTML = "Ingredients: ";
		for (i = 0; i < ingre.length; i++)
		{
			document.getElementById("ingre2").innerHTML += ingre[i];
			document.getElementById("ingre2").innerHTML += "<br />";
		}
		document.getElementById("instr1").innerHTML = "Instructions: ";
		for (i = 0; i < instr.length; i++)
		{
			document.getElementById("instr2").innerHTML += instr[i];
			document.getElementById("instr2").innerHTML += "<br />";
		}
		document.getElementById("img").innerHTML = "<br><img src=\"" +img+ "\">"; //only needed by the 
		</script>
	</body>
</html>