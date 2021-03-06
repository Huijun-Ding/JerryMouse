<%@page import="java.util.Date"%>
<%@page import="com.jms.util.DateUtil"%>
<%@page import="com.jms.model.Have"%>
<%@page import="com.jms.model.TimeSlot"%>
<%@page import="com.jms.model.Store"%>
<%@page import="com.jms.model.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%Store s = (Store) request.getSession().getAttribute("store");%>
<%Have h = (Have) request.getSession().getAttribute("have");%>

<nav class="navbar navbar-expand-lg navbar-light bg-body shadow fixed-top">
    <div class="container-fluid">
        <a href="index" class="mb-lg-1 text-decoration-none navbar-brand">
            <img id="logo" src="img/logo_small.png" alt="" class="d-inline-block align-text-top rounded">
        </a>
        <div class="d-flex flex-column">
            <button id="store_button" type="button" class="btn btn-sm btn-outline-primary dropdown-toggle border-0" data-toggle="dropdown">
                <i class="fa fa-shopping-bag"></i>
                <span id="store_name">
                    <%
                        if (s != null) {
                            out.print(s.getName());
                        } else {
                            out.print("Choisir un magasin");
                        }
                    %>
                </span>
            </button>
            <ul class="dropdown-menu">
                <li class="<%if (s == null) {
                        out.print("d-none");
                    }%> dropdown-item disabled">
                    <span id="store_street">
                        <%if (s != null) {
                                out.print(s.getStreet());
                            }%>
                    </span>
                </li>
                <li class="<%if (s == null) {
                        out.print("d-none");
                    }%> dropdown-item disabled">
                    <span id="store_city">
                        <%if (s != null) {
                                out.print(s.getPostalCode() + " " + s.getCity());
                            }%>
                    </span>
                </li>
                <li><hr class="<%if (s == null) {
                        out.print("d-none");
                    }%> dropdown-divider"></li>
                <li class="dropdown-item text-center">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#search_stores_modal">
                        Changer de magasin
                    </button>
                </li>
            </ul>
            <button id="time_slot_button" type="button"
                    class="<%if (s == null) {
                            out.print("d-none");
                        }%>
                    btn btn-sm btn-outline-primary border-0" data-toggle="modal" data-target="#search_time_slots_modal">
                <i class="fa fa-calendar-alt"></i>
                <span id="time_slot_name">
                    <%
                        if (h != null) {
                            out.print(DateUtil.dateOfHaveObject(h));
                        } else {
                            out.print("Choisir un cr??neau");
                        }
                    %>
                </span>
            </button>
        </div>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#toggler" aria-controls="toggler" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div id="toggler" class="collapse navbar-collapse">
            <div class="d-flex align-items-center justify-content-around my-4 ms-auto">
                <div id="search_bar">
                    <form>
                        <div class="input-group">
                            <input id="search" type="search" class="form-control" placeholder="Rechercher un produit ...">
                            <div class="input-group-append">
                                <button type="button" class="btn btn-primary h-100" id="search_button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                    <div id="output"><ul class="list-group" id="search_result"></ul></div>
                </div>

                <%
                    Client client = (Client) session.getAttribute("client");
                    String initial = (client != null) ? client.getFirstName().toUpperCase().substring(0, 1) + client.getLastName().toUpperCase().substring(0, 1) : null;
                %>

                <div class="btn-group ms-3">
                    <div class="btn-group">
                        <button type="button" class="btn btn-sm btn-outline-primary dropdown-toggle" data-toggle="dropdown">
                            <div class="d-flex flex-column">
                                <i class='far fa-id-badge'></i>
                                <%
                                    if (client != null) {
                                        out.print(initial);
                                    } else {
                                        out.print("Compte");
                                    }
                                %>
                            </div>
                        </button>
                        <div class="dropdown-menu">
                            <%if (client == null) {%>
                            <a class="dropdown-item" href="login"><i class="fa fa-sign-in-alt"></i> Se connecter</a>
                            <a class="dropdown-item" href="#"><i class="fa fa-user-plus"></i> S'inscrire</a>
                            <%} else {%>
                            <a class="dropdown-item" href="Deconnect"><i class="fa fa-sign-out-alt"></i> Se d&eacute;connecter</a>
                            <%}%>
                        </div>
                    </div>
                    <button id="basket_button" role="button" class="btn btn-sm btn-outline-primary">
                        <div class="d-flex flex-column">
                            <i class="fa fa-shopping-basket"></i>
                            Panier
                            <!--<span id='nb_products'></span>-->
                            <span id="nb_products" class="badge badge-light"></span>
                            <!-- comment 
                                Object nbProducts = session.getAttribute("nbProducts");
        //                                HttpSession session = request.getSession(true);
                                 if (nbProducts != null) {
                                    out.println("<span id='nb_product'>" + (int)nbProducts + "</span>");
                                }
                            -->

                        </div>
                    </button>

                    <%if (client != null) {%>
                    <button id="shopping_list_button" role="button" class="btn btn-sm btn-outline-primary" href>
                        <div class="d-flex flex-column">
                            <i class="far fa-list-alt"></i>
                            Liste de courses
                        </div>
                    </button> 
                    <%}%>
                </div>
            </div>
        </div>
    </div>
</nav>

<div class="modal" id="search_stores_modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><i class="fa fa-shopping-bag"></i> Changer de magasin</h4>
                <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body">
                <input id="search_stores" type="search" class="form-control form-control-lg" placeholder="Saisir le code postal ici ...">
                <ul id="stores_list"></ul>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Fermer</button>
            </div>
        </div>
    </div>
</div>

<div class="modal" id="search_time_slots_modal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><i class="fa fa-calendar-alt"></i> Choisir un cr??neau</h4>
                <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <select id="search_time_slots" class="form-select form-select-lg" aria-label="S??lectionner la date ici ...">
                    <option selected>S??lectionner la date ici ...</option>
                    <%
                        for (Date d : DateUtil.nextDays(4))
                            out.println("<option value='" + DateUtil.yearMonthDayFormat(d) + "'>" + DateUtil.allDateInLetters(d) + "</option>");
                    %>
                </select>
                <div class="mt-4 d-flex justify-content-between flex-wrap" id="time_slots_list"></div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Fermer</button>
            </div>
        </div>
    </div>
</div>

<script src="js/shoppingList.js"></script>
<script src="js/search.js"></script>
