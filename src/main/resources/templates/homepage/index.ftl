<#include "/layouts/header.ftl">

<body class="nav-md">
<div class="container body">
    <div class="main_container">
    <#include "/layouts/sidebar.ftl">
    <#include "/layouts/navbar.ftl">



        <!-- page content -->
        <div class="right_col" role="main">
            <div class="">
                 <#include "/homepage/_topBoxes.ftl">
                <div class="row">
                    <#include "/homepage/_calendar.ftl">
                    <#include "/homepage/_toDoList.ftl">


                    <!--ADD CONTENT HERE-->


                </div>
            </div>
        </div>
        <!-- /page content -->
    <#include "/layouts/Copyright.ftl">


    </div>
</div>

<#include "/layouts/footer.ftl">
<!-- jQuery Sparklines -->
<script>
    $(document).ready(function() {

        $(".sparkline_two").sparkline([2, 4, 3, 4, 5, 4, 5, 4, 3, 4, 5, 6, 7, 5, 4, 3, 5, 6], {
            type: 'line',
            width: '200',
            height: '40',
            lineColor: '#26B99A',
            fillColor: 'rgba(223, 223, 223, 0.57)',
            lineWidth: 2,
            spotColor: '#26B99A',
            minSpotColor: '#26B99A'
        });
    });
</script>
<!-- FullCalendar -->
<script>
    $(window).load(function() {
        var date = new Date(),
                d = date.getDate(),
                m = date.getMonth(),
                y = date.getFullYear(),
                started,
                categoryClass;

        var calendar = $('#calendar').fullCalendar({
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,agendaWeek,agendaDay'
            },
            selectable: true,
            selectHelper: true,
            select: function(start, end, allDay) {
                $('#fc_create').click();

                started = start;
                ended = end;

                $(".antosubmit").on("click", function() {
                    var title = $("#title").val();
                    if (end) {
                        ended = end;
                    }

                    categoryClass = $("#event_type").val();

                    if (title) {
                        calendar.fullCalendar('renderEvent', {
                                    title: title,
                                    start: started,
                                    end: end,
                                    allDay: allDay
                                },
                                true // make the event "stick"
                        );
                    }

                    $('#title').val('');

                    calendar.fullCalendar('unselect');

                    $('.antoclose').click();

                    return false;
                });
            },
            eventClick: function(calEvent, jsEvent, view) {
                $('#fc_edit').click();
                $('#title2').val(calEvent.title);

                categoryClass = $("#event_type").val();

                $(".antosubmit2").on("click", function() {
                    calEvent.title = $("#title2").val();

                    calendar.fullCalendar('updateEvent', calEvent);
                    $('.antoclose2').click();
                });

                calendar.fullCalendar('unselect');
            },
            //editable: true,
            events: [{
                title: 'All Day Event',
                start: new Date(y, m, 1)
            }, {
                title: 'Long Event',
                start: new Date(y, m, d - 5),
                end: new Date(y, m, d - 2)
            }, {
                title: 'Meeting',
                start: new Date(y, m, d, 10, 30),
                allDay: false
            }, {
                title: 'Lunch',
                start: new Date(y, m, d + 14, 12, 0),
                end: new Date(y, m, d, 14, 0),
                allDay: false
            }, {
                title: 'Birthday Party',
                start: new Date(y, m, d + 1, 19, 0),
                end: new Date(y, m, d + 1, 22, 30),
                allDay: false
            }, {
                title: 'Click for Google',
                start: new Date(y, m, 28),
                end: new Date(y, m, 29),
                url: 'http://google.com/'
            }]
        });
    });
</script>
<!-- /FullCalendar -->
<!-- /jQuery Sparklines -->
<#include "/layouts/pageCloser.ftl">



