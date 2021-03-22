<%-- 
    Document   : signup
    Created on : 22 mars 2021, 15:14:23
    Author     : RAKOTOARISOA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inscription</title>
    </head>
    <body>
        <h1>Inscription</h1>
        <div id="infoInscription">
            <p>Veuillez saisir les informations demandées<br>
            Les champs suivis d'un * sont obligatoires</p>
        </div>
        
        <form action="action" method="">
            <div>
                <p>Nom* :</p>
                <input type ="text" name="lastNameClient"/>
                <p>Prénom* :</p>
                <input type ="text" name ="firstNameClient"/>
                <p>Adresse mail* : </p>
                <input type ="email" name ="emailClient"/>
                <p>Confirmation de l'adresse mail* : </p>
                <input type ="email" name ="confEemailClient"/>
                <span id="msgerrorMail">Les adresses mail saisies ne sont pas identiques</span>
                <p>Mot de passe* : </p>
                <input type ="text" name ="passwordCreated">
                <span id="msgerrorPassword">Les mots de passe saisies ne sont pas identiques</span>
                <p>Confirmation du mot de passe* : </p>
                <input type ="text" name ="confPasswordCreated"/>
            </div>
            
            <input type="submit" value="Annuler"/>
            <input type="submit" value="S'inscrire"/>
        </form>
    </body>
</html>
