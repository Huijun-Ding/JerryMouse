<%@page import="java.util.Date"%>
<%@page import="com.jms.util.DateUtil"%>
<%@page import="com.jms.model.Have"%>
<%@page import="com.jms.model.TimeSlot"%>
<%@page import="com.jms.model.Store"%>
<%@page import="com.jms.model.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%Store s = (Store) request.getSession().getAttribute("store");%>
<%Have h = (Have) request.getSession().getAttribute("have");%>

<header class="shadow p-3 mb-5 bg-body fixed-top">
    <div class="d-flex flex-wrap align-items-center justify-content-around">
        <div class="d-flex flex-column align-items-center justify-content-center">
            <a href="/jm/jsp/index.jsp" class="d-flex align-items-center mb-2 mb-lg-1 text-decoration-none">
                <img id="logo" src="../img/logo_small.png" alt="" class="d-inline-block align-text-top rounded">
            </a>
            <button id="store_button" type="button" class="btn btn-sm btn-outline-primary dropdown-toggle border-0" data-toggle="dropdown">
                <i class="fa fa-shopping-bag"></i>
                <span id="store_name">
                    <%
                        if(s != null) out.print(s.getName());
                        else out.print("Choisir un magasin");
                    %>
                </span>
            </button>
            <button id="time_slot_button" type="button"
                    class="<%if(s == null) out.print("d-none");%>
                    btn btn-sm btn-outline-primary border-0" data-toggle="modal" data-target="#search_time_slots_modal">
                <i class="fa fa-calendar-alt"></i>
                <span id="time_slot_name">
                    <%
                        if(h != null) out.print(DateUtil.dateOfHaveObject(h));
                        else out.print("Choisir un créneau");
                    %>
                </span>
            </button>
            <ul class="dropdown-menu">
                <li class="<%if(s == null) out.print("d-none");%> dropdown-item disabled">
                    <span id="store_street">
                    <%if(s != null) out.print(s.getStreet());%>
                    </span>
                </li>
                <li class="<%if(s == null) out.print("d-none");%> dropdown-item disabled">
                    <span id="store_city">
                    <%if(s != null) out.print(s.getPostalCode() + " " + s.getCity());%>
                    </span>
                </li>
                <li><hr class="<%if(s == null) out.print("d-none");%> dropdown-divider"></li>
                <li class="dropdown-item text-center">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#search_stores_modal">
                        Changer de magasin
                    </button>
                </li>
            </ul>
        </div>

        <div id="search_bar">
            <form>
                <div class="input-group input-group">
                    <input id="search" type="search" class="form-control" placeholder="Rechercher un produit ...">
                    <div class="input-group-append">
                        <button type="button" class="btn btn-primary" id="search_button">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
            </form>
            <div id="output"><ul class="list-group" id="search_result"></ul></div>
        </div>

        <%
            Client client = (Client) session.getAttribute("client");
            if (client != null) {
                out.println("<div>");
                out.println("<h3>");
                out.println("Bonjour " + client.getFirstName());
                out.println("</h3>");
                out.println("</div>");
            }
        %>
        <div class="btn-group ms-3">
            <div class="btn-group">
                <button type="button" class="btn btn-sm btn-outline-primary dropdown-toggle" data-toggle="dropdown">
                    <div class="d-flex flex-column">
                        <i class='far fa-id-badge'></i>
                        Compte
                    </div>
                </button>
                <div class="dropdown-menu">
                    <%if (client == null) {%>
                    <a class="dropdown-item" href="login.jsp"><i class="fa fa-sign-in-alt"></i> Se connecter</a>
                    <a class="dropdown-item" href="signup.jsp"><i class="fa fa-user-plus"></i> S'inscrire</a>
                    <%} else {%>
                    <a class="dropdown-item" href="../Deconnect"><i class="fa fa-sign-out-alt"></i> Se d&eacute;connecter</a>
                    <%}%>
                </div>
            </div>
            <button id="basket_button" role="button" class="btn  btn-sm btn-primary">
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

            <button id="shopping_list_button" role="button" class="btn  btn-sm btn-primary">
                <div class="d-flex flex-column">
                    <i class="far fa-list-alt"></i>
                    Liste de courses
                </div>
            </button>                       
        </div>
    </div>
</header>

<div class="modal" id="search_stores_modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><i class="fa fa-shopping-bag"></i> Changer de magasin</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <div class="modal-body">
                <input id="search_stores" type="search" class="form-control" placeholder="Saisir le code postal ici ...">
                <ul id="stores_list"></ul>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Fermer</button>
            </div>
        </div>
    </div>
</div>
                
<div class="modal" id="search_time_slots_modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><i class="fa fa-calendar-alt"></i> Choisir un créneau</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <select id="search_time_slots" class="form-select" aria-label="Sélectionner la date ici ...">
                    <%
                        for(Date d : DateUtil.nextDays(4))
                            out.println("<option value='" + DateUtil.yearMonthDayFormat(d) + "'>" + DateUtil.allDateInLetters(d) + "</option>");
                    %>
                </select>
                <ul id="time_slots_list"></ul>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Fermer</button>
            </div>
        </div>
    </div>
</div>

