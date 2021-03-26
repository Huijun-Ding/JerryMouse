<%@page import="com.jms.model.Store"%>
<%@page import="com.jms.model.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mon panier</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/Style.css">
    </head>
    <body onload="displayBasket(), displayPoints()">
        <script type="text/JavaScript" src="js/ScriptCheckBasket.js"></script>
        <div class="container" id="container">
            <div class="row">
                <div class="col-2" name="en_tete">
                    <!--<img id="logo" src="img/logo.png">-->
                </div>
                <!--<div class="col-8">Store:(<span id="store"></span>)</div>-->
                <div class="col-2">
                    <!--<button class="btn btn-outline-primary" id="se_connecter">Login</button>-->
                </div>
            </div>

            <h1>Mon panier</h1>

            <%
                
                Client client = (Client)session.getAttribute("client");
                int idClient = 1;
                if(client == null){
                    response.sendRedirect("jsp/login.jsp");

                }else {
                    idClient = client.getCode();

                }
                session.setAttribute("client", client);
                
                Store store = (Store)session.getAttribute("store");
                session.setAttribute("store", store);
            %>
            <input type = 'hidden' id = 'idClient' name = 'value' value = '<%=idClient%>'></input>
            <form action="">
                <div class="row">
                    <div class="col-8" id="div_prod">
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
                    <div class="col-1"></div>      
                    <div class="col-3">    
                        <div name="calcul">
                            <table class="table" id="points">
                                <tr><td>Points acquis: </td><td><span class="bold" id="cagnotte_gagne"></span></td></tr>
                                <tr><td>Points cumulatifs: </td><td><span class="bold" id="cagnotte_cumul"></span></td></tr>
                                <tr><td>R&eacute;duction directe: </td><td><span class="bold" id="reduction"></span></td></tr>
                                <tr><td>Total: </td><td><span class="bold" id="total"></span></td></tr>                        
                            </table>
                        </div>

                        <div class="row">
                            <div class="col-6">
                                <input class="btn btn-outline-primary" type="submit" id="valider" name="valider" value="Valider">
                            </div>
                            <div class="col-6">
                                <button class="btn btn-outline-primary" id="retour">Retour</button>
                            </div>
<!--                            <div class="col-7"></div>-->
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
