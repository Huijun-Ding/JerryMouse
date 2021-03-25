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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        
        <title>Connexion</title>
    </head>
    <body>
        <div class ="container">
            <h1>Connexion</h1>
        
            <p>Veuillez entrer votre login et votre mot de passe <br>
            Les champs suivis d'un * sont obligatoires</p>
            <p class="text-danger">${requestScope.msg_error}</p>
            
            <form action="connect" method ="GET">
                <div>
                    <p>Login* :</p>
                    <input type="text" id="mail" name="mail" required />
                    <p>Mot de passe*: </p>
                    <input type="text" id="pw" name="password" required />    
                </div>
                <br>
                <a href="connect?method=return" class="btn btn-outline-primary"> Retour vers l'accueil<a/>
                <a href="connect?method=connection" class="btn btn-outline-primary">Se connecter</a>

            </form>
            
        </div>
    </body>
</html>
