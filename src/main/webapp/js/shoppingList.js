function displayShoppingLists() {
    // Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();

    xhr.open("GET", "CheckLogin");

    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            var idClients = xhr.responseXML.getElementsByTagName("idClient");
            if (idClients[0].firstChild.nodeValue === "no") {
                window.location.href = "login";
                
            } else {
                document.getElementById("view").src = "basketPage";
                document.getElementById("search_bar").style.display = "none";
                document.getElementById("rayon_categorie_navbar").style.display = "none";
                document.getElementById("view").style.marginTop = "120px";
            }
        }
    };

    xhr.send();
}



document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("shopping_list_button").addEventListener("click", displayShoppingList);
});