<#include "/layouts/header.ftl">

<body class="nav-md">
<div class="container body">
    <div class="main_container">
    <#include "/layouts/sidebar.ftl">
    <#include "/layouts/navbar.ftl">
        <!-- page content -->
        <div class="right_col" role="main">
            <div class="">
                <div class="row">
                <#include "/inventory/_inventoryTabs.ftl">
                </div>
            </div>
        </div>
        <!-- /page content -->
    <#include "/layouts/Copyright.ftl">
    </div>
</div>

<#include "/layouts/footer.ftl">
<#include "/layouts/pageCloser.ftl">



