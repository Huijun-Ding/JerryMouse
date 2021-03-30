/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var time_slots_list = document.getElementById("time_slots_list");

function search_time_slots() {
    // Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();
    // Requête au serveur avec les paramètres éventuels.
    var x = document.getElementById("search_time_slots").selectedIndex;
    var value = document.getElementsByTagName("option")[x].value;

    var param = "date=" + encodeURIComponent(value);
    var url = "ChooseTimeSlot" + "?" + param;
    xhr.open("GET", url, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
    xhr.onload = function () {
        // Si la requête http s'est bien passée.
        if (xhr.status === 200) {
            //document.getElementById("stores_list").innerHtml = "";
            var htmlText = "";
            var timeSlot = xhr.responseXML.getElementsByTagName("timeSlot");
            
            htmlText += url ;
            for (var i = 0; i < timeSlot.length; i++) {
                var startTime = timeSlot[i].getElementsByTagName("startTime")[0].firstChild.nodeValue;
                var endTime = timeSlot[i].getElementsByTagName("endTime")[0].firstChild.nodeValue;
                var capacity = timeSlot[i].getElementsByTagName("capacity")[0].firstChild.nodeValue;

                htmlText += '<li class="form-check">\n';
                htmlText += '   <input class="form-check-input" type="radio" name="startTime" id="ts' + i + '"';
                if (capacity === 0)
                    htmlText += ' disabled';
                htmlText += '>\n';
                htmlText += '   <label class="form-check-label" for="ts' + i + '">\n';
                htmlText += startTime + ' - ' + endTime + '</label>\n';
                htmlText += '</li>\n';
            }

            time_slots_list.innerHTML = htmlText;

            //Ajouter le listener pour le bouton de validation
        }
    };

    xhr.send(param);
}


function changeTimeSlot() {
    //Fontion pour modifier un time slot depuis la servlet ChooseTimeSlot
}

/**
 * Lancement après le chargement du DOM.
 */
document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("search_time_slots").addEventListener("change", search_time_slots);
});
    