//for common JS files

$(window).load(function() {
    $(".loader").fadeOut("slow");
    MAIN.createRelatedSelector
    ( document.querySelector('#countries')           // from select element
        ,document.querySelector('#cities')      // to select element
        ,{                                               // values object
            RepublicaDominicana: ['Santiago','La vega','Puerto Plata'],
            Venezuela: ['caracas','Blabla'],
            China: ['Hong kong','Namibia']
        }
        ,function(a,b){return a>b ? 1 : a<b ? -1 : 0;}   // sort method
    );
})