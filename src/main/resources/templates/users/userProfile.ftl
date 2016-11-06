<#include "/layouts/header.ftl">

<body class="nav-md">
<#include  "/layouts/_loader.ftl">
<div class="container body">
    <div class="main_container">
    <#include "/layouts/sidebar.ftl">
    <#include "/layouts/navbar.ftl">

        <!-- page content -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>${user.getFullName()}</h3>
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>${user.clinic.clinicPrefix} User Profile: ${user.userId}</h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="#">Settings 1</a>
                                            </li>
                                            <li><a href="#">Settings 2</a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li><a class="close-link"><i class="fa fa-close"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <div class="col-md-3 col-sm-3 col-xs-12 profile_left">
                                    <div class="profile_img">
                                        <div id="crop-avatar">
                                            <!-- Current avatar -->
                                            <img class="img-responsive avatar-view" src="/images/user.png" alt="Avatar" title="Change the avatar">
                                        </div>
                                    </div>
                                    <h3>${user.getFullName()}</h3>

                                    <ul class="list-unstyled user_data">
                                        <li>
                                            <i class="fa fa-briefcase user-profile-icon"></i> ${user.getRole()}
                                        </li>

                                        <li class="m-top-xs">
                                            <i class="fa fa-external-link user-profile-icon"></i>
                                            ${user.email}
                                        </li>
                                    </ul>

                                    <a class="btn btn-success"><i class="fa fa-edit m-right-xs"></i>Edit Profile</a>
                                    <br>

                                </div>
                                <div class="col-md-9 col-sm-9 col-xs-12">
                                    <div style="display: inline-block;">
                                        <h4>ID:</h4><h3>${user.getUserId()} </h3><br>
                                        <h4>First Name:</h4><h3>${user.getFirstName()} </h3><br>
                                        <h4>Last Name:</h4><h3>${user.getLastName()}</h3><br>
                                        <h4>Birth day:</h4><h3>${user.getBirthDate()}</h3><br>
                                    </div>
                                    <div style="display: inline-block;">
                                        <h4>Gender:</h4><h3>${user.getGender()}</h3><br>
                                        <h4>Role:</h4><h3>${user.getRole()}</h3><br>
                                        <h4>Email:</h4><h3>${user.getEmail()}</h3><br>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /page content -->
    <#include "/layouts/Copyright.ftl">


    </div>
</div>

<#include "/layouts/footer.ftl">
<#include "/layouts/pageCloser.ftl">



