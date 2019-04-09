<html>
<head>
    <title>Signup Page</title>
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
    <h4 id = "signuptext"> Sign Up for I'm Hungry </h4>

    <div>
        <p id = "account"> Already have an account? </p>
        <a href="loginPage.jsp" id = "login_link"> Log in </a>
    </div>

    <form action="LoginServlet" method="POST">
        <div class="form-group">
            <input class="form-control input-lg" type = "text" id = "fullname" name = "signup" placeholder=" Full Name">
        </div>
        <br>
        <div class="form-group">
            <input class="form-control input-lg" type="text" id="email" name="signup" placeholder=" Email">
        </div>
        <br>
        <div class = "form-group">
            <input class="form-control input-lg" type="text" id="password" name="signup" placeholder=" Password">
        </div>
        <br>

        <input id = "submit" type="submit" value="Sign Up">
    </form>

</div>

</body>
</html>
