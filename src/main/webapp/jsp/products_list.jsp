<%-- 
    Document   : products_list
    Created on : 22 mars 2021, 16:13:18
    Author     : Jerry Mouse Software
--%>

<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
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

        <% if ((session.getAttribute("client") != null) && request.getParameterMap().containsKey("home")) { %>
        <% Client client = (Client) session.getAttribute("client");
            if (!client.getFavoriteProducts().isEmpty()) {%>
        <%@include file="favorites.jsp" %>
        <% }
            } %>

        <div class="container-lg">
            <h1><% if (request.getParameterMap().containsKey("home"))
                    out.println("Promotions"); %></h1>
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
                                String ean = product.getEan();
                                String url = product.getUrlThumbnail();
                                String libelle = product.getName();
                                String marque = product.getBrand();
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
                                <a href="#" class ="stretched-link" data-toggle="modal" data-target="#<%=ean%>"></a>
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
                                <div class="d-inline-block ">
                                    <% for (String labelString : labelStrings) {%>
                                    <div class="btn btn-outline-warning"><%= labelString + " LABEL_HERE "%></div>
                                    <% }%>
                                </div> 
                                <% }%>

                                <!-- promotions -->
                                <% if (percent != "") {%>
                                <div class="d-inline-block ">
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
                            <div class="card-footer text-right" style="z-index:100">

                                <h3 class="d-inline-block text-left"><%= price + " €"%></h3>

                                <h2 class="d-inline-block text-right" name="addButton">
                                    <a href="AddProductServlet?ean=<%= product.getEan()%>" class="btn btn-primary ">
                                        <i class="fas fa-shopping-basket" ></i>
                                    </a>
                                </h2>
                            </div>
                        </div>
                    </div>
                    <% }%>
                </div>
            </div>
        </div>


        <%for (Product p : list) {
                String ean = p.getEan();
                // Properties of a product
                String url = p.getUrlThumbnail();
                String libelle = p.getName();
                String marque = p.getBrand();
                String price = String.format("%.2f", p.getUnitPrice());
                String priceKG = "";
                String description = p.getDescription();
                String composition = p.getComposition();
                String energie = p.getEnergy();
                String mg = p.getFats();
                String ags = p.getSaturatedFatAcids();
                String glucides = p.getCarbohydrates();
                String sucre = p.getSugar();
                String proteines = p.getProtein();
                String sel = p.getSalt();

                if (p.getKgPrice() != 0f) {
                    priceKG = String.format("%.2f", p.getKgPrice());
                }

                String format = p.getFormat();

                // Get the conditioning of the product
                String conditioningType;
                int conditioningVal;
                if (p.getPackaging() == ProductConditioning.LOT) {
                    conditioningType = "lot";
                    conditioningVal = p.getPackagingQuantity();
                } else {
                    conditioningType = "";
                    conditioningVal = 1;
                }
                // Get the nutriscore of the product.
                ProductNutriScore nutriscore = p.getNutriscore();

                // If there are labels for the product, get their names.
                ArrayList<String> labelStrings = new ArrayList<String>();
                if (!p.getLabels().isEmpty()) {
                    for (Label label : p.getLabels()) {
                        labelStrings.add(label.getDescription());
                    }
                }

                // If exists, get the current promotion on the product.  
                int place = 1;
                String percent = "";
                for (Promotion promotion : p.getPromotions().keySet()) {
                    place = promotion.getRank();
                    int val = (int) (promotion.getPercentage() * 100);
                    percent = String.valueOf(val);
                }
        %>
        <div class="modal" tabindex="-1"  id="<%=ean%>">
            <div class="modal-dialog modal-sm modal-lg modal-dialog-scrollable" width="900px">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="container">
                            <div class="row mb-3">
                                <div class="col h-100">
                                    <!--image-->
                                    <div>
                                        <img class="img-thumbnail" src="<%= url%>" alt="alt" />
                                    </div>
                                </div>
                                <div class="col h-100">
                                    <!--libelle-->
                                    <h4><%=libelle%></h4>
                                    <!--brand-->
                                    <h5><%=marque%></h5>
                                    <div>
                                        <!-----Description&Composition------->
                                        <h6>Description</h6>
                                        <span><%=description%></span>
                                    </div>
                                    <!-------ConditionType------>
                                    <div class="text-muted">
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
                                    </div>
                                    <div>
                                        <!--nuttriscore-->
                                        <% if (nutriscore != null) {%>
                                        <img src="img/Nutri-score-<%= nutriscore%>.svg" width="60px" alt="Nutriscore">
                                        <%}%>
                                    </div>  
                                    <!-- labels -->
                                    <% if (!labelStrings.isEmpty()) { %>
                                    <div class="d-inline-block ">
                                        <% for (String labelString : labelStrings) {%>
                                        <div class="btn btn-outline-warning"><%= labelString + " LABEL_HERE "%></div>
                                        <% }%>
                                    </div> 
                                    <% }%>
                                    <!--Promotion-->
                                    <% if (percent != "") {%>
                                    <div class="btn btn-outline-danger">
                                        <%= "-" + percent + "%"%>
                                        <% String infoPromo = "";
                                            if (place != 1)
                                                infoPromo = "sur le " + place + "ème";
                                        %>
                                        <%= infoPromo%>
                                    </div>
                                    <% }%>
                                    <div class="text-right">
                                        <!--PriceUnit-->
                                        <h5 class="d-inline-block"><%= price + " €"%></h5>
                                        <a href="AddProductServlet?ean=<%=ean%>" class="btn btn-primary">
                                            <i class="fas fa-shopping-basket" ></i>
                                        </a>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col">
                                    <div>
                                        <h5>Composition</h5>
                                        <%=composition%>
                                    </div>
                                </div>

                                <!-----Nutritional values------->
                                <div class="col">
                                    <table class="table-responsive-sm-md-lg table table-striped">
                                        <thead> 
                                        <h5>Valeurs nutritionnelles (pour 100g)</h5>
                                        </thead>
                                        <tr>
                                            <td>Energie (KJ)/(kcal)</td>
                                            <td><%=energie%></td>
                                        </tr>
                                        <tr>
                                            <td>Mati&egrave;res grasses (g)</td>
                                            <td><%=mg%></td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;&nbsp;&nbsp;&nbsp;dont acides gras saturés (g)</td>
                                            <td><%=ags%></td>
                                        </tr>
                                        <tr>
                                            <td>Glucides (g)</td>
                                            <td><%=glucides%></td>
                                        </tr>
                                        <tr>
                                            <td class="">&nbsp;&nbsp;&nbsp;&nbsp;dont sucres(g)</td>
                                            <td><%=sucre%></td>
                                        </tr>
                                        <tr>
                                            <td>Prot&eacute;ines (g)</td>
                                            <td><%=proteines%></td>
                                        </tr>
                                        <tr>
                                            <td>Sel(g)</td>
                                            <td><%=sel%></td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                    </div>
                </div>

            </div>
        </div>
        <%}%>
        <script type="text/javascript" src="js/quantityProduct.js"></script>
    </body>
</html>
