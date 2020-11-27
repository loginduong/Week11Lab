<%-- 
    Document   : resetNewPassword
    Created on : Nov 26, 2020, 3:22:11 PM
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
        <h1>Enter a New Password</h1>
        <div>
            <form method="POST" action="reset">
                
                <input type="text" name="newPassword">
                <input type="submit" value="Submit">
                <input type="hidden" value="${uuid}" name="userResetPassword">               
                
            </form>
        </div>
    </body>
</html>
