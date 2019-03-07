<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Recipe Page</title>
		<link rel="stylesheet" type="text/css" href="detailedPage.css" />
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
		
		<form action = "searchServlet">
    <div class = "backToResults">
    	<input type="image" id = "backtoresults" name="profile" value= "OK" src= "OK" />
    </div>
    </form>
    
    <form action = "recipePagePrint.jsp">
    <div class = "printableVersion">
    	<input type="image" id = "printableversion" name="profile" value= "OK" src= "OK" />
    </div>
    </form>    
     <form onsubmit = "return detailedPageAddItem()">
    <div class = "addToList">
    	<input type="image" id = "addtolist" name="profile" value= "OK" src= "OK" />
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

	<script>
		var x, i, j, selElmnt, a, b, c;
		x = document.getElementsByClassName("dropDown");
		for (i = 0; i < x.length; i++) {
		  selElmnt = x[i].getElementsByTagName("select")[0];
		  a = document.createElement("DIV");
		  a.setAttribute("class", "select-selected");
		  a.innerHTML = selElmnt.options[selElmnt.selectedIndex].innerHTML;
		  x[i].appendChild(a);
		  b = document.createElement("DIV");
		  b.setAttribute("class", "select-items select-hide");
		  for (j = 1; j < selElmnt.length; j++) {
		    c = document.createElement("DIV");
		    c.innerHTML = selElmnt.options[j].innerHTML;
		    c.addEventListener("click", function(e) {
		        var y, i, k, s, h;
		        s = this.parentNode.parentNode.getElementsByTagName("select")[0];
		        h = this.parentNode.previousSibling;
		        for (i = 0; i < s.length; i++) {
		          if (s.options[i].innerHTML == this.innerHTML) {
		            s.selectedIndex = i;
		            h.innerHTML = this.innerHTML;
		            y = this.parentNode.getElementsByClassName("same-as-selected");
		            for (k = 0; k < y.length; k++) {
		              y[k].removeAttribute("class");
		            }
		            this.setAttribute("class", "same-as-selected");
		            break;
		          }
		        }
		        h.click();
		    });
		    b.appendChild(c);
		  }
		  x[i].appendChild(b);
		  a.addEventListener("click", function(e) {
		      e.stopPropagation();
		      closeAllSelect(this);
		      this.nextSibling.classList.toggle("select-hide");
		      this.classList.toggle("select-arrow-active");
		    });
		}
		function closeAllSelect(elmnt) {
		  var x, y, i, arrNo = [];
		  x = document.getElementsByClassName("select-items");
		  y = document.getElementsByClassName("select-selected");
		  for (i = 0; i < y.length; i++) {
		    if (elmnt == y[i]) {
		      arrNo.push(i)
		    } else {
		      y[i].classList.remove("select-arrow-active");
		    }
		  }
		  for (i = 0; i < x.length; i++) {
		    if (arrNo.indexOf(i)) {
		      x[i].classList.add("select-hide");
		    }
		  }
		}
		document.addEventListener("click", closeAllSelect);
		</script>
		
		<script>
		function detailedPageAddItem()
		{
			var listString = document.getElementById("dropdown").value;
			var result = "0"; //replace "0" with Luke's method of getting the current info object
			addItem(listString, result);
		}
		
		
		function addItem(listString, result)
		{
			//window.alert(listString); //this has verfied that the getting of list-string works
			//luke's part is the rest of this function
		}
		</script>
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
		document.getElementById("backtoresults").src = "backToResults.png";
		document.getElementById("printableversion").src = "printableVersion.png";
		document.getElementById("addtolist").src = "addToList.png";
		document.getElementById("img").innerHTML = "<br><img src=\"" +img+ "\">"; //only needed by the 
		</script>
	</body>
</html>