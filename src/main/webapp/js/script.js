/**
 * This function AJAX is use for searching a product or a category.
 */
function getSearchElement ()
{
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

        var myinput = document.getElementById("input_search").value;
        xhr.open("GET", "searchServlet?product=" + myinput);

        xhr.onload = function ()
        {
            if (xhr.status === 200)
            {
                if (myinput != "") {
                suggestions = xhr.responseXML.getElementsByTagName("prod");
                elt = document.getElementById("search_result");
                if(suggestions!=null&&suggestions.length!=0){
                elt.style.display = "block";
                elt.innerHTML = "";
                for (i = 0; i < suggestions.length; i++) {
                    prod = suggestions[i].firstChild.nodeValue;
                    elt.insertAdjacentHTML("beforeend", "<div class='event'>" + prod + "</div>");
                }
                } else {
                elt.style.display = "none";
                }  
                document.querySelectorAll('.event').forEach(item => {
                    item.addEventListener('click', event => {
                        System.out.println("get it !");
                    })
                })
            } else {
                elt.style.display = "none";
                }
            }
        };

        xhr.send();
}


