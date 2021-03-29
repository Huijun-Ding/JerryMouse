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

        <div id="rayon_categorie_navbar">
            <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item btn-group">
                        <button id="departmentButton" type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown">
                        </button>
                        <div id="departments_list" class="dropdown-menu"></div>
                    </li>
                    <li id="category" class="breadcrumb-item btn-group" aria-current="page">
                        <button id="categoryButton" type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown">
                        </button>
                        <div id="productCategories_list" class="dropdown-menu"></div>
                    </li>
                </ol>
            </nav>
        </div>
  
        <!-- 16:9 aspect ratio -->
        <iframe id="view" class="h-100 d-inline-block w-100" src="DisplayProducts?home" allowfullscreen></iframe>

        <script type="text/JavaScript" src="js/search.js"></script>
        <script src="js/stores.js"></script>
        <script src="js/department.js"></script>
        <script src="js/basket.js"></script>
    </body>
</html>
