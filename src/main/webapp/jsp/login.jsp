<%-- 
    Document   : login
    Created on : 22 mars 2021, 15:13:58
    Author     : RAKOTOARISOA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion</title>
    </head>
    <body>
        <h1>Connexion</h1>
        <div id="infoConnection">
            <p>Veuillez entrer votre login et votre mot de passe <br>
            Les champs suivis d'un * sont obligatoires</p>
        </div>
        
        <form action="action">
            <div>
                <p>Login* :</p>
                <input type="email" name="login" required />
                <p>Mot de passe*: </p>
                <input type="text" name="password" required />    
            </div>
            
            <input type ="submit" value="retour"/>
            <input type="submit" value = "Se connecter"/>
        </form>
    </body>
</html>
