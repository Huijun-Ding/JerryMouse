/* 
* CC 2.0 License Iatek LLC 2018
* Attribution required
*/

$('#carouselExample').on('slide.bs.carousel', function (e) {

    var $e = $(e.relatedTarget);
    
    var idx = $e.index();
    console.log("IDX :  " + idx);
    
    var itemsPerSlide = 8;
    var totalItems = $('.carousel-item').length;
    
    if (idx >= totalItems-(itemsPerSlide-1)) {
        var it = itemsPerSlide - (totalItems - idx);
        for (var i=0; i<it; i++) {
            // append slides to end
            if (e.direction=="left") {
                $('.carousel-item').eq(i).appendTo('.carousel-inner');
            }
            else {
                $('.carousel-item').eq(0).appendTo('.carousel-inner');
            }
        }
    }
});

window.onbeforeunload = function getNumberOfItems() {
    var x = screen.width;
    //alert ("x = " + x);
    var max;
    if (x >= 992) {
        max = 4;
    } else if(x >= 768) {
        max = 3;
    } else max = 1;
    
    alert("window.location.href = " + window.location.href);
    
//    var xhr = new XMLHttpRequest();
    var param = "max="+max;
//    var url = "DisplayProducts" + "?home&" + param;
//    
//    xhr.open("GET", url, true);
//    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//    
//    xhr.send(param);
    var queryParams = new URLSearchParams(window.location.search);
    queryParams.set("max", max);
    history.pushState(null, null, "?" + queryParams.toString());
    //window.location.search = param;
    alert("location.href = " + location.href);
}

//document.onload = getNumberOfItems();
document.addEventListener("DOMContentLoaded", () => {
    //getNumberOfItems();
});
