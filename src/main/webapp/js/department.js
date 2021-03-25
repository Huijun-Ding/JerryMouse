/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function setDepartment(id, name) {
    document.getElementById("departmentButton").data = name;
    setViewOnDepartment(id);
    getProductCategoriesByDepartmentId(id);
}

function setCategory(id, name) {
    document.getElementById("categoryButton").data = name;
    setViewOnCategory(id);
}

function getProductCategoriesByDepartmentId(departmentId) {
    
    var xhr = new XMLHttpRequest();
    
    var param = "departmentId=" + departmentId;
    var url = "../ProductCategory?" + param;

    xhr.open("GET", url, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
    xhr.onload = function ()
    {
        // Si la requête http s'est bien passée.
        if (xhr.status === 200) {
            document.getElementById("productCategories_list").innerHtml = "";
            var categories = xhr.responseXML.getElementsByTagName("productCategory");
            var productCategories_list = document.getElementById("productCategories_list");
            
            for (var i = 0; i < categories.length; i++) {
                var id = categories[i].getElementsByTagName("id")[0].firstChild.nodeValue;
                var name = categories[i].getElementsByTagName("name")[0].firstChild.nodeValue;
                var button = document.createElement("button");
                button.innerHTML = name;
                button.className = "dropdown-item";
                button.addEventListener("click", setCategory(id, name));
                productCategories_list.appendChild(button);
            }
        }
    };

    // Envoie de la requête.
    xhr.send(param);
}

function getDepartments() {
    var xhr = new XMLHttpRequest();
    
    var url = "../Department";

    xhr.open("GET", url, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
    xhr.onload = function () {
        // Si la requête http s'est bien passée.
        if (xhr.status === 200) {
            document.getElementById("departments_list").innerHtml = "";
            var departments = xhr.responseXML.getElementsByTagName("department");
            var departments_list = document.getElementById("departments_list");
            
            for (var i = 0; i < departments.length; i++) {
                var id = departments[i].getElementsByTagName("id")[0].firstChild.nodeValue;
                var name = departments[i].getElementsByTagName("name")[0].firstChild.nodeValue;
                var button = document.createElement("button");
                button.innerHTML = name;
                button.className = "dropdown-item";
                button.addEventListener("click", setDepartment(id, name));
                console.log("button.addEventListener('click', setDepartment(" + id + ", " + name  + "));")
                departments_list.appendChild(button);
            }
        }
    };

    // Envoie de la requête.
    xhr.send();
}

function setViewOnDepartment(departmentId) {
    document.getElementById("view").src = "../DisplayProducts?dpt=" + departmentId;
}

function setViewOnCategory(productCategoryId) {
    document.getElementById("view").src = "../DisplayProducts?cat=" + productCategoryId;
}

/**
 * Lancement après le chargement du DOM.
 */
document.addEventListener("DOMContentLoaded", () => {
    getDepartments();
});