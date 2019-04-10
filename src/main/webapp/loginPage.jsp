<html>
<head>
    <title>Login Page</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="login.css">
    <link rel="stylesheet" type="text/css" href="common.css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<body>
<div id="common_header">
    <h4 id = "header_text">I'm Hungry </h4>
</div>
<div id="formContent">
    <h4 id = "signuptext"> Log in to I'm Hungry </h4>
    <div>
        <p id = "account"> New to I'm Hungry? </p>
        <a href="searchPage.jsp" id = "login_link"> Sign up </a>
    </div>

    <form onsubmit="return sendData()">
        <div class="form-group">
            <input class="form-control input-lg" id="username" type = "text" name = "username" placeholder=" Username">
        </div>
        <br>
        <div class="form-group">
            <input class="form-control input-lg" id="password" type="password" name="password" placeholder=" Password">
        </div>

        <h4 style="color: red" id="warning"></h4>

        <br>
        <input id = "submit" type="submit" value="Log In">
    </form>

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
            if(response.header != "LoggedIn") {
                document.getElementById("warning").innerHTML="Login failed:<br/>" + response.header;
                return false;
            }
            localStorage.setItem("loggedIn", response.header);
            localStorage.setItem("userID", response.body);
            //console.log(response);
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
