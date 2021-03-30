function displayShoppingLists() {
    // relocated to page myShoppingLists
    window.location.href = "myShoppingLists";
    alert('get');
}



document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("shopping_list_button").addEventListener("click", displayShoppingLists);
});