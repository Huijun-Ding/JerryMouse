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
            <h2>Ma liste de courses xxxx</h2>
            <br>
            <div class="row">
                <div class="col-7">
                    <div class="card">
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">Compote de fraise
                                <p class="text-end"><i class="fas fa-search-plus"></i> <i class="far fa-trash-alt"></i></p>
                            </li>

                            <li class="list-group-item">colle textil
                                <p class="text-end"><i class="fas fa-search-plus"></i> <i class="far fa-trash-alt"></i></p>
                            </li>
                        </ul>
                    </div>
                </div>

                <div class="col-1"></div>

                <div class="col-3">
                    <button type="button" id="add_shopping_list" class="btn btn-outline-primary">Ajouter</button>
                    <button type="button" id="import_shopping_list" class="btn btn-outline-primary">Importer</button>
                </div>
            </div>

            <div class="modal" tabindex="-1" id="modal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Ajouter un nouveau post-it</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <p>Contenu : </p>
                            <input type="text" class="form-control w-25 p-3 h-25" class="post_it" width="50">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Supprimer</button>
                            <button type="button" class="btn btn-primary">Sauvgarder</button>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <script>
        $(document).ready(function(){
                    $("#add_shopping_list").click(function(){
            $("#modal").modal("show");
            });
        });
        </script>
    </script>
    <script src="js/shoppingList.js"></script>
</body>
</html>
