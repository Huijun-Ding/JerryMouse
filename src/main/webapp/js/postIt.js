function showPostIts() {
    // Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();
    
//    id = window.location.search.substr(1);
    id = window.location.search;
    alert(id);
    alert(location.search.substring(1));
    
    var myinput = document.getElementById("search").value;
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
                alert(postItLine);
                code = postItLine.getElementsByTagName("code")[0].firstChild.nodeValue;
                name = postItLine.getElementsByTagName("name")[0].firstChild.nodeValue;
                pname = postItLine.getElementsByTagName("pname")[0].firstChild.nodeValue;
                pbrand = postItLine.getElementsByTagName("pbrand")[0].firstChild.nodeValue;

                elt.insertAdjacentHTML("beforeend", "<li class='list-group-item'>" + name + pname + " - " + pbrand + "<p class='text-end'><i class='fas fa-search-plus' value='" + name + pname + "'></i>");
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