/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function search_stores() {
    // Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();
    // Requête au serveur avec les paramètres éventuels.
    var param = "postalCode=" + encodeURIComponent(document.getElementById("search_stores").value);
    var url = "Store" + "?" + param;
    xhr.open("GET", url, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
    xhr.onload = function ()
    {
        // Si la requête http s'est bien passée.
        if (xhr.status === 200) {
            //document.getElementById("stores_list").innerHtml = "";
            var htmlText = "";
            var stores = xhr.responseXML.getElementsByTagName("store");
            for (var i = 0; i < stores.length; i++) {
                var id = stores[i].getElementsByTagName("id")[0].firstChild.nodeValue;
                var name = stores[i].getElementsByTagName("name")[0].firstChild.nodeValue;
                var street = stores[i].getElementsByTagName("street")[0].firstChild.nodeValue;
                var city = stores[i].getElementsByTagName("city")[0].firstChild.nodeValue;
                var postalCode = stores[i].getElementsByTagName("postalCode")[0].firstChild.nodeValue;

                htmlText += '<li class="list-group-item">\n';
                htmlText += '<div class="card">\n';
                htmlText += '<div class="card-header">\n';
                htmlText += name + '\n';
                htmlText += '</div>\n';
                htmlText += '<div class="card-body">\n';
                htmlText += '<h5 class="card-title">' + street + '</h5>\n';
                htmlText += '<p class="card-text">' + postalCode + ' ' + city + '</p>\n';
                htmlText += '<button value="' + id + '" class="btn btn-primary choose_store" data-dismiss="modal">Sélectionner</button>\n';
                htmlText += '</div>\n';

                htmlText += '</div>\n';
                htmlText += '</li>\n';
            }

            document.getElementById("stores_list").innerHTML = htmlText;

            var choose_store_buttons = document.getElementsByClassName("choose_store");

            for (let i = 0; i < choose_store_buttons.length; i++) {
                choose_store_buttons[i].addEventListener("click", function () {
                    changeStore(choose_store_buttons[i].value);
                });
            }
        }
    };

    xhr.send(param);
}

function changeStore(storeId) {
    // Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();
    // Requête au serveur avec les paramètres éventuels.
    var param = "setStoreId=" + encodeURIComponent(storeId);
    var url = "Store" + "?" + param;
    xhr.open("GET", url, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
    xhr.onload = function () {
        // Si la requête http s'est bien passée.
        if (xhr.status === 200) {
            var store = xhr.responseXML.getElementsByTagName("store")[0];
            var name = store.getElementsByTagName("name")[0].firstChild.nodeValue;
            var street = store.getElementsByTagName("street")[0].firstChild.nodeValue;
            var city = store.getElementsByTagName("city")[0].firstChild.nodeValue;
            var postalCode = store.getElementsByTagName("postalCode")[0].firstChild.nodeValue;

            document.getElementById("store_name").innerHTML = name;
            document.getElementById("store_street").innerHTML = street;
            document.getElementById("store_city").innerHTML = postalCode + " " + city;
            document.getElementById("search_stores").value = "";
            document.getElementById("stores_list").innerHTML = "";
            document.getElementById("time_slot_button").classList.remove("d-none");
        }
    };

    msg_error = document.getElementById("msg_error");
    if (msg_error !== null)
        msg_error.innerHTML = "";
    
//    corp_modal = document.getElementsByClassName("stock");
//    for (let i = 0; i < corp_modal.length; i++) {
//        corp_modal[i][0].firstChild.innerHTML = "";
//    }
    
    corp_modal = document.getElementById("corp_modal");
    if (corp_modal !== null)
        corp_modal.style.display="";
    
    

    xhr.send(param);  
}

/**
 * Lancement après le chargement du DOM.
 */
window.addEventListener("load", function () {
    document.getElementById("search_stores").addEventListener("input", search_stores);
});
    