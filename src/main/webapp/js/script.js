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
            //alert("ok")
            if (myinput != "") {
                suggestions = xhr.responseXML.getElementsByTagName("product");
                elt = document.getElementById("search_result");
                if (suggestions != null && suggestions.length != 0) {
                    elt.style.display = "block";

                    elt.innerHTML = "";

                    for (i = 0; i < suggestions.length; i++) {
                        prod = suggestions[i].firstChild.nodeValue;
                        elt.insertAdjacentHTML("beforeend", "<li class='list-group-item item'>" + prod + "</li>");
                    }

                    var items = document.getElementsByClassName("item");
                    for (var i = 0; i < items.length; i++) {
                       // console.log("items[" + i + "].addEventListener('click', searchProductsBySuggestion(" + items[i] + "))");
                        items[i].addEventListener('click', searchProductsBySuggestion);
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
    alert(myinput);
    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            document.getElementById('view').src = "../SendSearchRequestServlet?keyword=" + myinput;
        }
    };
    xhr.send();
}

function searchProductsBySuggestion() {
    var myinput = this.firstChild.nodeValue;
    //console.log(myinput + "----clicked----" + this.value);
    alert(this.firstChild.nodeValue);

    // Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();

    xhr.open("GET", "../SendSearchRequestServlet?keyword=" + myinput);

    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            document.getElementById('view').src = "../SendSearchRequestServlet?keyword=" + myinput;
        }
    };
    xhr.send();
}

/**
 * run
 */
document.addEventListener("DOMContentLoaded", () => {
    //test: Glaces et desserts glacés
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
