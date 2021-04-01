function displayShowShoppingList() {
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

function showPostIts() {
// Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();

    var myinput = window.location.search.substr(8);
    xhr.open("GET", "DisplayPostItServlet?id=" + myinput);

    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            postits = xhr.responseXML.getElementsByTagName("postIt");
            if (postits !== "") {
                elt = document.getElementById("show_postit");
                elt.innerHTML = "";
                for (i = 0; i < postits.length; i++) {
                    postItLine = postits[i];

                    code = postItLine.getElementsByTagName("code")[0].firstChild.nodeValue;
                    name = postItLine.getElementsByTagName("name")[0].firstChild.nodeValue;
                    pname = postItLine.getElementsByTagName("pname")[0].firstChild.nodeValue;
                    pbrand = postItLine.getElementsByTagName("pbrand")[0].firstChild.nodeValue;
                    pformat = postItLine.getElementsByTagName("pformat")[0].firstChild.nodeValue;

                    if (name === "" || name === "null") {
                        elt.insertAdjacentHTML("beforeend", "<li class='list-group-item' value='" + code + "'>" + pname + " " + pbrand + " " + pformat + "</i>");
                    } else {
                        elt.insertAdjacentHTML("beforeend", "<li class='list-group-item' value='" + code + "'>" + name + "</i>");
                    }
                }
            }
        }
    };
    xhr.send();
}

function showShoppingListName() {
    var xhr = new XMLHttpRequest();

    var myinput = window.location.search.substr(8);
    xhr.open("GET", "GetShoppingListName?id=" + myinput);

    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            var nameSL = xhr.responseXML.getElementsByTagName("Shoppinglist")[0];
            var name = nameSL.getElementsByTagName("name")[0].firstChild.nodeValue;
            document.getElementById("list_name").innerHTML = "<h2>" + name + "</h2>";
        }
    };
    xhr.send();
}

function sendAddPostItRequest() {

    var id = window.location.search.substr(8);
    var name = document.getElementById("input_post_it").value;

    if (name !== "") {
        var xhr = new XMLHttpRequest();
    }

    xhr.open("GET", "AddPostItServlet?id=" + id + "&name=" + name);

    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            alert("Post-it ajouté !");
            window.location.reload();
        }
    };
    xhr.send();
}

function sendAddPostItProductRequest() {
    var xhr = new XMLHttpRequest();

    var idsl = window.location.search.substr(8);
    var idp = this.firstChild.nodeValue;;

    if (idp !== "") {
        xhr.open("GET", "AddPostItProductServlet?idsl=" + idsl + "&idp=" + idp);
    }

    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            alert("Produit ajouté !");
            window.location.reload();
        }
    };
    xhr.send();
}

function showProducts() {
// Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();

    var myinput = document.getElementById("search_products").value;
    xhr.open("GET", "GetPostItProductServlet?name=" + myinput);

    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            lst = xhr.responseXML.getElementsByTagName("product");
            if (lst !== "") {
                elt = document.getElementById("products_list");
                elt.innerHTML = "";
                for (i = 0; i < lst.length; i++) {
                    p = lst[i];

                    code = p.getElementsByTagName("code")[0].firstChild.nodeValue;
                    name = p.getElementsByTagName("name")[0].firstChild.nodeValue;
                    brand = p.getElementsByTagName("brand")[0].firstChild.nodeValue;
                    format = p.getElementsByTagName("format")[0].firstChild.nodeValue;

                    elt.insertAdjacentHTML("beforeend", "<li class='list-group-item'>" + name + " " + brand + " " + format + " " + "<br><p class='text-end'><button type='button' class='btn btn-primary product_select' value='" + code + "'>sélectionner</button></p></li>");
                }

                var product_select_buttons = document.getElementsByClassName("product_select");
                for (let i = 0; i < product_select_buttons.length; i++) {
                    product_select_buttons[i].addEventListener('click', sendAddPostItProductRequest);
                }    
                    
            } else {
               elt.innerHTML = "Pas de produit correspondant";
           }
        }
    };
    xhr.send();
}

document.addEventListener("DOMContentLoaded", () => {
    if (window.addEventListener) {
        window.addEventListener('load', displayShowShoppingList); //W3C
        window.addEventListener('load', showPostIts); //W3C
        window.addEventListener('load', showShoppingListName); //W3C
    } else {
        window.attachEvent('onload', displayShowShoppingList); //IE
        window.attachEvent('onload', showPostIts); //IE
        window.attachEvent('onload', showShoppingListName);
    }

    var pt = document.getElementById("submit_post_it");
    if (pt !== null) {
        pt.addEventListener("click", sendAddPostItRequest);
    }

    var pt = document.getElementById("search_products");
    if (pt !== null) {
        pt.addEventListener("input", showProducts);
    }

});