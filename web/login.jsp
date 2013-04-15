<%-- 
    Document   : login
    Created on : 15-apr-2013, 19:59:32
    Author     : Eusebius
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Inloggen Gebruikers</h1>
        
        <form class="col col_6">
    	<fieldset>	
            <div>
            	<label for="Username">Gebruikersnaam</label>
                <input type="Name" id="email" required="required" class="box_shadow"  />
            </div>
            <div>
            	<label for="Password">Wachtwoord</label>
                <input type="Password" id="Password" required="required" class="box_shadow" min="10" />
            </div>
            <input type="submit" value="Login" />
        </fieldset>
    </form>

    </body>
</html>
