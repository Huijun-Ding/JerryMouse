<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mon panier</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="../css/Style.css">
    </head>
    <body>
        <div class="container" id="container">
            <div class="row">
                <div class="col-2" name="en_tete">
                    <img id="logo" src="../img/photo.png">
                </div>
                <div class="col-8">Magasin choisi:</div>
                <div class="col-2">
                    <button class="btn btn-outline-primary" id="se_connecter">Se connecter</button>
                </div>
            </div>

            <h1>Mon panier</h1>

            <form action="">
                <div class="lst_prod" name="div_prod">
                    <table class="table table-hover">
                        <%
                            // loop to write products
                            out.println("<tr><td>" + "photo" + "</td>");
                            out.println("<td>Nom</td>");
                            out.println("<td>Prix Unitaire</td>");
                            out.println("<td>Qte</td>");
                            out.println("<td>Prix Total</td>");
                            out.println("</tr>");
                        %>
                    </table>
                </div>

                <div name="calcul">
                    <table class="table">
                        <tr>
                            <td>Gagnotte gagn&eacute;: <span id="gagnotte_gagne"></span></td>
                            <td>Gagnotte cumul: <span id="gagnotte_cumul"></span></td>
                            <td>Total: <span id="total"></span></td>
                        </tr>
                    </table>
                </div>



                <div class="row">
                    <div class="col-8"></div>
                    <div class="col-2">
                        <input class="btn btn-outline-primary" type="submit" name="valider" value="Valider">
                    </div>
                    <div class="col-2">
                        <button class="btn btn-outline-primary" id="retour">Retour</button>
                    </div>
                </div>
            </form>

        </div>
    </body>
</html>
