<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My basket</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/Style.css">
    </head>
    <body onload="displayBasket(), displayPoints()">
        <script type="text/JavaScript" src="js/ScriptCheckBasket.js"></script>
        <div class="container" id="container">
            <div class="row">
                <div class="col-2" name="en_tete">
                    <img id="logo" src="img/photo.png">
                </div>
                <div class="col-8">Store:</div>
                <div class="col-2">
                    <button class="btn btn-outline-primary" id="se_connecter">Login</button>
                </div>
            </div>

            <h1>My basket</h1>

            <%
                String idClient = request.getParameter("idClient");
            %>
            <input type = 'hidden' id = 'idClient' name = 'value' value = '<%=idClient%>'></input>
            <form action="">
                <div class="lst_prod" id="div_prod">
                    <table class="table table-hover" id="tabProd">
                        <%
                            // loop to write products
                            out.print("<tr><td>" + "photo" + "</td>");
                            out.print("<td>Nom</td>");
                            out.print("<td>Prix Unitaire</td>");
                            out.print("<td>Qte</td>");
                            out.print("<td>Prix Total</td></tr>");
                        %>
                    </table>
                </div>

                    <div name="calcul">
                    <table class="table"  id="points">
                        <tr>
                            <td>Points got: <span id="cagnotte_gagne"></span></td>
                            <td>Points cumulative: <span id="cagnotte_cumul"></span></td>
                            <td>Total: <span id="total"></span></td>
                        </tr>
                    </table>
                </div>

                <div class="row">
                    <div class="col-8"></div>
                    <div class="col-2">
                        <input class="btn btn-outline-primary" type="submit" id="valider" name="valider" value="OK">
                    </div>
                    <div class="col-2">
                        <button class="btn btn-outline-primary" id="retour">Return</button>
                    </div>
                </div>
            </form>
        </div>
        
    </body>
</html>
