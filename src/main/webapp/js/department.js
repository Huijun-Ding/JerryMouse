/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function getProductCategoriesByDepartmentId(departmentId) {
    var xhr = new XMLHttpRequest();

    var param = "departmentId=" + departmentId;
    var url = "ProductCategory?" + param;

    xhr.open("GET", url, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
    xhr.onload = function ()
    {

        // Si la requête http s'est bien passée.
        if (xhr.status === 200) {
            var categories = xhr.responseXML.getElementsByTagName("productCategory");
            var productCategories_list = document.getElementById("productCategories_list");
            productCategories_list.innerHTML = "";
            document.getElementById("categoryButton").innerHTML = "Toutes les catégories";
            
            var button = document.createElement("button");
            button.innerHTML = "Toutes les catégories";
            button.className = "dropdown-item";
            button.addEventListener("click", function () {
                document.getElementById("categoryButton").innerHTML = name;
                setViewOnDepartment(departmentId);
            });
            
            for (var i = 0; i < categories.length; i++) {
                var id = categories[i].getElementsByTagName("id")[0].firstChild.nodeValue;
                var name = categories[i].getElementsByTagName("name")[0].firstChild.nodeValue;
                var button = document.createElement("button");
                button.innerHTML = name;
                button.className = "dropdown-item";
                button.value = id;
                button.addEventListener("click", function () {
                    document.getElementById("categoryButton").innerHTML = this.innerHTML;
                    setViewOnCategory(this.value);
                });
                productCategories_list.appendChild(button);
            }
        }
    };

    // Envoie de la requête.
    xhr.send(param);
}

function getDepartments() {
    var xhr = new XMLHttpRequest();

    var url = "Department";

    xhr.open("GET", url, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
    xhr.onload = function () {
        // Si la requête http s'est bien passée.
        if (xhr.status === 200) {
            document.getElementById("departmentButton").innerHTML = "Tous les rayons";
            document.getElementById("productCategories_list").innerHtml = "Toutes les catégories";

            document.getElementById("departments_list").innerHtml = "";
            var departments = xhr.responseXML.getElementsByTagName("department");
            var departments_list = document.getElementById("departments_list");

            var button = document.createElement("button");
            button.innerHTML = "Tous les rayons";
            button.className = "dropdown-item";
            button.addEventListener("click", function () {
                document.getElementById("departmentButton").innerHTML = "Tous les rayons";
                document.getElementById("categoryButton").innerHTML = "Toutes les catégories";
                document.getElementById("category").style.display = "none";
                setViewOnHome();
            });

            departments_list.appendChild(button);

            for (var i = 0; i < departments.length; i++) {
                var id = departments[i].getElementsByTagName("id")[0].firstChild.nodeValue;
                var name = departments[i].getElementsByTagName("name")[0].firstChild.nodeValue;
                var button = document.createElement("button");
                button.innerHTML = name;
                button.className = "dropdown-item choose_department";
                button.value = id;
                departments_list.appendChild(button);
            }

            document.getElementById("category").style.display = "none";

            var choose_store_buttons = document.getElementsByClassName("choose_department");

            for (let i = 0; i < choose_store_buttons.length; i++) {
                choose_store_buttons[i].addEventListener("click", function () {
                    changeDepartment(choose_store_buttons[i].value);
                });
            }
        }
    };

    // Envoie de la requête.
    xhr.send();
}

function changeDepartment(departmentId) {
    // Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();

    // Requête au serveur avec les paramètres éventuels.
    var param = "id=" + encodeURIComponent(departmentId);
    var url = "Department" + "?" + param;
    xhr.open("GET", url, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
    xhr.onload = function () {
        // Si la requête http s'est bien passée.
        if (xhr.status === 200) {
            var store = xhr.responseXML.getElementsByTagName("department")[0];
            var id = store.getElementsByTagName("id")[0].firstChild.nodeValue;
            var name = store.getElementsByTagName("name")[0].firstChild.nodeValue;
            document.getElementById("departmentButton").innerHTML = name;

            setViewOnDepartment(id);
            getProductCategoriesByDepartmentId(id);
        }
    };

    xhr.send(param);

    document.getElementById("category").style.display = "block";
}

function setViewOnHome() {
    document.getElementById("view").src = "DisplayProducts?home";
}

function setViewOnDepartment(departmentId) {
    document.getElementById("view").src = "DisplayProducts?dpt=" + departmentId;
}

function setViewOnCategory(productCategoryId) {
    document.getElementById("view").src = "DisplayProducts?cat=" + productCategoryId;
}

/**
 * Lancement après le chargement du DOM.
 */
document.addEventListener("DOMContentLoaded", () => {
    getDepartments();
});