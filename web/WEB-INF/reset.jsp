<%-- 
    Document   : reset
    Created on : Nov 26, 2020, 12:00:38 PM
    Author     : 760483
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
    </head>
    <body>
        <h1>Reset Password</h1>
        <span>
            Please enter your email address to reset your password
        </span>
        
        <div>
            <form method="POST" action="reset">
                <label>
                    Email Address: <input type="text" name="resetEmail">
                </label><br/>
                <input type="submit" value="Send Reset Mail">
            </form>
        </div>
        
    </body>
</html>
