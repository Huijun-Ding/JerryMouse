<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Jerry Mouse Software</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="../css/Style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body>
        <header class="shadow p-3 mb-5 bg-body fixed-top">
            <div class="d-flex flex-wrap align-items-center justify-content-around">
                <div class="d-flex flex-column align-items-center justify-content-center">
                    <a href="/" class="d-flex align-items-center mb-2 mb-lg-1 text-decoration-none">
                        <img id="logo" src="../img/logo_small.png" alt="" class="d-inline-block align-text-top rounded">
                    </a>
                    <button type="button" class="btn btn-sm btn-outline-primary dropdown-toggle border-0" data-toggle="dropdown">
                        <i class="fa fa-shopping-bag"></i> Toulouse
                    </button>
                    <ul class="dropdown-menu">
                        <li class="dropdown-item">
                            <i class="fas fa-store-alt"></i> Carrefour Market Toulouse Compans
                        </li>
                        <li class="dropdown-item">
                            <i class="fa fa-map-pin"></i> 3, Espl. Compans Caffarelli
                        </li>
                        <li class="dropdown-item">
                            <i class="fa fa-map-signs"></i> Toulouse
                        </li>
                        <li class="dropdown-item">
                            <i class="fa fa-envelope-open"></i> Code Postal
                        </li>
                        <li><hr class="dropdown-divider"></li>
                        <li class="dropdown-item">
                            Changer de magasin : 
                            <input type="search" class="form-control" placeholder="Saisir le code postal ici ...">
                        </li>
                        <li id="stores_list" class="dropdown-item"">
                            <ul class="list-group">
                            </ul>
                        </li>
                    </ul>
                </div>

                <form>
                    <div class="input-group input-group-sm">
                        <input type="search" class="form-control" placeholder="Rechercher un produit ...">
                        <div class="input-group-append">
                            <button type="button" class="btn btn-primary">
                                <i class="fa fa-search"></i>
                            </button>
                        </div>
                    </div>
                </form>

                <div class="btn-group ms-3">
                    <div class="btn-group">
                        <button type="button" class="btn btn-sm btn-outline-primary dropdown-toggle" data-toggle="dropdown">
                            <div class="d-flex flex-column">
                                <i class='far fa-id-badge'></i>
                                Compte
                            </div>
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="#">Se connecter</a>
                            <a class="dropdown-item" href="#">S'inscrire</a>
                        </div>
                    </div>
                    <a href="../basketPage?idClient=<%out.println("1");%>" role="button" class="btn  btn-sm btn-primary">
                        <div class="d-flex flex-column">
                            <i class="fa fa-shopping-basket"></i>
                            Panier
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </header>

    <ul id="rayon_categorie_navbar" class="nav nav-tabs nav-justified">
        <li class="nav-item">
            <button type="button" class="nav-link active dropdown-toggle" data-toggle="dropdown">
                Rayon
            </button>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="#">Rayon 1</a>
                <a class="dropdown-item" href="#">Rayon 2</a>
            </div>
        </li>
        <li class="nav-item">
            <button type="button" class="nav-link active dropdown-toggle" data-toggle="dropdown">
                Catégorie
            </button>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="#">Catégorie 1</a>
                <a class="dropdown-item" href="#">Catégorie 2</a>
            </div>
        </li>
    </ul>

    <h1>Hello World!</h1>
    <h1>Hello World!</h1>
    <h1>Hello World!</h1>
    <h1>Hello World!</h1>
    <h1>Hello World!</h1>
    <h1>Hello World!</h1>
    <h1>Hello World!</h1>
    <h1>Hello World!</h1>
    <h1>Hello World!</h1>
    <h1>Hello World!</h1>
    <h1>Hello World!</h1>
    <h1>Hello World!</h1>
    <h1>Hello World!</h1>
    <h1>Hello World!</h1>
    <h1>Hello World!</h1>
    <h1>Hello World!</h1>
</body>
</html>
