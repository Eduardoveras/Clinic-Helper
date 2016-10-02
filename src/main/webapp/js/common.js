//for common JS files

$(window).load(function() {
    $(".loader").fadeOut("slow");
    MAIN.createRelatedSelector
    ( document.querySelector('#countries')           // from select element
        ,document.querySelector('#cities')      // to select element
        ,{                                               // values object
            RD: ['Santiago','La vega','Puerto Plata'],
            Venezuela: ['caracas','Blabla'],
            China: ['Hong kong','Namibia']
        }
    );
})