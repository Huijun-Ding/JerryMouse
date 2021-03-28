/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function displayStore() {
    alert("test1");
    // Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();

    // Requête au serveur avec les paramètres éventuels.
    xhr.open("GET", "../CheckLogin");

    // On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
    xhr.onload = function ()
    {
        // Si la requête http s'est bien passée.
        if (xhr.status === 200)
        {
            // Elément html que l'on va mettre à jour.
            alert("test2");
            var idClients = xhr.responseXML.getElementsByTagName("idClient");
            if (idClients[0].firstChild.nodeValue == "no") {
                window.location.href = "../login";
                
            } else {
                document.getElementById("view").src = "../basketPage";
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
