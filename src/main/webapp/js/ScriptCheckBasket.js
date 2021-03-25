/**
 * This function AJAX is use for searching a product or a category.
 */

function displayBasket() {
    var xhr = new XMLHttpRequest();
    var idClient = document.getElementById("idClient").value;

    xhr.open("GET", "checkBasketServlet?idClient=" + idClient);

    xhr.onload = function () {
        if (xhr.status === 200) {
            tabProd = document.getElementById("tabProd");
            tabProd.innerHTML = "";

            var i, product;
            product = xhr.responseXML.getElementsByTagName("product");
            for (i = 0; i < product.length; i++) {
                product = product[i];

                photo = product.getElementsByTagName("photo")[0].firstChild.nodeValue;
                name = product.getElementsByTagName("name")[0].firstChild.nodeValue;
                price = product.getElementsByTagName("price")[0].firstChild.nodeValue;
                priceAfter = product.getElementsByTagName("priceAfter")[0].firstChild.nodeValue;
                quantity = product.getElementsByTagName("quantity")[0].firstChild.nodeValue;
                totalPrice = product.getElementsByTagName("totalPrice")[0].firstChild.nodeValue;
                promotion = product.getElementsByTagName("promotion")[0].firstChild.nodeValue;

                const rows = "<tr> <td>" + photo + "</td><td>" 
                        + name + "</td><td>" 
                        + price + "</br>" + priceAfter + "</td><td>" 
                        + quantity + "</td><td>" 
                        + totalPrice + "</br>" + promotion + "</td></tr>"
                        ;
                tabProd.insertAdjacentHTML('beforeend', rows);
            }

        }
    };
    xhr.send();
}


function displayPoints() {
    var xhr = new XMLHttpRequest();
    var idClient = document.getElementById("idClient").value;

    xhr.open("GET", "checkBasketServlet?idClient=" + idClient);

    xhr.onload = function () {
        if (xhr.status === 200) {
            cagnotte_gagne = document.getElementById("cagnotte_gagne");
            cagnotte_cumul = document.getElementById("cagnotte_cumul");
            totalSpan = document.getElementById("total");
            
            cagnotte_gagne.innerHTML = "";
            cagnotte_cumul.innerHTML = "";
            totalSpan.innerHTML = "";

            var client = xhr.responseXML.getElementsByTagName("client")[0];
            
            pointsGot = client.getElementsByTagName("pointsGot")[0].firstChild.nodeValue;
            pointsCumulative = client.getElementsByTagName("pointsCumulative")[0].firstChild.nodeValue;
            total = client.getElementsByTagName("total")[0].firstChild.nodeValue;
            alert(total);
            cagnotte_gagne.insertAdjacentHTML('beforeend', pointsGot);
            cagnotte_cumul.insertAdjacentHTML('beforeend', pointsCumulative);
            totalSpan.insertAdjacentHTML('beforeend', total);
        }
    };
    xhr.send();
}


