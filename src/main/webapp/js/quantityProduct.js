/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function displayNbProduct(){
    var xhr = new XMLHttpRequest();
//    var idClient = document.getElementById("idClient").value;

    xhr.open("GET", "AddProductServlet");

    xhr.onload = function () {
        if (xhr.status === 200) {
            nb_products = window.parent.document.getElementsByClassName("nb_products");
            nb_products.innerHTML = "";
            product = xhr.responseXML.getElementsByTagName("nbProducts");
            
            for (i = 0; i < product.length; i++) {
                productLine = product[i];
                nb = productLine[i].firstChild.nodeValue;
                alert(nb);
                nb_products.insertAdjacentHTML('beforeend', nb);
            }    
        }
    };
    xhr.send();
}

document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("addProduct").addEventListener("click", displayNbProduct);
});

window.onload = function () {
    document.getElementById("addProduct").onclick = function () {
        alert("1");
    };
};


