/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This function is to validate a basket.
 */
function validate() {
    //var myinput = this.firstChild.nodeValue;

    // Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();

    xhr.open("GET", "../Validate");

    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            
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