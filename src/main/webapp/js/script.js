/**
 * This function AJAX is use for searching a product or a category.
 */
function getSearchElement () {
    
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

        var myinput = document.getElementById("search").value;
        xhr.open("GET", "../searchByKeyWord?product=" + myinput);

        xhr.onload = function ()
        {
            if (xhr.status === 200)
            {
                if (myinput != "") {
                suggestions = xhr.responseXML.getElementsByTagName("product");
                elt = document.getElementById("search_result");
                if(suggestions!=null&&suggestions.length!=0){
                    elt.style.display = "block";
                    elt.innerHTML = "";
                    for (i = 0; i < suggestions.length; i++) {
                        prod = suggestions[i].firstChild.nodeValue;
                        elt.insertAdjacentHTML("beforeend", "<li class='list-group-item'>" + prod + "</li>");
                    }
                } else {
                    elt.style.display = "none";
                }  

            } else {
                elt.style.display = "none";
                }
            }
        };

        xhr.send();
}

/**
 * run
 */
document.addEventListener("DOMContentLoaded", () => {

    document.getElementById("search").addEventListener("input", getSearchElement);
    
});


