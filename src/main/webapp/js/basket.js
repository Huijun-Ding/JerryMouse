/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function displayStore() {
    // Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();

    // Requête au serveur avec les paramètres éventuels.
    xhr.open("GET", "CheckLogin");

    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            var idClients = xhr.responseXML.getElementsByTagName("idClient");
            if (idClients[0].firstChild.nodeValue === "no") {
                window.location.href = "login";
                
            } else {
                window.location.href = "basketPage";
                document.getElementById("search_bar").style.display = "none";
                document.getElementById("rayon_categorie_navbar").style.display = "none";
                document.getElementById("view").style.marginTop = "120px";
            }
        }
    };

    // Envoie de la requête.
    xhr.send();

}

document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("basket_button").addEventListener("click", displayStore);
});
