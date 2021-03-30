function displayShoppingLists() {
    // relocated to page myShoppingLists
    window.location.href = "createShoppingList";
}



document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("shopping_list_button").addEventListener("click", displayShoppingLists);
});