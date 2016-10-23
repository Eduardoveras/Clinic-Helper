//for common JS files

$(window).load(function() {
    $(".loader").fadeOut("slow");
    newpatient.createRelatedSelector
    ( document.querySelector('#countries')           // from select element
        ,document.querySelector('#cities')      // to select element
        ,{                                               // values object
            RD: ['Santiago','La vega','Puerto Plata'],
            Venezuela: ['caracas','Blabla'],
            China: ['Hong kong','Namibia']
        }
    );
});

$(document).ready(function() {

    // page is now ready, initialize the calendar...
    var calHeight = 1000;

    $('#calendar').fullCalendar({
        height:calHeight,
        contentHeight:calHeight,
        dayClick: function() {
            alert('a day has been clicked!');
        }
    });



});