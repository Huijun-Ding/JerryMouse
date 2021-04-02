/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function upload() {
    var elt = document.getElementById("fileShoppingList");
    var files = elt.files;

    var formData = new FormData();
    if (files[0] !== null && files[0] !== undefined) {
        formData.append("file", files[0], files[0].name);

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "UploadShoppingFile", true);

        xhr.onload = function () {
            if (xhr.status === 200) {
                var htmlText = "";

                var msg_error = xhr.responseXML.getElementsByTagName("msg_error");

                if (msg_error.length === 0) {
                    var postIts = xhr.responseXML.getElementsByTagName("postIt");

                    for (var i = 0; i < postIts.length; i++) {
                        var wording = postIts[i].getElementsByTagName("wording")[0].firstChild.nodeValue;
                        htmlText += '<input type="text" name="postIts" class="form-control my-2"';
                        htmlText += 'value="' + wording + '" placeholder="' + wording + '">\n';
                    }
                } else {
                    var title = xhr.responseXML.getElementsByTagName("title")[0].firstChild.nodeValue;
                    var content = xhr.responseXML.getElementsByTagName("content")[0].firstChild.nodeValue;
                    htmlText += '<div class="alert alert-danger alert-dismissible fade show" role="alert">\n';
                    htmlText += '   <strong>' + title + '</strong> ' + content + '\n';
                    htmlText += '   <button type="button" class="btn-close" data-dismiss="alert" aria-label="Close">\n';
                    htmlText += '       <span aria-hidden="true"></span>\n';
                    htmlText += '   </button>\n';
                    htmlText += '</div>\n';
                }

                document.getElementById("post_its_from_file").innerHTML = htmlText;
            }
        };

        xhr.send(formData);
    } else {
        document.getElementById("post_its_from_file").innerHTML = "";
    }
}

function save() {
    var formData = new FormData();

    var title = document.getElementById("import_title_list").value;
    var postIts = document.getElementsByName("postIts");

    formData.append("title", title);

    for (var i = 0; i < postIts.length - 1 ; i++) {
        formData.append("postIts", postIts[i].value);
    }

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "UploadShoppingFile", true);

    xhr.onload = function () {
        if (xhr.status === 200) {
            var htmlText = "";

            var msg_error = xhr.responseXML.getElementsByTagName("msg_error");
            var title = xhr.responseXML.getElementsByTagName("title")[0].firstChild.nodeValue;
            var content = xhr.responseXML.getElementsByTagName("content")[0].firstChild.nodeValue;

            if (msg_error.length === 0) {
                var msg_success = '<div class="alert alert-success alert-dismissible fade show" role="alert">\n';
                msg_success += '   <strong>' + title + ' :</strong> ' + content + '\n';
                msg_success += '   <button type="button" class="btn-close" data-dismiss="alert" aria-label="Close">\n';
                msg_success += '       <span aria-hidden="true"></span>\n';
                msg_success += '   </button>\n';
                msg_success += '</div>\n';
                
                elt.innerHTML = msg_success;
                
                printShoppingLists();
            } else {
                htmlText += '<div class="alert alert-danger alert-dismissible fade show" role="alert">\n';
                htmlText += '   <strong>' + title + '</strong> ' + content + '\n';
                htmlText += '   <button type="button" class="btn-close" data-dismiss="alert" aria-label="Close">\n';
                htmlText += '       <span aria-hidden="true"></span>\n';
                htmlText += '   </button>\n';
                htmlText += '</div>\n';
            }

            document.getElementById("post_its_from_file").innerHTML = htmlText;
        }
    };

    xhr.send(formData);
}

function printShoppingLists() {
    // Objet XMLHttpRequest.
    var xhr2 = new XMLHttpRequest();

    xhr2.open("GET", "DisplayAllShoppingLists");

    xhr2.onload = function ()
    {
        if (xhr2.status === 200)
        {
            suggestions = xhr2.responseXML.getElementsByTagName("shoppingList");
            elt = document.getElementById("my_lists");

            if (suggestions !== "") {
                for (i = 0; i < suggestions.length; i++) {
                    shoppingListLine = suggestions[i];

                    code = shoppingListLine.getElementsByTagName("code")[0].firstChild.nodeValue;
                    name = shoppingListLine.getElementsByTagName("name")[0].firstChild.nodeValue;

                    elt.insertAdjacentHTML("beforeend", "<a href='showShoppingList?codesl=" + code + "' class='list-group-item list-group-item-action'>" + name + "</a>");
                }
            }
        }
    };

    xhr2.send();
}

/**
 * Lancement après le chargement du DOM.
 */
document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("fileShoppingList").addEventListener("change", upload);
    document.getElementById("validate_uploading_file").addEventListener("click", save);
});
