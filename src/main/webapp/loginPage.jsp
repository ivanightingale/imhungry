<html>
<head>
    <title>Login Page</title>
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
    <h4 id = "signuptext"> Log in to I'm Hungry </h4>
    <div>
        <p id = "account"> New to I'm Hungry? </p>
        <a href="searchPage.jsp" id = "login_link"> Sign up </a>
    </div>

    <form id = "form" action="LoginServlet" method="POST">
        <div class="form-group">
            <input class="form-control input-lg" type = "text" name = "login" placeholder=" Email">
        </div>
        <br>
        <div class="form-group">
            <input class="form-control input-lg" type="text" name="password" placeholder=" Password">
        </div>

        <div >
            <a id="form_footer" href="#">Forgot Password?</a>
        </div>
        <br>
        <input id = "submit" type="submit" value="Log In">
    </form>

</div>

</body>
</html>
