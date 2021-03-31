/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function upload() {
    alert("input filt has changed !");
    var elt = document.getElementById("fileShoppingList");
    var files = elt.files;
    
    var formData = new FormData();
    formData.append("file", files[0], files[0].name);
    formData.append("nameList", "Test 31_03_2021");
    
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/upload", true);
    
    xhr.onload = function () {
        if (xhr.status === 200) {  
            alert("File successfully uploaded !");
        } else {
            alert("File upload failed !");
        }
    };

    xhr.send(formData);
}

/**
 * Lancement après le chargement du DOM.
 */
document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("fileShoppingList").addEventListener("change", upload);
});