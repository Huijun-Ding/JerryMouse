<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/Style.css">
        <link rel="stylesheet" type="text/css" href="css/background_login.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <title>Connexion</title>
    </head>
    <body id="login_body">
        <div class="container h-100">
            <div class="d-flex justify-content-center h-100">
                <div class="user_card">
                    <div class="d-flex justify-content-center">
                        <div class="brand_logo_container">
                            <img src="img/logo.png" class="brand_logo" alt="Logo">
                        </div>
                    </div>

                    <div class="d-flex justify-content-center form_container">

                        <form action="Connect" method ="POST">
                            <div class="d-flex justify-content-center">
                                <h1 id="titrePageConnexion">Connexion</h1>
                            </div>
                            <div>
                                <p>Veuillez entrer votre login et votre mot de passe.</p>
                            </div>
                            <% if (request.getAttribute("msg_error") != null) {%>
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                <%out.println(request.getAttribute("msg_error"));%>
                                <button type="button" class="btn-close" data-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <%}%>

                            <div class="input-group mb-3">
                                <div class="input-group-append">
                                    <span class="input-group-text h-100"><i class="fa fa-user"></i></span>
                                </div>
                                <input type="email" name="mail" class="form-control input_user" value="" placeholder="Votre adresse Email" required>
                            </div>
                            <div class="input-group mb-2">
                                <div class="input-group-append">
                                    <span class="input-group-text h-100"><i class="fas fa-key"></i></span>
                                </div>
                                <input type="password" name="password" class="form-control input_pass" value="" placeholder="Votre mot de passe" required>
                            </div>

                            <div class="d-flex justify-content-center mt-3 login_container">
                                <button type="submit" name="button" id="btn_login" class="btn btn-outline-light">Se connecter</button>

                            </div>
                            <div class="d-flex justify-content-center mt-3 login_container">
                                <a class="btn btn-outline-light" id="btn_return" href="index">Retour vers l'accueil</a> 
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </body>

</html>
