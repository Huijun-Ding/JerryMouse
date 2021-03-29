<%@page import="com.jms.model.Have"%>
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
        
        <jsp:include page="navbar" flush="true"/>
        
        <div class="container" id="rayon_categorie_navbar">
            <h1>Mon panier</h1>

            <%
                Client client = (Client) session.getAttribute("client");
                int idClient = (client != null) ? client.getCode() : 0;
                session.setAttribute("client", client);
                Store store = (Store) session.getAttribute("store");
                session.setAttribute("store", store);
                Have h = (Have) request.getSession().getAttribute("have");
                session.setAttribute("have", h);
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
                        
                        <div class="msg_error" id="msg_error"></div>
                        
                        <div class="row">
                            <div class="col-6">
                                <boutton class="btn btn-outline-primary" id="valider" name="valider">Valider</boutton>
                            </div>
                            <div class="col-6">
                                <a class="btn btn-outline-primary" href="index">Retour</a>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </body>
    <script src="js/ScriptCheckBasket.js"></script>
    <script src="js/basket.js"></script>
    <script src="js/valider.js"></script>
</html>
