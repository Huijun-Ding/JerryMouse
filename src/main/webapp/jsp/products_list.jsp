<%-- 
    Document   : products_list
    Created on : 22 mars 2021, 16:13:18
    Author     : mlk
--%>

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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">-->
        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">--> 
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <title>Products List</title>
    </head>
    <body>

        <div class="container">
            <h1>Liste des produits</h1>
            <%
                ArrayList<Product> list = (ArrayList<Product>) request.getAttribute("productsList");
                //int nb_product = 5; // number of products to display    

            %> 
            <div class="row">
                <div class = "card-group">
                    <% for (Product product : list) { %>
                    <!--% for (int i = 0; i < nb_product; i++) { %-->
                    <div class="col-sm-6 col-md-4 col-lg-2">
                        <div class="card">
                            <%  // Properties of a product
                                String url = product.getUrlThumbnail();
                                String libelle = product.getName();
                                String price = String.format("%.2f", product.getUnitPrice());
                                String priceKG = String.format("%.2f", product.getKgPrice());
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
                                for (Label label : product.getLabels()) {
                                    labelStrings.add(label.getDescription());
                                }
                                // If exists, get the list of promotions on the product.                                               
                                //                        Map<Integer, Float> promos = new HashMap<Integer, Float>(0);
                                //                        for (Promotion promotion : product.getPromotions().keySet()) {
                                //                            promos.put(promotion.getNieme(), promotion.getPourcentage());
                                //                        }

                                // --- Valeurs pour tester affichage -> Enlever apres
                                //                        url = "img/photo.png";
                                //                        libelle = "Libelle de l'article";
                                //                        price = 3.50f;
                                //                        priceKG = 7.0f;
                                //                        format = "valFormat";
                                //                        conditioningType = "lot";
                                //                        conditioningVal = 2;
                                //                        nutriscore = ProductNutriScore.A;
                                //                        for (int i = 0; i < labelStrings.size(); i++) {
                                //                            labelStrings.remove(i);
                                //                        }
                                //                        labelStrings.add("AOP");
                                //                        labelStrings.add("AB");
                                // --- Enlever
                            %>
                            <img class="card-img-top img-thumbnail" src="<%= url%>" alt="alt"/>
                            <div class="card-body">
                                <a href="#" class="stretched-link"></a> 
                                <h5 class="card-title "><%= libelle%></h5>
                                <h6 class="card-subtitle mb-2 text-muted"><%= priceKG + " €/kg"%>
                                    <% if (format != "") {
                                            out.print(" | " + format + "");
                                        } %>
                                    <% if (conditioningType != "") {
                                            if (conditioningType == "lot") {
                                                out.print(" | " + conditioningType + " de " + conditioningVal);
                                            } else {
                                                out.print(" | " + conditioningType);
                                            }
                                        }
                                    %>
                                </h6>
                                <!-- nutriscore -->
                                <% if (nutriscore != null) {%>
                                <img src="img/Nutri-score-<%= nutriscore%>.svg" width="100px" alt="alt"/>
                                <% }%>

                                <!-- labels -->
                                <div class=""></div> 

                                <!-- promotions -->
                                <div class=""></div> 

                                <footer class="row">
                                    <h3 class="col-lg-9"><%= price + " €"%></h3>

                                    <div class="col-lg-3 ">
                                        <i class="btn btn-primary fas fa-shopping-basket" ></i>
                                    </div>
                                </footer>
                            </div>
                        </div>
                    </div>
                    <% }%>
                </div>
            </div>
        </div>
    </body>
</html>
