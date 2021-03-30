function displayShoppingLists() {
    // relocated to page myShoppingLists
    window.location.href = "myShoppingLists";
}

function displayCreateShoppingList() {
    // relocated to page createShoppingList
    window.location.href = "createShoppingList";
}

function sendShoppingListName() {
    // Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();

    var myinput = document.getElementById("name_shopping_list").value;
    xhr.open("GET", "NewShoppingListServlet?name=" + myinput);

    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            window.location.href = "myShoppingLists";
        }
    };

    xhr.send();
}

function showMyLists() {
    // Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();

    xhr.open("GET", "NewShoppingListServlet");

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
                
                elt.insertAdjacentHTML("beforeend", "<a href='showShoppingList" + code +" class='list-group-item list-group-item-action'>" + name +"</a>");
            }
        }
    };

    xhr.send();
}

document.addEventListener("DOMContentLoaded", () => {
    var btn = document.getElementById("shopping_list_button");
    if (btn != null) {
        btn.addEventListener("click", displayShoppingLists);
    }

    var sl = document.getElementById("add_new_sl");
    if (sl != null) {
        sl.addEventListener("click", displayCreateShoppingList);
    }

    var el = document.getElementById("add_sl");
    if (el != null) {
        el.addEventListener("click", sendShoppingListName);
    }

    document.getElementById("my_lists").addEventListener("load", showMyLists);
});