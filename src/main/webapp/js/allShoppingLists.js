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

            for (i = 0; i < suggestions.length; i++) {
                shoppingListLine = suggestions[i];

                code = shoppingListLine.getElementsByTagName("code")[0].firstChild.nodeValue;
                name = shoppingListLine.getElementsByTagName("name")[0].firstChild.nodeValue;

                elt.insertAdjacentHTML("beforeend", "<a href='showShoppingList?codesl=" + code + "' class='list-group-item list-group-item-action'>" + name + "</a>");
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
        window.addEventListener('load', showMyLists); //W3C
    } else {
        window.attachEvent('onload', showMyLists); //IE
    }
});


