function showPostIts() {
// Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();

    var myinput = window.location.search.substr(8);
    xhr.open("GET", "DisplayPostItServlet?id=" + myinput);

    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            postits = xhr.responseXML.getElementsByTagName("postIt");
            elt = document.getElementById("show_postit");
            elt.innerHTML = "";
            for (i = 0; i < postits.length; i++) {
                postItLine = postits[i];

                code = postItLine.getElementsByTagName("code")[0].firstChild.nodeValue;
                name = postItLine.getElementsByTagName("name")[0].firstChild.nodeValue;
                pname = postItLine.getElementsByTagName("pname")[0].firstChild.nodeValue;
                pbrand = postItLine.getElementsByTagName("pbrand")[0].firstChild.nodeValue;

                if (name === "") {
                    elt.insertAdjacentHTML("beforeend", "<li class='list-group-item'>" + pname + " " + pbrand + "<p class='text-end'><i class='fas fa-search-plus' value='" + pname + "'></i>");
                } else {
                    elt.insertAdjacentHTML("beforeend", "<li class='list-group-item'>" + name + "<p class='text-end'><i class='fas fa-search-plus' value='" + name + "'></i>");
                }
            }
        }
    };
    xhr.send();
}

function showShoppingListName() {
    var xhr = new XMLHttpRequest();

    var myinput = window.location.search.substr(8);
    xhr.open("GET", "GetShoppingListName?id=" + myinput);

    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            var nameSL = xhr.responseXML.getElementsByTagName("Shoppinglist")[0];
            var name = nameSL.getElementsByTagName("name")[0].firstChild.nodeValue;
            document.getElementById("list_name").innerHTML = "<h2>" + name + "</h2>";
        }
    };
    xhr.send();
}

function sendAddPostItRequest() {
    var xhr = new XMLHttpRequest();

    var id = window.location.search.substr(8);
    var name = document.getElementById("input_post_it").value;
    xhr.open("GET", "AddPostItServlet?id=" + id + "&name=" + name);

    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            alert("Post-it ajouté !");
            window.location.reload();
        }
    };
    xhr.send();
}

document.addEventListener("DOMContentLoaded", () => {
    if (window.addEventListener) {
        window.addEventListener('load', showPostIts); //W3C
        window.addEventListener('load', showShoppingListName); //W3C
    } else {
        window.attachEvent('onload', showPostIts); //IE
        window.attachEvent('onload', showShoppingListName);
    }
    
    document.getElementById("submit_post_it").addEventListener("click", sendAddPostItRequest);
});