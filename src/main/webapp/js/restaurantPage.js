//Configure back to results button based on previously stored query value
if(document.getElementById("queryStringInput") != null) document.getElementById("queryStringInput").value = localStorage.getItem('search');

var query = parseQuery(window.location.search);
//Configure printable button to show the correct printable page (the one corresponding to this page)
if(document.getElementById("indexInput") != null) document.getElementById("indexInput").value = query.i;
var result = null;
//If the user arrived here from search page, load from the results array, otherwise, load from the specially saved item from the lists page
if(query.i >= 0) {
    result = JSON.parse(localStorage.getItem('searchResults'))[0][query.i];
}
else {
    result = JSON.parse(localStorage.getItem('listItem'));
}
//Parse out info for this item
var title = result.name;
var address = result.address;
var tel = result.phone;
var website = result.url; //assume this doesn't have "http://" included
var websiteHTTP = "http://"+website;

//Construct url for Google Maps navigation
var addressHTTP = "https://www.google.com/maps/dir/Tommy+Trojan,+801-899+Childs+Way,+Los+Angeles,+CA+90089/" + encodeURIComponent(address);

//Fill info into corresponding HTML tags on the JSP file
document.title = "Restaurant: " + title;
document.getElementById("title").innerHTML = title;
document.getElementById("address2").innerHTML = address;
document.getElementById("address2").href = addressHTTP;
document.getElementById("tel2").innerHTML = tel;
document.getElementById("website2").href = websiteHTTP;
document.getElementById("website2").innerHTML = website;