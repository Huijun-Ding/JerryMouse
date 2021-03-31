<%@page import="com.jms.model.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Jerry Mouse Software</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/Style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    </head>
    <body>

        <jsp:include page="navbar" flush="true"/>

        <div class="container" id="rayon_categorie_navbar">
            <div id="list_name"></div>
            <br>
            <div class="row">
                <div class="col-7">
                    <div class="card" id="show_postit">
                    </div>
                </div>

                <div class="col-1"></div>

                <div class="col-3">
                    <div class="btn-group-vertical">
                        <button type="button" id="valide_add_pt" class="btn btn-outline-primary" data-toggle="modal" data-target="#add_post_it">Ajouter un post-it dans cette liste</button>
                        <button type="button" id="valide_add_article" class="btn btn-outline-primary" data-toggle="modal" data-target="#add_article">Ajouter un article dans cette liste</button>
                        <button type="button" id="import_shopping_list" class="btn btn-outline-primary" data-toggle="modal" data-target="#import_list_file">Importer une liste de course</button>
                        <button type="button" id="list_to_panier" class="btn btn-outline-primary">Transformer ma liste en panier</button>
                    </div>
                </div>
            </div>

            <div class="modal" tabindex="-1" id="add_post_it">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Ajouter un post-it</h5>
                            <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <p>Contenu de post-it : </p>
                            <form>
                                <div class="input-group input-group">
                                    <input type="input" class="form-control" placeholder="Jus de fruit, Lait, Savon...">
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                            <button type="button" class="btn btn-primary">Valider</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal" tabindex="-1" id="add_article">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Ajouter un article</h5>
                            <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <p>Veuillez rechercher l'article : </p>
                            <form>
                                <div class="input-group input-group">
                                    <input id="search" type="search" class="form-control" placeholder="Rechercher un produit ...">
                                    <div class="input-group-append">
                                        <button type="button" class="btn btn-primary" id="search_button">
                                            <i class="fa fa-search"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                            <button type="button" class="btn btn-primary">Valider</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal" tabindex="-1" id="import_list_file">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Importer un fichier</h5>
                            <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <p>S&aacute;lectionner un fichier : </p>
                            <input type="file" class="form-control w-30 p-40 h-25" class="post_it" width="50">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                            <button type="button" class="btn btn-primary">Valider</button>
                        </div>
                    </div>
                </div>
            </div>    

        </div>

        <script src="js/postIt.js"></script>
        <script src="js/search.js"></script>
        <script src="js/stores.js"></script>
        <script src="js/time_slots.js"></script>
        <script src="js/basket.js"></script>
    </body>
</html>
