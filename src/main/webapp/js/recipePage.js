//Configure back to results button based on previously stored query value
if(document.getElementById("queryStringInput") != null) document.getElementById("queryStringInput").value = localStorage.getItem('search');

var query = parseQuery(window.location.search);
//Configure printable button to show the correct printable page (the one corresponding to this page)
if(document.getElementById("indexInput") != null) document.getElementById("indexInput").value = query.i;
var result = null;
//If the user arrived here from search page, load from the results array, otherwise, load from the specially saved item from the lists page
if(query.i >= 0) {
    result = JSON.parse(localStorage.getItem('searchResults'))[1][query.i];
}
else {
    result = JSON.parse(localStorage.getItem('listItem'));
}
//Parse out info for this item
var title =  result.name;
var pt = result.prepTime;
var ct = result.cookTime;
var ingre = result.ingredients;
var instr = result.instructions;
var img = result.imageURL;

//Fill info into corresponding HTML tags on the JSP file
document.title = "Recipe: " + title;
document.getElementById("title").innerHTML = title;
document.getElementById("prept2").innerHTML = pt + " minutes";
document.getElementById("cookt2").innerHTML = ct + " minutes";
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