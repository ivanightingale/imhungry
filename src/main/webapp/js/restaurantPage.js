if(document.getElementById("queryStringInput") != null) document.getElementById("queryStringInput").value = localStorage.getItem('search');

var query = parseQuery(window.location.search);
if(document.getElementById("indexInput") != null) document.getElementById("indexInput").value = query.i;
var result = null;
if(query.i >= 0) {
    result = JSON.parse(localStorage.getItem('searchResults'))[0][query.i];
}
else {
    result = JSON.parse(localStorage.getItem('listItem'));
}
var title = result.name;
var address = result.address;
var tel = result.phone;
var website = result.url; //assume this doesn't have "http://" included
var websiteHTTP = "http://"+website;

var addressHTTP = "https://www.google.com/maps/dir/Tommy+Trojan,+801-899+Childs+Way,+Los+Angeles,+CA+90089/" + encodeURIComponent(address);

document.title = "Restaurant: " + title;
document.getElementById("title").innerHTML = title;
document.getElementById("address2").innerHTML = address;
document.getElementById("address2").href = addressHTTP;
document.getElementById("tel2").innerHTML = tel;
document.getElementById("website2").href = websiteHTTP;
document.getElementById("website2").innerHTML = website;