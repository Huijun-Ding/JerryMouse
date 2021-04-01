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

            for (var i = 0; i < timeSlot.length; i++) {
                var startTime = timeSlot[i].getElementsByTagName("startTime")[0].firstChild.nodeValue;
                var endTime = timeSlot[i].getElementsByTagName("endTime")[0].firstChild.nodeValue;
                var capacity = timeSlot[i].getElementsByTagName("capacity")[0].firstChild.nodeValue;
                var message_capacity = (capacity <= 1) ? capacity + " place restante." : capacity + " places restantes.";


                htmlText += '   <button tabindex="' + i + '" id="ts' + i + '" class="btn btn-dark d-inline-block mb-3" type="button" value="' + startTime + '" title="' + message_capacity + '" data-toggle="tooltip" data-placement="top"';
                if (capacity === "0")
                    htmlText += ' disabled';
                htmlText += ' data-dismiss="modal">' + startTime + ' - ' + endTime + '</button>\n';
            }

            time_slots_list.innerHTML = htmlText;

            for (var i = 0; i < timeSlot.length; i++) {
                //var startTime = timeSlot[i].getElementsByTagName("startTime")[0].firstChild.nodeValue;
                document.getElementById('ts' + i).addEventListener("click", function () {
                    changeTimeSlot(this.value);

                });
            }
        }
    };
//    msg_error = document.getElementById("msg_error");
//    if (msg_error !== null)
//        msg_error.innerHTML = "";
//    $("#corp_modal").load(location.href + " #corp_modal");
    xhr.send();
}

function changeTimeSlot(startTime) {
    // Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();
    // Requête au serveur avec les paramètres éventuels.
    var x = document.getElementById("search_time_slots").selectedIndex;
    var value = document.getElementsByTagName("option")[x].value;

    var param = "date=" + encodeURIComponent(value);
    param += "&startTime=" + startTime;
    var url = "ChooseTimeSlot" + "?" + param;
    xhr.open("GET", url, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
    xhr.onload = function () {
        // Si la requête http s'est bien passée.
        if (xhr.status === 200) {
            var timeSlot = xhr.responseXML.getElementsByTagName("msg")[0];
            document.getElementById("time_slot_name").innerHTML = timeSlot.innerHTML;
            parent.$("#corp_modal").load(window.parent.location.href + " #corp_modal");
        }
    };
    
    msg_error = document.getElementById("msg_error");
    if (msg_error !== null)
        msg_error.innerHTML = "";
    
    xhr.send();
}

/**
 * Lancement après le chargement du DOM.
 */
document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("search_time_slots").addEventListener("change", search_time_slots);
});

$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});