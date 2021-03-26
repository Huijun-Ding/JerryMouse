/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function displayStore() {
    document.getElementById("view").src = "../basketPage";
    document.getElementById("search_bar").style.display = "none";
    document.getElementById("rayon_categorie_navbar").style.display = "none";
    document.getElementById("view").style.marginTop = "120px";
}

document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("basket_button").addEventListener("click", displayStore);
});
