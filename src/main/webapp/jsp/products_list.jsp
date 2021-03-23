<%-- 
    Document   : products_list
    Created on : 22 mars 2021, 16:13:18
    Author     : mlk
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <title>Products List</title>
    </head>
    <body>
        
        <div class="container">
            <h1>Liste des produits</h1>
            <% 
                int nb_product = 5; // number of products to display                 
            %> 
            <div class="card-deck">
                <% for(int i=0; i < nb_product; i++) { %>
                <div class="card">
                    <%  // Properties of a product
                        String libelle = "Libelle de l'article";
                        String price = "Prix";
                        String priceKG = "Prix au kg";
                        String nutriscore = "A";
                        ArrayList<String> labels = new ArrayList<String>(); // Remplacer par des objets Label
                        labels.add("AOP"); labels.add("AB");
                    %>
                    <img class="card-img-top" src="src" alt="alt"/>
                    <div class="card-body text-center">
                        <p class="card-text"><%= libelle %></p>
                        <h3><%= price %></h3>
                        <p><%= priceKG %></p>
                        <div class="nutriscore"><%= nutriscore %></div>
                        <a href="#" class="btn btn-primary stretched-link">Voir Produit</a> <!-- mettre le lien sur le libelle ou ailleurs ? -->
                    </div>
                </div>
                <% } %>
            </div>
        </div>
    </body>
</html>
