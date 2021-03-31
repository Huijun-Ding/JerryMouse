<%@page import="java.util.Date"%>
<%@page import="com.jms.model.Have"%>
<%@page import="com.jms.model.Store"%>
<%@page import="com.jms.model.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mon panier</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>      
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
                int idStore = (store != null) ? store.getId() : 0;

                Have h = (Have) request.getSession().getAttribute("have");
                session.setAttribute("have", h);
                String startTime = (h != null) ? h.getHaveId().getStartTime() : null;
                Date date = (h != null) ? h.getHaveId().getDate() : null;
            %>
            <input type = 'hidden' id = 'idClient' name = 'value' value = '<%=idClient%>'></input>
            <input type = 'hidden' id = 'idStore' name = 'value' value = '<%=idStore%>'></input>
            <input type = 'hidden' id = 'startTime' name = 'value' value = '<%=startTime%>'></input>
            <input type = 'hidden' id = 'date' name = 'value' value = '<%=date%>'></input>

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
                                <tr><td>Points acquis : </td><td><span class="bold" id="cagnotte_gagne"></span></td></tr>
                                <tr><td>Points actuels : </td><td><span class="bold" id="cagnotte_cumul"></span></td></tr>
                                <tr><td>R&eacute;duction directe : </td><td><span class="bold" id="reduction"></span></td></tr>
                                <tr><td>Total : </td><td><span class="bold" id="total"></span></td></tr>                        
                            </table>
                        </div>


                        <div>
                            <input type="checkbox" id="checkPoint" name="checkPoint" >
                            <label for="checkPoint"> Utiliser mes points de fid&eacute;lit&eacute;</label><br>
                        </div>
                        <div class="msg_error" id="msg_error"></div>

                        <div class="row">
                            <div class="col-6">
                                <boutton type="button" class="btn btn-outline-primary" name="valider" data-toggle="modal" data-target="#exampleModalLong">Valider</boutton>
                                <!--<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalLong">-->
                            </div>
                            <div class="col-6">
                                <a class="btn btn-outline-primary" href="index" id="test">Retour</a>
                            </div>
                        </div>

                        <!-- Modal -->
                        <div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLongTitle">Confirmation</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        Voulez-vous valider votre commande?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                                        <button type="button" id="valider" class="btn btn-outline-primary" data-dismiss="modal">Valider</button>
                                    </div>
                                </div>
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
