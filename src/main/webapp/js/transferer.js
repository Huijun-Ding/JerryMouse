function transfer() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "transferProductServlet");

    xhr.onload = function () {
        if (xhr.status === 200) {
            info = document.getElementById("modaltransferer");
            info.innerHTML = "";
            
            var m = xhr.responseXML.getElementsByTagName("message");
            message=m[0].firstChild.nodeValue;
            info.insertAdjacentHTML('beforeend', message);
            $('#transferer').modal('show');
        }

    };
    xhr.send();
}


document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("list_to_panier").addEventListener("click", transfer);
}
);
