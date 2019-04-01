<%--
  Created by IntelliJ IDEA.
  User: Sophia Hu
  Date: 3/31/2019
  Time: 3:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LogIn Page</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="stylesheet" type="text/css" href="login.css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<body>

<div class="wrapper fadeInDown">
    <div id="formContent">

        <h4> Log In </h4>
        <!-- Login Form -->
        <form onsubmit="return sendData();">
            <input type="text" id="username" name="username" placeholder="Username">
            <input type="password" id="password" name="password" placeholder="Password">
            <input type="submit" id="submit" value="Log In">
        </form>

    </div>
</div>

<script>
    function sendData() {
        var passhash = crypto.subtle.digest("SHA-512", new TextEncoder().encode(document.getElementById("password").value));
        passhash.then( hashedPass => {
            passhash = hexString(hashedPass);

            var xhttp = new XMLHttpRequest();
            xhttp.open("POST", "/Login?signOrLog=login", false);
            console.log(JSON.stringify({header:document.getElementById("username").value,body:passhash}));
            xhttp.send(JSON.stringify({header:document.getElementById("username").value,body:passhash}));
            var response = JSON.parse(xhttp.response);
            localStorage.setItem("loggedIn", "notnull");
            localStorage.setItem("userID", response.body);
            console.log(response);
            window.location = "/searchPage.jsp";
        });
        return false;
    }

    function hexString(buffer) {
        var byteArray = new Uint8Array(buffer);

        var hexCodes = [...byteArray].map(value => {
            const hexCode = value.toString(16);
        var paddedHexCode = hexCode.padStart(2, '0');
        return paddedHexCode;
    });

        return hexCodes.join('');
    }
</script>

</body>
</html>


