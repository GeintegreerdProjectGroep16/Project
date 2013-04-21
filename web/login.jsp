<%-- 
    Document   : login
    Created on : 15-apr-2013, 19:59:32
    Author     : Eusebius
--%><%@page import="javax.swing.JOptionPane"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./css/css3.css" media="screen">
        <link rel="stylesheet" type="text/css" href="./css/forms.css" media="screen">
        <link rel="stylesheet" type="text/css" href="./css/general.css" media="screen">
        <link rel="stylesheet" type="text/css" href="./css/grid.css" media="screen">
        <title>Login</title>
    </head>
    <body>
        <h1>Inloggen Gebruikers</h1>
        
        <form class="col col_6" action="servletLogin">
    	<fieldset>	
            <div>
            	<label name ="lbluName" for="Username">Gebruikersnaam</label>
                <input type="text" name="uName" required="required" class="box_shadow"  />
            </div>
            <div>
            	<label for="Password">Wachtwoord</label>
                <input type="Password" name="Password" required="required" class="box_shadow"/>
            </div>
            <input type="submit" value="Login" />
        </fieldset>
        </form>
       
    </body>
</html>
