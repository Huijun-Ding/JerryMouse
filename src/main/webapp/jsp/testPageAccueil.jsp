<%-- 
    Document   : testPageAccueil
    Created on : 24 mars 2021, 16:03:47
    Author     : Jerry Mouse Software.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <%out.println("<a href='../CtrlCentral?method=panier&idClient=1'>panier</a>");%>
<!--        <form action="../checkBasketServlet">
            <input id="ok" type="submit" value="ok">
        </form>
        <iframe src="basket_page.jsp" name="basket_page" style="display:none"></iframe>
        <script type="text/JavaScript" src="js/ScriptCheckBasket.js"></script>-->
    </body>
</html>
