<%@page import="com.jms.model.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmation Commande</title>
    </head>
    <body>
        
        <h1>Merci Chlo&eacute;</h1>
        
        <%
            Order order = (Order)session.getAttribute("order");
        %>
        <p>Votre commande num&eacute;ro 
            <span id="num_order"><%=order.getOrderId()%></span> 
            est bien &eacute;t&eacute; enregistr&eacute;e !</p>
    </body>
</html>
