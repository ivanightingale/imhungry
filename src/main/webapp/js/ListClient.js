function addItem(listName, item) {
    var request = {header: "addItem", body: {header: listName, body: item}};
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/Lists", false);
    xhttp.send(JSON.stringify(request));
    var response = JSON.parse(xhttp.response); //Could check and see if request was successful
}

function removeItem(listName, item) {
    var request = {header: "removeItem", body: {header: listName, body: item}};
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
    return response.body;
}