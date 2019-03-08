function addItem(listName, item) {
    var request = {header: "addItem", body: JSON.stringify({header: listName, body: JSON.stringify(item)})};
    console.log(request);
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/Lists", false);
    xhttp.send(JSON.stringify(request));
    console.log("RESP: " + xhttp.response);
    var response = JSON.parse(xhttp.response); //Could check and see if request was successful
}

function removeItem(listName, item) {
    var request = {header: "removeItem", body: JSON.stringify({header: listName, body: JSON.stringify(item)})};
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/Lists", false);
    xhttp.send(JSON.stringify(request));
    var response = JSON.parse(xhttp.response); //Could check and see if request was successful
}

function resetLists() { //Debugging function
    var request = {header: "resetLists", body: {header: "null", body: "null"}};
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/Lists", false);
    xhttp.send(JSON.stringify(request));
    var response = JSON.parse(xhttp.response); //Could check and see if request was successful
}

function getList(listName) {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "/Lists?list="+listName, false);
    xhttp.send();
    var response = JSON.parse(xhttp.response);
    console.log("RESP: " + xhttp.response);
    return response;
}