<#include "/layouts/header.ftl">

<body class="nav-md">
<div class="container body">
    <div class="main_container">
    <#include "/layouts/sidebar.ftl">
    <#include "/layouts/navbar.ftl">



        <!-- page content -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Plain Page</h3>
                    </div>
                </div>
                <div class="clearfix"></div>
                <!--ADD CONTENT HERE-->
                <div class="row">
                <#include "/patients/_patientsForm.ftl">
                <#include "/patients/_patientsTable.ftl">



                </div>
            </div>
        </div>
        <!-- /page content -->
    <#include "/layouts/Copyright.ftl">


    </div>
</div>
<#include "/layouts/footer.ftl">
<!-- bootstrap-daterangepicker -->
<script>
    $(document).ready(function() {
        $('#dateOfBirth').daterangepicker({
            singleDatePicker: true,
            calender_style: "picker_4"
        }, function(start, end, label) {
            console.log(start.toISOString(), end.toISOString(), label);
        });
    });
</script>
<!-- /bootstrap-daterangepicker -->
<#include "/layouts/pageCloser.ftl">



