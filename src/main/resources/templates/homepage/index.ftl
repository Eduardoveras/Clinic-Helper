<#include "/layouts/header.ftl">

<body class="nav-md">
<div class="loader"></div>
<div class="container body">
    <div class="main_container">
    <#include "/layouts/sidebar.ftl">
    <#include "/layouts/navbar.ftl">
        <!-- page content -->
        <div class="right_col" role="main">
            <div class="">
                 <#include "/homepage/_topBoxes.ftl">
                <div class="row">
                <#include "/homepage/_appointments.ftl">
                <#include "/homepage/_calendar.ftl">
                </div>
            </div>
        </div>
        <!-- /page content -->
    <#include "/layouts/Copyright.ftl">
    </div>
</div>

<#include "/layouts/footer.ftl">
<#include "/homepage/_scripts.ftl">
<#include "/layouts/pageCloser.ftl">



