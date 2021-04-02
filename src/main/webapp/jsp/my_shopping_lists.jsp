<%@page import="com.jms.model.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mes listes de courses</title>

        <link rel="icon" type="image/png" href="img/logo.png">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/Style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body id="page">
        <jsp:include page="navbar" flush="true"/>

        <div class="container" id="rayon_categorie_navbar">
            <h2>Mes listes de courses</h2>

            <br>
            <div class="list-group col-7" id="my_lists"></div>

            <br>
            <div class="btn-group w-50">
                <button type="button" class="btn btn-primary" id="add_new_sl">
                    <div class="d-flex flex-column">
                        <span class="far fa-clipboard"></span> Créer une nouvelle liste de courses
                    </div>
                </button>
                <button type="button" id="import_shopping_list" class="btn btn-primary" data-toggle="modal" data-target="#import_list_file">
                    <div class="d-flex flex-column">
                        <span class="fas fa-download"></span> Importer une liste de course
                    </div>
                </button>
            </div>
        </div>



        <div class="modal" tabindex="-1" id="import_list_file">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"><span class="fas fa-download"></span> Importer une liste de course</h5>
                        <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="import_title_list">Nom de votre nouvelle liste</label>
                            <input id="import_title_list" class="form-control" type="text" name="title" placeholder="Mettez le nom de votre liste ici ...">
                        </div>

                        <div class="form-group my-3">
                            <label for="fileShoppingList">S&eacute;lectionner un fichier</label>
                            <input id="fileShoppingList" name="file" type="file" class="form-control w-30 p-40 h-25 post_it">
                            <small class="form-text text-muted">Uniquement du "txt" ou du "csv".</small>
                        </div>

                        <div id="post_its_from_file" class="container"></div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                        <button id="validate_uploading_file" type="button" class="btn btn-primary">Valider</button>
                    </div>
                </div>
            </div>
        </div>

        <script src="js/allShoppingLists.js"></script>
        <script src="js/search.js"></script>
        <script src="js/stores.js"></script>
        <script src="js/time_slots.js"></script>
        <script src="js/basket.js"></script>
        <script src="js/upload_shopping_file.js"></script>
    </body>
</html>
