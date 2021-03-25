
/**
 * Cette méthode "Ajax" simule la zone de recherche 'Google'.
 */
function processKey()
{
    // Requête au serveur avec les paramètres éventuels.
    var mot = encodeURIComponent(document.getElementById("saisie").value);
    var elt = document.getElementById("zoneaff");
    
    elt.innerHTML = "";
    
    if (mot === "") {
        elt.style.display = "none";
    } else {
        // Objet XMLHttpRequest.
        var xhr = new XMLHttpRequest();

        xhr.open("GET", "ServletMot?method=saisie&mot=" + encodeURI(mot), true);

        // On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
        xhr.onload = function ()
        {
            // Si la requête http s'est bien passée.
            if (xhr.status === 200)
            {
                // Elément html que l'on va mettre à jour.
                var response = xhr.responseXML.getElementsByTagName("mot");

                if(response.length === 0) elt.style.display = "none";
                else elt.style.display = "block";
                
                var text = "";
                for (var i = 0; i < response.length; i++) {
                    var tmp = response[i].firstChild.nodeValue;
                    text += "<a class='motsrecherches'>" + tmp + "</a>";
                }

                elt.innerHTML = text;
                
                var nbMotsRecherches = document.getElementsByClassName("motsrecherches").length;
                var nodes = document.getElementsByClassName("motsrecherches");
                for(var i = 0 ; i < nbMotsRecherches ; i++)
                    nodes[i].addEventListener("click", supprimerMot(nodes[i]));
            }
        };

        // Envoie de la requête.
        xhr.send();
    }
}

/**
 * Cette méthode "Ajax" permet de supprimer un mot dans la BD.
 */
function supprimerMot() {
    // Requête au serveur avec les paramètres éventuels.
    var mot = encodeURIComponent(this.value);

    alert("Demande de suppression de " + this.value + " !");

    if (mot !== "") {
        // Objet XMLHttpRequest.
        var xhr = new XMLHttpRequest();

        xhr.open("GET", "ServletMot?method=supprimer&mot=" + mot, true);

        // On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
        xhr.onload = function ()
        {
            // Si la requête http s'est bien passée.
            if (xhr.status === 200)
            {
                var msg = xhr.responseXML.getElementsByTagName("msg")[0].firstChild.value;
                if(msg === "'" + this.value + "' supprimé avec succès !")
                    this.parentNode.removeChild(this);
            }
        };

        // Envoie de la requête.
        xhr.send();
        
        compterMot();
    }
}

