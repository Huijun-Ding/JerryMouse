<%@page import="com.jms.model.Client"%>
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
        <script src="../js/stores.js"></script>
        <script src="../js/department.js"></script>
        <script src="../js/basket.js"></script>

    </head>
    <body>
        <jsp:include page="navbar.jsp" flush="true"/>



        <div class="container" id="rayon_categorie_navbar">
            <h1>Créer ma nouvelle liste de courses</h1>

            <form>
                <div class="mb-3">
                    <label for="name_shopping_list" class="form-label">Nom de la liste</label>

                    <input type="text" class="form-control w-25 p-3 h-25" id="name_shopping_list" aria-describedby="nameHelp" width="50">
                    <div id="nameHelp" class="form-text">Veuillez saisir un nom personnalisé pour votre nouvelle liste de course.</div>
                </div>
                <div><button type="button" class="btn btn-primary">Créer</button></div>
            </form>
        </div>

        <script type="text/JavaScript" src="../js/search.js"></script>
    </body>
</html>
