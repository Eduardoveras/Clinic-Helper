<div class="col-md-12">
    <div class="x_panel">
        <div class="x_title">
            <h2>
                <a href="#" data-toggle="modal" data-target="#contactModal" class="btn btn-success">Add New Member</a>
            </h2>
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i
                            class="fa fa-wrench"></i></a>
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
            <div class="row" style="overflow: auto; max-height: 500px;">

                <#list contactList as contact>

                    <div class="col-md-4 col-sm-4 col-xs-12 profile_details">
                        <div class="well profile_view">
                            <div class="col-sm-12">
                                <h4 class="brief"><i>${user.clinic.clinicPrefix} Contact</i></h4>
                                <div class="left col-xs-7">
                                    <h2>${contact.getFullName()}</h2>
                                    <p><strong>Email: </strong>${contact.getEmail()}</p>
                                    <p><strong>Has an Account: </strong><#if contact.hasAccount>YES<#else>NO</#if></p>
                                </div>
                                <div class="right col-xs-5 text-center">
                                    <img src="images/img.jpg" alt="" class="img-circle img-responsive">
                                </div>
                            </div>
                            <div class="col-xs-12 bottom text-center">
                                <div class="col-xs-12 col-sm-6 emphasis">
                                    <button type="button" class="btn btn-success btn-xs"><i class="fa fa-user">
                                    </i> <i class="fa fa-comments-o">Message</i></button>
                                    <button type="button" class="btn btn-primary btn-xs">
                                        <i class="fa fa-user"> </i> View Profile
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                <#else>
                    <p><h4>You have don't have more appointments to show</h4></p>
                </#list>
            </div>
        </div>
    </div>
</div>