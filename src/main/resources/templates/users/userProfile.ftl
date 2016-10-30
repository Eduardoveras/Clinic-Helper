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
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>User Report <small>Activity report</small></h2>
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
                                    <h3>${patient.patientFirstName} ${patient.patientLastName}</h3>

                                    <ul class="list-unstyled user_data">
                                        <li><i class="fa fa-map-marker user-profile-icon"></i> ${patient.patientAddress},${patient.patientCity},${patient.patientCountry}
                                        </li>

                                        <li>
                                            <i class="fa fa-briefcase user-profile-icon"></i> ${patient.occupation}
                                        </li>

                                        <li class="m-top-xs">
                                            <i class="fa fa-external-link user-profile-icon"></i>
                                            <a href="mailto:${patient.patientEmail}?subject=SweetWords" target="_blank">${patient.patientEmail}</a>
                                        </li>
                                    </ul>

                                    <a class="btn btn-success"><i class="fa fa-edit m-right-xs"></i>Edit Profile</a>
                                    <br>

                                    <!-- start skills -->
                                    <h4>Skills</h4>
                                    <ul class="list-unstyled user_data">
                                        <li>
                                            <p>Web Applications</p>
                                            <div class="progress progress_sm">
                                                <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="50" aria-valuenow="49" style="width: 50%;"></div>
                                            </div>
                                        </li>
                                        <li>
                                            <p>Website Design</p>
                                            <div class="progress progress_sm">
                                                <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="70" aria-valuenow="70" style="width: 70%;"></div>
                                            </div>
                                        </li>
                                        <li>
                                            <p>Automation &amp; Testing</p>
                                            <div class="progress progress_sm">
                                                <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="30" aria-valuenow="29" style="width: 30%;"></div>
                                            </div>
                                        </li>
                                        <li>
                                            <p>UI / UX</p>
                                            <div class="progress progress_sm">
                                                <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="50" aria-valuenow="49" style="width: 50%;"></div>
                                            </div>
                                        </li>
                                    </ul>
                                    <!-- end of skills -->

                                </div>
                                <div class="col-md-9 col-sm-9 col-xs-12">
                                    <h6>ID:</h6><h6>${patient.patientFirstName} </h6><br>
                                    <h4>First Name:</h4><h3>${patient.patientFirstName} </h3><br>
                                    <h4>Last Name:</h4><h3>${patient.patientLastName}</h3><br>
                                    <h4>ID Card:</h4><h3>${patient.patientIdCard}</h3><br>
                                    <h4>Phone:</h4><h3>${patient.patientTelephoneNumber}</h3><br>
                                    <h4>Contact:</h4><h3>${patient.patientContactTelephoneNumber}</h3><br>
                                    <h4>Email:</h4><h3>${patient.patientEmail}</h3><br>
                                    <h4>Birth day:</h4><h3>${patient.patientBirthDate}</h3><br>
                                    <h4>Gender:</h4><h3>${patient.patientGender}</h3><br>
                                    <h4>Date Registered</h4><h3>${patient.patientRegisteredDate}</h3><br>
                                    <h4>Ocupation:</h4><h3>${patient.occupation}</h3><br>
                                    <h4>Nationality:</h4><h3>${patient.patientNationality}</h3><br>
                                    <h4>Address:</h4><h3>${patient.patientAddress}</h3><br>
                                    <h4>City:</h4><h3>${patient.patientCity}</h3><br>
                                    <h4>Country:</h4><h3>${patient.patientCountry}</h3><br>

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



