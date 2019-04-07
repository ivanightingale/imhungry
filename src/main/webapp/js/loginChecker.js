function checkLoggedIn() {
    var userID = localStorage.getItem("userID");
    if(userID === null) userID = -1;
    var request = JSON.stringify({header: "userID", body: userID.toString()});
    console.log(request);
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/Login?signOrLog=verify", false);
    xhttp.send(request);
    console.log("RESP: " + xhttp.response);
    var response = JSON.parse(xhttp.response); //Could check and see if request was successful
    if(response.header != "Verified") window.location = "/loginPage.jsp";
}