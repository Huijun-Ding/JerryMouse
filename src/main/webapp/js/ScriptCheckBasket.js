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
            for (i = 0; i < product.length; i++) {
                productLine = product[i];
                
                ean = productLine.getElementsByTagName("ean")[0].firstChild.nodeValue;
                photo = productLine.getElementsByTagName("photo")[0].firstChild.nodeValue;
                name = productLine.getElementsByTagName("name")[0].firstChild.nodeValue;
                format = productLine.getElementsByTagName("format")[0].firstChild.nodeValue;
                price = productLine.getElementsByTagName("price")[0].firstChild.nodeValue;
                priceAfter = productLine.getElementsByTagName("priceAfter")[0].firstChild.nodeValue;
                quantity = productLine.getElementsByTagName("quantity")[0].firstChild.nodeValue;
                totalPrice = productLine.getElementsByTagName("totalPrice")[0].firstChild.nodeValue;
//                promotion = productLine.getElementsByTagName("promotion")[0].firstChild.nodeValue;
                
                if(priceAfter !== " "){
                    priceAfter += "&#8364;";
                    price = "<span class='text-decoration-line-through'>" + price + "</span>";
                }else{
                    price = "<span>" + price + "</span>";
                }
                
                const rows = "<tr>"
                        + "<td><img src='" + photo + "' class='prodPhoto'></td>"
                        + "<td>" + name + "</br>" 
                        + "<span class='card-subtitle mb-2 text-muted'>" + format + "</span></td>"
                        + "<td>" + price + "&#8364;</br>" 
                        + "<span class='priceAfter'>" + priceAfter + "</span></td>"
                        + "<td><input type='button' name='minus' value='-' onclick='#'>"
                        + "<input type='number' class='quantity' value='" + quantity + "'>"
                        + "<input type='button' name='plus' value='+' onclick='#'></br>"
                        + "<span class='stock' id='" + ean + "'></span></td>"
                        + "<td>" + totalPrice + "&#8364;</td>"
                        + "</tr>";
                tabProd.insertAdjacentHTML('beforeend', rows);
            }

        }
    };
    xhr.send();
}


function displayPoints() {
    var xhr = new XMLHttpRequest();
    var idClient = document.getElementById("idClient").value;
    checkBox= document.getElementsByName("checkPoint");
    checkPoint=false;
    if(checkBox[0].checked) checkPoint=true;
    

    xhr.open("GET", "checkBasketServlet?idClient=" + idClient+"&checkPoint="+checkPoint);

    xhr.onload = function () {
        if (xhr.status === 200) {
            cagnotte_gagne = document.getElementById("cagnotte_gagne");
            cagnotte_cumul = document.getElementById("cagnotte_cumul");
            totalSpan = document.getElementById("total");
            discountSpan = document.getElementById("reduction");
            
            cagnotte_gagne.innerHTML = "";
            cagnotte_cumul.innerHTML = "";
            totalSpan.innerHTML = "";
            discountSpan.innerHTML = "";

            var client = xhr.responseXML.getElementsByTagName("client")[0];
            
            pointsGot = client.getElementsByTagName("pointsGot")[0].firstChild.nodeValue;
            pointsCumulative = client.getElementsByTagName("pointsCumulative")[0].firstChild.nodeValue;
            discount = client.getElementsByTagName("discount")[0].firstChild.nodeValue;
            total = client.getElementsByTagName("total")[0].firstChild.nodeValue;

            cagnotte_gagne.insertAdjacentHTML('beforeend', pointsGot);
            cagnotte_cumul.insertAdjacentHTML('beforeend', pointsCumulative);
            discountSpan.insertAdjacentHTML('beforeend', discount + "&#8364;");
            totalSpan.insertAdjacentHTML('beforeend', total + "&#8364;");
        }
    };
    xhr.send();
}

document.addEventListener("DOMContentLoaded", () => {
    //test: Glaces et desserts glacés
    document.getElementById("checkPoint").addEventListener("change", displayPoints);
});

