/**
 * This function AJAX is use for searching a product or a category.
 */
function processKey ()
{
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

        var myinput = document.getElementById("saisie").value;
        xhr.open("GET", "ServletGoogle?mot_begin=" + myinput);

        xhr.onload = function ()
        {
            if (xhr.status === 200)
            {
                if (myinput != "") {
                suggestions = xhr.responseXML.getElementsByTagName("mot");
                elt = document.getElementById("zoneaff");
                if(suggestions!=null&&suggestions.length!=0){
                elt.style.display = "block";
                elt.innerHTML = "";
                for (i = 0; i < suggestions.length; i++) {
                    m = suggestions[i].firstChild.nodeValue;
                    elt.insertAdjacentHTML("beforeend", "<div class='event'>" + m + "</div>"); // "afterbegin" dans l'ordre inverse
                }
                } else {
                elt.style.display = "none";
                }  
                document.querySelectorAll('.event').forEach(item => {
                    item.addEventListener('click', event => {
                        supprimer();
                    })
                })
            } else {
                elt.style.display = "none";
                }
            }
        };
	// Envoie de la requête.
	//if (myinput !== null) {
            xhr.send();
        //}
}


/**
 * Launch after loading the DOM.
 */
document.addEventListener("DOMContentLoaded", () => {

    document.getElementById("bt_connect").addEventListener("click", connection);
});


