function displayMyShoppingLists() {
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

function showMyLists() {
    // Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();

    xhr.open("GET", "DisplayAllShoppingLists");

    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            suggestions = xhr.responseXML.getElementsByTagName("shoppingList");
            elt = document.getElementById("my_lists");
            elt.innerHTML = "";

            if (suggestions !== "") {
                for (i = 0; i < suggestions.length; i++) {
                    shoppingListLine = suggestions[i];

                    code = shoppingListLine.getElementsByTagName("code")[0].firstChild.nodeValue;
                    name = shoppingListLine.getElementsByTagName("name")[0].firstChild.nodeValue;

                    elt.insertAdjacentHTML("beforeend", "<a href='showShoppingList?codesl=" + code + "' class='list-group-item list-group-item-action'>" + name + "</a>");
                }
            }
        }
    };

    xhr.send();
}

function displayCreateShoppingList() {
    // relocated to page createShoppingList
    window.location.href = "createShoppingList";
}

document.addEventListener("DOMContentLoaded", () => {

    var sl = document.getElementById("add_new_sl");
    if (sl !== null) {
        sl.addEventListener("click", displayCreateShoppingList);
    }

    if (window.addEventListener) {
        window.addEventListener('load', displayMyShoppingLists); //W3C
        window.addEventListener('load', showMyLists); //W3C
    } else {
        window.attachEvent('onload', displayMyShoppingLists); //IE
        window.attachEvent('onload', showMyLists); //IE
    }
});


