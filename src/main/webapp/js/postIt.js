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

document.addEventListener("DOMContentLoaded", () => {
    if (window.addEventListener) {
        window.addEventListener('load', showPostIts); //W3C
    } else {
        window.attachEvent('onload', showPostIts); //IE
    }
});