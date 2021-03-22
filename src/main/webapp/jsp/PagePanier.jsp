<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mon panier</title>
    </head>
    <body>
     <div class="en_tete" name="en_tete">
         < img src="">
         <button id="se_connecter">Se connecter</button>
        </div>
        
        <h1>Mon panier</h1>


        <div class="lst_prod" name="div_prod">
         <table name="tab_prod">
          <%

          %>
         </table>
        </div>

        <div name="calcul">
         <table name="tab_calcul">
          <tr>
           <td>Ganotte gagn&eacute;: <span id="ganotte_gagne"></span></td>
           <td>Ganotte cumul: <span id="ganotte_cumul"></span></td>
           <td>Total: <span id="total"></span></td>
          </tr>
         </table>
        </div>

        <div name="bouton">
         <input type="submit" name="valider" value="Valider">
         <button id="retour">Retour</button>
        </div>

    </body>
</html>