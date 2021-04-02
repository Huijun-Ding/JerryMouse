<%-- 
    Document   : favorites
    Created on : 22 mars 2021, 16:13:18
    Author     : Jerry Mouse Software
--%>

<%@page import="com.jms.model.Client"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.jms.model.Promotion"%>
<%@page import="com.jms.model.ProductNutriScore"%>
<%@page import="com.jms.model.ProductConditioning"%>
<%@page import="com.jms.model.Label"%>
<%@page import="com.jms.model.Label"%>
<%@page import="com.jms.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Style.css"/>
        <link rel="stylesheet" href="css/style_fav.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">--> 
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <title>Favoris</title>
    </head>
    <body>

        <div class="container-lg">
            <h1>Mes favoris</h1>
            <div class="d-flex justify-content-center mb-4" >

                <%
                    // Get the client who is logged in
                    Client clientFav = (Client) session.getAttribute("client");
                    // Get his/her favorite products
                    ArrayList<Product> listFav = new ArrayList<>(clientFav.getFavoriteProducts());
                %> 

                <!--Carousel Wrapper-->
                <div id="multi-item-example" class="carousel slide carousel-multi-item " data-ride="carousel">

                    <!--Controls-->
                    <a class="carousel-control-prev w-auto" href="#multi-item-example" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon bg-dark border border-dark rounded-circle" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next w-auto" href="#multi-item-example" role="button" data-slide="next">
                        <span class="carousel-control-next-icon bg-dark border border-dark rounded-circle" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                    <!--/.Controls-->

                    <!--Slides-->
                    <div class="carousel-inner " role="listbox">

                        <% // For each slide, create cards of products
                            int max = 4;
                            int nbSlides = (int) Math.floorDiv(listFav.size(), max);
                            System.out.println("nbSlides = "+nbSlides);
                            int reste = listFav.size() % max;
                            int n = 0;
                            for (int i = 0; i < nbSlides; i++) {
                                if (i == nbSlides) max = reste;
                        %>
                        <!--Slide-->
                        <div class="carousel-item  <% if (i == 0) {
                                out.println("active");
                            } %>">
                            <div class="row row-cols-lg-3 row-cols-xl-4 g-4">
                                <%
                                    for (int j = n; j < n + max; j++) {

                                        // Properties of a product
                                        Product product = listFav.get(j);
                                        String url = product.getUrlThumbnail();
                                        String libelle = product.getName();
                                        String price = String.format("%.2f", product.getUnitPrice());
                                        String priceKG = "";
                                        if (product.getKgPrice() != 0f) {
                                            priceKG = String.format("%.2f", product.getKgPrice());
                                        }

                                        String format = product.getFormat();

                                        // Get the conditioning of the product
                                        String conditioningType;
                                        int conditioningVal;
                                        if (product.getPackaging() == ProductConditioning.LOT) {
                                            conditioningType = "lot";
                                            conditioningVal = product.getPackagingQuantity();
                                        } else {
                                            conditioningType = "";
                                            conditioningVal = 1;
                                        }
                                        // Get the nutriscore of the product.
                                        ProductNutriScore nutriscore = product.getNutriscore();

                                        // If there are labels for the product, get their names.
                                        ArrayList<String> labelStrings = new ArrayList<String>();
                                        if (!product.getLabels().isEmpty()) {
                                            for (Label label : product.getLabels()) {
                                                labelStrings.add(label.getDescription());
                                            }
                                        }

                                        // If exists, get the current promotion on the product.  
                                        int place = 1;
                                        String percent = "";
                                        for (Promotion promotion : product.getPromotions().keySet()) {
                                            place = promotion.getRank();
                                            int val = (int) (promotion.getPercentage() * 100);
                                            percent = String.valueOf(val);
                                        }
                                %>

                                <div class="col" >
                                    <div class="card h-100 mb-2">
                                        <img class="img-thumbnail" src="<%= url%>" alt="alt"/>
                                        <div class="card-body">
                                            <a id="addProduct" href="" class="stretched-link"></a> 
                                            <h6 class="card-title "><%= libelle%></h6>
                                            <!-- subtitle -->
                                            <h8 class="card-subtitle mb-1 text-muted">
                                                <% if (priceKG != "") {
                                                        out.print(priceKG + " €/kg");
                                                    }
                                                %>
                                                <% if (format != "") {
                                                        out.print("  " + format + "");
                                                    } %>
                                                <% if (conditioningType != "") {
                                                        if (conditioningType == "lot") {
                                                            out.print(" | " + conditioningType + " de " + conditioningVal);
                                                        } else {
                                                            out.print(" | " + conditioningType);
                                                        }
                                                    }
                                                %>
                                            </h8>
                                            <!-- nutriscore -->
                                            <% if (nutriscore != null) {%>
                                            <div class="d-inline-block mx-2"><img class="img-fluid" width="60px" src="img/Nutri-score-<%= nutriscore%>.svg" alt="alt"/></div>
                                                <% }%>

                                            <!-- labels -->
                                            <% if (!labelStrings.isEmpty()) { %>
                                            <div class="d-inline-flex">
                                                <% for (String labelString : labelStrings) {%>
                                                <div class=" btn btn-outline-warning " disabled ><%= labelString %></div>
                                                <% }%>
                                            </div> 
                                            <% }%>

                                            <!-- promotions -->
                                            <% if (percent != "") {%>
                                            <div class="d-inline-flex ">
                                                <div class="btn btn-outline-danger">
                                                    <%= "-" + percent + "%"%>
                                                    <% String infoPromo = "";
                                                        if (place != 1)
                                                            infoPromo = "sur le " + place + "ème";
                                                    %>
                                                    <%= infoPromo%>
                                                </div>
                                            </div> 
                                            <% }%>

                                        </div>
                                        <!-- footer (price, button) -->
                                        <div class="card-footer text-end">
                                            <h3 class="d-inline-block text-left"><%= price + " €"%></h3>

                                            <h2 class="d-inline-block" name="addButton">
                                                <a href="AddProductServlet?ean=<%= product.getEan()%>" class="">
                                                    <i class="btn btn-primary fas fa-shopping-basket" ></i>
                                                </a>
                                            </h2>
                                        </div>
                                    </div>
                                </div>

                                <% 
                                    }
                                    n += max;
                                %>
                            </div> <!-- end row -->
                        </div>
                        <% }%>
                    </div>
                </div>
            </div>
            <script type="text/javascript" src="js/favorites.js"></script>
    </body>
</html>
