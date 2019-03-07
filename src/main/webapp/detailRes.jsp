<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>detailres</title>
		<link rel="stylesheet" type="text/css" href="detail.css" />
	</head>
	<body>
		<div class = "textinfo">
			<p id="title"></p>
			<div class = "address">
				<span id="address1"></span>
				<a href = "" id ="address2"></a>
			</div>
			<div class ="tel">
				<span id="tel1"></span>
				<span id="tel2"></span>
			</div>
			<div class ="website">
				<span id="website1"></span>
				<a href = "" id ="website2"></a>
			</div>
		</div>
		
		<p id="img"></p> <!-- only will be needed in the recipe page -->
		
		<form action = "searchServlet">
    <div class = "backToResults">
    	<input type="image" id = "backtoresults" name="profile" value= "OK" src= "OK" />
    </div>
    </form>
    
    <form action = "detailResPrint.jsp">
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