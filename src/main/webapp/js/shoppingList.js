function displayShoppingLists() {
    // relocated to page myShoppingLists
    window.location.href = "myShoppingLists";
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

document.addEventListener("DOMContentLoaded", () => {
    var btn = document.getElementById("shopping_list_button");
    if (btn !== null) {
        btn.addEventListener("click", displayShoppingLists);
    }

    var el = document.getElementById("add_sl");
    if (el !== null) {
        el.addEventListener("click", sendShoppingListName);
    }
});