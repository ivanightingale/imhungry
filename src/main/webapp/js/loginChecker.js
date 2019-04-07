function checkLoggedIn() {
    var userID = localStorage.getItem("userID");
    var request = JSON.stringify({header: "userID", body: userID.toString()});
    console.log(request);
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/Login", false);
    xhttp.send(request);
    console.log("RESP: " + xhttp.response);
    var response = JSON.parse(xhttp.response); //Could check and see if request was successful
    if(response.header != "Verified") window.location = "/loginPage.jsp";
}