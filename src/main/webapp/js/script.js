/**
 * This function AJAX is use for searching a product or a category.
 */
function getSearchElement() {

    // Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();

    var myinput = document.getElementById("search").value;
    xhr.open("GET", "../CompleteSearchBarServlet?product=" + myinput);

    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            if (myinput != "") {
                suggestions = xhr.responseXML.getElementsByTagName("product");
                elt = document.getElementById("search_result");
                if (suggestions != null && suggestions.length != 0) {
                    elt.style.display = "block";

                    elt.innerHTML = "";

                    var text = "";
                    for (i = 0; i < suggestions.length; i++) {
                        var prod = suggestions[i].firstChild.nodeValue;
                        text += "<li class='list-group-item' class='elements'>" + prod + "</li>";

                        elt.innerHTML = text;

                        var resultSize = document.getElementsByClassName("elements").length;
                        var nodes = document.getElementsByClassName("elements");
                        for (var i = 0; i < resultSize; i++)
                            nodes[i].addEventListener("click", searchProductsBySuggestion(nodes[i]));
                    }
                } else {
                    elt.style.display = "none";
                }

            } else {
                elt.style.display = "none";
            }
        }
    };

    xhr.send();
}

function searchProducts() {
    // Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();

    var myinput = document.getElementById("search").value;
    xhr.open("GET", "../SendSearchRequestServlet?keyword=" + myinput);
    //alert(myinput);
    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            alert(xhr.status);
            suggestions = xhr.responseXML.getElementsByTagName("product");
            elt = document.getElementById("products");
            if (suggestions != null && suggestions.length != 0) {
                elt.innerHTML = "";
                for (i = 0; i < suggestions.length; i++) {
                    produit = suggestions[i].firstChild.nodeValue;
                    //alert(produit);
                    elt.insertAdjacentHTML("beforeend", "<li>" + produit + "</li>");
                }
            } else {
                elt.insertAdjacentHTML("beforeend", "<li>Il n'y a pas de produit correspondant.</li>");
            }
        }
    };
    xhr.send();
}

function searchProductsBySuggestion() {
    var myinput = encodeURIComponent(this.value);
    alert(myinput);
    // Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();
 
    xhr.open("GET", "../SendSearchRequestServlet?keyword=" + myinput);

    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            alert(xhr.status);
            suggestions = xhr.responseXML.getElementsByTagName("product");
            elt = document.getElementById("products");
            if (suggestions != null && suggestions.length != 0) {
                elt.innerHTML = "";
                for (i = 0; i < suggestions.length; i++) {
                    produit = suggestions[i].firstChild.nodeValue;

                    elt.insertAdjacentHTML("beforeend", "<li>" + produit + "</li>");
                }
            } else {
                elt.insertAdjacentHTML("beforeend", "<li>Il n'y a pas de produit correspondant.</li>");
            }
        }
    };
    xhr.send();
}

/**
 * run
 */
document.addEventListener("DOMContentLoaded", () => {

    document.getElementById("search").addEventListener("input", getSearchElement);
    document.getElementById("search_button").addEventListener("click", searchProducts);

});


/**
 * Launch after loading the DOM.
 */
//document.addEventListener("DOMContentLoaded", () => {
//
//    document.getElementById("bt_connect").addEventListener("click", connection);
//});
