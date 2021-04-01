/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function displayNbProduct(){
    var ean = this.value;
    var xhr = new XMLHttpRequest();
//    var idClient = document.getElementById("idClient").value;

    xhr.open("GET", "AddProductServlet?ean=" + ean);

    xhr.onload = function () {
        if (xhr.status === 200) {
            nb_products = window.parent.document.getElementsByClassName("nb_products");
            nb_products.innerHTML = "";
            product = xhr.responseXML.getElementsByTagName("nbProducts");
            
            nb = product[0].firstChild.nodeValue;
            if(nb !== "no"){
                alert(nb);
                var text = '<jsp:param name="ref2"' + ' value="' + nb +'"/>'
                nb_products.innerHTML = text;
            }
        }
    };
    xhr.send();
}

document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("addProd").addEventListener("click", displayNbProduct);
});


