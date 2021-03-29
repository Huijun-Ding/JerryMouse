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
        <link rel="stylesheet" type="text/css" href="../css/Style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <title>Connexion</title>
    </head>
    <body>


        <form action="../Connect" method ="POST" class="signin">
            <div class ="container">
                <h1>Connexion</h1>
                    <p>Veuillez entrer votre login et votre mot de passe .<br>
                        Les champs suivis d'un '*' sont obligatoires.</p>
                    <p class="text-danger">
                        <% if (request.getAttribute("msg_error") != null)
                                out.println(request.getAttribute("msg_error"));%>
                    </p>
                <div class="form-group">

                    <p>Login * :</p>
                    <input type="email" id="mail" name="mail" placeholder=" Votre adresse email"  required>

                </div>
                <div class="form-group">
                    <p>Mot de passe *: </p>
                    <input type="password" id="pw" name="password" placeholder=" Votre mot de passe"  required>

                </div>
                <br>
                <a href="index.jsp" class="btn btn-outline-primary"> Retour vers l'accueil</a>
                <button type="submit" class="btn btn-outline-primary">Se connecter</button>
        </form>

    </div>
</body>

</html>
