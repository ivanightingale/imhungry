<html>
<head>
    <title>Sign Up Page</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/loginPage.css">
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<body>
<div id="common_header">
    <h4 id = "header_text">I'm Hungry </h4>
</div>
<div id="formContent">
    <h4 id = "signuptext"> Sign Up for I'm Hungry </h4>

    <div>
        <p id = "account"> Already have an account? </p>
        <a href="loginPage.jsp" id = "login_link"> Log in </a>
    </div>

    <form id = "form" onsubmit="return sendData()">
        <div class="form-group">
            <input class="form-control input-lg" type="text" id="username" name="username" placeholder=" Username">
        </div>
        <br>
        <div class = "form-group">
            <input class="form-control input-lg" type="password" id="password" name="password" placeholder=" Password">
        </div>
        <br>

        <h4 style="color: red" id="warning"></h4>

        <input id = "submit" type="submit" value="Sign Up">
    </form>

</div>

<script>
    function sendData() {
        var passhash = crypto.subtle.digest("SHA-512", new TextEncoder().encode(document.getElementById("password").value));
        passhash.then( hashedPass => {
            passhash = hexString(hashedPass);

            var xhttp = new XMLHttpRequest();
            xhttp.open("POST", "/Login?signOrLog=signup", false);
            console.log(JSON.stringify({header:document.getElementById("username").value,body:passhash}));
            xhttp.send(JSON.stringify({header:document.getElementById("username").value,body:passhash}));
            var response = JSON.parse(xhttp.response);
            if(response.header != "LoggedIn") {

                if ((response.header == "Created")) {
                    document.getElementById("warning").innerHTML = "Account Created";
                    document.getElementById("warning").style.color = "black";
                } else {
                    document.getElementById("warning").innerHTML = "Login failed:<br/>" + response.header;
                }
                return false;
            }
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
