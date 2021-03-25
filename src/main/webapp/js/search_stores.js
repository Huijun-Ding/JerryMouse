/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function search_stores() {
    // Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();

    // Requête au serveur avec les paramètres éventuels.
    var param = "postalCode=" + encodeURIComponent(document.getElementById("search_stores").value);
    var url = "../Store" + "?" + param;

    xhr.open("GET", url, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
    xhr.onload = function ()
    {
        // Si la requête http s'est bien passée.
        if (xhr.status === 200) {
            //document.getElementById("stores_list").innerHtml = "";
            var htmlText = "";
            var stores = xhr.responseXML.getElementsByTagName("store");

            for (var i = 0; i < stores.length; i++) {
                var name = stores[i].getElementsByTagName("name")[0].firstChild.nodeValue;
                var street = stores[i].getElementsByTagName("street")[0].firstChild.nodeValue;
                var city = stores[i].getElementsByTagName("city")[0].firstChild.nodeValue;
                var postalCode = stores[i].getElementsByTagName("postalCode")[0].firstChild.nodeValue;
                
                htmlText += '<div class="accordion-item">\n';
                htmlText += '<h2 class="accordion-header" id="headingOne">\n';
                htmlText += '<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">\n';
                htmlText += name + "\n";
                htmlText += '</button>\n';
                htmlText += '</h2>';
                htmlText += '<div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">\n';
                htmlText += '<div class="accordion-body">\n';
                htmlText += '<p>' + street +'</p>\n';
                htmlText += '<p>' + city +'</p>\n';
                htmlText += '<p>' + postalCode +'</p>\n';
                htmlText += '</div>\n';
                htmlText += '</div>\n';
                htmlText += '</div>\n';
            }
            
            document.getElementById("stores_list").innerHTML = htmlText;
        }
    };

    // Envoie de la requête.
    xhr.send(param);
}

/**
 * Lancement après le chargement du DOM.
 */
document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("search_stores").addEventListener("input", search_stores);
});