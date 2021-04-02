function displayCreateShoppingList() {
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

            }
        }
    };

    // Envoie de la requête.
    xhr.send();
}

document.addEventListener("DOMContentLoaded", () => {
    if (window.addEventListener) {
        window.addEventListener('load', displayCreateShoppingList); //W3C
    } else {
        window.attachEvent('onload', displayCreateShoppingList); //IE
    }
});