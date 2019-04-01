<%--
  Created by IntelliJ IDEA.
  User: Sophia Hu
  Date: 3/31/2019
  Time: 3:05 PM
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

        <h4> Sign Up </h4>
        <form action="LoginServlet" method="POST">
            <input type = "text" id = "fullname" name = "signup" placeholder="Full Name">
            <input type="text" id="email" name="signup" placeholder="Email">
            <input type="text" id="password" name="signup" placeholder="Password">
            <input type="submit" value="Sign Up">
        </form>


    </div>
</div>

</body>
</html>
