<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>List Page</title>
		<link rel="stylesheet" type="text/css" href="listPage.css" />
	</head>
	<body>
	
	<form action="listPage.jsp">
   	 	<input type="submit" id = "manage_list" value="Manage List" />
	</form>
	
	<form action="resultPage.jsp">
   	 	<input type="submit" id = "back_result" value="Back to Result" />
	</form>
	
	<form action="searchPage.jsp">
   	 	<input type="submit" id = "back_search" value="Back to Search" />
	</form>
	    
	<div id = "header">X List </div>
	<div id = "container">
		<div class = "item" id = "item1">
			<div class = "item_format1" >Name</div>
			<div class = "item_format2" >Stars</div>
			<div class = "divider"></div>
			<div class = "item_format3" >Mins</div>
			<div class = "item_format4" >Address</div>
			<div class = "item_format5" >Price</div>
		</div>
		<div class = "item" id = "item2">
			<div class = "item_format1">Name</div>
			<div class = "item_format2">Stars</div>
			<div class = "divider"></div>
			<div class = "item_format3" >Mins</div>
			<div class = "item_format4" >Address</div>
			<div class = "item_format5">Price</div>
		</div>
		
	</div>
	
	
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

		
		function deleteItem(listString, result)
		{
			
		}
		</script>
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
		var websiteHTTP = "http://"+website;
		var temp = address.replace(" ","+");
		var addressHTTP = "https://www.google.com/maps/dir/Tommy+Trojan,+801-899+Childs+Way,+Los+Angeles,+CA+90089/" + temp;
		
		
		document.getElementById("title").innerHTML = title;
		document.getElementById("address1").innerHTML = "Address: ";
		document.getElementById("address2").innerHTML = address;
		document.getElementById("address2").href = addressHTTP;
		document.getElementById("tel1").innerHTML = "Tel. "
		document.getElementById("tel2").innerHTML = tel;
		document.getElementById("website1").innerHTML = "Website: "
		document.getElementById("website2").href = websiteHTTP;
		document.getElementById("website2").innerHTML = website;
		document.getElementById("backtoresults").src = "backToResults.png";
		document.getElementById("printableversion").src = "printableVersion.png";
		document.getElementById("addtolist").src = "addToList.png";
		//document.getElementById("img").innerHTML = "<br><img src=\"" +img+ "\">"; //only needed by the 
		</script>
	</body>
</html>