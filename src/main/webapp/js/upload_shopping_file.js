/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function upload() {
    var elt = document.getElementById("fileShoppingList");
    var files = elt.files;

    var formData = new FormData();
    if (files[0] !== null) {
        formData.append("file", files[0], files[0].name);

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "UploadShoppingFile", true);

        xhr.onload = function () {
            if (xhr.status === 200) {
                var htmlText = "";
                
                var msg_error = xhr.responseXML.getElementsByTagName("msg_error");
                
                if(msg_error.length === 0) {
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
                    htmlText += '   <strong>' + title + '</strong> ' + content +'\n';
                    htmlText += '   <button type="button" class="btn_close" data-dismiss="alert" aria-label="Close">\n';
                    htmlText += '       <span aria-hidden="true">&times;</span>\n';
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

/**
 * Lancement après le chargement du DOM.
 */
document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("fileShoppingList").addEventListener("change", upload);
});