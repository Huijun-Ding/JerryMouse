<%-- 
    Document   : products_list
    Created on : 22 mars 2021, 16:13:18
    Author     : mlk
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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">--> 
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <script src="js/quantityProduct.js"></script>
        <title>Products List</title>
    </head>
    <body>

        <div class="container">
            <h1>Liste des produits</h1>
            <%
                ArrayList<Product> list = (ArrayList<Product>) request.getAttribute("productsList");
                request.setAttribute("productsList", list);
                Client client = (Client)session.getAttribute("client");
                session.setAttribute("client", client);
                //int nb_product = 5; // number of products to display    

            %> 
            <div class="">
                <div class = "card-deck">
                    <% for (Product product : list) { %>
                    <!--% for (int i = 0; i < nb_product; i++) { %-->
                    <!--div class="col-sm-6 col-md-4 col-lg-2"-->
                    <div class="card mb-4 " style="min-width:15rem; max-width: 15rem;">
                        <%  // Properties of a product
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
                        <img class="card-img-top img-thumbnail" src="<%= url%>" alt="alt"/>
                        <div class="card-body">
                            <a id="addProduct" href="AddProductServlet?ean=<%= product.getEan() %>" class="stretched-link"></a> 
                            <h6 class="card-title "><%= libelle%></h6>
                            <!-- subtitle -->
                            <h7 class="card-subtitle mb-2 text-muted">
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
                            </h7>
                            <!-- nutriscore -->
                            <% if (nutriscore != null) {%>
                            <div><img src="img/Nutri-score-<%= nutriscore%>.svg" width="50px" alt="alt"/></div>
                                <% }%>

                            <!-- labels -->
                            <div class="row">
                                <% for (String labelString : labelStrings) {%>
                                <div class="btn btn-outline-warning"><%= labelString + " LABEL_HERE "%></div>
                                <% }%>
                            </div> 

                            <!-- promotions -->
                            <% if (percent != "") {%>
                            <div class="">
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
                        <div class="card-footer text-right">
                            <h3 class=""><%= price + " €"%></h3>

                            <div class="" name="addButton">
                                <a href="AddProductServlet?ean=<%= product.getEan() %>" class="">
                                <i class="btn btn-primary fas fa-shopping-basket" ></i>
                                </a>
                            </div>
                        </div>
                    </div>
                    <!--/div-->
                    <% }%>
                </div>
            </div>
        </div>
    </body>
</html>
