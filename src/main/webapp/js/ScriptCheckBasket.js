/**
 * This function AJAX is use for searching a product or a category.
 */

function displayBasket() {
    var xhr = new XMLHttpRequest();

    xhr.open("GET", "checkBasketServlet");

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

                if (priceAfter !== " ") {
                    priceAfter += "&#8364;";
                    price = "<span class='text-decoration-line-through'>" + price + "</span>";
                } else {
                    price = "<span>" + price + "</span>";
                }

                const rows = "<tr>"
                        + "<td><img src='" + photo + "' class='prodPhoto'></td>"
                        + "<td>" + name + "</br>"
                        + "<span class='card-subtitle mb-2 text-muted'>" + format + "</span></td>"
                        + "<td>" + price + "&#8364;</br>"
                        + "<span class='priceAfter'>" + priceAfter + "</span></td>"
                        + "<td><button type='button' class='btn btn-primary btn-sm rounded-circle' id='m_" + ean + "'><i class='fas fa-minus'></i></button>"
                        + "<input type='text' class='quantity' id='qty_" + ean + "' value='" + quantity + "' readonly='readonly'>"
                        + "<button type='button' class='btn btn-primary btn-sm rounded-circle' id='p_" + ean + "'><i class='fas fa-plus'></i></button></br>"
                        + "<span class='stock' id='" + ean + "'></span></td>"
                        + "<td>" + totalPrice + "&#8364;</td>"
                        + "</tr>";
                tabProd.insertAdjacentHTML('beforeend', rows);
                
                document.getElementById('m_' + ean).addEventListener("click", function() {
                    var idp = this.id.substring(2);
                    if(document.getElementById('qty_' + idp).value === "1")
                        this.parentNode.parentNode.remove();
                    editQuantity(this);
                });
                
                document.getElementById('p_' + ean).addEventListener("click", function() {
                    editQuantity(this);
                });
                
            }
        }
    };
    xhr.send();
}


function displayPoints() {
    var xhr = new XMLHttpRequest();
    checkBox = document.getElementsByName("checkPoint");
    checkPoint = false;
    if (checkBox[0].checked)
        checkPoint = true;


    xhr.open("GET", "checkBasketServlet?checkPoint=" + checkPoint);

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
    
function editQuantity(button) {
//    var idBtn = event.target.id;
    var idBtn = button.id;
    var ean = idBtn.substring(2);
    var method = idBtn.substring(0, 1);
//    alert(ean);
//    alert(method);

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "EditQuantityServlet?ean=" + ean + "&method=" + method);

    xhr.onload = function () {
        if (xhr.status === 200) {
            qtyProd = document.getElementById('qty_' + ean);

            results = xhr.responseXML.getElementsByTagName("results");
            msg = results[0].getElementsByTagName("msg")[0].firstChild.nodeValue;
            res = results[0].getElementsByTagName("res")[0].firstChild.nodeValue;

            var msg_error = "";
            if (msg === "ok" && qtyProd !== null) {
                qtyProd.value = res;
            } else {
                if (msg === "noMinus") {
                    msg_error = "D??j?? 0 !";
                } else if (msg === "noPlus") {
                    msg_error = "Rupture de stock !";
                } else if (msg === "store") {
                    msg_error = "Aucun magasin choisi !";
                }
            }
            stock = document.getElementById(ean);
            
            if(stock !== null) {
                stock.innerHTML = "";
                stock.insertAdjacentHTML('beforeend', msg_error);
            }
            
            displayBasket();
            displayPoints();
        }
    };
    xhr.send();
};

document.addEventListener("DOMContentLoaded", () => {
    //test: Glaces et desserts glac??s
    document.getElementById("checkPoint").addEventListener("change", displayPoints);
    displayBasket();
    displayPoints();
});

