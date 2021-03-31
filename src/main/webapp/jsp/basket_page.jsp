<%@page import="java.text.SimpleDateFormat"%>
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
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/Style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body onload="displayBasket(), displayPoints()">

        <jsp:include page="navbar" flush="true"/>

        <div class="container" id="rayon_categorie_navbar">
            <h1>Mon panier</h1>
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
                                        <h5 class="modal-title" id="exampleModalLongTitle">Voulez-vous valider votre commande?</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div>
                                            <%
                                                Store store = (Store)session.getAttribute("store");
                                                Have have = (Have)session.getAttribute("have");
                                                SimpleDateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
                                                if(store != null && have != null){
                                            %>
                                                <li class='list-group-item'>
                                                    <div class='card'>
                                                        <div class='card-header'>Magasin de retrait :</div>
                                                        <div class='card-body'>
                                                            <h5 class='card-title'><%=store.getName()%>  </h5>
                                                            <p class='card-text'><%=store.getStreet()%></p>
                                                            <p class='card-text'><%=store.getPostalCode() + " " + store.getCity()%></p>
                                                        </div>

                                                        <div class='card-header'>Cr&eacute;neau de retrait :</div>
                                                            <div class='card-body'>
                                                                <p class='card-text'><%=DF.format(have.getHaveId().getDate())%></p>
                                                                <p class='card-text'><%=have.getTimeSlot().getStartTime() + " - " + have.getTimeSlot().getEndTime()%></p>
                                                            </div>
                                                    </div>
                                                </li>
                                                <%}%>
                                        </div>
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
    <script src="js/search.js"></script>
    <script src="js/stores.js"></script>
    <script src="js/time_slots.js"></script>
    <script src="js/confirm_store_time.js"></script>
</html>
