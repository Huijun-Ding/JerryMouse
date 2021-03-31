<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.jms.model.Have"%>
<%@page import="com.jms.model.Store"%>
<%@page import="com.jms.model.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmation Commande</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/Style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div id="confirm_body" class="mt-4">
            <div id="icon" class="mb-3">
                <i class="far fa-check-circle fa-8x"></i><br>
            </div>

            <h2>Merci Chlo&eacute;</h2>

            <%
                Order order = (Order)session.getAttribute("order");
                Store store = (Store)session.getAttribute("store");
                Have have = (Have)session.getAttribute("have");
                DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
            %>
            <p>Votre commande num&eacute;ro 
                <span id="num_order"> <%=order.getOrderId()%> </span> 
                est bien &eacute;t&eacute; enregistr&eacute;e !</p>

            <div class="container">
                <div class="row">
                    <table id="tab_confirm" frame='above'>
                        <tr>
                            <th class="col-6" id="th1">Magasin de retrait</th>
                            <th class="col-6" id="th2">Cr&eacute;neau de retrait</th>
                        </tr>
                        <tr>
                            <td class="col-6" id="td1">
                                <%=store.getName()%></br>
                                <%=store.getStreet()%></br>
                                <%=store.getPostalCode() + ", " + store.getCity()%>
                            </td>
                            <td class="col-6" id="td2">
                                <%=DF.format(have.getHaveId().getDate())%></br>
                                <%=have.getTimeSlot().getStartTime() + "-"+ have.getTimeSlot().getEndTime()%>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

            <div class="mt-4">
                <boutton type="button" id="retour" class="btn btn-outline-primary">Page d'accueil</boutton>
            </div>
        </div>
    </body>
</html>
