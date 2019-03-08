if(document.getElementById("queryStringInput") != null) document.getElementById("queryStringInput").value = localStorage.getItem('search');
if(document.getElementById("numberResultsInput") != null) document.getElementById("numberResultsInput").value = JSON.parse(localStorage.getItem('searchResults'))[0].length;

var query = parseQuery(window.location.search);
if(document.getElementById("indexInput") != null) document.getElementById("indexInput").value = query.i;
var result = null;
if(query.i >= 0) {
    result = JSON.parse(localStorage.getItem('searchResults'))[1][query.i];
}
else {
    result = JSON.parse(localStorage.getItem('listItem'));
}
var title =  result.name;
var pt = result.prepTime;
var ct = result.cookTime;
var ingre = result.ingredients;
var instr = result.instructions;
var img = result.imageURL;

document.title = "Recipe: " + title;
document.getElementById("title").innerHTML = title;
document.getElementById("prept2").innerHTML = pt;
document.getElementById("cookt2").innerHTML = ct;
for (i = 0; i < ingre.length; i++)
{
    document.getElementById("ingre2").innerHTML += ingre[i];
    document.getElementById("ingre2").innerHTML += "<br />";
}
for (i = 0; i < instr.length; i++)
{
    document.getElementById("instr2").innerHTML += instr[i];
    document.getElementById("instr2").innerHTML += "<br />";
}
document.getElementById("img").innerHTML = "<br /><img style='max-width:50%; max-height:50%;' src=\"" +img+ "\">";