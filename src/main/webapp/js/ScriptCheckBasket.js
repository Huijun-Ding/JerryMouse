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

            var i;
            product = xhr.responseXML.getElementsByTagName("product");
            alert(product.length);
            for (i = 0; i < product.length; i++) {
                productLine = product[i];

                photo = productLine.getElementsByTagName("photo")[0].firstChild.nodeValue;
                name = productLine.getElementsByTagName("name")[0].firstChild.nodeValue;
                price = productLine.getElementsByTagName("price")[0].firstChild.nodeValue;
                priceAfter = productLine.getElementsByTagName("priceAfter")[0].firstChild.nodeValue;
                quantity = productLine.getElementsByTagName("quantity")[0].firstChild.nodeValue;
                totalPrice = productLine.getElementsByTagName("totalPrice")[0].firstChild.nodeValue;
                promotion = productLine.getElementsByTagName("promotion")[0].firstChild.nodeValue;

                const rows = "<tr><td><img src='" + photo + "' class='img-thumbnail'></td><td>" 
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

            cagnotte_gagne.insertAdjacentHTML('beforeend', pointsGot);
            cagnotte_cumul.insertAdjacentHTML('beforeend', pointsCumulative);
            totalSpan.insertAdjacentHTML('beforeend', total);
        }
    };
    xhr.send();
}

