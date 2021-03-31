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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/Style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <title>Products List</title>
    </head>
    <body>

        <div class="container-lg">
            <!--<h1>Liste des produits</h1>-->
            <div class="d-flex justify-content-center" >

                <%
                    ArrayList<Product> list = (ArrayList<Product>) request.getAttribute("productsList");
                    request.setAttribute("productsList", list);
                    Client client = (Client) session.getAttribute("client");
                    session.setAttribute("client", client);

                %> 
                <div class="row row-cols-sm-1 row-cols-md-1 row-cols-lg-3 row-cols-xl-4 g-4">
                    <% for (Product product : list) { %>
                    <div class = "col">
                        <div class="card h-100" >
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
                            <img class="img-thumbnail" src="<%= url%>" alt="alt"/>
                            <div class="card-body">
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
                                <div><img src="img/Nutri-score-<%= nutriscore%>.svg" width="50px" alt="alt"/></div>
                                    <% }%>

                                <!-- labels -->
                                <% if (!labelStrings.isEmpty()) { %>
                                <div class="row">
                                    <% for (String labelString : labelStrings) {%>
                                    <div class="btn btn-outline-warning"><%= labelString + " LABEL_HERE "%></div>
                                    <% }%>
                                </div> 
                                <% }%>

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

                                <h3 class="d-inline-block text-left"><%= price + " €"%></h3>

                                <h2 class="d-inline-block text-right" name="addButton">
                                    <a href="AddProductServlet?ean=<%= product.getEan()%>" class="btn btn-primary ">
                                        <i class="fas fa-shopping-basket" ></i>
                                    </a>
                                </h2>
                                <button class="btn btn-primary" data-toggle="modal" data-target="#visualise_article">
                                    <i class="fas fa-eye"></i>
                                </button>

                            </div>
                        </div>
                    </div>
                    <% }%>

                </div>
            </div>
        </div>
                    
        <div class="modal" tabindex="-1" id="visualise_article">
            <div class="modal-dialog modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-1 col-lg-3 col-xl-6">
                                    <!--image-->
                                    <div>
                                        <img class="img-thumbnail" src="" alt="alt" />
                                    </div>
                                </div>
                                <div class="col-md-1 col-lg-3 col-xl-6">
                                    <!--detail-->
                                    <div class="container">
                                        <div class="row">
                                            <h4>Libellé</h4>
                                            <span>Marque</span>
                                            <span>Conditionnement</span>
                                            <img src="" alt="Nutriscore">
                                            <div>
                                                <span class="btn btn-outline-danger">Promo</span>
                                                <h5 class="d-inline-block text-left">Prix €</h5>
                                                <a href="AddProductServlet?ean=" class="btn btn-primary">
                                                    <i class="fas fa-shopping-basket" ></i>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>



                        <!--description-->
                        <!--composition-->
                        <!--nutritional values-->
                        <div></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>

                    </div>
                </div>
            </div>
        </div>
        
        <script type="text/javascript" src="js/quantityProduct.js"></script>
    </body>
</html>
