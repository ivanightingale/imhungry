//All of these methods put together an AJAX call to ListServlet; this is the only place ListServlet is interacted with.

//Adds the item in item to the list in listName
function addItem(listName, item) {
    var request = {header: "addItem", body: JSON.stringify({header: listName, body: JSON.stringify(item)})};
    console.log(request);
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/Lists", false);
    xhttp.send(JSON.stringify(request));
    console.log("RESP: " + xhttp.response);
    var response = JSON.parse(xhttp.response); //Could check and see if request was successful
}

//Removes the item in item from the list in listName
function removeItem(listName, item) {
    var request = {header: "removeItem", body: JSON.stringify({header: listName, body: JSON.stringify(item)})};
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/Lists", false);
    xhttp.send(JSON.stringify(request));
    var response = JSON.parse(xhttp.response); //Could check and see if request was successful
}

//Requests reordering of recipes/restaurants by rating or alphabetically
function reorderResults(order, listName){
    // note: "item" only used for listName (stored in item.imageURL), must be fully included for parsing purposes in ListServlet.java doPost() method
    var item = new Object();
    item.recipeID = 0;
    item.prepTime = 0;
    item.cookTime = 0;
    var ingredients = [];
    ingredients.push("");
    item.ingredients = ingredients;
    var instructions = [];
    instructions.push("");
    item.instructions = instructions;
    item.imageURL = "";
    item.name = order;
    var request = {header: "reorderList", body: JSON.stringify({header: listName, body: JSON.stringify(item)})};
    var xhttp = new XMLHTTPRequest();
    xhttp.open("POST", "/Lists", false);
    xhttp.open(JSON.stringify(request));
    var response = JSON.parse(xhttp.response);
}

//Invalidates server-side session
function resetLists() { //Debugging function
    var request = {header: "resetLists", body: {header: "null", body: "null"}};
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/Lists", false);
    xhttp.send(JSON.stringify(request));
    var response = JSON.parse(xhttp.response); //Could check and see if request was successful
}

//Gets the list who's name is in listName
function getList(listName) {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "/Lists?list="+listName, false);
    xhttp.send();
    var response = JSON.parse(xhttp.response);
    console.log("RESP: " + xhttp.response);
    return response;
}