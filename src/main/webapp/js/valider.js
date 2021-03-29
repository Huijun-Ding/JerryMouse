/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This function is to validate a basket.
 */
function validate() {
    // Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();
    idClient = document.getElementById("idClient").value;
    idStore = document.getElementById("idStore").value;
    startTime = document.getElementById("startTime").value;
    date = document.getElementById("date").value;
    
    xhr.open("GET", "Validate?idClient="+idClient 
            + "&idStore=" + idStore
            + "&startTime=" + startTime
            + "&date=" + date);

    xhr.onload = function () {
        if (xhr.status === 200) {
            msg_error = document.getElementById("msg_error");
            msg_error.innerHTML = "";

            result = xhr.responseXML.getElementsByTagName("results");
            res = result[0].getElementsByTagName("res")[0].firstChild.nodeValue;
            if(res === "ok"){
                window.location.href = "confirmationOrder";
            }else if(res === "connection"){
                window.location.href = "login";
            }else{
                message = result[0].getElementsByTagName("message")[0].firstChild.nodeValue;
                msg_error.insertAdjacentHTML('beforeend', message);
            }
        }
    };
    xhr.send();
}

/**
 * run
 */
document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("valider").addEventListener("click", validate);
});