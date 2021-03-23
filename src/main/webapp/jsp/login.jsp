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
            <div>${requestScope.msg_error}</div>
        </div>
        
        <form action="connect" method ="GET">
            <div>
                <p>Login* :</p>
                <input type="email" id="mail" name="login" required />
                <p>Mot de passe*: </p>
                <input type="text" id="pw" name="password" required />    
            </div>
            
            <a href="connect?method=return"> Retour vers l'accueil<a/>
            <a href="connect?method=connection" >Se connecter</a>
        </form>
        
    </body>
</html>
