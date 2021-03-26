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
            nb_products = document.getElementsByClassName("nb_products");
            nb_products.innerHTML = "";
            product = xhr.responseXML.getElementsByTagName("nb_products");
            for (i = 0; i < product.length; i++) {
                productLine = product[i];
                nb = productLine.getElementsByTagName("nb_products")[0].firstChild.nodeValue;
                nb_products.insertAdjacentHTML('beforeend', nb);
            }    
        }
    };
    xhr.send();
}

