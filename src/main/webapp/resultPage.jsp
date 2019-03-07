<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resultPage.css" />
<meta charset="UTF-8">
<title>Result Page</title>
</head>
<body>
<div id = "header">Results for X </div>
<div id = "collage">
</div>

	<div class="dropDown" style="width:200px;">
	  <select id = "dropdown">
	    <option value="invalid">&nbsp</option>
	    <option value="Favorites">Favorites</option>
	    <option value="To Explore">To Explore</option>
	    <option value="Do Not Show">Do Not Show</option>
	  </select>
	</div>
	
	<form action="listPage.jsp">
   	 	<input type="submit" id = "manage_list" value="Manage List" />
	</form>
	
	<form action="searchPage.jsp">
   	 	<input type="submit" id = "back_search" value="Back to Search" />
	</form>
	
<div id = "container">
	<div id = "column1">
		<div class = "sub_header">Restaurants</div>
		<div class = "item" id = "Res_item1">
			<div class ="Res_section1">Section 1</div>
			<div class ="Res_section2">Section 2</div>
			<div class = "divider"></div>
			<div class ="Res_section3">Section 3</div>
			<div class ="Res_section4">Section 4</div>
			<div class ="Res_section5">Section 5</div>
		</div>
		<div class = "item" id = "Res_item2">
			<div class ="Res_section1">Section 1</div>
			<div class ="Res_section2">Section 2</div>
			<div class = "divider"></div>
			<div class ="Res_section3">Section 3</div>
			<div class ="Res_section4">Section 4</div>
			<div class ="Res_section5">Section 5</div>
		
		</div>
			<div class = "item" id = "Res_item3">
			<div class ="Res_section1">Section 1</div>
			<div class ="Res_section2">Section 2</div>
			<div class = "divider"></div>
			<div class ="Res_section3">Section 3</div>
			<div class ="Res_section4">Section 4</div>
			<div class ="Res_section5">Section 5</div>
		</div>
		<div class = "item" id = "Res_item4">
			<div class ="Res_section1">Section 1</div>
			<div class ="Res_section2">Section 2</div>
			<div class = "divider"></div>
			<div class ="Res_section3">Section 3</div>
			<div class ="Res_section4">Section 4</div>
			<div class ="Res_section5">Section 5</div>
		
		</div>
		
		
		
	</div>
	<div id = "column2">
		<div class = "sub_header">Recipe</div>
		<div class = "item" id = "Rec_item1">
			<div class ="Rec_section1">Section 1</div>
			<div class ="Rec_section2">Section 2</div>
			<div class = "divider"></div>
			<div class ="Rec_section3">Section 3</div>
			<div class ="Rec_section4">Section 4</div>
			<div class ="Rec_section5">Section 5</div>	
		</div>
		
		<div class = "item" id = "Rec_item2">
			<div class ="Rec_section1">Section 1</div>
			<div class ="Rec_section2">Section 2</div>
			<div class = "divider"></div>
			<div class ="Rec_section3">Section 3</div>
			<div class ="Rec_section4">Section 4</div>
			<div class ="Rec_section5">Section 5</div>	
		</div>
		<div class = "item" id = "Rec_item3">
			<div class ="Rec_section1">Section 1</div>
			<div class ="Rec_section2">Section 2</div>
			<div class = "divider"></div>
			<div class ="Rec_section3">Section 3</div>
			<div class ="Rec_section4">Section 4</div>
			<div class ="Rec_section5">Section 5</div>	
		</div>
		<div class = "item" id = "Rec_item4">
			<div class ="Rec_section1">Section 1</div>
			<div class ="Rec_section2">Section 2</div>
			<div class = "divider"></div>
			<div class ="Rec_section3">Section 3</div>
			<div class ="Rec_section4">Section 4</div>
			<div class ="Rec_section5">Section 5</div>	
		</div>
	</div>
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
		      document.getElementById("manage_list").style.top = "34%";
		   	 document.getElementById("back_search").style.top = "38%";
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

</body>
</html>