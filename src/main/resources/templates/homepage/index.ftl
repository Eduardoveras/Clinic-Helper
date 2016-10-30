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
<!-- /jQuery Sparklines -->
<#include "/layouts/pageCloser.ftl">



